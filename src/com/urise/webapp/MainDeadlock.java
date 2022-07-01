package com.urise.webapp;

public class MainDeadlock {

    public static final Object LOCK_1 = new Object();
    public static final Object LOCK_2 = new Object();

    private static final String str = "I`m java programmer";

    private static String getStr(String s) {
        return str + s;
    }

    private static class Thread_1 extends Thread {
        @Override
        public void run() {
            synchronized (LOCK_1) {
                System.out.println(getStr("? Thread_1 lock Object LOCK_1."));
                sleepThread();
                System.out.println("Welcome to deadlock");
                synchronized (LOCK_2) {
                    System.out.println(getStr("! Thread_1 ждет когда Thread_2 освободит Object LOCK_2."));
                }
            }
        }
    }

    private static class Thread_2 extends Thread {
        @Override
        public void run() {
            synchronized (LOCK_2) {
                System.out.println(getStr("!!! Thread_2 lock Object LOCK_2."));
                sleepThread();

                synchronized (LOCK_1) {
                    System.out.println(getStr("??? Thread_2 ждет когда Thread_1 освободит Object LOCK_1."));
                }
            }
        }
    }

    private static void sleepThread() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Thread_1 t1 = new Thread_1();
        Thread_2 t2 = new Thread_2();
        t1.start();
        t2.start();
    }
}
