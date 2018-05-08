package com.lwk.thread;

import java.util.concurrent.*;

/**
 * @author lwk
 * @date 2018/5/8 11:25
 */
public class ThreadTest {

    public static void main(String[] args) {

        //futureCallable();
        futureTaskCallable();

        System.out.println("----END----");
    }

    /**
     * Future只是一个接口，所以是无法直接用来创建对象使用的。
     */
    public static void futureCallable(){

        int timeout = 4;
        ExecutorService exec = Executors.newSingleThreadExecutor();

        long sTime = System.currentTimeMillis();
        Future<String> future = exec.submit(new CallableTest(10));
        try {
            String res = future.get(timeout, TimeUnit.SECONDS);
            System.out.println("任务成功返回：" + res);
        } catch (InterruptedException e) {
            System.out.println("任务中断...");
            //中断执行此任务的线程
            future.cancel(true);
        } catch (ExecutionException e) {
            System.out.println("任务失败...");
            //中断执行此任务的线程
            future.cancel(true);
        } catch (TimeoutException e) {
            System.out.println("任务超时...");
            //中断执行此任务的线程
            future.cancel(true);
        } finally {
            exec.shutdown();
        }
        long eTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (eTime-sTime) + "ms");
    }

    /**
     * 由于单独使用Runnable时无法获得返回值，而单独使用Callable时无法在新线程
     * (即new Thread(Runnable r))中使用，只能通过ExecutorService执行；
     * FutureTask实现了Runnable和Future接口，所以它既可以作为Runnable被线程
     * 执行，又可以作为Future得到Callable的返回值
     */
    public static void futureTaskCallable(){

        int timeout = 4;
        FutureTask<String> futureTask = new FutureTask<String>(new CallableTest(10));
        Thread myThread = new Thread(futureTask);

        long sTime = System.currentTimeMillis();
        myThread.start();

        try {
            String res = futureTask.get(timeout, TimeUnit.SECONDS);
            System.out.println("任务成功返回：" + res);
        } catch (InterruptedException e) {
            System.out.println("任务中断...");
            futureTask.cancel(true);
        } catch (ExecutionException e) {
            System.out.println("任务失败...");
            futureTask.cancel(true);
        } catch (TimeoutException e) {
            System.out.println("任务超时...");
            futureTask.cancel(true);
        }

        long eTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (eTime-sTime) + "ms");
    }
}
