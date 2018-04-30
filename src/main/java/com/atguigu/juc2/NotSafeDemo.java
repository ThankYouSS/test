package com.atguigu.juc2;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

public class NotSafeDemo {
	
	public static void main(String[] args) {
//		Map<Object,Object> map = new HashMap<Object,Object>();
		Map<Object,Object> map = new ConcurrentHashMap<Object,Object>();
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 4));
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}

	private static void setNotSafe() {
//		Set<String> set = new HashSet<String>();
		Set<String> set = new CopyOnWriteArraySet<String>();
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 4));
				System.out.println(set);
			}, "A").start();
		}
	}
	
	
	/**
	 * CopyOnWrite容器即写时复制容器。往一个容器中添加元素的时候，不直接往Object[]中添加，而是先将当前Object[]
	 * 容器复制出一个新的Object[] newElement,然后往新容器Object[] newElement中添加元素，添加完成后，再将
	 * 原来容器的引用指向新的容器setArray(newElement)；这样做的好处时可以对CopyOnWrite容器进行并发的读，而不需
	 * 要枷锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离思想，读和写不同的容器
	 * 写时复制技术
	 * 源代码433行
	 *  public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
	 */

	private static void listNotSafe() {
//		List<String> list = new ArrayList<String>();
		List<String> list = new CopyOnWriteArrayList<String>();
//		Collections.synchronizedList(list);
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 4));
				System.out.println(list);
			}, "A").start();
				
			}
	}
	

}
