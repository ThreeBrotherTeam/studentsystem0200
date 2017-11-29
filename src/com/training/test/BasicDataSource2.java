package com.training.test;

import org.apache.commons.dbcp2.BasicDataSource;

public class BasicDataSource2 extends BasicDataSource {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		super.setUsername(name);
	}
	public static void main(String[] args) {
		System.out.println(BasicDataSource2.class);
	}
}
