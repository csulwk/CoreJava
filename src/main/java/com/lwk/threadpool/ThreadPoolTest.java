package com.lwk.threadpool;

import java.util.concurrent.*;

/**
 * @author lwk
 * @date 2018/5/8 17:04
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        threadPool();

        System.out.println("----END----");
    }

    public static void threadPool(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

        Thread checkTask = new Thread(new ThreadListener(executor));
        checkTask.start();

        try {
            for (int i = 1; i <= 15; i++) {
                MyTask myTask = new MyTask("task-"+i);
                executor.execute(myTask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Future<Integer> future = executor.submit(new MyCallable());
        try {
            Integer res = future.get(400, TimeUnit.SECONDS);
            System.out.println("单独提交的任务执行结果："+res);
        } catch (InterruptedException e) {
            System.out.println("任务中断...");
            future.cancel(true);
        } catch (ExecutionException e) {
            System.out.println("任务异常...");
            future.cancel(true);
        } catch (TimeoutException e) {
            System.out.println("任务超时...");
            future.cancel(true);
        } finally {
            executor.shutdown();
        }

    }
}
