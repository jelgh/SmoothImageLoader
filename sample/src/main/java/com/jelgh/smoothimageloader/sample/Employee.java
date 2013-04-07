package com.jelgh.smoothimageloader.sample;

public class Employee {
	private String name;
	private String picUrl;

	public Employee(String name, String picUrl) {
		this.name = name;
		this.picUrl = picUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getName() {
		return name;
	}
}
