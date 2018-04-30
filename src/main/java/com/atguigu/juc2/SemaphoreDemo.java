package com.atguigu.juc2;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
 * 在信号量上我们定义两种操作
 * acquire(获取)：当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减一）
 * 				要么一直等下去，直到有线程释放信号量或超时
 * release(释放)：实际上信号量加一，然后唤醒等待的线程
 * 
 * @author admin
 */
public class SemaphoreDemo {
	
	public static void main(String[] args) {
		Semaphore s = new Semaphore(3);
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				try {
					s.acquire();
					System.out.println(Thread.currentThread().getName()+"\t 抢到了车位");
					TimeUnit.SECONDS.sleep(3);
//					TimeUnit.SECONDS.sleep(new Random().nextInt(6));
					System.out.println(Thread.currentThread().getName()+"\t -----离开");
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
