/*     */ package com.viettel.etc.xlibrary.jwt;
/*     */ 
/*     */ import com.auth0.jwt.JWT;
/*     */ import com.auth0.jwt.JWTVerifier;
/*     */ import com.auth0.jwt.algorithms.Algorithm;
/*     */ import com.auth0.jwt.exceptions.JWTVerificationException;
/*     */ import com.viettel.etc.xlibrary.core.constants.ConnectServer;
/*     */ import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
/*     */ import com.viettel.etc.xlibrary.core.entities.UserSystemEntity;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.KeyFactory;
/*     */ import java.security.cert.CertificateFactory;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.security.interfaces.RSAPublicKey;
/*     */ import java.security.spec.X509EncodedKeySpec;
/*     */ import org.apache.commons.codec.binary.Base64;
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
/*     */ public class FunctionJwt
/*     */ {
/*     */   public static void main(String[] args) {
/*  37 */     String jwtToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJiOEZHOURyWm9fOVJ6X1NCWFRJM0ZtN2hyT3Z4S0JPVklsLXU3d2dQbmVzIn0.eyJleHAiOjE1OTIyMjMzOTgsImlhdCI6MTU5MjE5MzM5OCwianRpIjoiNjZhZmY2ZjQtYWNhMi00ZTZlLTk1NzctN2M3MWZmYmIxOTg4IiwiaXNzIjoiaHR0cDovLzEwLjYwLjE1Ni4xNTk6ODA4MC9hdXRoL3JlYWxtcy9ldGMtaW50ZXJuYWwiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMzFiZGY4MjYtMTQ2OS00ZTkwLWIwM2UtMTk4NjE1NDlhZDJiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiY3JtIiwic2Vzc2lvbl9zdGF0ZSI6IjIyMWI3ZGZkLTg3NDQtNDA0NC1iZmZlLTlmYzhmNWRiMzBiMSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovLzEwLjYwLjE1Ni4xNTg6OTM5MyIsImh0dHA6Ly9sb2NhbGhvc3Q6NDIwMCIsImh0dHA6Ly8xMC42MC4xNTYuMTU4Ojk1OTUiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX0sImNybSI6eyJyb2xlcyI6WyJVc2VyIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ0ZXN0dmlldyJ9.hmEV_NWOxbAwrqahxTZ72e-mg2W-dbQ71eTjxRs8XgcmytTSMgm6VfOqjCA8Gp0VJCz3T8GU6jpT58Y91R8QSB7rNFX1uD6SV_dCLwTQd00Exb4E1Tp_sDOTdTwkvRqsmryJQKPzF1tK8jyQnXCsk5FZ6g5DtHbjFVNkwTlmVgLDkZ9oWBWPir3r_Tpup_OKd0Vuh5f7G1Enxkj6kx_oQRwo-v5m0a4htpbqM_MCv0xygNEg1jbcNbrByYSSCYbkvOYhJMydFz6R2FwhUJf687zOeZrrD3Ua2n3v2Mca5HGWW5sDpNGcEogo54lPCcHi9ybCxraxCYb74n1havXWAQ";
/*  38 */     getDataUserFromToken(jwtToken);
/*  39 */     boolean value = verifyToken(jwtToken);
/*  40 */     System.out.println("strResultData=" + value);
/*  41 */     String[] split_string = jwtToken.split("\\.");
/*  42 */     String base64EncodedBody = split_string[1];
/*  43 */     Base64 base64Url = new Base64(true);
/*  44 */     String body = new String(base64Url.decode(base64EncodedBody));
/*     */     
/*  46 */     UserSystemEntity authen = (UserSystemEntity)FunctionCommon.convertJsonToObject(body, UserSystemEntity.class);
/*  47 */     System.out.println("authen=" + authen);
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
/*     */   public static UserSystemEntity getDataUserFromToken(String jwtToken) {
/*     */     try {
/*  60 */       String[] split_string = jwtToken.split("\\.");
/*  61 */       if (split_string.length <= 0) {
/*  62 */         return null;
/*     */       }
/*  64 */       String base64EncodedBody = split_string[1];
/*  65 */       Base64 base64Url = new Base64(true);
/*  66 */       String body = new String(base64Url.decode(base64EncodedBody));
/*  67 */       UserSystemEntity authen = (UserSystemEntity)FunctionCommon.convertJsonToObject(body, UserSystemEntity.class);
/*  68 */       if (authen != null) {
/*  69 */         authen.setIsVerifyToken(true);
/*     */       }
/*  71 */       return authen;
/*  72 */     } catch (Exception e) {
/*  73 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean verifyToken(String token) {
/*     */     try {
/*  86 */       String valueConfig = FunctionCommon.getPropertiesValue("keycloakverify.url");
/*  87 */       String strResultData = ConnectServer.sendRequestToServerGet(valueConfig);
/*  88 */       JwtEntity listItemEntity = null;
/*  89 */       if (strResultData != null && strResultData.trim().length() > 5) {
/*  90 */         listItemEntity = (JwtEntity)FunctionCommon.convertJsonToObject(strResultData, JwtEntity.class);
/*     */       }
/*  92 */       if (listItemEntity == null || listItemEntity.getKeys() == null || listItemEntity
/*  93 */         .getKeys().size() < 1 || ((JwtItemEntity)listItemEntity
/*  94 */         .getKeys().get(0)).getX5c().size() <= 0) {
/*  95 */         return false;
/*     */       }
/*  97 */       JwtItemEntity itemEntity = listItemEntity.getKeys().get(0);
/*  98 */       String strDataCert = itemEntity.getX5c().get(0);
/*  99 */       RSAPublicKey publicKey = getStringCert(strDataCert);
/* 100 */       Algorithm algorithm = Algorithm.RSA256(publicKey, null);
/* 101 */       JWTVerifier verifier = JWT.require(algorithm).build();
/* 102 */       verifier.verify(token);
/* 103 */       return true;
/* 104 */     } catch (JWTVerificationException|IllegalArgumentException e) {
/* 105 */       System.out.println("Exception in verifying " + e.toString());
/* 106 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static RSAPublicKey getPublicKeyFromString(String key) throws IOException, GeneralSecurityException {
/* 112 */     String publicKeyPEM = key;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
/* 118 */     publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
/*     */     
/* 120 */     byte[] encoded = Base64.decodeBase64(publicKeyPEM);
/* 121 */     KeyFactory kf = KeyFactory.getInstance("RSA");
/* 122 */     RSAPublicKey pubKey = (RSAPublicKey)kf.generatePublic(new X509EncodedKeySpec(encoded));
/*     */     
/* 124 */     return pubKey;
/*     */   }
/*     */   
/*     */   private static RSAPublicKey getStringCert(String certString) {
/*     */     try {
/* 129 */       String cert = certString;
/* 130 */       cert = cert.replace("-----BEGIN CERTIFICATE-----\n", "");
/* 131 */       cert = cert.replace("-----END CERTIFICATE-----\n", "");
/*     */ 
/*     */       
/* 134 */       byte[] encodedCert = cert.getBytes("UTF-8");
/* 135 */       byte[] decodedCert = Base64.decodeBase64(encodedCert);
/* 136 */       CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
/* 137 */       InputStream in = new ByteArrayInputStream(decodedCert);
/* 138 */       X509Certificate certificate = (X509Certificate)certFactory.generateCertificate(in);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 147 */       RSAPublicKey publicKey = (RSAPublicKey)certificate.getPublicKey();
/*     */       
/* 149 */       return publicKey;
/* 150 */     } catch (UnsupportedEncodingException|java.security.cert.CertificateException ex) {
/* 151 */       System.out.println(ex);
/*     */       
/* 153 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\jwt\FunctionJwt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */