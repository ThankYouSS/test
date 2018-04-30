package com.atguigu.juc;

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
		new Thread(() -> { for (int i = 0; i < 40; i++) t.sale(); }, "A").start();
		new Thread(() -> { for (int i = 0; i < 40; i++) t.sale(); }, "B").start();
		new Thread(() -> { for (int i = 0; i < 40; i++) t.sale(); }, "C").start();
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					t.sale();
				}
			}
		},"A").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					t.sale();
				}
			}
		},"B").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 40; i++) {
					t.sale();
				}
			}
		},"C").start();*/

	}

}

class Tickets{
	
	private int number = 30;
	private Lock lock = new ReentrantLock();
	
	public  void sale(){
		lock.lock();
		try {
			while(number > 0) {
				System.out.println(Thread.currentThread().getName()+"卖出第：\t"+(number--)+"\t 还剩下："+number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
}