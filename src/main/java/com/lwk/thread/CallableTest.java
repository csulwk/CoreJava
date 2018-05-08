package com.lwk.thread;

import java.util.concurrent.Callable;

/**
 * @author lwk
 * @date 2018/5/8 15:47
 */
public class CallableTest implements Callable<String> {

    private long requiredTime;

    public CallableTest(long requiredTime) {
        this.requiredTime = requiredTime;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(requiredTime*1000);
        return "SUCCESS";
    }
}
