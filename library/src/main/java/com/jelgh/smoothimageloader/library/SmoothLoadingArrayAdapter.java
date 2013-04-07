package com.jelgh.smoothimageloader.library;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;


public abstract class SmoothLoadingArrayAdapter<T> extends ArrayAdapter<T> implements SmoothLoadingAdapter {

	private SmoothImageLoader mImageLoader;
	private ScrollListener mScrollListener;
	private int mDefaultImgResource = -1;


	public SmoothLoadingArrayAdapter(Context context, int textViewResourceId, ScrollListener scrollListener, IBitmapStorageApi api) {
		super(context, textViewResourceId);
		construct(scrollListener, api);
	}

	public SmoothLoadingArrayAdapter(Context context, int resource, int textViewResourceId, ScrollListener scrollListener, IBitmapStorageApi api) {
		super(context, resource, textViewResourceId);
		construct(scrollListener, api);
	}

	public SmoothLoadingArrayAdapter(Context context, int textViewResourceId, T[] objects, ScrollListener scrollListener, IBitmapStorageApi api) {
		super(context, textViewResourceId, objects);
		construct(scrollListener, api);
	}

	public SmoothLoadingArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects, ScrollListener scrollListener, IBitmapStorageApi api) {
		super(context, resource, textViewResourceId, objects);
		construct(scrollListener, api);
	}

	public SmoothLoadingArrayAdapter(Context context, int textViewResourceId, List<T> objects, ScrollListener scrollListener, IBitmapStorageApi api) {
		super(context, textViewResourceId, objects);
		construct(scrollListener, api);
	}

	public SmoothLoadingArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects, ScrollListener scrollListener, IBitmapStorageApi api) {
		super(context, resource, textViewResourceId, objects);
		construct(scrollListener, api);
	}

	private final  void construct(ScrollListener scrollListener, IBitmapStorageApi api) {
		mScrollListener = scrollListener;
		mScrollListener.setAdapter(this);
		mImageLoader = new SmoothImageLoader(api);
	}

	@Override
	public final void setDefaultImgResource(int resource) {
		mDefaultImgResource = resource;
		mImageLoader.setDefaultImgResource(mDefaultImgResource);
	}

	@Override
	public final void loadImage(ImageView imageView, String uri) {

		if (mImageLoader.isImageInMemoryCache(uri) || !mScrollListener.isScrolling()) {
			mImageLoader.loadImage(uri, imageView);
		} else {
			if (mDefaultImgResource != -1) {
				imageView.setImageResource(mDefaultImgResource);
			} else {
				imageView.setImageDrawable(null);
			}
		}
	}

	@Override
	public abstract void reloadImage(final Object viewHolder);

	@Override
	public final void reloadImages(AbsListView view) {
		for (int i = 0; i < view.getChildCount(); i++) {
			final Object rowItem = view.getChildAt(i).getTag();
			if (rowItem != null) {
				reloadImage(rowItem);
			}
		}
	}

	@Override
	public final void clearMemoryCache() {
		mImageLoader.clearCache();
	}
}
