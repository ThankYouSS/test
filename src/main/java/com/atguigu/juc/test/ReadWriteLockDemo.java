package com.atguigu.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写分离
 * @Description: 一个线程写入,100个线程读取
 * @author zzyy
 * @date 2018年3月27日
 */
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		MyQueue m = new MyQueue();
		new Thread(() -> {
			m.write(1);
		}, "a").start();
		for (int i = 0; i < 100; i++) {
		new Thread(() -> {
			
				try {
					m.read();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			
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
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\twrite\t" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw.writeLock().unlock();
		}
	}
	
	public void read() {
		rw.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+ "\t" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw.readLock().unlock();
		}
	}
}

