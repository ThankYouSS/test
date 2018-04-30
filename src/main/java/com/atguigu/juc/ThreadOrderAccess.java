package com.atguigu.juc;

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
		ShareResource s = new ShareResource();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				s.p5(i);
			}
			
		}, "A").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				s.p10();
			}
			
		}, "B").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				s.p15();
			}
			
		}, "C").start();
	}
}
class ShareResource {
	int number = 1;
	Lock l = new ReentrantLock();
	Condition c1 = l.newCondition();
	Condition c2 = l.newCondition();
	Condition c3 = l.newCondition();
	
	public void p5(int t) {
		l.lock();
		try {
			//判断
			while(number != 1) {
				c1.await();
			}
			//干活
			for (int i = 0; i < 5; i++) {
				System.out.println("a"+t);
			}
			number = 2;
			//通知
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			l.unlock();
		}
	}
	public void p10() {
		l.lock();
		try {
			//判断
			while(number != 2) {
				c2.await();
			}
			//干活
			for (int i = 0; i < 10; i++) {
				System.out.println("b");
			}
			number = 3;
			//通知
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			l.unlock();
		}
	}
	public void p15() {
		l.lock();
		try {
			//判断
			while(number != 3) {
				c3.await();
			}
			//干活
			for (int i = 0; i < 15; i++) {
				System.out.println("c");
			}
			number = 1;
			//通知
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			l.unlock();
		}
	}
}