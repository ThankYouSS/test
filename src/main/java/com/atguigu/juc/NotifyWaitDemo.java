package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程
 * 判断 干活 通知
 * @author admin
 *
 */

public class NotifyWaitDemo {
	public static void main(String[] args) {
		ShareData s = new ShareData();
		new Thread(() -> { 
			for (int i = 0; i < 10; i++) {
				try {
					s.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "A").start();
		new Thread(() -> { 
			for (int i = 0; i < 10; i++) {
				try {
					s.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "B").start();
		new Thread(() -> { 
			for (int i = 0; i < 10; i++) {
				try {
					s.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "C").start();
		new Thread(() -> { 
			for (int i = 0; i < 10; i++) {
				try {
					s.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "D").start();
	}
	
	
}

class ShareData {
	int number = 0;
	
	Lock lock = new ReentrantLock();
	Condition c = lock.newCondition();
	
	public void increment() throws Exception {
		lock.lock();
		try {
			// 
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
		}finally {
			lock.unlock();
		}
	}

	public void decrement() throws Exception {
		lock.lock();
		try {
			// 判断
			while (number == 0) {
				c.await();
			}
			// 干活
			--number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			// 通知
			c.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}	
	
	/*public synchronized void increment() throws Exception {
		// 判断
		while (number != 0) {
			this.wait();
		}
		// 干活
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		// 通知
		this.notifyAll();
	}

	public synchronized void decrement() throws Exception {
		// 判断
		while (number == 0) {
			this.wait();
		}
		// 干活
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		// 通知
		this.notifyAll();
	}*/

	/*public synchronized void increment() throws Exception {
		// 判断
		if (number != 0) {
			this.wait();
		}
		// 干活
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		// 通知
		this.notifyAll();
	}

	public synchronized void decrement() throws Exception {
		// 判断
		if (number == 0) {
			this.wait();
		}
		// 干活
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		// 通知
		this.notifyAll();
	}*/
}
