package com.jelgh.smoothimageloader.library;


import android.widget.AbsListView;
import android.widget.ImageView;

interface SmoothLoadingAdapter {

	public void setDefaultImgResource(int resource);

	public void reloadImage(final Object viewHolder);

	public void reloadImages(final AbsListView view);

	public void loadImage(ImageView imageView, String uri);

	public void clearMemoryCache();
}
