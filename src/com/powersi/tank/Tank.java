package com.powersi.tank;

import java.awt.*;

//坦克类
public class Tank {
    private int x,y;
    private  Dir dir = Dir.DOWN;
    private static final int SPEED =5;

    //坦克静止
    private  boolean moving = false;

    private  TankFrame  tankFrame =null;


    public Tank(int x, int y, Dir dir,TankFrame  tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame=tankFrame;
    }



    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        Color color =  g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x,y,50,50);
        g.setColor(color);
        move();


    }

    private void move(){
        //如果moving为false，则结束程序，后面的移动代码就不执行了，所以是静止
        //moving为true，则继续下面的移动代码
        System.out.println("还没有结束");

        if (!moving) return;
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }

    }

    public void fire() {
        //坦克有窗口的引用，造一个子弹然后给坦克引用窗口的子弹属性
        tankFrame.bullet = new Bullet(this.x, this.y, this.dir);


    }
}
