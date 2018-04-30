package com.atguigu.juc.test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore s = new Semaphore(3);
		for (int i = 1; i < 7; i++) {
			new Thread(() -> {
				try {
					s.acquire();
					System.out.println(Thread.currentThread().getName()+"\t 抢到了车位");
					TimeUnit.SECONDS.sleep(new Random().nextInt(6));
//					Thread.sleep(3000);
					System.out.println(Thread.currentThread().getName()+"\t 离开");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					s.release();
				}
			}, String.valueOf(i)).start();
		}

	}

}
