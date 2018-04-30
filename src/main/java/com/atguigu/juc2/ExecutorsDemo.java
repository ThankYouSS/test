package com.atguigu.juc2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

//ScheduledExecutorService 时间轮询
public class ExecutorsDemo {
	
	public static void main(String[] args) {
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		
		ScheduledFuture<Integer> s;
		try {
			for (int i = 1; i <= 15; i++) {
				s = service.schedule(() -> {
					System.out.print(Thread.currentThread().getName()+"\t 营业员处理XXX业务并返回：");
					return new Random().nextInt(10);//模拟营业员给客户一个回执单。
				} , 2, TimeUnit.SECONDS);
				System.out.println("**********result:" + s.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
		
	}
	
	public static void testThreadPool()
	{
//		ExecutorService service = Executors.newFixedThreadPool(5);//一池5线程
		//ExecutorService service = Executors.newSingleThreadExecutor();//一池1线程
		ExecutorService service = Executors.newCachedThreadPool();//一池N线程
		Future<Integer> future = null;
		try 
		{
			for (int i = 1; i <=15; i++) //模拟15个过来办理业务的客户，也即15个commit请求
			{
				future = service.submit( () -> {
					System.out.print(Thread.currentThread().getName()+"\t 营业员处理XXX业务并返回：");
					return new Random().nextInt(10);//模拟营业员给客户一个回执单。
				} );
				System.out.println("**********result:" + future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}

}
