package com.cifpay.insurance;

public class Hello implements Runnable {

	private int count=5;
	public void run() {
        for(int i=0;i<10;++i){
            if(count>0){
                /*try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }*/
                System.out.println(count--);
            }
        }
    }
	public static void main(String[] args) {
		Hello he=new Hello();
        Thread h1=new Thread(he);
        Thread h2=new Thread(he);
        Thread h3=new Thread(he);
        h1.start();
        h2.start();
        h3.start();
        
        double testnum = 90;
        
		
    }
}