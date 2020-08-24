package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class writeImage {
    public static void main(String[] args) throws AWTException, IOException {
        Robot robot=new Robot();
        robot.delay(3000);
        saveImg(1696,915,80,80,"单人挑战魂10");
    }

    /*
    int x ,X轴
    int y ,Y轴
    int width,宽
    int height，高
    String pngName ，图片的名字（不用+PNG，默认补上PNG）
    String pathname，图片路径，没用则默认保存在E:\阴阳师\img下
    * */
    static void saveImg(int x,int y,int width,int height,String pngName,String pathname) throws AWTException, IOException {
        Robot robot=new Robot();
        robot.delay(3000);
        BufferedImage bufferedImage =robot.createScreenCapture(new Rectangle(x,y,width,height));
        robot.mouseMove(x,y);
        File file=new File(pathname+"\\"+pngName+".png");
        OutputStream os=new FileOutputStream(file);
        ImageIO.write(bufferedImage,"png",os);
    }


    static void saveImg(int x,int y,int width,int height,String pngName) throws AWTException, IOException {
        Robot robot=new Robot();
        robot.delay(3000);
        BufferedImage bufferedImage =robot.createScreenCapture(new Rectangle(x,y,width,height));
        robot.mouseMove(x,y);
        String pathname="D:\\idea\\OnmyojiPlus\\img";
        File file=new File(pathname+"\\"+pngName+".png");
        OutputStream os=new FileOutputStream(file);
        ImageIO.write(bufferedImage,"png",os);
    }
}
