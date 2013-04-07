//package com.jelgh.smoothimageloader.sample;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ExampleActivity extends Activity {
//
//
//	@Override
//	public void onCreate(Bundle savedData) {
//
//		super.onCreate(savedData);
//		this.setContentView(R.layout.a_list);
//		ListView list = (ListView) this.findViewById(R.id.list);
//
//		ExampleAdapter adapter = new ExampleAdapter(this, R.layout.list_item, buildDummyData());
//	}
//
//
//	public List<Employee> buildDummyData() {
//		final int numEmployees = 20;
//		List<Employee> list = new ArrayList<Employee>(numEmployees);
//		for (int i = 0; i < numEmployees; i++) {
//			String name = "Employee " + i;
//			list.add(new Employee(name, String.valueOf(name.hashCode())));
//		}
//
//		return list;
//	}
//}
