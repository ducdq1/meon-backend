/*     */ package com.mrlep.meon.xlibrary.core.constants;
/*     */ 
/*     */

import org.apache.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

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
/*     */ public class RedisUtil
/*     */ {
/*  21 */   private static final Logger LOGGER = Logger.getLogger(RedisUtil.class);
/*     */   
/*  23 */   private static RedisUtil INSTANCE = null;
/*     */   private JedisCluster jedisCluster;
/*     */   private String redisAddress;
/*     */   private int redisTimeout;
/*     */   
/*     */   private RedisUtil(JedisCluster jedisCluster) {
/*  29 */     this.jedisCluster = jedisCluster;
/*     */   }
/*     */   
/*     */   public RedisUtil(String redisAddress, int redisTimeout) {
/*  33 */     this.redisAddress = redisAddress;
/*  34 */     this.redisTimeout = redisTimeout;
/*     */   }
/*     */ 
/*     */   
/*     */   public RedisUtil() {}
/*     */   
/*     */   public synchronized RedisUtil setup() {
/*  41 */     if (INSTANCE == null) {
/*  42 */       LOGGER.info("Start connect Redis: " + this.redisAddress);
/*  43 */       Set<HostAndPort> hostAndPortNodes = new HashSet<>();
/*  44 */       String[] hostAndPorts = this.redisAddress.split(",");
/*  45 */       for (String hostAndPort : hostAndPorts) {
/*  46 */         String[] parts = hostAndPort.split(":");
/*  47 */         String host = parts[0];
/*  48 */         int port = Integer.parseInt(parts[1].trim());
/*  49 */         hostAndPortNodes.add(new HostAndPort(host, port));
/*     */       } 
/*  51 */       INSTANCE = new RedisUtil(new JedisCluster(hostAndPortNodes, this.redisTimeout));
/*     */     } 
/*     */     
/*  54 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public static synchronized RedisUtil getInstance() {
/*  58 */     if (INSTANCE == null) {
/*  59 */       INSTANCE = (new RedisUtil()).setup();
/*     */     }
/*  61 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public JedisCluster getRedis() {
/*  65 */     return INSTANCE.jedisCluster;
/*     */   }
/*     */   
/*     */   public void setRedisAddress(String dress) {
/*  69 */     this.redisAddress = dress;
/*     */   }
/*     */   
/*     */   public void setRedisTimeout(int timeout) {
/*  73 */     this.redisTimeout = timeout;
/*     */   }
/*     */   
/*     */   public String getRedisAddress() {
/*  77 */     return this.redisAddress;
/*     */   }
/*     */   
/*     */   public int getRedisTimeout() {
/*  81 */     return this.redisTimeout;
/*     */   }
/*     */   
/*     */   public String get(String key) {
/*     */     try {
/*  86 */       JedisCluster jedis = getRedis();
/*  87 */       return jedis.get(key);
/*  88 */     } catch (Exception ex) {
/*  89 */       LOGGER.error(ex.getMessage(), ex);
/*     */ 
/*     */       
/*  92 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean save(String key, String value) {
/* 103 */     if (key == null) {
/* 104 */       return false;
/*     */     }
/*     */     try {
/* 107 */       JedisCluster jedis = getRedis();
/* 108 */       jedis.set(key, value);
/* 109 */     } catch (Exception ex) {
/* 110 */       LOGGER.error(ex.getMessage(), ex);
/*     */     } 
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long incr(String key) {
/* 122 */     if (key == null) {
/* 123 */       return Long.valueOf(0L);
/*     */     }
/*     */     try {
/* 126 */       JedisCluster jedis = getRedis();
/* 127 */       if (jedis.exists(key).booleanValue()) {
/* 128 */         return jedis.incr(key);
/*     */       }
/* 130 */       return Long.valueOf(-1L);
/*     */     }
/* 132 */     catch (Exception ex) {
/* 133 */       LOGGER.error(ex.getMessage(), ex);
/*     */       
/* 135 */       return Long.valueOf(0L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long incr(String key, Long increment) {
/* 146 */     if (key == null) {
/* 147 */       return Long.valueOf(0L);
/*     */     }
/*     */     try {
/* 150 */       JedisCluster jedis = getRedis();
/* 151 */       if (jedis.exists(key).booleanValue()) {
/* 152 */         return jedis.incrBy(key, increment.longValue());
/*     */       }
/* 154 */       return Long.valueOf(-1L);
/*     */     }
/* 156 */     catch (Exception ex) {
/* 157 */       LOGGER.error(ex.getMessage(), ex);
/*     */       
/* 159 */       return Long.valueOf(0L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long decr(String key) {
/* 169 */     if (key == null) {
/* 170 */       return Long.valueOf(0L);
/*     */     }
/*     */     try {
/* 173 */       JedisCluster jedis = getRedis();
/* 174 */       if (jedis.exists(key).booleanValue()) {
/* 175 */         return jedis.decr(key);
/*     */       }
/* 177 */       return Long.valueOf(-1L);
/*     */     }
/* 179 */     catch (Exception ex) {
/* 180 */       LOGGER.error(ex.getMessage(), ex);
/*     */       
/* 182 */       return Long.valueOf(0L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long decrBy(String key, Long decrement) {
/* 193 */     if (key == null) {
/* 194 */       return Long.valueOf(0L);
/*     */     }
/*     */     try {
/* 197 */       JedisCluster jedis = getRedis();
/* 198 */       if (jedis.exists(key).booleanValue()) {
/* 199 */         return jedis.decrBy(key, decrement.longValue());
/*     */       }
/* 201 */       return Long.valueOf(-1L);
/*     */     }
/* 203 */     catch (Exception ex) {
/* 204 */       LOGGER.error(ex.getMessage(), ex);
/*     */       
/* 206 */       return Long.valueOf(0L);
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
/*     */   public boolean save(String key, String value, int expire) {
/* 218 */     if (key == null) {
/* 219 */       return false;
/*     */     }
/*     */     try {
/* 222 */       JedisCluster jedis = getRedis();
/* 223 */       jedis.set(key, value);
/* 224 */       jedis.expire(key, expire);
/* 225 */     } catch (Exception ex) {
/* 226 */       LOGGER.error(ex.getMessage(), ex);
/*     */     } 
/* 228 */     return true;
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
/*     */   public boolean saveWithTimeExpire(String key, String value, long expireAt) {
/* 240 */     if (key == null) {
/* 241 */       return false;
/*     */     }
/*     */     try {
/* 244 */       JedisCluster jedis = getRedis();
/* 245 */       jedis.set(key, value);
/* 246 */       jedis.expireAt(key, expireAt);
/* 247 */     } catch (Exception ex) {
/* 248 */       LOGGER.error(ex.getMessage(), ex);
/*     */     } 
/* 250 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeObject(String key) {
/* 254 */     if (key == null) {
/* 255 */       return false;
/*     */     }
/*     */     try {
/* 258 */       JedisCluster jedis = getRedis();
/* 259 */       jedis.del(key);
/* 260 */     } catch (Exception ex) {
/* 261 */       LOGGER.error(ex.getMessage(), ex);
/* 262 */       return false;
/*     */     } 
/* 264 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\constants\RedisUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */