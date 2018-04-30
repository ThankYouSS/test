package com.atguigu.juc.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> ft = new FutureTask<>(new MyThread());
		new Thread(ft, "a").start();
		Integer integer = ft.get();
		System.out.println(Thread.currentThread().getName()+"\t main");
		
		System.out.println(integer);

	}

}

class MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		Thread.sleep(4000);
		System.out.println(Thread.currentThread().getName()+ "******callable");
		return 1;
	}
	
}
