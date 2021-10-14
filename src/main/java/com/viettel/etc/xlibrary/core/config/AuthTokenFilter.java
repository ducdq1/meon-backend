/*     */ package com.viettel.etc.xlibrary.core.config;
/*     */ 
/*     */ import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
/*     */ import com.viettel.etc.xlibrary.core.entities.UserSystemEntity;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.FilterChain;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/*     */ import org.springframework.security.core.Authentication;
/*     */ import org.springframework.security.core.GrantedAuthority;
/*     */ import org.springframework.security.core.authority.SimpleGrantedAuthority;
/*     */ import org.springframework.security.core.context.SecurityContextHolder;
/*     */ import org.springframework.util.StringUtils;
/*     */ import org.springframework.web.filter.GenericFilterBean;
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
/*     */ public class AuthTokenFilter
/*     */   extends GenericFilterBean
/*     */ {
/*     */   public static final String AUTHORIZATION_HEADER = "Authorization";
/*     */   
/*     */   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain fc) throws IOException, ServletException {
/*  41 */     HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
/*  42 */     String jwt = resolveToken(httpServletRequest);
/*  43 */     if (jwt != null) {
/*  44 */       Authentication result = authenticate(jwt);
/*  45 */       if (result != null) {
/*  46 */         SecurityContextHolder.getContext().setAuthentication(result);
/*     */       }
/*     */     } 
/*  49 */     HttpServletResponse response = (HttpServletResponse)servletResponse;
/*  50 */     String strAccept = FunctionCommon.getPropertiesValue("security.access.url");
/*     */ 
/*     */     
/*  53 */     String originHeader = httpServletRequest.getHeader("Origin");
/*     */     
/*  55 */     if (strAccept.trim().length() <= 0) {
/*  56 */       response.setHeader("Access-Control-Allow-Origin", "*");
/*     */     } else {
/*  58 */       String[] arrURlAc = strAccept.replaceAll("\\s", "").split(";");
/*     */       
/*  60 */       Set<String> allowedOrigins = new HashSet<>(Arrays.asList(arrURlAc));
/*  61 */       if (allowedOrigins.contains(originHeader)) {
/*  62 */         response.setHeader("Access-Control-Allow-Origin", originHeader);
/*     */       } else {
/*  64 */         System.out.println("Khong duoc cau hinh Access-Control-Allow-Origin:" + originHeader);
/*     */       } 
/*     */     } 
/*  67 */     response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
/*  68 */     response.setHeader("Access-Control-Allow-Credentials", "true");
/*  69 */     response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
/*  70 */     fc.doFilter(servletRequest, (ServletResponse)response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String resolveToken(HttpServletRequest request) {
/*  80 */     String bearerToken = request.getHeader("Authorization");
/*  81 */     if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
/*  82 */       return bearerToken.substring(7);
/*     */     }
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Authentication authenticate(String jwt) {
/*  94 */     UserSystemEntity authenEntity = FunctionCommon.getUserInforFromToken(jwt);
/*  95 */     if (authenEntity != null) {
/*  96 */       List<GrantedAuthority> grantedAuths = new ArrayList<>();
/*  97 */       if (authenEntity.getRealm_access() != null && authenEntity.getRealm_access().getRoles() != null) {
/*  98 */         List<String> listRole = authenEntity.getRealm_access().getRoles();
/*  99 */         if (listRole != null && listRole.size() > 0) {
/* 100 */           for (String roleName : listRole) {
/* 101 */             SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
/* 102 */             grantedAuths.add(simpleGrantedAuthority);
/*     */           } 
/*     */         }
/*     */       } 
/* 106 */       return (Authentication)new UsernamePasswordAuthenticationToken(authenEntity, "", grantedAuths);
/*     */     } 
/* 108 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\AuthTokenFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */