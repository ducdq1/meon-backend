/*     */ package com.viettel.etc.xlibrary.core.constants;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.xml.bind.DatatypeConverter;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConnectServer
/*     */ {
/*  25 */   private static final Logger LOGGER = Logger.getLogger(ConnectServer.class);
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
/*     */   public static String sendRequestToServer(String urlServer, String strQuery, String strUserPass) {
/*  37 */     String strResult = ""; 
/*     */     try { StringBuffer response;
/*  39 */       URL url = new URL(urlServer);
/*     */       
/*  41 */       byte[] postDataBytes = strQuery.getBytes(StandardCharsets.UTF_8);
/*     */       
/*  43 */       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
/*  44 */       conn.setRequestMethod("POST");
/*  45 */       conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
/*     */       
/*  47 */       conn.setRequestProperty("Accept-Charset", "UTF-8");
/*  48 */       conn.setRequestProperty("Content-Length", 
/*  49 */           String.valueOf(postDataBytes.length));
/*     */       
/*  51 */       if (strUserPass != null && strUserPass.trim().length() > 0) {
/*  52 */         byte[] message = strUserPass.getBytes(StandardCharsets.UTF_8);
/*  53 */         String encoded = DatatypeConverter.printBase64Binary(message);
/*  54 */         conn.setRequestProperty("Authorization", "Basic " + encoded);
/*     */       } 
/*  56 */       conn.setDoOutput(true);
/*  57 */       conn.getOutputStream().write(postDataBytes);
/*  58 */       conn.setConnectTimeout(1000);
/*  59 */       int responseCode = conn.getResponseCode();
/*  60 */       switch (responseCode)
/*     */       
/*     */       { case 200:
/*  63 */           try (BufferedReader in = new BufferedReader(new InputStreamReader(conn
/*  64 */                   .getInputStream(), StandardCharsets.UTF_8))) {
/*     */             
/*  66 */             response = new StringBuffer(); String inputLine;
/*  67 */             while ((inputLine = in.readLine()) != null) {
/*  68 */               response.append(inputLine);
/*     */             }
/*     */           } 
/*  71 */           strResult = response.toString();
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
/*  83 */           return strResult;case 201: strResult = "1"; return strResult; }  strResult = String.valueOf(responseCode); } catch (IOException e) { LOGGER.error("sendRequestToServer", e); }  return strResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String sendRequestToServerGet(String urlServer) {
/*  93 */     String strResult = "";
/*  94 */     if (urlServer == null || urlServer.trim().length() <= 0)
/*  95 */       return strResult; 
/*     */     
/*     */     try { StringBuffer response;
/*  98 */       URL url = new URL(urlServer);
/*  99 */       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
/* 100 */       conn.setRequestMethod("GET");
/* 101 */       conn.setRequestProperty("Accept-Charset", "UTF-8");
/* 102 */       conn.setDoOutput(true);
/* 103 */       conn.setConnectTimeout(12000);
/* 104 */       int responseCode = conn.getResponseCode();
/* 105 */       switch (responseCode)
/*     */       
/*     */       { case 200:
/* 108 */           try (BufferedReader in = new BufferedReader(new InputStreamReader(conn
/* 109 */                   .getInputStream(), StandardCharsets.UTF_8))) {
/*     */             
/* 111 */             response = new StringBuffer(); String inputLine;
/* 112 */             while ((inputLine = in.readLine()) != null) {
/* 113 */               response.append(inputLine);
/*     */             }
/*     */           } 
/* 116 */           strResult = response.toString();
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
/* 128 */           return strResult;case 201: strResult = "1"; return strResult; }  strResult = ""; } catch (IOException e) { LOGGER.error("sendRequestToServer", e); }  return strResult;
/*     */   }
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
/*     */   public static Object getJsonDataRequest(String jsonServerResponse, boolean isCount) {
/*     */     try {
/* 142 */       JSONObject json = new JSONObject(jsonServerResponse);
/* 143 */       if (json.isNull("hits")) {
/* 144 */         return null;
/*     */       }
/* 146 */       JSONObject jsonhits = (JSONObject)json.get("hits");
/* 147 */       if (isCount) {
/*     */         
/* 149 */         if (!jsonhits.isNull("total")) {
/* 150 */           String count = jsonhits.getString("total");
/* 151 */           if (FunctionCommon.isNumeric(count)) {
/* 152 */             return Long.valueOf(count);
/*     */           }
/* 154 */           return Long.valueOf(0L);
/*     */         } 
/*     */         
/* 157 */         return Long.valueOf(0L);
/*     */       } 
/*     */       
/* 160 */       if (jsonhits.isNull("hits")) {
/* 161 */         return null;
/*     */       }
/* 163 */       return jsonhits.getJSONArray("hits");
/* 164 */     } catch (JSONException ex) {
/* 165 */       LOGGER.error("getJsonDataRequest", (Throwable)ex);
/*     */       
/* 167 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getJsonFromDataGet(String jsonServerResponse, Class<?> classOfT) {
/*     */     try {
/* 179 */       JSONObject json = new JSONObject(jsonServerResponse);
/* 180 */       if (json.isNull("_source")) {
/* 181 */         return null;
/*     */       }
/* 183 */       String jsonData = json.getString("_source");
/*     */       
/* 185 */       GsonBuilder builder = new GsonBuilder();
/* 186 */       Gson gson = builder.create();
/* 187 */       return gson.fromJson(jsonData, classOfT);
/* 188 */     } catch (JSONException ex) {
/* 189 */       LOGGER.error("getJsonDataRequest", (Throwable)ex);
/*     */       
/* 191 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> getArrHightlight(String strHightlight) {
/* 201 */     List<String> listResult = new ArrayList<>();
/* 202 */     Pattern pattern = Pattern.compile("<b>(.*?)</b>");
/* 203 */     Matcher matcher = pattern.matcher(strHightlight);
/* 204 */     boolean have = false;
/* 205 */     while (matcher.find()) {
/* 206 */       have = true;
/* 207 */       listResult.add(matcher.group(1));
/*     */     } 
/* 209 */     if (!have) {
/* 210 */       listResult = null;
/*     */     }
/* 212 */     return listResult;
/*     */   }
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\constants\ConnectServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */