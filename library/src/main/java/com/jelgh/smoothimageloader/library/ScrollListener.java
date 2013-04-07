package com.jelgh.smoothimageloader.library;

import android.widget.AbsListView;

interface ScrollListener extends AbsListView.OnScrollListener{

	public boolean isScrolling();

	public void setAdapter(SmoothLoadingAdapter adapter);

}
