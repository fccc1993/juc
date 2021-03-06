package com.atguigu.juc;

/**
 * 线程八锁
 * 题目:判断打印的是 "one" or "two" ?
 * 1. 两个普通同步方法,两个线程标准	  		// one	two
 * 2. 新增Thread.sleep()给getOne  //one	two
 * 3. 新增普通方法getThreee()			//three one two
 * 4. 两个普通同步方法,两个Number对象		//two one
 * 5. 修改getOne() 为静态同步方法		//two one
 * 6. 修改两个方法均为静态同步方法,一个Number对象//one two
 * 7. 一个静态同步方法,一个非静态同步方法,两个Number对象//two one
 * 8. 两个静态同步方法,两个Number对象		//one two
 * 
 * 
 * 线程八锁的关键:
 * 非静态方法的锁默认为 this, 静态方法的锁为对应的 Class对象
 * 某一个时刻内,只能有一个线程持有锁
 * 
 * 
 * 
 * @author xfc
 *
 */
public class TestThread8Monitor {
	public static void main(String[] args) {
		Number number = new Number();
		Number number2 = new Number();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				number.getOne();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//number.getTwo();
				number2.getTwo();
			}
		}).start();
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				number.getThree();
//			}
//		}).start();
	}
}
class Number{
	
	public static synchronized void getOne(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("one");
	}
	
	public synchronized void getTwo(){
		System.out.println("two");
	}
	
	public void getThree(){
		System.out.println("three");
	}
}