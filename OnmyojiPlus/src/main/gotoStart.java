package main;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class gotoStart {
    public static final int FUNC_KEY_MARK = 1;
    public static final int EXIT_KEY_MARK = 0;

    public gotoStart() throws AWTException, InterruptedException {

        //添加全局监听器
        JIntellitype.getInstance().registerHotKey(FUNC_KEY_MARK,JIntellitype.MOD_ALT,(int)'Q');
        JIntellitype.getInstance().registerHotKey(EXIT_KEY_MARK,JIntellitype.MOD_ALT,(int)'D');

        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            @Override
            public void onHotKey(int i) {
                switch (i){
                    case FUNC_KEY_MARK:
                        System.out.println("test");
                        break;
                    case EXIT_KEY_MARK:
                        System.out.println("程序结束！！！！！");
                        System.exit(0);
                        break;
                }
            }
        });

        Robot robot=new Robot();
        robot.delay(3000);
        ArrayList<BufferedImage> images =new ArrayList<>();
//        images.add(getBfImageFromPath(new FileInputStream("src/img/御灵挑战")));
        images.add(getBfImageFromPath("E:\\阴阳师\\img\\卑弥呼结束.png"));
        images.add(getBfImageFromPath("E:\\阴阳师\\img\\协同取消.png"));
        images.add(getBfImageFromPath("E:\\阴阳师\\img\\卑弥呼挑战.png"));
        images.add(getBfImageFromPath("E:\\阴阳师\\img\\御灵挑战.png"));
        images.add(getBfImageFromPath("E:\\阴阳师\\img\\御灵结束.png"));
        images.add(getBfImageFromPath("D:\\idea\\OnmyojiPlus\\img\\协同取消2.png"));
        images.add(getBfImageFromPath("D:\\idea\\OnmyojiPlus\\img\\御灵失败.png"));
        images.add(getBfImageFromPath("D:\\idea\\OnmyojiPlus\\img\\单人挑战魂10.png"));
        images.add(getBfImageFromPath("D:\\idea\\OnmyojiPlus\\img\\御魂大于6000.png"));
        BufferedImage tempImg=null;
        int i=-1;

        while (true){
            //这个是根据情况返回对应的时间
            i=-1;
            i = chooseThingReturnNum(images);
            System.out.println(i);
            switch (i){
                //卑弥呼打完
                case 1:{
                    next();
                    robot.delay(randomNum(1.5,0.5));
                    break;
                }
                //协同任务点击取消
                case 2:
                    mouseRandomMoveTrueArea(1275,783,21,22);
                    mouseSwitchClick("left");
                    break;
                //点击卑弥呼挑战
                case 3:
                    mouseRandomMoveTrueArea(1645,878,180,158);
                    mouseSwitchClick("left");
                    break;
                //挑战御灵
                case 4:
                    mouseRandomMoveTrueArea(1620,875,180,170);
                    mouseSwitchClick("left");
                    break;
                //御灵结束
                case 5:{
                    next();
                    robot.delay(randomNum(1.5,0.5));
                    break;
                }//协同取消2
                case 6:
                    mouseRandomMoveTrueArea(1230,730,250,90);
                    mouseSwitchClick("left");
                    break;
                //御灵失败
                case 7:
                    next2();
                    robot.delay(randomNum());
                    break;
                //单人挑战魂10
                case 8:
                    mouseRandomMoveTrueArea(1634,867,180,180);
                    mouseSwitchClick("left");
                    break;
                //御魂数量大于6K
                case 9:
                    mouseRandomMoveTrueArea(866,592,180,85);
                    mouseSwitchClick("left");
                    break;
                //其他情况，就什么都没有，鼠标随机移动,有长时间移动和短时间移动
                case -1:
                    int choose= (int) (Math.random()*2);
                    if (choose==1){
                        int a=randomNum(1,0.5);
                        System.out.println(a);
                        robot.delay(a);

                    }else{
                        int b=randomNum(3,2);
                        System.out.println(b);
                        robot.delay(b);
                    }
                    randomMove();
                    break;
            }//switch(i)

        }//while(true)

    }



    //@String 图片的绝对路径。从本地文件读取目标图片
    static public BufferedImage getBfImageFromPath(String keyImagePath) {
        BufferedImage bfImage = null;
        try {
            bfImage = ImageIO.read(new File(keyImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bfImage;
    }

    /*
    最核心的部分，代码块从这里修改
     */
    public static int chooseThingReturnNum(ArrayList<BufferedImage> images) throws AWTException {
        Robot robot=new Robot();
        for (BufferedImage image:images){
            //匹配是否为卑弥呼结束
            if (matchImg(image,robot.createScreenCapture(new Rectangle(900,800,100,100)))){
                System.out.println("匹配到了卑弥呼结束");
                return 1;
            }//有人要来协同任务的时候
            else if (matchImg(image,robot.createScreenCapture(new Rectangle(1275,783,21,22)))){
                System.out.println("匹配到了协同取消");
                return 2;
            }//是否在卑弥呼点挑战之前
            else if(matchImg(image,robot.createScreenCapture(new Rectangle(1690,920,90,74)))){
                System.out.println("匹配到了要挑战卑弥呼");
                return 3;
            }//御灵挑战
            else if(matchImg(image,robot.createScreenCapture(new Rectangle(1670,907,98,86)))){
                System.out.println("匹配到了要挑战御灵");
                return 4;
            }//御灵结束
            else if(matchImg(image,robot.createScreenCapture(new Rectangle(980,784,10,10)))){
                System.out.println("匹配到了要御灵结束");
                return 5;
            }//协同取消2
            else if(matchImg(image,robot.createScreenCapture(new Rectangle(1270,766,20,20)))){
                System.out.println("协同取消2");
                return 6;
            }//御灵失败
            else if(matchImg(image,robot.createScreenCapture(new Rectangle(630,758,20,20)))){
                System.out.println("挑战御灵失败");
                return 7;
            }//挑战单人魂10
            else if(matchImg(image,robot.createScreenCapture(new Rectangle(1696,915,80,80)))){
                System.out.println("单人挑战魂10");
                return 8;
            }//御魂大于6000
            else if(matchImg(image,robot.createScreenCapture(new Rectangle(900,600,100,50)))){
                System.out.println("御魂数量>6000");
                return 9;
            }

        }
        System.out.println("什么都没匹配到，随机移动");
        return -1;
    }

    /*
    随机移动到我自己设定的4个区域中的某一点，并且点击
     */
    public static void next() throws AWTException, InterruptedException {
        //随机移动到一个区域中
        int random= (int) (Math.random()*4);
        switch (random){
            //移动到左上角区域
            case 0:
                mouseRandomMoveTrueArea(500,0,770,200);
                mouseSwitchClick("left");
                break;
            //移动到左下角区域
            case 1:
                mouseRandomMoveTrueArea(0, 400, 200, 680);
                mouseSwitchClick("left");
                break;
            //右上角区域
            case 2:
                mouseRandomMoveTrueArea(1580, 70, 340, 630);
                mouseSwitchClick("left");
                break;
            //右下角区域
            case 3:
                mouseRandomMoveTrueArea(900, 700, 1020, 380);
                mouseSwitchClick("left");
                break;
        }//随机移动到某个区域结束
    }

    /*
    随机移动到我自己设定的4个区域中的某一点，并且点击
     */
    public static void next2() throws AWTException, InterruptedException {
        //随机移动到一个区域中
        int random= (int) (Math.random()*3);
        switch (random){
            //移动到左上角区域
            case 0:
                mouseRandomMoveTrueArea(0,0,1270,200);
                mouseRandomMoveTrueArea(0, 0, 1270, 200);
                mouseSwitchClick("left");
                break;
            //移动到左下角区域
            case 1:
                mouseRandomMoveTrueArea(0, 200, 200, 880);
                mouseSwitchClick("left");
                break;
            //右上角区域
            case 2:
                mouseRandomMoveTrueArea(1580, 70, 340, 630);
                mouseSwitchClick("left");
                break;
        }//随机移动到某个区域结束
    }

    /*匹配图片，
     * 输入参数：sourceImg 本地源文件，最大不超过100*100
     * 		tempImg 当前系统捕抓到的部分屏幕，固定为100*100
     * 输出参数：true或者false
     * 检查捕捉图片的每一个像素点是否和本地的源文件一样，全部一样就返回TRUE，有一个不一样都返回FALSE
     */
    public static boolean matchImg(BufferedImage sourceImg,BufferedImage tempImg) {
        int height = sourceImg.getHeight();
        int width = sourceImg.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int tempRGB = tempImg.getRGB(j, i);
                int testRGB = sourceImg.getRGB(j, i);
                if (tempRGB!=testRGB) {
                    return false;
                }

            }
        }
        return true;
    }//matchImg

    /*

    X，Y最大为1920和1080，隔一段时间后随机移动到某个位置，再隔一段时间
     */
    @Test
    public static void randomMove() throws AWTException {
        Robot robot=new Robot();
        int maxX=1920;
        int maxY=1080;

        double v=(0.2+Math.random()*0.3)*1000;
        robot.delay((int) v);
        int x = (int) (Math.random()*(maxX-1));
        int y = (int) (Math.random()*(maxY-1));
        robot.mouseMove(x,y);
        v=(0.2+Math.random()*0.3)*1000;
        robot.delay((int) v);
    }

    /*
    鼠标随机移动到正确的区域
    @参数分别为X轴，Y轴，宽，高
    返回参数：int [] array：array[0]为X轴，array[1]为Y轴
     */
    public static void mouseRandomMoveTrueArea(int x,int y,int width,int height) throws AWTException {
        int moveX= (int) (x+Math.random()*(width-1));
        int moveY= (int) (y+Math.random()*(height-1));
        Robot robot=new Robot();
        robot.delay(randomNum());
        robot.mouseMove(moveX,moveY);
    }

    /*
    @String key: left为按下左键，right为按下右键
     */
    public static void mouseSwitchClick(String key) throws AWTException, InterruptedException {
        switch (key){
            case "left":mouseClick(InputEvent.BUTTON1_DOWN_MASK);break;
            case "right":mouseClick(InputEvent.BUTTON3_DOWN_MASK);break;
            default:
                System.out.println("请填入正确的参数");
        }
    }

    /*
    @int buttons:与mouseSwitchClick函数配合使用
     */
    public static void mouseClick(int buttons) throws AWTException, InterruptedException {
        Robot robot=new Robot();
        robot.delay(randomNum());
        robot.mousePress(buttons);
        robot.delay(randomNum());
        robot.mouseRelease(buttons);
        robot.delay(randomNum());
        Thread.sleep(randomNum(0.5,0.2));
    }

    /*
    @double preTime：最小要几秒
    @double maxTime：随机的数字最大值
    @不写参数就默认(0.3+(0~0.3))
     */
    public static int randomNum(double preTime, double maxTime){
        int i = (int) ((preTime + Math.random() * maxTime) * 1000);
//        System.out.println(i);
        return i;
    }

    public static int randomNum(){
        int i = (int) ((0.3 + Math.random() * 0.3) * 1000);
//        System.out.println(i);
        return i;
    }

    public static void main(String[] args) throws AWTException, InterruptedException {
        new gotoStart();
    }//main
}

