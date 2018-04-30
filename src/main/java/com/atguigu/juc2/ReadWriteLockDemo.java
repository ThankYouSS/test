package com.atguigu.juc2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写分离
 * @Description: 一个线程写入,100个线程读取
 * @author zzyy
 * @date 2018年3月27日
 */
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		MyQueue mq = new MyQueue();
		new Thread(() -> { mq.write("love"); }, "a").start();
		for (int i = 1; i <=100; i++) {
			new Thread(() -> {
				mq.read();
			}, String.valueOf(i)).start();
		}
		
	}
}

class MyQueue{
	
	Object obj;
	ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
	
	public void write(Object obj) {
		rw.writeLock().lock();
		try {
			this.obj=obj;
			System.out.println(Thread.currentThread().getName()+"\twrite\t" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			rw.writeLock().unlock();
		}
	}
	public void read() {
		rw.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			rw.readLock().unlock();
		}
	}
}
