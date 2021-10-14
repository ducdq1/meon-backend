/*    */ package com.viettel.etc.xlibrary.core.entities;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonInclude;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonInclude(JsonInclude.Include.NON_NULL)
/*    */ public class ResponseEntity
/*    */ {
/*    */   Object data;
/*    */   MessEntity mess;
/*    */   
/*    */   public Object getData() {
/* 20 */     return this.data;
/*    */   }
/*    */   
/*    */   public void setData(Object data) {
/* 24 */     this.data = data;
/*    */   }
/*    */   
/*    */   public MessEntity getMess() {
/* 28 */     return this.mess;
/*    */   }
/*    */   
/*    */   public void setMess(MessEntity mess) {
/* 32 */     this.mess = mess;
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\entities\ResponseEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */