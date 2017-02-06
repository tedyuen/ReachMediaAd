package com.reachmedia.ad.reachmediaad.app;

/**
 * Created by tedyuen on 2017/2/6.
 */

public class AppApiContact {
    public static final String API_HOST_ST = "http://120.26.65.65:8281/app";//API Host 测试
    public static final String API_HOST_MN = "http://120.26.64.180:8381/app";//API Host 模拟
    public static final String API_HOST_PD = "http://opapp.reachmedia.cn:8281/app";//API Host 模拟
    public static final String API_ACTION = "/data.api";//api action

    public static String getApiHost(){
//http://120.26.64.180:8281/app/data.api
        return API_HOST_ST;
    }

    /**
     * 接口method定义ç
     */
    public class InterfaceMethod{
        /**
         * 3.1 用户登录
         */
        public static final String LOGIN_METHOD = "RM_GCBAPP_login";

    }

    /**
     * 错误代码
     */
    public class ErrorCode{

        /**
         * 正确返回
         */
        public static final String SUCCESS = "0000";

        /**
         * 程序异常
         */
        public static final String EXCEPTION = "ER99";

        /**
         * 定位异常
         */
        public static final String ERROR_LON = "20001";

        /**
         * 缺少文件
         */
        public static final String ERROR_LESS_FILE = "2101";

    }
}
