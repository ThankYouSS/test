package com.atguigu.juc2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * 
 * CyclicBarrier字面意识是可循环（Cyclic）使用的屏障（Barrier）。
 * 它要做的事是让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程达到屏障时，
 * 屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * 线程进入屏障通过CyclicBarrier的await()方法
 * 
 * @author admin
 *
 */
public class CyclicBarrierDemo {
	
	private final static int number = 7;
	
	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(number, () -> { System.out.println("七个龙珠集合完毕，召唤神龙"); });
		for (int i = 1; i <= 7; i++) {
			final int figure = i;
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName()+"\t第"+figure+"颗龙珠集合");
					cb.await();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}, String.valueOf(i)).start();
		}
		
	}

}
