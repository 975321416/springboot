//package com.msunhealth.springboot.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.msunhealth.common.constant.SysConstant;
//import com.msunhealth.projectinfo.entity.MtsEtlWorkflow;
//import com.msunhealth.projectinfo.entity.MtsTsTask;
//import com.msunhealth.schedulemanagement.job.MyJob;
//import com.msunhealth.schedulemanagement.job.TableSpaceJob;
//import org.quartz.*;
//
//import java.util.List;
//
///**
// * @Description:Quartz,Schedule任务调度器工具类 任务的新增，删除，修改，暂停，恢复，立即执行(只执行一次)，全部执行，全部暂停。
// * @Company：众阳健康
// * @Author: shh
// * @Date: 2019/11/30 20:06
// * @Version 1.0
// */
//public class ScheduleUtils {
//    /**
//     * 创建ETL监控的任务
//     *
//     * @param mtsEtlWorkflows
//     * @param scheduler
//     */
//    public static void createSchedule(List<MtsEtlWorkflow> mtsEtlWorkflows, Scheduler scheduler) {
//
//        for (MtsEtlWorkflow job : mtsEtlWorkflows) {
//            try {
//                //1、创建一个jobdetail,job执行内容为Myjob类
//                //保证job名唯一
//                JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(SysConstant.JOB_NAME_PREFIX + job.getWorkflowId(), SysConstant.JOB_GROUP_PREFIX + job.getProjectId()).build();
//                //向myjob类中传入参数，确定调用哪个方法，哪些参数
//                String json = JSON.toJSONString(job);
//                //通过map传参
//                jobDetail.getJobDataMap().put(SysConstant.SCHEDULE_JOB_KEY, json);
//                //scheduler.getContext().put(SysConstant.SCHEDULE_JOB_KEY,json);
//                //2、创建一个触发器设置采用那个调度器（crons）并设置名称
//                Trigger trigger = TriggerBuilder.newTrigger().
//                        withSchedule(CronScheduleBuilder.cronSchedule(job.getWfFre())).
//                        withIdentity(SysConstant.TRIGGER_NAME_PREFIX + job.getWorkflowId(), SysConstant.TRIGGER_GROUP_PREFIX + job.getProjectId()).build();
//                //3、创建Schedule对象
//                //scheduler前端传入，并且已经在QartzConfig中已经创建了，不用再创建了
//                //4、注册触发器和JobDetail
//                scheduler.scheduleJob(jobDetail, trigger);
//
//                //5、启动任务
//                //在QuartzConfig中scheduler设置为自动启动，因此这里不必启动
//                //scheduler.start();
//            } catch (SchedulerException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 创建表空间监控的任务
//     * @param mtsTsTasks
//     * @param scheduler
//     */
//    public static void createTsTask(List<MtsTsTask> mtsTsTasks,Scheduler scheduler){
//        for (MtsTsTask job : mtsTsTasks) {
//            try {
//                //1、创建一个jobdetail,job执行内容为Myjob类
//                //保证job名唯一
//                JobDetail jobDetail = JobBuilder.newJob(TableSpaceJob.class).withIdentity(SysConstant.JOB_NAME_PREFIX + job.getDbtaskId(), SysConstant.JOB_GROUP_PREFIX + job.getProjectId()).build();
//                //向myjob类中传入参数，确定调用哪个方法，哪些参数
//                String json = JSON.toJSONString(job);
//                //通过map传参
//                jobDetail.getJobDataMap().put(SysConstant.SCHEDULE_JOB_KEY, json);
//                //scheduler.getContext().put(SysConstant.SCHEDULE_JOB_KEY,json);
//                //2、创建一个触发器设置采用那个调度器（crons）并设置名称
//                Trigger trigger = TriggerBuilder.newTrigger().
//                        withSchedule(CronScheduleBuilder.cronSchedule(job.getDbtaskFre())).
//                        withIdentity(SysConstant.TRIGGER_NAME_PREFIX + job.getDbtaskId(), SysConstant.TRIGGER_GROUP_PREFIX + job.getProjectId()).build();
//                //3、创建Schedule对象
//                //scheduler前端传入，并且已经在QartzConfig中已经创建了，不用再创建了
//                //4、注册触发器和JobDetail
//                scheduler.scheduleJob(jobDetail, trigger);
//
//                //5、启动任务
//                //在QuartzConfig中scheduler设置为自动启动，因此这里不必启动
//                //scheduler.start();
//            } catch (SchedulerException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    /**
//     * 立即运行，执行一次
//     *
//     * @param scheduler
//     * @param jobId
//     */
//    public static void runTask(Scheduler scheduler, String jobId, String groupId) {
//        JobKey jobKey = JobKey.jobKey(SysConstant.JOB_NAME_PREFIX + jobId, SysConstant.JOB_GROUP_PREFIX + groupId);
//        try {
//            scheduler.triggerJob(jobKey);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 暂停任务
//     * @param scheduler
//     * @param jobId
//     * @param groupId
//     */
//    public static void pauseTask(Scheduler scheduler, String jobId, String groupId) {
//        try {
//            scheduler.pauseJob(JobKey.jobKey(SysConstant.JOB_NAME_PREFIX + jobId, SysConstant.JOB_GROUP_PREFIX + groupId));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 恢复任务
//     *
//     * @param scheduler
//     * @param groupId
//     */
//    public static void resumeTask(Scheduler scheduler, String jobId, String groupId) {
//        try {
//            scheduler.resumeJob(JobKey.jobKey(SysConstant.JOB_NAME_PREFIX + jobId, SysConstant.JOB_GROUP_PREFIX + groupId));
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 功能描述:
//     * 〈批量删除任务〉
//     *
//     * @param scheduler  1
//     * @param groupId 2
//     * @return : void
//     * @author : songhuanhao
//     * @date : 2019/12/1 21:17
//     */
//    public static void deleteTask(Scheduler scheduler, String jobId, String groupId) {
//        try {
//            scheduler.deleteJob(JobKey.jobKey(SysConstant.JOB_NAME_PREFIX + jobId, SysConstant.JOB_GROUP_PREFIX + groupId));
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 启动所有的任务
//     *
//     * @param scheduler
//     */
//    public static void startJobs(Scheduler scheduler) {
//        try {
//            scheduler.start();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    /**
//     * 关闭所有的任务
//     *
//     * @param scheduler
//     */
//    public static void shutdownJobs(Scheduler scheduler) {
//        try {
//            if (!scheduler.isShutdown()) {
//                scheduler.shutdown();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 修改任务(这里是修改任务的频率)
//     *
//     * @param scheduler
//     */
//    public static void updateTask(Scheduler scheduler, MtsEtlWorkflow mtsEtlWorkflow) {
//        try {
//            String json = JSON.toJSONString(mtsEtlWorkflow);
//            //得到触发器的key
//            TriggerKey triggerKey = TriggerKey.triggerKey(SysConstant.TRIGGER_NAME_PREFIX + mtsEtlWorkflow.getWorkflowId(), SysConstant.TRIGGER_GROUP_PREFIX + mtsEtlWorkflow.getProjectId());
//            scheduler.getContext().put(SysConstant.SCHEDULE_JOB_KEY, json);
//            //得到原来的触发器对象
//            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//
//            //替换触发器的表达式
//            trigger = trigger.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(mtsEtlWorkflow.getWfFre())).build();
//            //重置触发器
//            scheduler.rescheduleJob(triggerKey, trigger);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获得任务的状态
//     * @param scheduler
//     * @param jobName
//     * @param groupName
//     * @return
//     */
//    public static String getTriggerStates(Scheduler scheduler, String jobName,String groupName) {
//        try {
//            //获取triggerKey
//            TriggerKey triggerKey = TriggerKey.triggerKey(SysConstant.TRIGGER_NAME_PREFIX + jobName, SysConstant.TRIGGER_GROUP_PREFIX + groupName);
//            //获取触发器
//            Trigger.TriggerState state = scheduler.getTriggerState(triggerKey);
//            return state.name();
//        } catch (SchedulerException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//}