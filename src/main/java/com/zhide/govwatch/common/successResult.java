package com.zhide.govwatch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;


/**
 * String className = Thread.currentThread().getStackTrace()[2].getClassName();//调用的类名
 * String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();//调用的方法名
 * int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();//调用的行数
 */
public class successResult implements Serializable {
    //错误信息。
    private String message;
    //返回结果
    private Object data;
    //执行是否成功。
    private boolean success;
    private boolean showLog;

    Logger logger = LoggerFactory.getLogger(successResult.class);
    private String className;
    private String methodName;
    Date begin;
    Date end;

    public successResult() {
        success = true;
        try {
            className = Thread.currentThread().getStackTrace()[2].getClassName();//调用的类名
            logger = LoggerFactory.getLogger(Class.forName(className));
            methodName = Thread.currentThread().getStackTrace()[2].getMethodName();//调用的方法名
            begin = new Date();
            showLog=true;
            logger.info("----" + "[" + methodName + "]开始执行----");
        } catch (Exception ax) {
            logger.info("success在获取当前方法和当前类名时发生了错误:" + ax.getMessage());
        }
    }
    public successResult(boolean showLog){
        this.showLog=showLog;
        success = true;
        try {
            className = Thread.currentThread().getStackTrace()[2].getClassName();//调用的类名
            logger = LoggerFactory.getLogger(Class.forName(className));
            methodName = Thread.currentThread().getStackTrace()[2].getMethodName();//调用的方法名
            begin = new Date();
            if(showLog==true) logger.info("----" + "[" + methodName + "]开始执行----");
        } catch (Exception ax) {
            logger.info("success在获取当前方法和当前类名时发生了错误:" + ax.getMessage());
        }
    }
    public void raiseException(Exception ax) {
        setMessage(ax.getMessage());
        setSuccess(false);
        String message = ax.getMessage();

        String VX = "\r\n" +
                "X--------------------------------------" + methodName +
                "执行出现异常------------------------------------X";
        VX += "\r\n" + ExceptionUtils.getStrackTrace(ax) + "\r\n";
        VX += "X------------------------------------------------X";
        logger.error(VX);
    }

    public String getMessage() {
        end = new Date();
        if(isShowLog()==true) {
            logger.info("[" + methodName + "] 执行结束累计用时:" + Long.toString(end.getTime() - begin.getTime()) +
                    "毫秒。");
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isShowLog() {
        return showLog;
    }

    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }
}
