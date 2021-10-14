/*    */ package com.viettel.etc.xlibrary.core.config;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.security.core.annotation.AuthenticationPrincipal;
/*    */ import org.springframework.web.bind.annotation.RestController;
/*    */ import springfox.documentation.builders.ParameterBuilder;
/*    */ import springfox.documentation.builders.PathSelectors;
/*    */ import springfox.documentation.builders.RequestHandlerSelectors;
/*    */ import springfox.documentation.schema.ModelRef;
/*    */ import springfox.documentation.schema.ModelReference;
/*    */ import springfox.documentation.service.Parameter;
/*    */ import springfox.documentation.spi.DocumentationType;
/*    */ import springfox.documentation.spring.web.plugins.Docket;
/*    */ import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ @EnableSwagger2
/*    */ public class Swagger2Config
/*    */ {
/*    */   @Bean
/*    */   public Docket api() {
/* 32 */     ParameterBuilder aParameterBuilder = new ParameterBuilder();
/* 33 */     aParameterBuilder.name("Authorization")
/* 34 */       .modelRef((ModelReference)new ModelRef("string"))
/* 35 */       .parameterType("header")
/* 36 */       .defaultValue("Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VybmFtZVwiOlwiZGF0bnY1XCIsXCJwYXNzd29yZFwiOlwiMTIzXCIsXCJpc1ZlcmlmeVRva2VuXCI6ZmFsc2V9IiwiZXhwIjoxNTkxNzE5ODAwLCJpYXQiOjE1OTE3MDE4MDB9.0VWhBpKlo-8_HxnSwxiOK-0Z5dwPXEffMKyXwFe4hQs")
/* 37 */       .required(true)
/* 38 */       .build();
/* 39 */     List<Parameter> aParameters = new ArrayList<>();
/* 40 */     aParameters.add(aParameterBuilder.build());
/* 41 */     return (new Docket(DocumentationType.SWAGGER_2))
/* 42 */       .ignoredParameterTypes(new Class[] { AuthenticationPrincipal.class
/* 43 */         }).select()
/* 44 */       .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
/* 45 */       .paths(PathSelectors.regex("/.*"))
/* 46 */       .build()
/* 47 */       .globalOperationParameters(aParameters);
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\Swagger2Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */