package com.atguigu.juc.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch c = new CountDownLatch(5);
		for (int i = 1; i < 5; i++) {
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + "\t号同学已经走了");
					c.countDown();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
		c.await();
		System.out.println(Thread.currentThread().getName()+"\t班长锁门");

	}

}
