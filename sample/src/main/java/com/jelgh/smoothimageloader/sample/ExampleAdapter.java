//package com.jelgh.smoothimageloader.sample;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.jelgh.smoothimageloader.library.BitmapStorageApi;
//import com.jelgh.smoothimageloader.library.SmoothImageLoader;
//import com.jelgh.smoothimageloader.library.SmoothLoadingListAdapter;
//
//import java.util.List;
//
//public class ExampleAdapter extends ArrayAdapter<Employee> implements SmoothLoadingListAdapter {
//
//	private SmoothImageLoader mSmoothImageLoader;
//	private List<Employee> mObjects;
//
//	public ExampleAdapter(Context context, int textViewResourceId, List<Employee> objects) {
//		super(context, textViewResourceId, objects);
////		BitmapStorageApi api = BitmapStorageApi.getInstance();
////		mSmoothImageLoader = new SmoothImageLoader(api);
//	}
//
//	@Override
//	public View getView(int position, View view, ViewGroup parent) {
//		final ViewHolder viewHolder;
//
//		if (view == null) {
//			LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			view = li.inflate(R.layout.list_item, parent, false);
//
//			viewHolder = new ViewHolder();
//			viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
//			viewHolder.imgPortrait = (ImageView) view.findViewById(R.id.imgPortrait);
//			view.setTag(viewHolder);
//		} else {
//			viewHolder = (ViewHolder) view.getTag();
//		}
//
//		Employee employee = getItem(position);
//
//		viewHolder.listPosition = position;
//		viewHolder.txtName.setText(employee.getName());
//		mSmoothImageLoader.loadImage(employee.getPicUrl(), viewHolder.imgPortrait);
//
//		return view;
//	}
//
//	@Override
//	public void reloadImage(Object viewHolderObject) {
//		if(viewHolderObject instanceof ViewHolder) {
//			final ViewHolder viewHolder = (ViewHolder) viewHolderObject;
//			Employee employee = mObjects.get(viewHolder.listPosition);
//
//			mSmoothImageLoader.loadImage(employee.getPicUrl(), viewHolder.imgPortrait);
//		}
//	}
//
//	static class ViewHolder {
//		int listPosition;
//		ImageView imgPortrait;
//		TextView txtName;
//	}
//}
