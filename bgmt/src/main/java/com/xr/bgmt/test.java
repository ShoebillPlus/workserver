package com.xr.bgmt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Function;

public class test {



    public static long getTime(String str) throws ParseException {
        SimpleDateFormat sDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sDateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date init = sDateTimeFormat.parse(str);
        return init.getTime();
    }

    static class  MusicThread extends Thread {
        //2):在A类中覆盖Thread类中的run方法.
        public void run() {
            //3):在run方法中编写需要执行的操作
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try{
                System.out.println("线程内"+getTime(df.format(new Date())));
                Thread.currentThread().sleep(1000);
                System.out.println("线程内"+getTime(df.format(new Date())));
                Thread.currentThread().sleep(1000);
                System.out.println("线程内"+getTime(df.format(new Date())));
                Thread.currentThread().sleep(1000);
                System.out.println("线程内"+getTime(df.format(new Date())));
                Thread.currentThread().sleep(1000);
                System.out.println("线程内"+getTime(df.format(new Date())));
            }catch (Exception e){

            }

        }
    }

    public static void main(String[] args) throws Exception{
            //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            /*MusicThread music = new MusicThread();
            music.start();
            MusicThread music1 = new MusicThread();
            Thread.currentThread().sleep(1000);
            music1.start();*/
            /*Thread.currentThread().sleep(1000);
            System.out.println(getTime(df.format(new Date())));
            Thread.currentThread().sleep(1000);
            System.out.println(getTime(df.format(new Date())));
            Thread.currentThread().sleep(1000);
            System.out.println(getTime(df.format(new Date())));
            Thread.currentThread().sleep(1000);
            System.out.println(getTime(df.format(new Date())));
            Thread.currentThread().sleep(1000);
            System.out.println(getTime(df.format(new Date())));*/

        /*Function<String, String> function = a -> a + " Jack!";
        Function<String, String> function1 = a -> a + " Bob!";
        String greet = function.compose(function1).apply("Hello");*/
        SimpleDateFormat df = new SimpleDateFormat("d");
        Date date = new Date();
        System.out.println(df.format(date));



    }
    }
