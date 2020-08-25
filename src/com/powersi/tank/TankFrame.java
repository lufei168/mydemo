package com.powersi.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    //    int x=200, y= 200;
//    Dir dir =Dir.DOWN;
//    private static final int SPEED =10;
    //坦克持有窗口的引用
    Tank myTank = new Tank(200,200,Dir.DOWN,this);
    Bullet bullet = new Bullet(300,300,Dir.DOWN);
    static final int GAME_WIDTH=800,GAME_HEIGHT=600;

    List<Bullet> bulletList = new ArrayList<>();


    public TankFrame() throws HeadlessException {
        //设置窗口的大小
        setSize(GAME_WIDTH,GAME_HEIGHT);
        //设置不能够改变大小
        setResizable(false);
        //设置标题
        setTitle("tank war");
        //显示窗口，设置为可见
        setVisible(true);

        this.addKeyListener(new MyKeyListener());
        //添加了一个window的监听器，点击关闭按钮的时候，就会触发这个windowClosing
        //WindowAdapter是WindowListener的子类，实现关闭窗口的功能
        //  传的是一个匿名内部类  new WindowAdapter() {
        //                      @Override
        //                    public void windowClosing(WindowEvent e) {
        //                    System.exit(0);
        //                }
        //           }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }


    //解决坦克和子弹闪烁的问题
    Image offScreenImage=null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage==null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        //把画笔叫给paint
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);

    }


    //继承就是为了重写它的方法 ,窗口第一次显示出来的时候，窗口需要 重新绘制的时候，调用这个方法
    //Graphics g 是系统递给你的一只画笔
    //坐标是从左上角开始的
    //内部类可能只被一个类用到，那就没有必要额外写个类
    @Override//告诉编译器，我这个方法是重写的方法
    public void paint(Graphics g) {
        //谁知道坦克怎么画，当然是坦克自己
        myTank.paint(g);
        bullet.paint(g);
        //画一个可变方块
//        g.fillRect(x,y,50,50);
//
//        switch (dir){
//            case LEFT:
//                x-=SPEED;
//                break;
//            case UP:
//                y-=SPEED;
//                break;
//            case RIGHT:
//                x+=SPEED;
//                break;
//            case DOWN:
//                y+=SPEED;
//                break;
//        }
//        x+=10;
//        y+=10;

    }


    //内部类
    class  MyKeyListener extends KeyAdapter{
        //添加键盘处理，根据上下左右箭头的按键状态，判定坦克的移动方向
        //用4个boolean值来记录按键的状态
        boolean bL =false;
        boolean bU =false;
        boolean bR =false;
        boolean bD =false;

        //键盘按下
        //keyEvent是系统传过来的对象
        @Override
        public  void  keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            switch (key){
                //按下向左的按钮  VK_LEFT按键的状态
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                //按下向上的按钮
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                //按下向右的按钮
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                //按下向下的按钮
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
//            System.out.println("键盘按下");
//            x+=200;
            //默认调用paint方法
//            repaint();
        }

        //键盘抬起
        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("键盘抬起");
            int key = e.getKeyCode();
            switch (key){
                //按下向左的按钮
                case KeyEvent.VK_LEFT:
                    bL=false;
                    break;
                //按下向上的按钮
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                //按下向右的按钮
                case KeyEvent.VK_RIGHT:
                    bR=false;
                    break;
                //按下向下的按钮
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;

                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            //四个方向键都没有按，就是坦克 静止
            if (!bR &! bU &! bL & !bD) myTank.setMoving(false);
            else {
                //坦克移动，为什么设置为false坦克就 不动了呢
                myTank.setMoving(true);

                //根据按键的状态来改变坦克的方向
                //如果bl为true 则向左移动
                if (bL) myTank.setDir(Dir.LEFT);   //dir=LEFT
                if (bU) myTank.setDir(Dir.UP);     //dir=UP
                System.out.println("能看到我么");

                if (bR) myTank.setDir(Dir.RIGHT);   //dir=RIGHT
                if (bD) myTank.setDir(Dir.DOWN);   //dir=DOWN


            }
        }



    }


}
