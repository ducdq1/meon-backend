/*     */ package com.mrlep.meon.xlibrary.core.config.log.service;
/*     */ 
/*     */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mrlep.meon.xlibrary.core.constants.FunctionCommon;
import com.mrlep.meon.xlibrary.core.entities.UserSystemEntity;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.*;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class LoggingServiceImpl
/*     */   implements LoggingService
/*     */ {
/*  39 */   public static final String IPPORTSERVICE = getIpAddressAndPort_q();
/*  40 */   private static final Logger logger = Logger.getLogger(LoggingServiceImpl.class);
/*  41 */   private static final Logger KPI_LOGGER = Logger.getLogger("kpiLogger");
/*     */   
/*     */   public static final String AUTHORIZATION_HEADER = "Authorization";
/*     */   private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS";
/*     */   
/*     */   public void logRequest(HttpServletRequest httpServletRequest, Object body) {
/*  47 */     Map<String, String> parameters = buildParametersMap(httpServletRequest);
/*  48 */     long startTime = System.currentTimeMillis();
/*  49 */     httpServletRequest.setAttribute("startDate", new Date());
/*  50 */     httpServletRequest.setAttribute("startTime", Long.valueOf(startTime));
/*     */ 
/*     */     
/*  53 */     StringBuilder strLogs = new StringBuilder();
/*  54 */     String strCodeApp = "SERVICEAPPLICATION_START";
/*  55 */     if (FunctionCommon.getPropertiesValue("app.code").trim().length() > 0) {
/*  56 */       strCodeApp = FunctionCommon.getPropertiesValue("app.code") + "_START";
/*     */     }
/*  58 */     strLogs.append(strCodeApp).append("|");
/*     */     
/*  60 */     String strMethod = httpServletRequest.getMethod();
/*  61 */     String strBasePath = "";
/*  62 */     if (FunctionCommon.getPropertiesValue("app.basepath").trim().length() > 0) {
/*  63 */       strBasePath = FunctionCommon.getPropertiesValue("app.basepath").trim();
/*     */     }
/*  65 */     strLogs.append(strBasePath);
/*  66 */     strLogs.append(httpServletRequest.getRequestURI()).append("(");
/*  67 */     strLogs.append(strMethod).append(")|");
/*     */     
/*  69 */     boolean deviceHeaderExists = (httpServletRequest.getHeader("transactionTime") != null);
/*  70 */     if (deviceHeaderExists) {
/*  71 */       strLogs.append(httpServletRequest.getHeader("transactionTime")).append("|");
/*     */     }
/*     */     
/*  74 */     String strClientRemote = fetchClientIpAddr(httpServletRequest);
/*  75 */     strLogs.append(strClientRemote).append("|");
/*     */     
/*  77 */     strLogs.append(IPPORTSERVICE.replace("/", "")).append("|");
/*     */ 
/*     */     
/*  80 */     String strParams = "";
/*  81 */     if (!parameters.isEmpty()) {
/*  82 */       strParams = parameters.toString() + ";";
/*     */     }
/*  84 */     if (body != null) {
/*     */       try {
/*  86 */         ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
/*  87 */         String json = ow.writeValueAsString(body);
/*  88 */         strParams = strParams + json.replace("\n", "").replace("\r", "").trim().replaceAll(" +", " ");
/*  89 */       } catch (JsonProcessingException ex) {
/*  90 */         logger.error(ex);
/*     */       } 
/*     */     }
/*     */     
/*  94 */     String[] arrString = strParams.split("\"");
/*  95 */     if (arrString.length > 0) {
/*  96 */       for (String string : arrString) {
/*  97 */         if (string != null && string.trim().length() > 200) {
/*  98 */           boolean isBase64 = Base64.isBase64(string.getBytes());
/*  99 */           if (isBase64 || string.trim().length() > 500) {
/* 100 */             strParams = strParams.replace(string, "REPLACE CHUOI BASE 64 File");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 107 */     httpServletRequest.setAttribute("requestParams", strParams);
/* 108 */     strLogs.append(strParams).append("|");
/*     */     
/* 110 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
/* 111 */     String strStartTime = sdf.format(new Date());
/* 112 */     strLogs.append(strStartTime).append("|");
/*     */     
/* 114 */     String jwt = resolveToken(httpServletRequest);
/* 115 */     UserSystemEntity authenEntity = null;
/* 116 */     if (jwt != null && jwt.trim().length() > 0) {
/* 117 */       authenEntity = FunctionCommon.getUserInforFromToken(jwt);
/*     */     }
/* 119 */     if (authenEntity != null) {
/* 120 */       strLogs.append(authenEntity.getPreferred_username()).append("|");
/*     */     }
/* 122 */     KPI_LOGGER.info(strLogs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
/*     */   }
/*     */   
/*     */   private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
/* 215 */     Map<String, String> resultMap = new HashMap<>();
/* 216 */     Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
/*     */     
/* 218 */     while (parameterNames.hasMoreElements()) {
/* 219 */       String key = parameterNames.nextElement();
/* 220 */       String value = httpServletRequest.getParameter(key);
/* 221 */       resultMap.put(key, value);
/*     */     } 
/*     */     
/* 224 */     return resultMap;
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<String, String> buildHeadersMap(HttpServletResponse response) {
/* 229 */     Map<String, String> map = new HashMap<>();
/*     */     
/* 231 */     Collection<String> headerNames = response.getHeaderNames();
/* 232 */     for (String header : headerNames) {
/* 233 */       map.put(header, response.getHeader(header));
/*     */     }
/*     */     
/* 236 */     return map;
/*     */   }
/*     */   
/*     */   static String displayInterfaceInformation(NetworkInterface netint) {
/* 240 */     StringBuilder strIp = new StringBuilder();
/* 241 */     Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
/* 242 */     for (InetAddress inetAddress : Collections.<InetAddress>list(inetAddresses)) {
/* 243 */       strIp.append("-").append(inetAddress);
/*     */     }
/* 245 */     return strIp.toString();
/*     */   }
/*     */   protected String fetchClientIpAddr(HttpServletRequest request) {
/* 248 */     String ip = Optional.<String>ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
/* 249 */     if (ip.equals("0:0:0:0:0:0:0:1")) {
/* 250 */       ip = "127.0.0.1";
/*     */     }
/* 252 */     ip = ip.replace(",", "-");
/* 253 */     return ip;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String resolveToken(HttpServletRequest request) {
/* 263 */     String bearerToken = request.getHeader("Authorization");
/* 264 */     if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
/* 265 */       return bearerToken.substring(7);
/*     */     }
/* 267 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getIpAddressAndPort_q() {
/* 276 */     String strIpServer = "";
/*     */     try {
/* 278 */       MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
/*     */       
/* 280 */       Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"), 
/* 281 */           Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
/*     */       
/* 283 */       strIpServer = InetAddress.getLocalHost().getHostAddress();
/* 284 */       if (objectNames != null && objectNames.size() > 0) {
/* 285 */         String port = ((ObjectName)objectNames.iterator().next()).getKeyProperty("port");
/* 286 */         strIpServer = strIpServer + ":" + port;
/*     */       } 
/* 288 */     } catch (MalformedObjectNameException|java.net.UnknownHostException ex) {
/* 289 */       logger.error(ex);
/*     */     } 
/* 291 */     return strIpServer;
/*     */   }
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\log\service\LoggingServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */