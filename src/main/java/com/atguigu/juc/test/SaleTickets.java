package com.atguigu.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程   卖出  三十张票
 * @author admin
 * 线程	操作	资源
 * 高内聚	低耦合
 */
public class SaleTickets {
	public static void main(String[] args) {
		Tickets t = new Tickets();
		new Thread(() -> { for (int i = 0; i < 40; i++) t.saleTicket(); }, "a").start();
		new Thread(() -> { for (int i = 0; i < 40; i++) t.saleTicket(); }, "b").start();
		new Thread(() -> { for (int i = 0; i < 40; i++) t.saleTicket(); }, "c").start();
	}
}

class Tickets{
	
	private int tickets = 30;
	private Lock lock = new ReentrantLock();
	
	public void saleTicket() {
		lock.lock();
		try { 
			while(tickets>0) {
				System.out.println(Thread.currentThread().getName() + "\t 卖出第："+tickets+"张票，剩余："+ (--tickets) +"张票");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
}
