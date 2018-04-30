package com.atguigu.juc;

@FunctionalInterface
interface F {
//	public void test();
//	public int test(int a,int b);
	public void test(int a,String b);
	default void test1() {
		System.out.println("default1");
	}
	
	public static int test2() {
		return 1;
	}
	
}
/**
 * 1.粘贴(参数)+写死右箭头->+落地大括号{}
 * 2.有且只有一个public abstract 方法，用@FunctionInterface注解加强定义
 * 3.可以有defult方法
 * 4.可以有静态方法
 */
public class LambdaDemo {

	public static void main(String[] args) {
		/*F f = new F() {
			
			@Override
			public void test() {
				System.out.println(1);
				
			}
		};*/
//		F f = () -> { System.out.println("lambdademo"); };
		F f = (x,y) -> { 
			System.out.println(x+y);
			};
		f.test(1,"2");
		f.test1();
		System.out.println(F.test2());
	}

}
