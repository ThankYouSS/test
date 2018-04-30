package com.atguigu.juc;

public class XLJTest {
	
	public static void main(String[] args) {
		XLJ x = new XLJ();
		x.setAge(99);
		x.getAge();
		x.setAge(2).setName("2");
		x.toString();
	}
}
