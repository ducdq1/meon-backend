/*     */ package com.mrlep.meon.xlibrary.core.config;
/*     */

/*     */

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

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
///*     */ @Configuration
///*     */ @EnableWebSecurity
///*     */ @EnableGlobalMethodSecurity(jsr250Enabled = true)
/*     */ public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebSecurityConfigurer<WebSecurity> {
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
/*     */           "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/webjars/**", "/**", "favicon.ico"
/*  55 */         })).permitAll();
/*     */
///* 106 */     ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().anyRequest()).authenticated();
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
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\SecurityConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */