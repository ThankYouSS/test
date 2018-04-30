package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.ws.FaultAction;

public class Test {

	public static void main(String[] args) {
		FLSource fls = new FLSource();
		new Thread(() -> { for (int i = 0; i < 52; i++) fls.Figure(); }, "A").start();
		new Thread(() -> { for (int i = 0; i < 26; i++) fls.Letter(); }, "B").start();
		
	}
}

class FLSource{
	
	int number = 1;
	int figure = 1;
	int letter = 65;
	Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	
	public void Figure() {
		lock.lock();
		try {
			//判断
			while(number != 1) {
				c1.await();
			}
			//干活
			int x = figure + 2;
			if(figure < 53) {
				for (int i = figure; i < x; i++) {
					System.out.print(figure+++"\t");
				}
			}
			
			//通知
			number = 2;
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	public void Letter() {
		lock.lock();
		try {
			//判断
			while(number != 2) {
				c2.await();
			}
			//干活
			
			for (int i = figure; i < figure + 1; i++) {
				System.out.println((char)letter);				
			}
			//通知
			number = 1;
			letter = letter + 1;
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
}