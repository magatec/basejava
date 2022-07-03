package com.urise.webapp;

public class MainDeadlock {

    public static final Object LOCK_1 = new Object();
    public static final Object LOCK_2 = new Object();

    private static final String str = "I`m java programmer";

    private static String getStr(String s) {
        return str + s;
    }

    private static Thread getThread(Object obj1, Object obj2, String s) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (obj1) {
                    System.out.println(getStr(s + " " + getName() + " lock Object " + obj1.toString()));
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (obj2) {
                        System.out.println(getStr(s + " Поток пытаются обратиться к уже занятому объекту " + obj2.toString()));
                    }
                }
            }
        };
        return thread;
    }

    public static void main(String[] args) {
        Thread t1 = getThread(LOCK_1, LOCK_2, "?");
        Thread t2 = getThread(LOCK_2, LOCK_1, "!");
        t1.start();
        t2.start();
    }
}
