/*    */ package com.viettel.etc.xlibrary.core.config.log;
/*    */ 
/*    */ import com.viettel.etc.xlibrary.core.config.log.service.LoggingService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.core.MethodParameter;
/*    */ import org.springframework.http.MediaType;
/*    */ import org.springframework.http.converter.HttpMessageConverter;
/*    */ import org.springframework.http.server.ServerHttpRequest;
/*    */ import org.springframework.http.server.ServerHttpResponse;
/*    */ import org.springframework.http.server.ServletServerHttpRequest;
/*    */ import org.springframework.http.server.ServletServerHttpResponse;
/*    */ import org.springframework.web.bind.annotation.ControllerAdvice;
/*    */ import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/*    */ 
/*    */ 
/*    */ @ControllerAdvice
/*    */ public class CustomResponseBodyAdviceAdapter
/*    */   implements ResponseBodyAdvice<Object>
/*    */ {
/*    */   @Autowired
/*    */   LoggingService loggingService;
/*    */   
/*    */   public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
/* 30 */     if (serverHttpRequest instanceof ServletServerHttpRequest && serverHttpResponse instanceof ServletServerHttpResponse) {
/* 31 */       this.loggingService.logResponse(((ServletServerHttpRequest)serverHttpRequest).getServletRequest(), ((ServletServerHttpResponse)serverHttpResponse)
/* 32 */           .getServletResponse(), o);
/*    */     }
/*    */     
/* 35 */     return o;
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\log\CustomResponseBodyAdviceAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */