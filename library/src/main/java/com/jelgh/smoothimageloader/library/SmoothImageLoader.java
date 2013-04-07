package com.jelgh.smoothimageloader.library;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmoothImageLoader {
	private MemoryCache mMemoryCache;
	private IBitmapStorageApi mBitmapStorageApi;
	private Map<ImageView, String> mImageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
	private ExecutorService mExecutorService;
	private int mDefaultImgResource = -1;

	private int threadPoolSize = 20;
	private boolean debug = false;

	public SmoothImageLoader(IBitmapStorageApi bitmapStorageApi) {
		mBitmapStorageApi = bitmapStorageApi;
		initLoader();
	}

	public SmoothImageLoader(IBitmapStorageApi bitmapStorageApi, int threadPoolSize) {
		mBitmapStorageApi = bitmapStorageApi;
		this.threadPoolSize = threadPoolSize;
		initLoader();
	}

	public SmoothImageLoader(IBitmapStorageApi bitmapStorageApi, boolean debug) {
		mBitmapStorageApi = bitmapStorageApi;
		this.debug = debug;
		initLoader();
	}

	public SmoothImageLoader(IBitmapStorageApi bitmapStorageApi, int threadPoolSize, boolean debug) {
		mBitmapStorageApi = bitmapStorageApi;
		this.debug = debug;
		this.threadPoolSize = threadPoolSize;
		initLoader();
	}

	private void initLoader() {
		mMemoryCache = new MemoryCache(debug);
		mExecutorService = Executors.newFixedThreadPool(threadPoolSize);
	}

	public void setDefaultImgResource(int resource) {
		mDefaultImgResource = resource;
	}

	private void setDefaultImgResource(ImageView view) {
		view.setImageResource(mDefaultImgResource);
	}

	public boolean isImageInMemoryCache(String uri) {
		if (uri == null) {
			throw new IllegalArgumentException("Cannot have null parameters");
		}
		return mMemoryCache.contains(uri);
	}

	public void loadImage(String uri, ImageView imageView) {

		if (imageView == null || uri == null) {
			throw new IllegalArgumentException("Cannot have null parameters");
		}


		mImageViews.put(imageView, uri);
		BitmapDrawable drawable = mMemoryCache.get(uri);
		if (drawable != null) {
			imageView.setImageDrawable(drawable);
		} else {
			queuePhoto(uri, imageView);
			setDefaultImgResource(imageView);
		}
	}

	private void queuePhoto(String uri, ImageView imageView) {
		PhotoToLoad p = new PhotoToLoad(uri, imageView);
		mExecutorService.submit(new PhotoLoader(p));
	}

	private boolean imageViewReused(PhotoToLoad photoToLoad) {
		String tag = mImageViews.get(photoToLoad.imageView);
		if (tag == null || !tag.equals(photoToLoad.uri)) {
			return true;
		}
		return false;
	}

	public void clearCache() {
		mMemoryCache.clear();
	}

	private class PhotoToLoad {
		public String uri;
		public ImageView imageView;

		public PhotoToLoad(String uri, ImageView i) {
			this.uri = uri;
			imageView = i;
		}
	}

	private class PhotoLoader implements Runnable {
		PhotoToLoad photoToLoad;

		PhotoLoader(PhotoToLoad photoToLoad) {
			this.photoToLoad = photoToLoad;
		}

		@Override
		public void run() {
			try {
				if (imageViewReused(photoToLoad)) {
					return;
				}
				Activity activity = (Activity) photoToLoad.imageView.getContext();

				Bitmap bitmap = mBitmapStorageApi.getBitmap(photoToLoad.uri);
				BitmapDrawable drawable = new BitmapDrawable(activity.getResources(), bitmap);

				mMemoryCache.put(photoToLoad.uri, drawable);
				if (imageViewReused(photoToLoad)) {
					return;
				}
				BitmapDisplayer bd = new BitmapDisplayer(drawable, photoToLoad);
				activity.runOnUiThread(bd);

			} catch (Throwable th) {
				if (debug) th.printStackTrace();
			}
		}
	}

	// Used to display bitmap in the UI thread
	class BitmapDisplayer implements Runnable {
		BitmapDrawable drawable;
		PhotoToLoad photoToLoad;

		public BitmapDisplayer(BitmapDrawable d, PhotoToLoad p) {
			drawable = d;
			photoToLoad = p;
		}

		public void run() {
			if (imageViewReused(photoToLoad)) {
				return;
			}
			if (drawable != null) {
				photoToLoad.imageView.setImageDrawable(drawable);
			} else {
				setDefaultImgResource(photoToLoad.imageView);
			}
		}
	}
}