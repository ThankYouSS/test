package com.atguigu.juc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @Description: 
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * 
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮  
 * @author zzyy
 * @date 2018年3月26日
 */
public class ThreadOrderAccess {

	public static void main(String[] args) {
		Source s = new Source();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				s.A();
			}
		}, "1").start();
		
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				s.B();
			}
		}, "2").start();
		
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				s.C();
			}
		}, "3").start();

	}

}

class Source{
	
	private int number = 1;
	private int number2 = 1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
	
	public void A() {
		lock.lock();
		try {
			//判断 
			while (number != 1) {
				c1.await();
			}
			// 干活
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t AA \t 第" + number2 +"圈" );
			}
			// 通知
			number = 2;
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}	
	}
	
	public void B() {
		lock.lock();
		try {
			//判断 
			while (number != 2) {
				c2.await();
			}
			// 干活
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t BB \t 第" + number2 +"圈");
			}
			// 通知
			number = 3;
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}	
	}
	
	public void C() {
		lock.lock();
		try {
			//判断 
			while (number != 3) {
				c3.await();
			}
			// 干活
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t CC \t 第" + number2 +"圈");
			}
			// 通知
			number = 1;
			number2++;
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}	
	}
}
