/*    */ package com.viettel.etc.xlibrary.core.constants;
/*    */ 
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.stream.JsonReader;
/*    */ import com.google.gson.stream.JsonToken;
/*    */ import com.google.gson.stream.JsonWriter;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class GsonEmptyStringToNumber
/*    */ {
/*    */   public static class EmptyStringToNumberTypeAdapter
/*    */     extends TypeAdapter<Integer> {
/*    */     public void write(JsonWriter jsonWriter, Integer number) throws IOException {
/* 15 */       if (number == null) {
/* 16 */         jsonWriter.nullValue();
/*    */         return;
/*    */       } 
/* 19 */       jsonWriter.value(number);
/*    */     }
/*    */ 
/*    */     
/*    */     public Integer read(JsonReader jsonReader) throws IOException {
/* 24 */       if (jsonReader.peek() == JsonToken.NULL) {
/* 25 */         jsonReader.nextNull();
/* 26 */         return null;
/*    */       } 
/*    */       
/*    */       try {
/* 30 */         String value = jsonReader.nextString();
/* 31 */         if ("".equals(value)) {
/* 32 */           return Integer.valueOf(0);
/*    */         }
/* 34 */         return Integer.valueOf(value);
/* 35 */       } catch (NumberFormatException e) {
/* 36 */         throw new JsonSyntaxException(e);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public static class EmptyStringToDoubleTypeAdapter
/*    */     extends TypeAdapter<Double> {
/*    */     public void write(JsonWriter jsonWriter, Double number) throws IOException {
/* 44 */       if (number == null) {
/* 45 */         jsonWriter.nullValue();
/*    */         return;
/*    */       } 
/* 48 */       jsonWriter.value(number);
/*    */     }
/*    */ 
/*    */     
/*    */     public Double read(JsonReader jsonReader) throws IOException {
/* 53 */       if (jsonReader.peek() == JsonToken.NULL) {
/* 54 */         jsonReader.nextNull();
/* 55 */         return null;
/*    */       } 
/*    */       
/*    */       try {
/* 59 */         String value = jsonReader.nextString();
/* 60 */         if ("".equals(value)) {
/* 61 */           return Double.valueOf(0.0D);
/*    */         }
/* 63 */         return Double.valueOf(value);
/* 64 */       } catch (NumberFormatException e) {
/* 65 */         throw new JsonSyntaxException(e);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public static class EmptyStringToLongTypeAdapter
/*    */     extends TypeAdapter<Long> {
/*    */     public void write(JsonWriter jsonWriter, Long number) throws IOException {
/* 73 */       if (number == null) {
/* 74 */         jsonWriter.nullValue();
/*    */         return;
/*    */       } 
/* 77 */       jsonWriter.value(number);
/*    */     }
/*    */ 
/*    */     
/*    */     public Long read(JsonReader jsonReader) throws IOException {
/* 82 */       if (jsonReader.peek() == JsonToken.NULL) {
/* 83 */         jsonReader.nextNull();
/* 84 */         return null;
/*    */       } 
/*    */       
/*    */       try {
/* 88 */         String value = jsonReader.nextString();
/* 89 */         if ("".equals(value)) {
/* 90 */           return Long.valueOf(0L);
/*    */         }
/* 92 */         return Long.valueOf(value);
/* 93 */       } catch (NumberFormatException e) {
/* 94 */         throw new JsonSyntaxException(e);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\constants\GsonEmptyStringToNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */