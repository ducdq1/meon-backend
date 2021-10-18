/*    */ package com.mrlep.meon.xlibrary.core.constants;
/*    */ 
/*    */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ByteArraySerializer
/*    */   extends JsonSerializer<byte[]>
/*    */ {
/*    */   public void serialize(byte[] bytes, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
/* 24 */     jgen.writeStartArray();
/*    */     
/* 26 */     for (byte b : bytes) {
/* 27 */       jgen.writeNumber(unsignedToBytes(b));
/*    */     }
/*    */     
/* 30 */     jgen.writeEndArray();
/*    */   }
/*    */ 
/*    */   
/*    */   private static int unsignedToBytes(byte b) {
/* 35 */     return b & 0xFF;
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\constants\ByteArraySerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */