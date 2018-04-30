package com.atguigu.juc.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier c = new CyclicBarrier(6, () -> { System.out.println("人齐了，开会"); } );
		for (int i = 1; i < 7; i++) {
			new Thread(() -> {
				try {
					System.out.println("第"+Thread.currentThread().getName()+"个人来了");
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}

}
