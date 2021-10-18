package com.mrlep.meon.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LoggingInfo {
    public static final String ATTRIBUTE_NAME = "logInfo";
    private String sessionID;
    private String ipPortParentNode;
    private String requestContent;
    private String responseContent;
    private Date startTime;
    private Date endTime;
    private Integer errorCode;
    private String errorDescription;
    private int transactionStatus = LoggingTransactionStatus.FAILED;
    private String actionName;
    private String userName;
    private String account;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getIpPortParentNode() {
        return ipPortParentNode;
    }

    public void setIpPortParentNode(String ipPortParentNode) {
        this.ipPortParentNode = ipPortParentNode;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        if (this.startTime == null || this.endTime == null) {
            return null;
        }
        return this.endTime.getTime() - this.startTime.getTime();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public void setErrorDescription(Exception e) {
        this.setErrorDescription(ExceptionUtils.getStackTrace(e));
    }

    public int getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public static LoggingInfo buildFromHttpRequest(HttpServletRequest request) {
        LoggingInfo loggingInfo = new LoggingInfo();
        loggingInfo.setSessionID(request.getSession().getId());
        String ipPortParentNode = request.getRemoteAddr() + ":" + request.getRemotePort();
        loggingInfo.setIpPortParentNode(ipPortParentNode);
        loggingInfo.setStartTime(new Date());
        // Get request url
        StringBuilder requestUrl = new StringBuilder("[" + request.getMethod() + "]:" + request.getRequestURI());
        String queryString = request.getQueryString();
        if (queryString != null) {
            requestUrl.append("?").append(queryString);
        }
        loggingInfo.setRequestContent(requestUrl.toString());
        String userName = request.getHeader("username");
        if (userName == null) {
            userName = request.getHeader("uid");
        }

        loggingInfo.setUserName(userName);

        return loggingInfo;
    }

    public static LoggingInfo getFromHttpRequest(HttpServletRequest request) {
        LoggingInfo loggingInfo = new LoggingInfo();
        if (request != null && request.getAttribute(ATTRIBUTE_NAME) != null) {
            loggingInfo = (LoggingInfo) request.getAttribute(ATTRIBUTE_NAME);
        }

        return loggingInfo;
    }

    public static LoggingInfo getFromHttpRequest(HttpServletRequest request, Exception e, String actionName) {
        LoggingInfo loggingInfo = new LoggingInfo();
        if (request != null && request.getAttribute(ATTRIBUTE_NAME) != null) {
            loggingInfo = (LoggingInfo) request.getAttribute(ATTRIBUTE_NAME);
        }
        loggingInfo.setErrorDescription(e);
        loggingInfo.setActionName(actionName);
        loggingInfo.setStartTime(new Date());
        return loggingInfo;
    }

    public static LoggingInfo getFromHttpRequest(HttpServletRequest request, Exception e, String actionName, int errorCode) {
        LoggingInfo loggingInfo = getFromHttpRequest(request, e, actionName);
        loggingInfo.setErrorCode(errorCode);
        return loggingInfo;
    }
}
