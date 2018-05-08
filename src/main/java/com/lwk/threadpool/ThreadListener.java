package com.lwk.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lwk
 * @date 2018/5/8 17:19
 */
public class ThreadListener implements Runnable {

    private ThreadPoolExecutor executor;

    public ThreadListener(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void run() {

        int count = 1;
        while (true) {
            try {
                //每隔1s检测一次
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----"+count+"----");
            System.out.println("线程池中核心线程数："+executor.getCorePoolSize());
            System.out.println("线程池中活跃线程数："+executor.getActiveCount());
            System.out.println("线程池中最大线程数："+executor.getMaximumPoolSize());
            System.out.println("队列中等待执行任务数："+executor.getQueue().size());
            System.out.println("已经执行完成的任务数："+executor.getCompletedTaskCount());

            //isShutDown()当调用shutdown()或shutdownNow()方法后返回为true;
            //isTerminated()当调用shutdown()方法后，并且所有提交的任务完成后返回为true。
            if (executor.isTerminated()){
                break;
            }
            count++;
        }
    }
}
