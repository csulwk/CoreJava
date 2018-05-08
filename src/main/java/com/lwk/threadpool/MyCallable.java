package com.lwk.threadpool;

import java.util.concurrent.Callable;

/**
 * @author lwk
 * @date 2018/5/8 17:03
 */
public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("任务执行中...");
        Thread.sleep(10000);
        return new Integer(666);
    }
}
