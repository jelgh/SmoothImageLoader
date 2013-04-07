package com.jelgh.smoothimageloader.library;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: jelgh
 * Date: 4/7/13
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractAdapter implements ListAdapter {

	@Override
	public boolean areAllItemsEnabled() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean isEnabled(int i) {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void registerDataSetObserver(DataSetObserver dataSetObserver) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getCount() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Object getItem(int i) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public long getItemId(int i) {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean hasStableIds() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getItemViewType(int i) {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getViewTypeCount() {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean isEmpty() {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
