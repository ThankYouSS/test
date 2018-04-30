package com.atguigu.juc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
	public static void main(String[] args) {
		DataSources s = new DataSources();
		new Thread(() -> {
			try {
				for(int i=0;i<26;i++) {
					s.pFigure();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}, "F").start();
		new Thread(() -> {
			try {
				for(int i=0;i<26;i++) {
					s.pLetter();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}, "F").start();
	}
}

class DataSources{
	private int number = 1;
	private int figure = 1;
	private int letter = 65;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	
	public void pFigure() {
		lock.lock();
		try {
			//判断 
			while (number != 1) {
				c1.await();
			}
			// 干活
			int x = figure+2;
			for (int i = figure; i < x; i++) {
				System.out.print(figure+++"\t");
			}
			
			number = 0;
			// 通知
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void pLetter() {
		lock.lock();
		try {
			//判断 
			while (number != 0) {
				c2.await();
			}
			// 干活
			for (int i = letter; i < (letter+1); i++) {
				System.out.println((char)letter);
			}
			letter += 1;
			number = 1;
			// 通知
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
