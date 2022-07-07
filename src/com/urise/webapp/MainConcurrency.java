package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainConcurrency {
    private static int counter;
    public static final Object LOCK = new Object();
    public static final int THREADS_NUMBER = 10000;

    private static int minValue(int[] ints) {
        int reduce = Arrays.stream(ints)
                .distinct()
                .reduce(0, (a, b) -> 10 * a + b);
        return reduce;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int summ = integers.stream().mapToInt(Integer::valueOf).sum() % 2;
        return integers
                .stream()
                .filter(a -> a % 2 != summ)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("minValue = " + minValue(new int[]{1, 2, 1, 5, 3, 7}));
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 121);
        System.out.println("oddOrEven result = " + oddOrEven(integers).toString());
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        }).start();

        System.out.println(thread0.getState());

        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            executorService.submit(() ->
//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
                latch.countDown();
            });
//            thread.start();
//            threads.add(thread);
        }

//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(counter);
    }

    private static void inc() {
        double a = Math.sin(31.);
        synchronized (LOCK) {
            counter++;
        }
    }
}
