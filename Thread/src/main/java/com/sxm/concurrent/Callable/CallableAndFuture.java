package com.sxm.concurrent.Callable;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/9 0009 下午 16:28
 * @since 0.1
 */
public class CallableAndFuture {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        });

        System.out.println("等待结果");

        try {
            System.out.println("拿到结果：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService(executorService1);

        for (int i = 0; i < 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(500));
                    return seq;
                }
            });

        }

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
