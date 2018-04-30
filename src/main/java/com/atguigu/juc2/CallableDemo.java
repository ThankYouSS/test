package com.atguigu.juc2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @Description: Callable接口获得多线程
 * @author zzyy
 * @date 2018年3月17日
 * 
/**
 * 在主线程需要执行耗时的操作时，但是又不想阻塞线程，这时我们可以把这些作业交给Future对象在后台完成 
 * 当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或执行状态
 *
 * 一般FutureTask多用于耗时的计算，主线程可以完成自己的任务后，再去获得结果
 *
 * 仅在计算完成时才能检索结果，如果计算尚未完成时，则阻塞get方法。一旦计算完成，就不能再重新开始或取消计算。
 * get方法获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，然后会返回结果或抛出异常
 * 
 * 只计算一次
 * get放最后
 */
public class CallableDemo
{
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		FutureTask<Integer> ft = new FutureTask<>(new MyThread());
		new Thread(ft, "a").start();
		new Thread(ft, "c").start();
		
		
		/*FutureTask<Integer> ft1 = new FutureTask<>(new MyThread());
		new Thread(ft1, "b").start();*/
		
		System.out.println(Thread.currentThread().getName()+"*********main");
		
		Integer result = ft.get();
		System.out.println(result);
		
		/*Integer result1 = ft1.get();
		System.out.println(result1);*/
	} 
}

class MyThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			Thread.sleep(4000);
			System.out.println(Thread.currentThread().getName()+ "******callable");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
//		Thread.sleep(4000);
//		System.out.println(Thread.currentThread().getName()+ "******callable");
		return 1;
	}
}
