/*    */ package com.viettel.etc.xlibrary.core.config;
/*    */ 
/*    */ import com.viettel.etc.xlibrary.core.config.log.LogInterceptor;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.transaction.annotation.EnableTransactionManagement;
/*    */ import org.springframework.web.servlet.HandlerInterceptor;
/*    */ import org.springframework.web.servlet.config.annotation.CorsRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
/*    */ import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/*    */ import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ @EnableWebMvc
/*    */ @EnableTransactionManagement
/*    */ public class WebMvcConfig
/*    */   implements WebMvcConfigurer
/*    */ {
/*    */   @Autowired
/*    */   LogInterceptor logInterceptor;
/*    */   
/*    */   public void addResourceHandlers(ResourceHandlerRegistry registry) {
/* 29 */     registry.addResourceHandler(new String[] { "swagger-ui.html"
/* 30 */         }).addResourceLocations(new String[] { "classpath:/META-INF/resources/" });
/* 31 */     registry.addResourceHandler(new String[] { "/webjars/*"
/* 32 */         }).addResourceLocations(new String[] { "classpath:/META-INF/resources/webjars/" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
/* 37 */     configurer.enable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInterceptors(InterceptorRegistry registry) {
/* 42 */     registry.addInterceptor((HandlerInterceptor)this.logInterceptor);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addCorsMappings(CorsRegistry registry) {
/* 47 */      registry.addMapping("/**").allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"});
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\WebMvcConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */