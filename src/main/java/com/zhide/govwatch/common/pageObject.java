package com.zhide.govwatch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;

public class pageObject {
    int total;
    Object data;
    Logger logger;
    Long time;
    String className;
    Date begin;
    Date end;
    boolean success;
    String errorMsg;
    private String methodName;

    public pageObject() {
        logger = LoggerFactory.getLogger(pageObject.class);
        className = Thread.currentThread().getStackTrace()[2].getClassName();
        methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        begin = new Date();
        logger.info("[" + className + "." + methodName + "]开始执行");
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotal(long total) {
        this.total = Integer.parseInt(Long.toString(total));
    }

    public Object getData() {
        end = new Date();
        logger.info("[" + className + "." + methodName + "]执行结束,累计用时:" + Long.toString(end.getTime() - begin.getTime()) +"毫秒");
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void raiseException(Exception ax) {
        success = false;
        errorMsg = ax.getMessage();
        if (StringUtils.isEmpty(errorMsg)) errorMsg = ExceptionUtils.getStrackTrace(ax);
        logger.info(errorMsg);
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
