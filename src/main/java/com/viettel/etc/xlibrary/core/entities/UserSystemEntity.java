/*     */ package com.viettel.etc.xlibrary.core.entities;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonInclude;
/*     */ import com.viettel.etc.xlibrary.jwt.JwtAccountEntity;
/*     */ import com.viettel.etc.xlibrary.jwt.JwtRoleEntity;
/*     */ 
/*     */ @JsonInclude(JsonInclude.Include.NON_NULL)
/*     */ public class UserSystemEntity {
/*     */   Long id;
/*     */   String username;
/*     */   String password;
/*     */   String name;
/*     */   String role;
/*     */   String baseRole;
/*     */   String strJwtToken;
/*     */   String orgName;
/*     */   Long exp;
/*     */   Long iat;
/*     */   Long auth_time;
/*     */   JwtRoleEntity realm_access;
/*     */   JwtAccountEntity resource_access;
/*     */   String preferred_username;
/*     */   boolean isVerifyToken;
/*     */   
/*     */   public Long getId() {
/*  26 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  30 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getUsername() {
/*  34 */     return this.username;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/*  38 */     this.username = username;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/*  42 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/*  46 */     this.password = password;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  50 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  54 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getRole() {
/*  58 */     return this.role;
/*     */   }
/*     */   
/*     */   public void setRole(String role) {
/*  62 */     this.role = role;
/*     */   }
/*     */   
/*     */   public String getBaseRole() {
/*  66 */     return this.baseRole;
/*     */   }
/*     */   
/*     */   public void setBaseRole(String baseRole) {
/*  70 */     this.baseRole = baseRole;
/*     */   }
/*     */   
/*     */   public String getStrJwtToken() {
/*  74 */     return this.strJwtToken;
/*     */   }
/*     */   
/*     */   public void setStrJwtToken(String strJwtToken) {
/*  78 */     this.strJwtToken = strJwtToken;
/*     */   }
/*     */   
/*     */   public String getOrgName() {
/*  82 */     return this.orgName;
/*     */   }
/*     */   
/*     */   public void setOrgName(String orgName) {
/*  86 */     this.orgName = orgName;
/*     */   }
/*     */   
/*     */   public Long getExp() {
/*  90 */     return this.exp;
/*     */   }
/*     */   
/*     */   public void setExp(Long exp) {
/*  94 */     this.exp = exp;
/*     */   }
/*     */   
/*     */   public Long getIat() {
/*  98 */     return this.iat;
/*     */   }
/*     */   
/*     */   public void setIat(Long iat) {
/* 102 */     this.iat = iat;
/*     */   }
/*     */   
/*     */   public Long getAuth_time() {
/* 106 */     return this.auth_time;
/*     */   }
/*     */   
/*     */   public void setAuth_time(Long auth_time) {
/* 110 */     this.auth_time = auth_time;
/*     */   }
/*     */   
/*     */   public JwtRoleEntity getRealm_access() {
/* 114 */     return this.realm_access;
/*     */   }
/*     */   
/*     */   public void setRealm_access(JwtRoleEntity realm_access) {
/* 118 */     this.realm_access = realm_access;
/*     */   }
/*     */   
/*     */   public JwtAccountEntity getResource_access() {
/* 122 */     return this.resource_access;
/*     */   }
/*     */   
/*     */   public void setResource_access(JwtAccountEntity resource_access) {
/* 126 */     this.resource_access = resource_access;
/*     */   }
/*     */   
/*     */   public String getPreferred_username() {
/* 130 */     return this.preferred_username;
/*     */   }
/*     */   
/*     */   public void setPreferred_username(String preferred_username) {
/* 134 */     this.preferred_username = preferred_username;
/*     */   }
/*     */   
/*     */   public boolean isIsVerifyToken() {
/* 138 */     return this.isVerifyToken;
/*     */   }
/*     */   
/*     */   public void setIsVerifyToken(boolean isVerifyToken) {
/* 142 */     this.isVerifyToken = isVerifyToken;
/*     */   }
/*     */   
/*     */   public Boolean userHasPermission() {
/* 146 */     return Boolean.valueOf((this.isVerifyToken && this.preferred_username != null && this.preferred_username.trim().length() > 0));
/*     */   }
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\entities\UserSystemEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */