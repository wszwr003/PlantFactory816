package com.plant.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start:" + getCurrentTime());
        startTimer();	
    }

    public static void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task begin:" + getCurrentTime());
//                try {
//                    Thread.sleep(1000 * 20);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("task   end:" + getCurrentTime());
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, buildTime2(), 1000*60);
      //  timer.schedule(task, buildTime(), 1000 * 60 * 60 * 24);
    }

    private static Date buildTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        if (time.before(new Date())) {
            //������ǰʱ���Ѿ����賿1�����Ҫ�����1�죬�������������ִ�С�
            //�ܶ�ϵͳ����ϵͳ����ʱ����Ҫ����ִ��һ�����񣬵���������Ҫÿ���賿1��ִ�У���ô���أ�
            //�ܼ򵥣�����ϵͳ��ʼ����ʱ����ִ��һ�����񣨲���Ҫ�ö�ʱ����ֻ��ִ���Ƕ�����Ĵ��룩
            time = addDay(time, 1);
        }
        return time;
    }
    
    private static Date buildTime2() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,1);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        return time;
    }

    private static Date addDay(Date date, int days) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, days);
        return startDT.getTime();
    }

}
