package com.atguigu.juc.test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

	public static void main(String[] args) {
//		ExecutorService service = Executors.newFixedThreadPool(5);
//		ExecutorService service = Executors.newSingleThreadExecutor();
//		ExecutorService service = Executors.newCachedThreadPool();
		/*Future<Integer> future = null;
		try {
			for (int i = 0; i < 5; i++) {
				future = service.submit(() -> {
					System.out.print(Thread.currentThread().getName()+"\t 营业员处理XXX业务并返回：");
					return new Random().nextInt(10);
				});
				System.out.println("**********result:" + future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}*/
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> scheduledFuture = null;
		try {
			for (int i = 0; i < 5; i++) {
				scheduledFuture = service.schedule(() -> {
					System.out.print(Thread.currentThread().getName()+"\t 营业员处理XXX业务并返回：");
					return new Random().nextInt(10);
				}, 1, TimeUnit.SECONDS);
				System.out.println("**********result:" + scheduledFuture.get());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

}
