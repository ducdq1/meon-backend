/*    */ package com.viettel.etc.xlibrary.core.config.log;
/*    */ 
/*    */ import com.viettel.etc.xlibrary.core.config.log.service.LoggingService;
/*    */ import java.lang.reflect.Type;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.core.MethodParameter;
/*    */ import org.springframework.http.HttpInputMessage;
/*    */ import org.springframework.http.converter.HttpMessageConverter;
/*    */ import org.springframework.web.bind.annotation.ControllerAdvice;
/*    */ import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ControllerAdvice
/*    */ public class CustomRequestBodyAdviceAdapter
/*    */   extends RequestBodyAdviceAdapter
/*    */ {
/*    */   @Autowired
/*    */   LoggingService loggingService;
/*    */   @Autowired
/*    */   HttpServletRequest httpServletRequest;
/*    */   
/*    */   public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
/* 34 */     this.loggingService.logRequest(this.httpServletRequest, body);
/*    */     
/* 36 */     return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\log\CustomRequestBodyAdviceAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */