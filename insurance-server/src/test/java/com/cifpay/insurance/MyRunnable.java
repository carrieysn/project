package com.cifpay.insurance;



public class MyRunnable implements Runnable {
    String name;
    public MyRunnable(String name){
        this.name=name;
    }
    @Override
    public void run() {
        for(int i=0;i<5;++i){
            System.out.println(name+" 第 "+i+" 次运行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name+" 运行完毕停机");
    }
    
    

    class ThreadContext{
        public  int id = -1;
    }

    public static void doSomeThingBackground(final ThreadContext context)
    {
        new Thread(){
            @Override
            public void run(){
                context.id++;
            }
        }.start();
    }
    public static void main(String[] args) {
   	 new Thread(new MyRunnable("0号机")).start();
	 new Thread(new MyRunnable("1号机")).start();
	
	}

}