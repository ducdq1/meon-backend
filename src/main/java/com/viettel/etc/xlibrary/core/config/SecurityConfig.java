/*     */ package com.viettel.etc.xlibrary.core.config;
/*     */ import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
/*     */ import org.keycloak.adapters.KeycloakConfigResolver;
/*     */ import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
/*     */ import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
/*     */ import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
/*     */ import org.springframework.context.annotation.Bean;
/*     */ import org.springframework.context.annotation.Configuration;
/*     */ import org.springframework.http.HttpMethod;
/*     */ import org.springframework.security.authentication.AuthenticationManager;
/*     */ import org.springframework.security.authentication.AuthenticationProvider;
/*     */ import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
/*     */ import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
/*     */ import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*     */ import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*     */ import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
/*     */ import org.springframework.security.config.http.SessionCreationPolicy;
/*     */ import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
/*     */ import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
/*     */ import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/*     */ import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
/*     */ import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
/*     */ 
/*     */ //@Configuration
/*     */ //@EnableWebSecurity
/*     */ //@EnableGlobalMethodSecurity(jsr250Enabled = true)
/*     */ public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
/*     */   //@Bean
/*     */   public AuthenticationManager authenticationManagerBean() throws Exception {
/*  30 */     return super.authenticationManagerBean();
/*     */   }
/*     */ 
/*     */   
/*     */   //@Bean
/*     */   public AuthTokenFilter authenticationJwtTokenFilter() {
/*  36 */     return new AuthTokenFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configure(HttpSecurity http) throws Exception {
/*  42 */     ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)((HttpSecurity)((HttpSecurity)http.cors().and())
/*  43 */       .httpBasic().disable())
/*  44 */       .csrf().disable())
/*  45 */       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
/*  46 */       .and())
/*  47 */       .authorizeRequests()
/*  48 */       .antMatchers(HttpMethod.GET, new String[] {
/*     */ 
/*     */           
/*     */           "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/webjars/**", "/**", "favicon.ico"
/*     */ 
/*     */ 
/*     */         
/*  55 */         })).permitAll();
/*     */     
/* 106 */     ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().anyRequest()).authenticated();
/*     */   }
/*     */   
/*     */
/*     */ 
/*     */   
/*     */  // @Bean
/*     */   protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
/* 121 */     return (SessionAuthenticationStrategy)new NullAuthenticatedSessionStrategy();
/*     */   }
/*     */   
/*     */   //@Bean
/*     */   public KeycloakConfigResolver KeycloakConfigResolver() {
/* 126 */     return (KeycloakConfigResolver)new KeycloakSpringBootConfigResolver();
/*     */   }
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\SecurityConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */