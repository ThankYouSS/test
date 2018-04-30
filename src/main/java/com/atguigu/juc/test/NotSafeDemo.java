package com.atguigu.juc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSafeDemo {

	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
//		Map<String,Object> map = new ConcurrentHashMap<String,Object>();
		
		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				map.put(UUID.randomUUID().toString().substring(0, 4),new Random().nextInt(5));
				System.out.println(map);
			}, "B").start();
		}
		
		
/*//		Set<String> set = new HashSet<>();
		Set<String> set = new CopyOnWriteArraySet<String>();
		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 4));
				System.out.println(set);
			}, "B").start();
		}*/
	
		
		
/*//		List<String> list = new ArrayList<String>();
//		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list = new CopyOnWriteArrayList<Integer>();
		
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
//				list.add(UUID.randomUUID().toString().substring(0, 4));
				list.add(new Random().nextInt(6));
				System.out.println(list);
			}, "A").start();
				
		}*/
	}

}
