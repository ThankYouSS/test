package com.atguigu.juc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotifyWaitDemo {

	public static void main(String[] args) {
		Sources s = new Sources();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					s.increment();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}, "A").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					s.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}, "B").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					s.increment();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}, "C").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					s.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}, "D").start();

	}

}

class Sources {
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();
	
	public void increment() {
		lock.lock();
		try {
			//判断 
			while (number != 0) {
				c.await();
			}
			// 干活
			++number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			// 通知
			c.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() {
		lock.lock();
		try {
			//判断 
			while (number != 1) {
				c.await();
			}
			// 干活
			--number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			// 通知
			c.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
