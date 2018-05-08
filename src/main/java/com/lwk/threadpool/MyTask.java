package com.lwk.threadpool;

/**
 * @author lwk
 * @date 2018/5/8 19:51
 */
public class MyTask implements Runnable {

    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name+"开始...");
        try {
            Thread.sleep((long)(Math.random()*10000));
        } catch (InterruptedException e) {
            System.out.println(name+"线程被中断");
        }
        System.out.println(name+"结束");
    }
}
