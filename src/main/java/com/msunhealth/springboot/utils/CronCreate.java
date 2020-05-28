package com.msunhealth.springboot.utils;

/**
 * @ClassNameCronCreate
 * @Company：众阳健康
 * @Description 目前只是简易的去生成新的表达式，后续会增加更多的逻辑，方便大家的使用
 * @Author KM
 * @Date2019/12/18 15:22
 * @Version V1.0
 **/
public class CronCreate {

    /**
     * 功能描述: 判断两个输入值的关系，如果同时为空，则输出*，如果相等则输出两者之一，如果不想等则输出两者的跨度
     *
     * @return: java.lang.String
     * @Author: kangmeng
     * @Date: 2019/12/18 15:52
     */
    public static String getStringByTwoPoint(String pointStart, String pointEnd) {


        if (pointStart.equals(pointEnd) && pointStart.length() > 0) {
            return pointEnd;
        }
        if (!pointStart.equals(pointEnd)) {
            return pointStart + "-" + pointEnd;
        } else {
            return "*";
        }
    }

    /**
     * 功能描述: 输入开始的小时以及结束的小时，输入的rate为分钟为单位的
     *
     * @return: java.lang.String
     * @Author: kangmeng
     * @Date: 2019/12/18 15:53
     */
    public static String getCronByHourInfo(String hourStart, String hourEnd, String exeRate) {
        String hourString = getStringByTwoPoint(hourStart, hourEnd);
        String corn = "0 0/" + exeRate + " " + hourString + " * * ? *";
        return corn;
    }

    /**
     * @return
     * @Author jiangheng
     * @Description //TODO 格式化 数据库监控的cron表达式
     * @Date 7:54 2020/2/26
     * @Param
     **/
    public static String cronDateFormat(String date) {
        String cron = "";
        switch (date) {
            case "5天":
                cron = "0 0 0 1/5 * ? ";//每5天执行一次
                break;
            case "1天":
                cron = "0 0 1 1/1 * ? ";
                break;
            case "10天":
                cron = "0 0 6 1/10 * ? ";
                break;
            case "2天":
                cron = "0 0 3 1/2 * ? ";
                break;
            case "3天":
                cron = "0 0 4 1/3 * ? ";
                break;
            case "4天":
                cron = "0 0 5 1/4 * ? ";
                break;
            case "6天":
                cron = "0 0 6 1/6 * ? ";
                break;
            case "7天":
                cron = "0 0 2 1/7 * ? ";
                break;
        }
        return cron;
    }

}
