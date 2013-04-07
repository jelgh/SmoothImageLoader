package com.jelgh.smoothimageloader.library;


import android.widget.AbsListView;

public class SmoothScrollListener implements ScrollListener {

	private int mScrollState;
	private int mVisibleItemCount;
	private SmoothLoadingAdapter mAdapter;
	private boolean mIsScrolling;

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		mVisibleItemCount = visibleItemCount;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		mScrollState = scrollState;
		switch (scrollState) {
			case SCROLL_STATE_IDLE:
				if (!isScrolling()) {
					mAdapter.reloadImages(view);
				}
				break;
			case SCROLL_STATE_FLING:
			case SCROLL_STATE_TOUCH_SCROLL:
				mIsScrolling = true;
				break;
		}
	}

	@Override
	public boolean isScrolling() {
		if (mVisibleItemCount > 0 && mScrollState == SCROLL_STATE_IDLE) {
			mIsScrolling = false;
		}
		return mIsScrolling;
	}

	@Override
	public void setAdapter(SmoothLoadingAdapter adapter) {
		mAdapter = adapter;
	}
}
