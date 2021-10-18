package com.mrlep.meon.utils;

import com.google.gson.Gson;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;

import java.util.Date;

public class LoggingUtils {
    private static Logger logger;
    private static final Gson gson = new Gson();
    private static final String DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static String functionCode = "N/A";
    private static String funcType = "READ";
    private static String ipClientAddress = "N/A";
    private static String loginUserCode = "N/A";
    private static String applicationCode = "F0-Management";
    private static String ipPortCurrentNode;

    private static String toJsonString(Object object) {
        if (object == null) {
            return "";
        }
        if (object instanceof String) {
            return (String) object;
        }
        return gson.toJson(object);
    }

    public static void setLogger(Logger logger) {
        LoggingUtils.logger = logger;
    }

    public static void logVTMDebug(String message) {
        logger.debug(message);
    }

    public static void logVTMDebug(String format, Object... arguments) {
        logger.debug(format, arguments);
    }

    public static void logVTMTrace(String message) {
        logger.trace(message);
    }

    public static void logVTMTrace(Object object) {
        logger.trace(toJsonString(object));
    }

    public static void logVTMTrace(String format, Object... arguments) {
        logger.trace(format, arguments);
    }

    public static void logVTMError(String message) {
        logger.error(message);
    }

    public static void logVTMError(String format, Object... arguments) {
        logger.error(format, arguments);
    }

    public static void logVTMWarn(String message) {
        logger.warn(message);
    }

    public static void logVTMInfo(String message) {
        logger.info(message);
    }

    public static void setLoginUserCode(String loginUserCode) {
        LoggingUtils.loginUserCode = loginUserCode;
    }

    public static void setIPClientAddress(String ipClientAddress) {
        LoggingUtils.ipClientAddress = ipClientAddress;
    }

    public static void setFunctionCode(String functionCode) {
        LoggingUtils.functionCode = functionCode;
    }

    public static void setFunctionType(String funcType) {
        LoggingUtils.funcType = funcType;
    }

    public static String getIpPortCurrentNode() {
        return ipPortCurrentNode;
    }

    public static void setIpPortCurrentNode(String ipPortCurrentNode) {
        LoggingUtils.ipPortCurrentNode = ipPortCurrentNode;
    }

    public static void logVTMException(Exception exception) {
		/*StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("[EXCEPTION]\n");
		messageBuilder.append("Message: ").append(exception.getMessage()).append("\n");
		messageBuilder.append("StackTrace: ").append(ExceptionUtils.getStackTrace(exception));
		logger.error(messageBuilder.toString());*/

        StringBuilder sb = new StringBuilder();
        sb.append("VTMAPS").append("||"); /** Ma ung dung */
        sb.append("N/A").append("||"); /** thread_id */
        sb.append(DateUtility.format(new Date())).append("||");  /** start_time */
        sb.append(DateUtility.format(new Date())).append("||"); /** end_time */
        sb.append(loginUserCode).append("||"); /** id user login */
        sb.append(ipClientAddress).append("||");  /** ip_address  */
        sb.append(functionCode).append("||"); /** function_code  */
        sb.append(funcType).append("||");  /** action_type  */
        sb.append("N/A").append("||");  /** object_id  */
        if (exception != null) {   /** content  */
            sb.append(ExceptionUtils.getStackTrace(exception));
        }

        logger.error(sb.toString());
    }

    public static void logVTMException(LoggingInfo logInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(applicationCode).append("||"); /** Ma ung dung */
        sb.append(functionCode).append("||"); /** service code */
        sb.append(logInfo.getSessionID()).append("||"); /** session_id */
        sb.append(logInfo.getIpPortParentNode()).append("||");
        sb.append(ipPortCurrentNode).append("||");
        sb.append(logInfo.getRequestContent()).append("||");
        sb.append(logInfo.getResponseContent()).append("||");

        sb.append(DateUtility.format(logInfo.getStartTime(), DATETIME_FORMAT)).append("||");  /** start_time */
        sb.append(DateUtility.format(logInfo.getEndTime(), DATETIME_FORMAT)).append("||"); /** end_time */
        sb.append(logInfo.getDuration()).append("||"); // Duration
        sb.append(logInfo.getErrorCode()).append("||");
        sb.append(logInfo.getErrorDescription()).append("||"); /** content  */
        sb.append(logInfo.getTransactionStatus()).append("||");
        sb.append(logInfo.getActionName()).append("||");
        sb.append(logInfo.getUserName()).append("||");
        sb.append(logInfo.getAccount());

        logger.error(sb.toString());
    }

    public static void logVTMExceptionDebug(Exception exception) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("[EXCEPTION]\n");
        messageBuilder.append("Message: ").append(exception.getMessage()).append("\n");
        messageBuilder.append("StackTrace: ").append(ExceptionUtils.getStackTrace(exception));
        logger.debug(messageBuilder.toString());
    }
}
