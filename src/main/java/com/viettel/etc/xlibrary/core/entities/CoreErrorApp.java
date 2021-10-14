/*    */ package com.viettel.etc.xlibrary.core.entities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum CoreErrorApp
/*    */ {
/* 12 */   SUCCESS(1, "Thành công"),
/* 13 */   ERR_LOGIN(2, "Lỗi login"),
/* 14 */   NOPERMITION(3, "Không có quyền truy cập hệ thống."),
/* 15 */   ERR_NOSESSION(4, "Lỗi Phiên đăng nhập"),
/* 16 */   DATAEMTY(5, "Không có dữ liệu thỏa mãn");
/*    */   
/*    */   private final int code;
/*    */   
/*    */   private final String description;
/*    */   
/*    */   CoreErrorApp(int code, String description) {
/* 23 */     this.code = code;
/* 24 */     this.description = description;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 28 */     return this.description;
/*    */   }
/*    */   
/*    */   public int getCode() {
/* 32 */     return this.code;
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\entities\CoreErrorApp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */