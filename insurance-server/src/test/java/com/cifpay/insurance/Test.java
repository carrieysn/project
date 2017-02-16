package com.cifpay.insurance;

public class Test {
	
	public static void main(String[] args) {
		 new Thread(new MyRunnable("0号机")).start();
		 new Thread(new MyRunnable("1号机")).start();
	}

}
