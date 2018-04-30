package com.atguigu.juc2;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @Description:
 * 
 *  让一些线程阻塞知道另一些线程执行完一系列操作后再被唤醒。
 *  
 *  CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程阻塞。
 *  其他线程调用countdown方法会将计数器减一（调用countdown方法线程不会阻塞）
 *  当计数器为0时，因为await方法阻塞的线程会被唤醒，继续执行。
 *  
 * 解释：6个同学陆续离开教室后值班同学才可以关门。
 * 也即	秦灭6国，一统华夏
 * main主线程必须要等前面6个线程完成全部工作后，自己才能开干 
 * @author zzyy
 * @date 2018年3月27日
 */
public class CountDownLatchDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		CountDownLatch countDownLatch = new CountDownLatch(4);
		
		for (int i = 1; i <= 4; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"\t 已经过去");
				countDownLatch.countDown();
			}, SeasonEnum.foreachSeasonEnum(i).getSeason()).start();
		}
		countDownLatch.await();
		System.out.println(Thread.currentThread().getName()+"\t 一年结束");
		
		System.out.println(SeasonEnum.ONE);
		System.out.println(SeasonEnum.ONE.getCode());
		System.out.println(SeasonEnum.ONE.getSeason());
	}

	public static void closeDoor() throws InterruptedException
	{
		CountDownLatch cdt = new CountDownLatch(6);
		for (int i = 1; i <=6; i++) {//6个同学陆续离开教室后值班同学才可以关门。
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName()+"\t号同学走了");
					cdt.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
		cdt.await();
		System.out.println(Thread.currentThread().getName()+"\t班长锁门");
	}


}