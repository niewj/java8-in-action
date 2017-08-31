package com.niewj.basic.multi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijun.nie on 2017/8/31.
 */
public class WaitNotify1 {

    private volatile List<String> list = new ArrayList<String>();

    public void add() {
        list.add("object 1");
    }

    public static void main(String[] args) {

        final WaitNotify1 app = new WaitNotify1();
        final Object lock = new Object();

        // #1. 线程1 往list中存放数据
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    System.out.println("#\t线程1 启动运行");
                    for (int i = 1; i < 10; i++) {
                        app.add();
                        System.out.println("Thread 1 add data " + i);
                        if(app.list.size() == 5){
                            try {
                                lock.wait(); // 停止，发出信号
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    System.out.println(">\t线程2 启动运行");
                    while (app.list.size() == 5) {
                        System.out.println("List.size == 5, 通知程序可以中断了！");

                        lock.notify(); // 通知
                        throw new RuntimeException("可以退出了！");
                    }
                }
            }
        });

        t2.start();

        t1.start();
    }
}
