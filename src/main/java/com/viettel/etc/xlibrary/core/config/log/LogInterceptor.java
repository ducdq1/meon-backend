/*    */ package com.viettel.etc.xlibrary.core.config.log;
/*    */ 
/*    */ import com.viettel.etc.xlibrary.core.config.log.service.LoggingService;
/*    */ import javax.servlet.DispatcherType;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.http.HttpMethod;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class LogInterceptor
/*    */   implements HandlerInterceptor
/*    */ {
/*    */   @Autowired
/*    */   LoggingService loggingService;
/*    */   
/*    */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
/* 24 */     if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && request.getMethod().equals(HttpMethod.GET.name())) {
/* 25 */       this.loggingService.logRequest(request, null);
/*    */     }
/* 27 */     return true;
/*    */   }
/*    */   
/*    */   public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {}
/*    */   
/*    */   public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\log\LogInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */