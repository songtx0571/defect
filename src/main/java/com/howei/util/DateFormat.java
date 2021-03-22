package com.howei.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    /**
     * 获取当前时间
     * yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String getYMDHMS(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String created=sdf.format(date);
        return created;
    }

    /**
     * 当前时间加上Hour
     * @param Hour
     * @return
     */
    public static String getBehindTime(String Hour){
        long currentTime = System.currentTimeMillis() ;
        currentTime +=Integer.parseInt(Hour)*60*60*1000;//小时
        Date date=new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String created=dateFormat.format(date);
        return created;
    }

    /**
     * 指定时间加上Hour
     * @param Hour
     * @return
     */
    public static String getBehindTime2(String time,String Hour) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bt=sdf.parse(time);
        long currentTime =bt.getTime();
        currentTime +=Integer.parseInt(Hour)*60*60*1000;//小时
        Date date=new Date(currentTime);
        String created=sdf.format(date);
        return created;
    }

    /**
     * 当前时间加上minute
     * @param minute
     * @return
     */
    public static String getBehindTime3(String minute){
        long currentTime = System.currentTimeMillis() ;
        currentTime +=Integer.parseInt(minute)*60*1000;//分钟
        Date date=new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String created=dateFormat.format(date);
        return created;
    }

    /**
     * 比较两个时间大小
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean comparetoTime(String beginTime,String endTime) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bt=sdf.parse(beginTime);
        Date et=sdf.parse(endTime);
        if (bt.before(et)){
            return true;
        }else{
            if(beginTime.equals(endTime)){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * 获取格式：yyyy-MM-dd
     * @return
     */
    public static String getYMD(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String created=sdf.format(new Date());
        return created;
    }


    public static String getBothDate(String beginTime,String endTime)throws ParseException{
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long bt=sdf.parse(beginTime).getTime();
        long et=sdf.parse(endTime).getTime();
        long diff=(et-bt);
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        String result="";
        if(day<1&&hour>1){
            result=hour + "时" + min + "分钟";
        }else if(day<1&&hour<1){
            result=min + "分钟";
        }else{
            result=day + "天" + hour + "时" + min + "分钟";
        }
        return result;
    }

    /**
     * 获取当前时间的毫秒数
     * @return
     */
    public static long getLongTime(){
        Date date=new Date();
        long time=date.getTime();
        return time;
    }
}
