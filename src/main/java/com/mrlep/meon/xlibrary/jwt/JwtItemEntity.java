/*    */ package com.mrlep.meon.xlibrary.jwt;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JwtItemEntity
/*    */ {
/*    */   String kid;
/*    */   String kty;
/*    */   String alg;
/*    */   List<String> x5c;
/*    */   
/*    */   public String getKid() {
/* 20 */     return this.kid;
/*    */   }
/*    */   
/*    */   public void setKid(String kid) {
/* 24 */     this.kid = kid;
/*    */   }
/*    */   
/*    */   public String getKty() {
/* 28 */     return this.kty;
/*    */   }
/*    */   
/*    */   public void setKty(String kty) {
/* 32 */     this.kty = kty;
/*    */   }
/*    */   
/*    */   public String getAlg() {
/* 36 */     return this.alg;
/*    */   }
/*    */   
/*    */   public void setAlg(String alg) {
/* 40 */     this.alg = alg;
/*    */   }
/*    */   
/*    */   public List<String> getX5c() {
/* 44 */     return this.x5c;
/*    */   }
/*    */   
/*    */   public void setX5c(List<String> x5c) {
/* 48 */     this.x5c = x5c;
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\jwt\JwtItemEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */