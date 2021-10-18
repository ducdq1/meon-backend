/*     */ package com.mrlep.meon.xlibrary.core.repositories;
/*     */ 
/*     */

import com.mrlep.meon.xlibrary.core.constants.FunctionCommon;
import com.mrlep.meon.xlibrary.core.entities.ResultSelectEntity;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Repository
/*     */ @Transactional
/*     */ public class CommonDataBaseRepository
/*     */ {
/*     */   @PersistenceContext
/*     */   private EntityManager entityManager;
/*  36 */   private static final Logger LOGGER = Logger.getLogger(CommonDataBaseRepository.class);
/*     */ 
/*     */ 
/*     */   
/*     */   public List<? extends Object> getListData_Core(StringBuilder queryString, List<Object> arrParams, Integer startPage, Integer pageLoad) {
/*  41 */     Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
/*  42 */     int countParams = 1;
/*  43 */     if (arrParams != null) {
/*  44 */       for (Object arrParam : arrParams) {
/*  45 */         query.setParameter(countParams, arrParam);
/*  46 */         countParams++;
/*     */       } 
/*     */     }
/*  49 */     if (startPage != null && pageLoad != null) {
/*  50 */       query.setFirstResult(startPage.intValue()).setMaxResults(startPage.intValue() + pageLoad.intValue());
/*     */     }
/*  52 */     List<? extends Object> objectList = query.getResultList();
/*  53 */     this.entityManager.close();
/*  54 */     return objectList;
/*     */   }
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
/*     */   public List<? extends Object> getListData(StringBuilder queryString, List<Object> arrParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
/*     */     try {
/*  73 */       boolean databaseIsOracle = false;
/*     */       
/*     */       try {
/*  76 */         HikariDataSource hikariDataS = (HikariDataSource)this.entityManager.getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
/*  77 */         String strDriverName = hikariDataS.getDriverClassName().toLowerCase();
/*  78 */         if (strDriverName.contains("oracle"))
/*     */         {
/*  80 */           databaseIsOracle = true;
/*     */         }
/*  82 */       } catch (Exception e) {
/*  83 */         LOGGER.error(e);
/*     */       } 
/*  85 */       StringBuilder sqlPage = new StringBuilder();
/*  86 */       if (databaseIsOracle && startPage != null && pageLoad != null) {
/*     */         
/*  88 */         String strVersionOracle = FunctionCommon.getPropertiesValue("oracle.version");
/*  89 */         if (strVersionOracle.trim().equals("11")) {
/*  90 */           sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
/*  91 */           sqlPage.append(queryString.toString());
/*  92 */           sqlPage.append(" ) a ) ");
/*  93 */           sqlPage.append(String.format(" WHERE r__ > %d", new Object[] { startPage }));
/*  94 */           sqlPage.append(String.format(" and r__ <= %d", new Object[] { Integer.valueOf(startPage.intValue() + pageLoad.intValue()) }));
/*     */         } else {
/*  96 */           sqlPage.append(" SELECT * FROM ( ");
/*  97 */           sqlPage.append(queryString.toString());
/*  98 */           sqlPage.append(" ) a ");
/*  99 */           sqlPage.append(String.format("  OFFSET %d ROWS FETCH NEXT %d ROWS ONLY ", new Object[] { startPage, pageLoad }));
/*     */         } 
/*     */       } else {
/* 102 */         sqlPage = queryString;
/*     */       } 
/* 104 */       Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
/* 105 */       int countParams = 1;
/* 106 */       if (arrParams != null) {
/* 107 */         for (Object arrParam : arrParams) {
/* 108 */           query.setParameter(countParams, arrParam);
/* 109 */           countParams++;
/*     */         } 
/*     */       }
/* 112 */       if (startPage != null && pageLoad != null && !databaseIsOracle) {
/* 113 */         query.setFirstResult(startPage.intValue()).setMaxResults(pageLoad.intValue());
/*     */       }
/* 115 */       List objectList = query.getResultList();
/* 116 */       List<Object> listResult = FunctionCommon.convertToEntity(objectList, classOfT);
/* 117 */       this.entityManager.close();
/* 118 */       return listResult;
/* 119 */     } catch (Exception e) {
/* 120 */       LOGGER.error(e);
/* 121 */       return null;
/*     */     } 
/*     */   }
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
/*     */   public List getListDataOriginal(StringBuilder queryString, List<Object> arrParams, Integer startPage, Integer pageLoad) {
/*     */     try {
/* 136 */       Boolean databaseIsOracle = Boolean.valueOf(false);
/*     */       
/*     */       try {
/* 139 */         HikariDataSource hikariDataS = (HikariDataSource)this.entityManager.getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
/* 140 */         String strDriverName = hikariDataS.getDriverClassName().toLowerCase();
/* 141 */         if (strDriverName.contains("oracle"))
/*     */         {
/* 143 */           databaseIsOracle = Boolean.valueOf(true);
/*     */         }
/* 145 */       } catch (Exception e) {
/* 146 */         LOGGER.error(e);
/*     */       } 
/* 148 */       StringBuilder sqlPage = new StringBuilder();
/* 149 */       if (databaseIsOracle.booleanValue() && startPage != null && pageLoad != null) {
/*     */         
/* 151 */         String strVersionOracle = FunctionCommon.getPropertiesValue("oracle.version");
/* 152 */         if (strVersionOracle.trim().equals("11")) {
/* 153 */           sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
/* 154 */           sqlPage.append(queryString.toString());
/* 155 */           sqlPage.append(" ) a ) ");
/* 156 */           sqlPage.append(String.format(" WHERE r__ > %d", new Object[] { startPage }));
/* 157 */           sqlPage.append(String.format(" and r__ <= %d", new Object[] { Integer.valueOf(startPage.intValue() + pageLoad.intValue()) }));
/*     */         } else {
/* 159 */           sqlPage.append(" SELECT * FROM ( ");
/* 160 */           sqlPage.append(queryString.toString());
/* 161 */           sqlPage.append(" ) a ");
/* 162 */           sqlPage.append(String.format("  OFFSET %d ROWS FETCH NEXT %d ROWS ONLY ", new Object[] { startPage, pageLoad }));
/*     */         } 
/*     */       } else {
/* 165 */         sqlPage = queryString;
/*     */       } 
/* 167 */       Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
/* 168 */       int countParams = 1;
/* 169 */       if (arrParams != null) {
/* 170 */         for (Object arrParam : arrParams) {
/* 171 */           query.setParameter(countParams, arrParam);
/* 172 */           countParams++;
/*     */         } 
/*     */       }
/* 175 */       if (startPage != null && pageLoad != null && !databaseIsOracle.booleanValue()) {
/* 176 */         query.setFirstResult(startPage.intValue()).setMaxResults(pageLoad.intValue());
/*     */       }
/* 178 */       List objectList = query.getResultList();
/* 179 */       this.entityManager.close();
/* 180 */       return objectList;
/* 181 */     } catch (Exception e) {
/* 182 */       LOGGER.error(e);
/* 183 */       return null;
/*     */     } 
/*     */   }
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
/*     */   public Object getFirstData(StringBuilder queryString, List<Object> arrParams, Class<?> classOfT) {
/*     */     try {
/* 198 */       Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
/* 199 */       int countParams = 1;
/* 200 */       if (arrParams != null) {
/* 201 */         for (Object arrParam : arrParams) {
/* 202 */           query.setParameter(countParams, arrParam);
/* 203 */           countParams++;
/*     */         } 
/*     */       }
/* 206 */       query.setFirstResult(0).setMaxResults(1);
/* 207 */       List objectList = query.getResultList();
/* 208 */       List<Object> listResult = FunctionCommon.convertToEntity(objectList, classOfT);
/* 209 */       this.entityManager.close();
/* 210 */       return listResult;
/* 211 */     } catch (Exception e) {
/* 212 */       LOGGER.error(e);
/* 213 */       return null;
/*     */     } 
/*     */   }
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
/*     */   public List<? extends Object> getListData(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
/*     */     try {
/* 233 */       boolean databaseIsOracle = false;
/*     */       
/*     */       try {
/* 236 */         HikariDataSource hikariDataS = (HikariDataSource)this.entityManager.getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
/* 237 */         String strDriverName = hikariDataS.getDriverClassName().toLowerCase();
/* 238 */         if (strDriverName.contains("oracle"))
/*     */         {
/* 240 */           databaseIsOracle = true;
/*     */         }
/* 242 */       } catch (Exception e) {
/* 243 */         LOGGER.error(e);
/*     */       } 
/* 245 */       StringBuilder sqlPage = new StringBuilder();
/* 246 */       if (databaseIsOracle && startPage != null && pageLoad != null) {
/*     */         
/* 248 */         String strVersionOracle = FunctionCommon.getPropertiesValue("oracle.version");
/* 249 */         if (strVersionOracle.trim().equals("11")) {
/* 250 */           sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
/* 251 */           sqlPage.append(queryString.toString());
/* 252 */           sqlPage.append(" ) a ) ");
/* 253 */           sqlPage.append(String.format(" WHERE r__ > %d", new Object[] { startPage }));
/* 254 */           sqlPage.append(String.format(" and r__ <= %d", new Object[] { Integer.valueOf(startPage.intValue() + pageLoad.intValue()) }));
/*     */         } else {
/* 256 */           sqlPage.append(" SELECT * FROM ( ");
/* 257 */           sqlPage.append(queryString.toString());
/* 258 */           sqlPage.append(" ) a ");
/* 259 */           sqlPage.append(String.format("  OFFSET %d ROWS FETCH NEXT %d ROWS ONLY ", new Object[] { startPage, pageLoad }));
/*     */         } 
/*     */       } else {
/* 262 */         sqlPage = queryString;
/*     */       } 
/* 264 */       Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
/* 265 */       if (hmapParams != null) {
/* 266 */         Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
/* 267 */         for (Object o : set) {
/* 268 */           Map.Entry mentry = (Map.Entry)o;
/* 269 */           query.setParameter(mentry.getKey().toString(), mentry.getValue());
/*     */         } 
/*     */       } 
/* 272 */       if (startPage != null && pageLoad != null && !databaseIsOracle) {
/* 273 */         query.setFirstResult(startPage.intValue()).setMaxResults(pageLoad.intValue());
/*     */       }
/* 275 */       List objectList = query.getResultList();
/* 276 */       List<Object> listResult = FunctionCommon.convertToEntity(objectList, classOfT);
/* 277 */       this.entityManager.close();
/* 278 */       return listResult;
/* 279 */     } catch (Exception e) {
/* 280 */       LOGGER.error(e);
/* 281 */       return null;
/*     */     } 
/*     */   }
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
/*     */   public List getListDataOriginal(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad) {
/*     */     try {
/* 298 */       boolean databaseIsOracle = false;
/*     */       
/*     */       try {
/* 301 */         HikariDataSource hikariDataS = (HikariDataSource)this.entityManager.getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
/* 302 */         String strDriverName = hikariDataS.getDriverClassName().toLowerCase();
/* 303 */         if (strDriverName.contains("oracle"))
/*     */         {
/* 305 */           databaseIsOracle = true;
/*     */         }
/* 307 */       } catch (Exception e) {
/* 308 */         LOGGER.error(e);
/*     */       } 
/* 310 */       StringBuilder sqlPage = new StringBuilder();
/* 311 */       if (databaseIsOracle && startPage != null && pageLoad != null) {
/*     */         
/* 313 */         String strVersionOracle = FunctionCommon.getPropertiesValue("oracle.version");
/* 314 */         if (strVersionOracle.trim().equals("11")) {
/* 315 */           sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
/* 316 */           sqlPage.append(queryString.toString());
/* 317 */           sqlPage.append(" ) a ) ");
/* 318 */           sqlPage.append(String.format(" WHERE r__ > %d", new Object[] { startPage }));
/* 319 */           sqlPage.append(String.format(" and r__ <= %d", new Object[] { Integer.valueOf(startPage.intValue() + pageLoad.intValue()) }));
/*     */         } else {
/* 321 */           sqlPage.append(" SELECT * FROM ( ");
/* 322 */           sqlPage.append(queryString.toString());
/* 323 */           sqlPage.append(" ) a ");
/* 324 */           sqlPage.append(String.format("  OFFSET %d ROWS FETCH NEXT %d ROWS ONLY ", new Object[] { startPage, pageLoad }));
/*     */         } 
/*     */       } else {
/* 327 */         sqlPage = queryString;
/*     */       } 
/* 329 */       Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
/* 330 */       if (hmapParams != null) {
/* 331 */         Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
/* 332 */         for (Object o : set) {
/* 333 */           Map.Entry mentry = (Map.Entry)o;
/* 334 */           query.setParameter(mentry.getKey().toString(), mentry.getValue());
/*     */         } 
/*     */       } 
/* 337 */       if (startPage != null && pageLoad != null && !databaseIsOracle) {
/* 338 */         query.setFirstResult(startPage.intValue()).setMaxResults(pageLoad.intValue());
/*     */       }
/* 340 */       List objectList = query.getResultList();
/* 341 */       this.entityManager.close();
/* 342 */       return objectList;
/* 343 */     } catch (Exception e) {
/* 344 */       LOGGER.error(e);
/* 345 */       return null;
/*     */     } 
/*     */   }
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
/*     */   public Object getFirstData(StringBuilder queryString, HashMap<String, Object> hmapParams, Class<?> classOfT) {
/*     */     try {
/* 360 */       Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
/* 361 */       if (hmapParams != null) {
/* 362 */         Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
/* 363 */         for (Object o : set) {
/* 364 */           Map.Entry mentry = (Map.Entry)o;
/* 365 */           query.setParameter(mentry.getKey().toString(), mentry.getValue());
/*     */         } 
/*     */       } 
/* 368 */       query.setFirstResult(0).setMaxResults(1);
/* 369 */       List objectList = query.getResultList();
/* 370 */       List<Object> listResult = FunctionCommon.convertToEntity(objectList, classOfT);
/*     */       
/* 372 */       this.entityManager.close();
/* 373 */       if (listResult.size() > 0) {
/* 374 */         return listResult.get(0);
/*     */       }
/* 376 */       return null;
/*     */     }
/* 378 */     catch (Exception e) {
/* 379 */       LOGGER.error(e);
/* 380 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getOnlyOneValueFirstData(StringBuilder queryString, List<Object> arrParams) {
/*     */     try {
/* 393 */       Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
/* 394 */       int countParams = 1;
/* 395 */       if (arrParams != null) {
/* 396 */         for (Object arrParam : arrParams) {
/* 397 */           query.setParameter(countParams, arrParam);
/* 398 */           countParams++;
/*     */         } 
/*     */       }
/* 401 */       query.setFirstResult(0).setMaxResults(1);
/* 402 */       List<Tuple> listResult = query.getResultList();
/* 403 */       this.entityManager.close();
/* 404 */       if (listResult != null && listResult.size() > 0) {
/* 405 */         Tuple tuple = listResult.get(0);
/* 406 */         return tuple.get(((TupleElement)tuple.getElements().stream().findFirst().get()).getAlias());
/*     */       } 
/* 408 */       return null;
/*     */     }
/* 410 */     catch (Exception e) {
/* 411 */       LOGGER.error(e);
/* 412 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getOnlyOneValueFirstData(StringBuilder queryString, HashMap<String, Object> hmapParams) {
/*     */     try {
/* 425 */       Query query = this.entityManager.createNativeQuery(queryString.toString(), Tuple.class);
/* 426 */       if (hmapParams != null) {
/* 427 */         Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
/* 428 */         for (Object o : set) {
/* 429 */           Map.Entry mentry = (Map.Entry)o;
/* 430 */           query.setParameter(mentry.getKey().toString(), mentry.getValue());
/*     */         } 
/*     */       } 
/* 433 */       query.setFirstResult(0).setMaxResults(1);
/* 434 */       List<Tuple> listResult = query.getResultList();
/* 435 */       this.entityManager.close();
/* 436 */       if (listResult != null && listResult.size() > 0) {
/* 437 */         Tuple tuple = listResult.get(0);
/* 438 */         return tuple.get(((TupleElement)tuple.getElements().stream().findFirst().get()).getAlias());
/*     */       } 
/* 440 */       return null;
/*     */     }
/* 442 */     catch (Exception e) {
/* 443 */       LOGGER.error(e);
/* 444 */       return null;
/*     */     } 
/*     */   }
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
/*     */   public ResultSelectEntity getListDataAndCount(StringBuilder queryString, HashMap<String, Object> hmapParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
/*     */     try {
/* 462 */       boolean databaseIsOracle = false;
/*     */       
/*     */       try {
/* 465 */         HikariDataSource hikariDataS = (HikariDataSource)this.entityManager.getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
/* 466 */         String strDriverName = hikariDataS.getDriverClassName().toLowerCase();
/* 467 */         if (strDriverName.contains("oracle"))
/*     */         {
/* 469 */           databaseIsOracle = true;
/*     */         }
/* 471 */       } catch (Exception e) {
/* 472 */         LOGGER.error(e);
/*     */       } 
/* 474 */       StringBuilder sqlPage = new StringBuilder();
/* 475 */       if (databaseIsOracle && startPage != null && pageLoad != null) {
/*     */         
/* 477 */         String strVersionOracle = FunctionCommon.getPropertiesValue("oracle.version");
/* 478 */         if (strVersionOracle.trim().equals("11")) {
/* 479 */           sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
/* 480 */           sqlPage.append(queryString.toString());
/* 481 */           sqlPage.append(" ) a ) ");
/* 482 */           sqlPage.append(String.format(" WHERE r__ > %d", new Object[] { startPage }));
/* 483 */           sqlPage.append(String.format(" and r__ <= %d", new Object[] { Integer.valueOf(startPage.intValue() + pageLoad.intValue()) }));
/*     */         } else {
/* 485 */           sqlPage.append(" SELECT * FROM ( ");
/* 486 */           sqlPage.append(queryString.toString());
/* 487 */           sqlPage.append(" ) a ");
/* 488 */           sqlPage.append(String.format("  OFFSET %d ROWS FETCH NEXT %d ROWS ONLY ", new Object[] { startPage, pageLoad }));
/*     */         } 
/*     */       } else {
/* 491 */         sqlPage = queryString;
/*     */       } 
/*     */       
/* 494 */       Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
/* 495 */       if (hmapParams != null) {
/* 496 */         Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
/* 497 */         for (Object o : set) {
/* 498 */           Map.Entry mentry = (Map.Entry)o;
/* 499 */           Object value = mentry.getValue();
/* 500 */           if (value == null) {
/* 501 */             value = "";
/*     */           }
/* 503 */           query.setParameter(mentry.getKey().toString(), value);
/*     */         } 
/*     */       } 
/* 506 */       if (startPage != null && pageLoad != null && !databaseIsOracle)
/*     */       {
/* 508 */         query.setFirstResult(startPage.intValue()).setMaxResults(pageLoad.intValue());
/*     */       }
/*     */       
/* 511 */       List objectList = query.getResultList();
/* 512 */       ResultSelectEntity result = new ResultSelectEntity();
/* 513 */       if (objectList != null) {
/* 514 */         List<Object> listResult = FunctionCommon.convertToEntity(objectList, classOfT);
/* 515 */         result.setListData(listResult);
/*     */       } 
/* 517 */       result.setCount(Integer.valueOf(getCountDataInSelect(queryString, hmapParams)));
/* 518 */       this.entityManager.close();
/* 519 */       return result;
/* 520 */     } catch (NumberFormatException e) {
/* 521 */       LOGGER.error(e);
/* 522 */       return null;
/*     */     } 
/*     */   }
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
/*     */   public ResultSelectEntity getListDataAndCount(StringBuilder queryString, List<Object> arrParams, Integer startPage, Integer pageLoad, Class<?> classOfT) {
/*     */     try {
/* 539 */       boolean databaseIsOracle = false;
/*     */       
/*     */       try {
/* 542 */         HikariDataSource hikariDataS = (HikariDataSource)this.entityManager.getEntityManagerFactory().getProperties().get("hibernate.connection.datasource");
/* 543 */         String strDriverName = hikariDataS.getDriverClassName().toLowerCase();
/* 544 */         if (strDriverName.contains("oracle"))
/*     */         {
/* 546 */           databaseIsOracle = true;
/*     */         }
/* 548 */       } catch (Exception e) {
/* 549 */         LOGGER.error(e);
/*     */       } 
/* 551 */       StringBuilder sqlPage = new StringBuilder();
/* 552 */       if (databaseIsOracle && startPage != null && pageLoad != null) {
/*     */         
/* 554 */         String strVersionOracle = FunctionCommon.getPropertiesValue("oracle.version");
/* 555 */         if (strVersionOracle.trim().equals("11")) {
/* 556 */           sqlPage.append(" SELECT * FROM ( SELECT a.*, rownum r__  FROM (");
/* 557 */           sqlPage.append(queryString.toString());
/* 558 */           sqlPage.append(" ) a ) ");
/* 559 */           sqlPage.append(String.format(" WHERE r__ > %d", new Object[] { startPage }));
/* 560 */           sqlPage.append(String.format(" and r__ <= %d", new Object[] { Integer.valueOf(startPage.intValue() + pageLoad.intValue()) }));
/*     */         } else {
/* 562 */           sqlPage.append(" SELECT * FROM ( ");
/* 563 */           sqlPage.append(queryString.toString());
/* 564 */           sqlPage.append(" ) a ");
/* 565 */           sqlPage.append(String.format("  OFFSET %d ROWS FETCH NEXT %d ROWS ONLY ", new Object[] { startPage, pageLoad }));
/*     */         } 
/*     */       } else {
/* 568 */         sqlPage = queryString;
/*     */       } 
/* 570 */       Query query = this.entityManager.createNativeQuery(sqlPage.toString(), Tuple.class);
/* 571 */       int countParams = 1;
/* 572 */       if (arrParams != null) {
/* 573 */         for (Object arrParam : arrParams) {
/* 574 */           query.setParameter(countParams, arrParam);
/* 575 */           countParams++;
/*     */         } 
/*     */       }
/*     */       
/* 579 */       if (startPage != null && pageLoad != null && !databaseIsOracle) {
/* 580 */         query.setFirstResult(startPage.intValue()).setMaxResults(pageLoad.intValue());
/*     */       }
/* 582 */       List objectList = query.getResultList();
/*     */       
/* 584 */       ResultSelectEntity result = new ResultSelectEntity();
/* 585 */       if (objectList != null) {
/* 586 */         List<Object> listResult = FunctionCommon.convertToEntity(objectList, classOfT);
/* 587 */         result.setListData(listResult);
/*     */       } 
/* 589 */       result.setCount(Integer.valueOf(getCountDataInSelect(queryString, arrParams)));
/* 590 */       this.entityManager.close();
/* 591 */       return result;
/* 592 */     } catch (NumberFormatException e) {
/* 593 */       LOGGER.error(e);
/* 594 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getCountDataInSelect(StringBuilder queryString, List<Object> arrParams) {
/* 607 */     StringBuilder strBuild = new StringBuilder();
/*     */     
/* 609 */     String strReplace = queryString.toString().trim().replaceAll(" +", " ");
/* 610 */     String strSql = removeOrderBy(strReplace);
/* 611 */     strBuild.append("Select count(1) as count From (");
/* 612 */     strBuild.append(strSql);
/* 613 */     strBuild.append(") tbcount");
/* 614 */     Query query = this.entityManager.createNativeQuery(strBuild.toString());
/* 615 */     int countParams = 1;
/* 616 */     if (arrParams != null) {
/* 617 */       for (Object arrParam : arrParams) {
/* 618 */         query.setParameter(countParams, arrParam);
/* 619 */         countParams++;
/*     */       } 
/*     */     }
/* 622 */     List resultQr = query.getResultList();
/* 623 */     if (resultQr != null && resultQr.size() > 0) {
/* 624 */       Object value = resultQr.get(0);
/* 625 */       String result = String.valueOf(value);
/* 626 */       this.entityManager.close();
/* 627 */       return Integer.parseInt(result);
/*     */     } 
/* 629 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCountData(StringBuilder queryString, List<Object> arrParams) {
/* 641 */     StringBuilder strBuild = new StringBuilder();
/* 642 */     strBuild.append("Select count(1) as count From (");
/* 643 */     strBuild.append(queryString);
/* 644 */     strBuild.append(") tbcount");
/* 645 */     Query query = this.entityManager.createNativeQuery(strBuild.toString());
/* 646 */     int countParams = 1;
/* 647 */     if (arrParams != null) {
/* 648 */       for (Object arrParam : arrParams) {
/* 649 */         query.setParameter(countParams, arrParam);
/* 650 */         countParams++;
/*     */       } 
/*     */     }
/* 653 */     List resultQr = query.getResultList();
/* 654 */     if (resultQr != null && resultQr.size() > 0) {
/* 655 */       Object value = resultQr.get(0);
/* 656 */       String result = String.valueOf(value);
/* 657 */       this.entityManager.close();
/* 658 */       return Integer.valueOf(result).intValue();
/*     */     } 
/* 660 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCountData(StringBuilder queryString, HashMap<String, Object> hmapParams) {
/* 672 */     StringBuilder strBuild = new StringBuilder();
/*     */     
/* 674 */     strBuild.append("Select count(1) as count From (");
/* 675 */     strBuild.append(queryString);
/* 676 */     strBuild.append(") tbcount");
/* 677 */     Query query = this.entityManager.createNativeQuery(strBuild.toString());
/* 678 */     if (hmapParams != null) {
/* 679 */       Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
/* 680 */       Iterator<Map.Entry<String, Object>> iterator = set.iterator();
/* 681 */       while (iterator.hasNext()) {
/* 682 */         Map.Entry mentry = iterator.next();
/* 683 */         Object value = mentry.getValue();
/* 684 */         if (value == null) {
/* 685 */           value = "";
/*     */         }
/* 687 */         query.setParameter(mentry.getKey().toString(), value);
/*     */       } 
/*     */     } 
/* 690 */     List resultQr = query.getResultList();
/* 691 */     if (resultQr != null && resultQr.size() > 0) {
/* 692 */       Object value = resultQr.get(0);
/* 693 */       String result = String.valueOf(value);
/* 694 */       this.entityManager.close();
/* 695 */       return Integer.valueOf(result).intValue();
/*     */     } 
/* 697 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getCountDataInSelect(StringBuilder queryString, HashMap<String, Object> hmapParams) {
/* 708 */     StringBuilder strBuild = new StringBuilder();
/*     */     
/* 710 */     String strReplace = queryString.toString().trim().replaceAll(" +", " ");
/* 711 */     String strSql = removeOrderBy(strReplace);
/* 712 */     strBuild.append("Select count(1) as count From (");
/* 713 */     strBuild.append(strSql);
/* 714 */     strBuild.append(") tbcount");
/* 715 */     Query query = this.entityManager.createNativeQuery(strBuild.toString());
/* 716 */     if (hmapParams != null) {
/* 717 */       Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
/* 718 */       for (Object o : set) {
/* 719 */         Map.Entry mentry = (Map.Entry)o;
/* 720 */         Object value = mentry.getValue();
/* 721 */         if (value == null) {
/* 722 */           value = "";
/*     */         }
/* 724 */         query.setParameter(mentry.getKey().toString().toLowerCase(), value);
/*     */       } 
/*     */     } 
/* 727 */     List resultQr = query.getResultList();
/* 728 */     if (resultQr != null && resultQr.size() > 0) {
/* 729 */       Object value = resultQr.get(0);
/* 730 */       String result = String.valueOf(value);
/* 731 */       this.entityManager.close();
/* 732 */       return Integer.parseInt(result);
/*     */     } 
/* 734 */     return 0;
/*     */   }
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
/*     */   public Boolean excuteSqlDatabase(StringBuilder queryString, List<Object> arrParams) {
/* 749 */     boolean result = true;
/*     */     try {
/* 751 */       Query query = this.entityManager.createNativeQuery(queryString.toString());
/* 752 */       int countParams = 1;
/* 753 */       if (arrParams != null && arrParams.size() > 0) {
/* 754 */         for (Object arrParam : arrParams) {
/* 755 */           query.setParameter(countParams, arrParam);
/* 756 */           countParams++;
/*     */         } 
/*     */       }
/* 759 */       int i = query.executeUpdate();
/* 760 */     } catch (Exception e) {
/* 761 */       LOGGER.error(e);
/* 762 */       result = false;
/*     */     } 
/* 764 */     this.entityManager.close();
/* 765 */     return Boolean.valueOf(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public Boolean excuteSqlDatabase(StringBuilder queryString, HashMap<String, Object> hmapParams) {
/* 778 */     boolean result = true;
/*     */     try {
/* 780 */       Query query = this.entityManager.createNativeQuery(queryString.toString());
/* 781 */       if (hmapParams != null) {
/* 782 */         Set<Map.Entry<String, Object>> set = hmapParams.entrySet();
/* 783 */         for (Object o : set) {
/* 784 */           Map.Entry mentry = (Map.Entry)o;
/* 785 */           Object value = mentry.getValue();
/* 786 */           if (value == null) {
/* 787 */             value = "";
/*     */           }
/* 789 */           query.setParameter(mentry.getKey().toString(), value);
/*     */         } 
/*     */       } 
/* 792 */       int i = query.executeUpdate();
/* 793 */     } catch (Exception e) {
/* 794 */       LOGGER.error(e);
/* 795 */       result = false;
/*     */     } 
/* 797 */     this.entityManager.close();
/* 798 */     return Boolean.valueOf(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String removeOrderBy(String strReplace) {
/* 807 */     String strResult = strReplace.toLowerCase();
/* 808 */     int indexLast = strResult.lastIndexOf("order by");
/* 809 */     int indexLastCm = strResult.lastIndexOf(")");
/* 810 */     if (indexLast > 0 && indexLastCm < indexLast) {
/* 811 */       strResult = strResult.substring(0, indexLast);
/*     */     }
/* 813 */     return strResult;
/*     */   }
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\repositories\CommonDataBaseRepository.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */