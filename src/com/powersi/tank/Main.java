package com.powersi.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //窗口的对象
//        Frame frame = new Frame();
        //设置窗口的大小
//        frame.setSize(800,600);
        //设置不能够改变大小
//        frame.setResizable(false);
        //设置标题
//        frame.setTitle("tank war");
        //显示窗口，设置为可见
//        frame.setVisible(true);
        //添加了一个window的监听器，点击关闭按钮的时候，就会触发这个windowClosing
        //WindowAdapter是WindowListener的子类，实现关闭窗口的功能
        //  传的是一个匿名内部类  new WindowAdapter() {
        //                      @Override
        //                    public void windowClosing(WindowEvent e) {
        //                    System.exit(0);
        //                }
        //           }
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
        //调的是构造方法
        TankFrame tankFrame = new TankFrame();
        //在主线程，每隔50毫秒刷新窗口，调用repaint方法，repaint会调用paint方法，不能自己调用，因为你自己拿不到画笔，
        //画笔是系统给的
        while (true){
            Thread.sleep(10);
            tankFrame.repaint();
        }


    }
}
