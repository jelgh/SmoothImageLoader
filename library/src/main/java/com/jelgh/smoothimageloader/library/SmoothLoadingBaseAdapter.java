package com.jelgh.smoothimageloader.library;

import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public abstract class SmoothLoadingBaseAdapter extends BaseAdapter implements SmoothLoadingAdapter {

	private SmoothImageLoader mImageLoader;
	private ScrollListener mScrollListener;
	private int mDefaultImgResource = -1;

	public SmoothLoadingBaseAdapter(ScrollListener scrollListener, IBitmapStorageApi api) {
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
