package com.msunhealth.springboot.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Description:网络测试联通工具类
 * @Company：众阳健康
 * @Author: shh
 * @Date: 2019/12/23 9:31
 * @Version 1.0
 */
public class NetworkUtils {


    /**
     * 功能描述:
     * 〈使用操作系统cmd执行ping命令，可能会耗费资源但是不会出错〉
     *
     * @param host 1
     * @return : void
     * @author : songhuanhao
     * @date : 2019/12/23 10:30
     */
    public static AjaxRusult pingTest(String host)  {
        // 当返回值是true时，说明host是可用的，false则不可连接。
        try {
            if (host!=null){
                boolean status = (0==Runtime.getRuntime().exec("ping -c 2 "+host).waitFor());
                if (status==true){
                    return AjaxRusult.ok("Ping"+host+"成功");
                }else if (status==false){
                    return AjaxRusult.error("Ping"+host+"失败");
                }
            }else {
                return AjaxRusult.error("ip地址为空");
            }

        } catch (InterruptedException e) {
            return AjaxRusult.error("Ping出现异常："+e.getMessage());
        } catch (IOException e) {
            return AjaxRusult.error("Ping出现异常："+e.getMessage());
        }
        return null;
    }
    /**
     * 功能描述:
     * 〈用tcp协议socket测试网络连接，指定端口号〉
     *
     * @param host 1
     * @param port 2
     * @return : void
     * @author : songhuanhao
     * @date : 2019/12/23 10:31
     */
    public static AjaxRusult telnetTest(String host, Integer port)  {
        Socket target = null;
        boolean result = true;
        try {
            target = new Socket();
            InetSocketAddress address = new InetSocketAddress(host, port);
            target.connect(address, 5000);
        }
         catch (IOException e) {
            result = false;
            return AjaxRusult.error("socket连接失败，网络不可达");
        } finally {
            if (result==true){
                return AjaxRusult.ok("连接成功");
            }
            if (target != null)
                try {
                    target.close();
                } catch (IOException e) {

                }
        }
        return AjaxRusult.error("连接失败");
    }


}
