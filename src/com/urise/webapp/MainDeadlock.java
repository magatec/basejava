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
                System.out.println(getStr("?"));

                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (LOCK_2) {
                    System.out.println(getStr("!"));
                }
            }
        }
    }

    private static class Thread_2 extends Thread {
        @Override
        public void run() {
            synchronized (LOCK_2) {
                System.out.println(getStr("!!!"));
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

              synchronized (LOCK_1) {
                  System.out.println(getStr("???"));
              }
            }


        }
    }

    public static void main(String[] args) {
        Thread_1 t1 = new Thread_1();
        Thread_2 t2 = new Thread_2();
        t1.start();
        t2.start();
    }
}
