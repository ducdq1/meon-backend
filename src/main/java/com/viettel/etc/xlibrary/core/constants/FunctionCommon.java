/*      */
package com.viettel.etc.xlibrary.core.constants;
/*      */

import com.fasterxml.jackson.core.JsonProcessingException;
/*      */ import com.fasterxml.jackson.databind.DeserializationFeature;
/*      */ import com.fasterxml.jackson.databind.MapperFeature;
/*      */ import com.fasterxml.jackson.databind.ObjectMapper;
/*      */ import com.google.gson.Gson;
/*      */ import com.google.gson.GsonBuilder;
/*      */ import com.google.gson.JsonIOException;
/*      */ import com.google.gson.JsonParseException;
/*      */ import com.google.gson.JsonSyntaxException;
/*      */ import com.google.gson.stream.JsonReader;
/*      */ import com.viettel.etc.xlibrary.core.entities.CoreErrorApp;
/*      */ import com.viettel.etc.xlibrary.core.entities.ExcellDataEntity;
/*      */ import com.viettel.etc.xlibrary.core.entities.ExcellHeaderEntity;
/*      */ import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
/*      */ import com.viettel.etc.xlibrary.core.entities.MessEntity;
/*      */ import com.viettel.etc.xlibrary.core.entities.ResponseEntity;
/*      */ import com.viettel.etc.xlibrary.core.entities.UserSystemEntity;
/*      */ import com.viettel.etc.xlibrary.jwt.FunctionJwt;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.StringReader;
/*      */ import java.lang.reflect.Field;
/*      */ import java.security.SecureRandom;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Timestamp;
/*      */ import java.text.Normalizer;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Set;
/*      */ import java.util.TreeMap;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ import java.util.concurrent.Executors;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.persistence.Tuple;
/*      */ import javax.persistence.TupleElement;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import org.apache.log4j.Logger;
/*      */ import org.apache.poi.ss.usermodel.Cell;
/*      */ import org.apache.poi.ss.usermodel.CellStyle;
/*      */ import org.apache.poi.ss.usermodel.FillPatternType;
/*      */ import org.apache.poi.ss.usermodel.Font;
/*      */ import org.apache.poi.ss.usermodel.HorizontalAlignment;
/*      */ import org.apache.poi.ss.usermodel.IndexedColors;
/*      */ import org.apache.poi.ss.usermodel.Row;
/*      */ import org.apache.poi.ss.usermodel.Sheet;
/*      */ import org.apache.poi.ss.util.CellRangeAddress;
/*      */ import org.apache.poi.xssf.usermodel.XSSFFont;
/*      */ import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ import org.springframework.security.core.Authentication;

/*      */
/*      */ public class FunctionCommon {
    /*   69 */   private static final Logger LOGGER = Logger.getLogger(FunctionCommon.class);
    /*   70 */   private static final ResourceBundle RESOURCE_BUNDLE = getResourceBundle();
    private static ExecutorService executorService;

    /*      */
    /*      */
    private static ResourceBundle getResourceBundle() {
        /*      */
        try {
            /*   74 */
            return ResourceBundle.getBundle(StringConstants.CONFIGFILEPROPERTIES);
            /*      */
        }
        /*   76 */ catch (Exception e) {
            /*   77 */
            LOGGER.error("Loi! getResourceBundle: " + e.getMessage());
            /*      */
            /*   79 */
            return null;
            /*      */
        }
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Object jsonGetItem(String item, String strJsonData) {
        /*   90 */
        Object result = null;
        /*      */
        try {
            /*   92 */
            JSONObject obj = new JSONObject(strJsonData);
            /*   93 */
            if (!obj.isNull(item)) {
                /*   94 */
                result = obj.get(item);
                /*      */
            }
            /*   96 */
        } catch (JSONException e) {
            /*   97 */
            LOGGER.error("Loi! jsonGetItem: " + e.getMessage());
            /*      */
        }
        /*   99 */
        return result;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Object convertJsonToObject(String strJsonData, Class<?> classOfT) {
        /*  110 */
        Object result = null;
        /*      */
        try {
            /*  112 */
            GsonBuilder builder = new GsonBuilder();
            /*  113 */
            Gson gson = builder.create();
            /*  114 */
            JsonReader reader = new JsonReader(new StringReader(strJsonData));
            /*  115 */
            reader.setLenient(true);
            /*  116 */
            result = gson.fromJson(reader, classOfT);
            /*  117 */
        } catch (JsonIOException | JsonSyntaxException e) {
            /*  118 */
            LOGGER.error("Loi convertJsonToObject:", (Throwable) e);
            /*      */
        }
        /*  120 */
        return result;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String convertObjectToStringJson(Object object) {
        /*  130 */
        String strMess = "";
        /*      */
        try {
            /*  132 */
            GsonBuilder gsonBuilder = new GsonBuilder();
            /*  133 */
            Gson gson = gsonBuilder.create();
            /*  134 */
            strMess = gson.toJson(object);
            /*  135 */
        } catch (Exception e) {
            /*  136 */
            LOGGER.error("Loi convertObjectToStringJson ", e);
            /*      */
        }
        /*      */
        /*  139 */
        return strMess;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        /*  152 */
        ResultSetMetaData rsmd = rs.getMetaData();
        /*  153 */
        int columns = rsmd.getColumnCount();
        /*  154 */
        for (int x = 1; x <= columns; x++) {
            /*  155 */
            String cl = rsmd.getColumnName(x);
            /*  156 */
            if (columnName.toLowerCase().equals(cl.toLowerCase())) {
                /*  157 */
                return true;
                /*      */
            }
            /*      */
        }
        /*  160 */
        return false;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Timestamp convertDateToSql(Date date) {
        /*  172 */
        Timestamp result = null;
        /*      */
        try {
            /*  174 */
            result = new Timestamp(date.getTime());
            /*  175 */
        } catch (Exception e) {
            /*  176 */
            LOGGER.error("Loi! FuctionCommon.convertDateToSql: " + e);
            /*      */
        }
        /*  178 */
        return result;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String trimspace(String str) {
        /*  188 */
        str = str.replaceAll("\\s+", " ");
        /*  189 */
        str = str.replaceAll("(^\\s+|\\s+$)", "");
        /*  190 */
        return str;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String escapeSql(String input) {
        /*  200 */
        return input.trim().replace("/", "//")
/*  201 */.replace("_", "/_").replace("%", "/%");
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static int[] getReplaceSqlInArr(String strSql, String variale) {
        /*  214 */
        int[] result = new int[4];
        /*  215 */
        int indext = strSql.indexOf(variale);
        /*  216 */
        int i = indext;
        /*  217 */
        int spase = 0;
        /*  218 */
        int end = indext + variale.length() + 1;
        /*      */
        /*  220 */
        int charEnd = 0;
        /*  221 */
        int strInOrNotin = 0;
        /*  222 */
        int start = 0;
        /*      */
        while (true) {
            /*  224 */
            char a_char = strSql.charAt(i);
            /*      */
            /*  226 */
            if (a_char == ' ') {
                /*  227 */
                spase++;
                /*      */
            }
            /*  229 */
            if (spase == 1) {
                /*      */
                /*  231 */
                charEnd = i;
                /*  232 */
                spase = 2;
                /*      */
            }
            /*  234 */
            if (spase == 3) {
                /*      */
                /*  236 */
                start = i;
                /*      */
                break;
                /*      */
            }
            /*  239 */
            i--;
            /*  240 */
            if (a_char == '(') {
                /*  241 */
                strInOrNotin = i;
                /*      */
            }
            /*      */
        }
        /*  244 */
        result[0] = start;
        /*  245 */
        result[1] = charEnd;
        /*  246 */
        result[2] = end;
        /*  247 */
        result[3] = strInOrNotin;
        /*  248 */
        return result;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static byte[] hexStringToByteArray(String s) {
        /*  280 */
        int len = s.length();
        /*  281 */
        byte[] data = new byte[len / 2];
        /*  282 */
        for (int i = 0; i < len; i += 2) {
            /*  283 */
            data[i / 2] =
                    /*  284 */         (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
            /*      */
        }
        /*  286 */
        return data;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String bytesToHex(byte[] bytes) {
        /*  296 */
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        /*  297 */
        char[] hexChars = new char[bytes.length * 2];
        /*  298 */
        for (int j = 0; j < bytes.length; j++) {
            /*  299 */
            int v = bytes[j] & 0xFF;
            /*  300 */
            hexChars[j * 2] = hexArray[v >>> 4];
            /*  301 */
            hexChars[j * 2 + 1] = hexArray[v & 0xF];
            /*      */
        }
        /*  303 */
        return new String(hexChars);
        /*      */
    }

    /*      */
    /*      */
    public static String createTokenRandom() {
        /*  307 */
        SecureRandom random = new SecureRandom();
        /*  308 */
        byte[] bytes = new byte[20];
        /*  309 */
        random.nextBytes(bytes);
        /*  310 */
        return Arrays.toString(bytes);
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String generateJSONBase(Object obj) {
        /*      */
        String data;
        /*  327 */
        Gson gson = (new GsonBuilder()).registerTypeAdapter(int.class, new GsonEmptyStringToNumber.EmptyStringToNumberTypeAdapter()).registerTypeAdapter(Integer.class, new GsonEmptyStringToNumber.EmptyStringToNumberTypeAdapter()).registerTypeAdapter(long.class, new GsonEmptyStringToNumber.EmptyStringToLongTypeAdapter()).registerTypeAdapter(Long.class, new GsonEmptyStringToNumber.EmptyStringToLongTypeAdapter()).registerTypeAdapter(double.class, new GsonEmptyStringToNumber.EmptyStringToDoubleTypeAdapter()).registerTypeAdapter(Double.class, new GsonEmptyStringToNumber.EmptyStringToDoubleTypeAdapter()).setDateFormat(0, 0).create();
        /*      */
        /*      */
        try {
            /*  330 */
            data = gson.toJson(obj);
            /*  331 */
        } catch (Exception e) {
            /*  332 */
            data = null;
            /*  333 */
            LOGGER.error("Loi! generateJSONBase", e);
            /*      */
        }
        /*  335 */
        return data;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static List<String> getValuesFromJSON(JSONObject json, String[] keys) throws JSONException {
        /*  347 */
        List<String> listValue = new ArrayList<>();
        /*  348 */
        if (json != null && keys != null && keys.length > 0) {
            /*  349 */
            for (String key : keys) {
                /*  350 */
                if (json.isNull(key)) {
                    /*  351 */
                    listValue.add("");
                    /*      */
                } else {
                    /*  353 */
                    listValue.add(json.getString(key).trim());
                    /*      */
                }
                /*      */
            }
            /*      */
        }
        /*  357 */
        return listValue;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static void startListThread(List<Runnable> listRunAble) {
        /*      */
        try {
            /*  369 */
            if (executorService == null) {
                /*  370 */
                executorService = Executors.newFixedThreadPool(NumberConstants.I_SUM_THREAD);
                /*      */
            }
            /*  372 */
            for (Runnable runnable : listRunAble) {
                /*  373 */
                executorService.execute(runnable);
                /*      */
            }
            /*  375 */
            executorService.awaitTermination(NumberConstants.L_TIMESECON_WAITINGTHREAD.longValue(), TimeUnit.SECONDS);
            /*  376 */
        } catch (InterruptedException ex) {
            /*  377 */
            LOGGER.error("Loi! Khong thuc hien duoc cac tien trinh song song: " + ex.getMessage());
            /*      */
        }
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static JSONArray jsonGetArray(String item, String strJsonData) {
        /*  389 */
        JSONArray result = null;
        /*      */
        try {
            /*  391 */
            JSONObject obj = new JSONObject(strJsonData);
            /*  392 */
            result = obj.getJSONArray(item);
            /*  393 */
        } catch (JSONException e) {
            /*  394 */
            LOGGER.error("Loi! jsonGetArray: " + e.getMessage());
            /*      */
        }
        /*  396 */
        return result;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String getPropertiesValue(String key) {
        /*  406 */
        return RESOURCE_BUNDLE.containsKey(key) ? RESOURCE_BUNDLE
/*  407 */.getString(key) : StringConstants.STR_EMTY;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String generateQuestionMark(int size) {
        /*  418 */
        if (size == 0) {
            /*  419 */
            return "";
            /*      */
        }
        /*  421 */
        StringBuilder questionMarks = new StringBuilder();
        /*  422 */
        for (int i = 0; i < size - 1; i++) {
            /*  423 */
            questionMarks.append(" ?, ");
            /*      */
        }
        /*  425 */
        questionMarks.append(" ? ");
        /*  426 */
        return questionMarks.toString();
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String generateDocumentId() {
        /*  435 */
        UUID uuid = UUID.randomUUID();
        /*  436 */
        return uuid.toString();
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String removeAccent(String s) {
        /*  446 */
        if (s == null) {
            /*  447 */
            return "";
            /*      */
        }
        /*  449 */
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        /*  450 */
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        /*  451 */
        return pattern.matcher(temp).replaceAll("").replace("đ", "d").replace("Đ", "D");
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String dateShow(Date notAfter, Boolean isFullDateTime) {
        /*      */
        String date;
        /*  463 */
        if (notAfter == null) {
            /*  464 */
            return "";
            /*      */
        }
        /*  466 */
        if (isFullDateTime.booleanValue()) {
            /*  467 */
            date = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(notAfter);
            /*      */
        } else {
            /*  469 */
            date = (new SimpleDateFormat("dd/MM/yyyy")).format(notAfter);
            /*      */
        }
        /*  471 */
        return date;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static String getNumberAndDotFromString(String strInput) {
        /*  481 */
        return strInput.replaceAll("[^0-9./:]", "");
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Properties readProperties(String filename) {
        /*      */
        try {
            /*  492 */
            Properties properties = new Properties();
            /*  493 */
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            /*  494 */
            InputStream stream = loader.getResourceAsStream(filename);
            /*      */
            /*  496 */
            properties.load(stream);
            /*  497 */
            return properties;
            /*  498 */
        } catch (IOException ex) {
            /*  499 */
            LOGGER.error("readProperties not file name = " + filename);
            /*  500 */
            LOGGER.error(ex);
            /*  501 */
            return null;
            /*      */
        }
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Object getDataFromJson(String strJsonData, Class<?> classOfT) {
        /*  513 */
        if (strJsonData == null || strJsonData.trim().length() <= 0) {
            /*  514 */
            return null;
            /*      */
        }
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*      */
        /*  523 */
        Gson gson = (new GsonBuilder()).registerTypeAdapter(int.class, new GsonEmptyStringToNumber.EmptyStringToNumberTypeAdapter()).registerTypeAdapter(Integer.class, new GsonEmptyStringToNumber.EmptyStringToNumberTypeAdapter()).registerTypeAdapter(long.class, new GsonEmptyStringToNumber.EmptyStringToLongTypeAdapter()).registerTypeAdapter(Long.class, new GsonEmptyStringToNumber.EmptyStringToLongTypeAdapter()).registerTypeAdapter(double.class, new GsonEmptyStringToNumber.EmptyStringToDoubleTypeAdapter()).registerTypeAdapter(Double.class, new GsonEmptyStringToNumber.EmptyStringToDoubleTypeAdapter()).setDateFormat(0, 0).create();
        /*  524 */
        Object item = null;
        /*      */
        try {
            /*  526 */
            item = gson.fromJson(strJsonData, classOfT);
            /*  527 */
        } catch (JsonSyntaxException e) {
            /*  528 */
            LOGGER.error("getDataFromJson err params = " + strJsonData);
            /*      */
        }
        /*      */
        /*  531 */
        return item;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static UserSystemEntity checkDataRequest(HttpServletRequest req) {
        /*  543 */
        String strAuthen = (req.getHeader("authorization") != null) ? req.getHeader("authorization") : req.getHeader("Authorization");
        /*  544 */
        String[] arrStrAuthen = strAuthen.split(" ");
        /*  545 */
        String strJwtToken = strAuthen;
        /*  546 */
        if (arrStrAuthen.length > 1) {
            /*  547 */
            strJwtToken = arrStrAuthen[1];
            /*      */
        }
        /*  549 */
        return FunctionJwt.getDataUserFromToken(strJwtToken);
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static UserSystemEntity getUserInforFromToken(String strJwtToken) {
        /*  560 */
        return FunctionJwt.getDataUserFromToken(strJwtToken);
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Object responseToClient(Authentication req, Object itemObject) {
        /*      */
        CoreErrorApp errorApp;
        /*  572 */
        ResponseEntity responseEntity = new ResponseEntity();
        /*  573 */
        if (itemObject == null) {
            /*  574 */
            errorApp = CoreErrorApp.DATAEMTY;
            /*      */
        } else {
            /*  576 */
            errorApp = CoreErrorApp.SUCCESS;
            /*  577 */
            responseEntity.setData(itemObject);
            /*      */
        }
        /*  579 */
        MessEntity itemEntity = new MessEntity();
        /*  580 */
        itemEntity.setCode(errorApp.getCode());
        /*  581 */
        itemEntity.setDescription(errorApp.getDescription());
        /*  582 */
        responseEntity.setMess(itemEntity);
        /*  583 */
        return responseEntity;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Object responseToClient(Object itemObject) {
        /*      */
        CoreErrorApp errorApp;
        /*  594 */
        ResponseEntity responseEntity = new ResponseEntity();
        /*  595 */
        if (itemObject == null) {
            /*  596 */
            errorApp = CoreErrorApp.DATAEMTY;
            /*      */
        } else {
            /*  598 */
            errorApp = CoreErrorApp.SUCCESS;
            /*  599 */
            responseEntity.setData(itemObject);
            /*      */
        }
        /*  601 */
        MessEntity itemEntity = new MessEntity();
        /*  602 */
        itemEntity.setCode(errorApp.getCode());
        /*  603 */
        itemEntity.setDescription(errorApp.getDescription());
        /*  604 */
        responseEntity.setMess(itemEntity);
        /*  605 */
        return responseEntity;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Object responseToClient(CoreErrorApp errorApp, Object itemObject) {
        /*  616 */
        ResponseEntity responseEntity = new ResponseEntity();
        /*  617 */
        if (itemObject != null) {
            /*  618 */
            responseEntity.setData(itemObject);
            /*      */
        }
        /*  620 */
        MessEntity itemEntity = new MessEntity();
        /*  621 */
        itemEntity.setCode(errorApp.getCode());
        /*  622 */
        itemEntity.setDescription(errorApp.getDescription());
        /*  623 */
        responseEntity.setMess(itemEntity);
        /*  624 */
        return responseEntity;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static List<?> convertToEntity(List<Tuple> input, Class<?> dtoClass) {
        List<Object> arrayList = new ArrayList();
        input.stream().forEach((tuple) -> {
            Map<String, Object> temp = new HashMap();
            tuple.getElements().stream().forEach((tupleElement) -> {
                Object value = tuple.get(tupleElement.getAlias());
                temp.put(tupleElement.getAlias().toLowerCase(), value);
            });
            ObjectMapper map = new ObjectMapper();
            map.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                String mapToString = map.writeValueAsString(temp);
                arrayList.add(map.readValue(mapToString, dtoClass));
            } catch (JsonProcessingException var6) {
                throw new RuntimeException(var6.getMessage());
            }
        });
        return arrayList;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Object convertObjectToObject(Object object1, Class<?> classOfT) {
        /*  671 */
        String jsonString = generateJSONBase(object1);
        /*  672 */
        if (jsonString != null) {
            /*  673 */
            return getDataFromJson(jsonString, classOfT);
            /*      */
        }
        /*  675 */
        return null;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static boolean isNumeric(String str) {
        /*  685 */
        return str.matches("-?\\d+(\\.\\d+)?");
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static List<String> getListParamsSql(String strSql) {
        /*  695 */
        if (strSql == null || strSql.trim().length() <= 0) {
            /*  696 */
            return null;
            /*      */
        }
        /*  698 */
        List<String> listParameter = new ArrayList<>();
        /*  699 */
        String strSqlSub = strSql.toLowerCase().replaceAll("\\s+", " ");
        /*      */
        while (true) {
            /*  701 */
            int iFirstParasm = strSqlSub.indexOf(':');
            /*  702 */
            if (iFirstParasm <= 0) {
                /*      */
                break;
                /*      */
            }
            /*  705 */
            int iLast = -1;
            /*  706 */
            String strVariable = null;
            /*  707 */
            int length = strSqlSub.length();
            /*  708 */
            for (int i = iFirstParasm; i < length; i++) {
                /*  709 */
                if (' ' == strSqlSub.charAt(i) || ',' == strSqlSub.charAt(i) || ')' == strSqlSub.charAt(i)) {
                    /*  710 */
                    strVariable = strSqlSub.substring(iFirstParasm, i);
                    /*  711 */
                    String strParams = strVariable.replace(":", "").trim();
                    /*  712 */
                    listParameter.add(strParams);
                    /*  713 */
                    iLast = i;
                    break;
                    /*      */
                }
                /*  715 */
                if (i == strSqlSub.length() - 1) {
                    /*  716 */
                    strVariable = strSqlSub.substring(iFirstParasm);
                    /*  717 */
                    String strParams = strVariable.replace(":", "").trim();
                    /*  718 */
                    listParameter.add(strParams);
                    /*  719 */
                    iLast = strSqlSub.length();
                    /*      */
                    break;
                    /*      */
                }
                /*      */
            }
            /*  723 */
            if (iLast > 0) {
                /*  724 */
                strSqlSub = strSqlSub.replace(strVariable, "");
                /*      */
            }
            /*      */
        }
        /*  727 */
        return listParameter;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Object getValueParamsFromObject(Object object, String strNameParam) {
        /*  738 */
        Class<?> clazz = object.getClass();
        /*  739 */
        Field[] fields = clazz.getDeclaredFields();
        /*  740 */
        for (Field field : fields) {
            /*      */
            try {
                /*  742 */
                field.setAccessible(true);
                /*  743 */
                String name = field.getName().trim().toLowerCase();
                /*  744 */
                Object value = field.get(object);
                /*  745 */
                if (name.equals(strNameParam.trim().toLowerCase())) {
                    /*  746 */
                    return value;
                    /*      */
                }
                /*  748 */
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                /*  749 */
                LOGGER.error(ex);
                /*      */
            }
            /*      */
        }
        /*  752 */
        return null;
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    public static Boolean exportFileExcellMultiSheet(List<ExcellSheet> listSheet, String strPathExportFile) {
        /*  763 */
        if (listSheet == null || listSheet.size() <= 0 || strPathExportFile == null || strPathExportFile
/*  764 */.trim().length() <= 0) {
            /*  765 */
            return Boolean.valueOf(false);
            /*      */
        }
        /*      */
        try {
            /*  768 */
            XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();
            /*  769 */
            int i = 0;
            /*  770 */
            for (ExcellSheet sheetExprort : listSheet) {
                /*      */
                /*  772 */
                CellStyle headerStyle = xSSFWorkbook.createCellStyle();
                /*  773 */
                headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                /*  774 */
                headerStyle.setFillPattern(FillPatternType.NO_FILL);
                /*  775 */
                headerStyle.setAlignment(HorizontalAlignment.CENTER);
                /*      */
                /*  777 */
                XSSFFont font = xSSFWorkbook.createFont();
                /*  778 */
                font.setFontName("Arial");
                /*  779 */
                font.setFontHeightInPoints((short) 16);
                /*  780 */
                font.setBold(true);
                /*  781 */
                headerStyle.setFont((Font) font);
                /*      */
                /*      */
                /*  784 */
                i++;
                /*      */
                /*  786 */
                String strNameSheet = (sheetExprort.getStrSheetName() != null && sheetExprort.getStrSheetName().trim().length() > 0) ? sheetExprort.getStrSheetName() : ("Sheet" + i);
                /*  787 */
                Sheet sheet = xSSFWorkbook.createSheet(strNameSheet);
                /*      */
                /*  789 */
                List<ExcellHeaderEntity> listHeader = sheetExprort.getListHeader();
                /*  790 */
                for (int j = 0; j < listHeader.size(); j++) {
                    /*  791 */
                    ExcellHeaderEntity excellHeaderEntity = listHeader.get(j);
                    /*  792 */
                    int withCell = 6000;
                    /*  793 */
                    if (excellHeaderEntity.getWidth() != null && excellHeaderEntity.getWidth().intValue() > 0) {
                        /*  794 */
                        withCell = excellHeaderEntity.getWidth().intValue();
                        /*      */
                    }
                    /*  796 */
                    sheet.setColumnWidth(j, withCell);
                    /*      */
                }
                /*  798 */
                Row header = sheet.createRow(0);
                /*      */
                /*  800 */
                for (ExcellHeaderEntity excellHeaderEntity : listHeader) {
                    /*  801 */
                    Cell headerCell = header.createCell(0);
                    /*  802 */
                    headerCell.setCellValue(excellHeaderEntity.getHeaderName());
                    /*  803 */
                    headerCell.setCellStyle(headerStyle);
                    /*      */
                }
                /*      */
                /*      */
                /*      */
                /*  808 */
                CellStyle style = xSSFWorkbook.createCellStyle();
                /*  809 */
                style.setWrapText(false);
                /*  810 */
                ExcellDataEntity listData = sheetExprort.getExcellDataEntity();
                /*      */
                /*  812 */
                for (int k = 0; k < listData.getListData().size(); k++) {
                    /*  813 */
                    List<Object> listObjectRow = listData.getListData().get(k);
                    /*  814 */
                    Row row = sheet.createRow(1);
                    /*  815 */
                    for (int m = 0; m < listObjectRow.size(); m++) {
                        /*  816 */
                        Cell cell = row.createCell(m);
                        /*  817 */
                        if (checkIsNumber(listObjectRow.get(m)).booleanValue()) {
                            /*  818 */
                            cell.setCellValue(((Double) listObjectRow.get(m)).doubleValue());
                            /*      */
                        }
                        /*  820 */
                        else if (listObjectRow.get(m) == null) {
                            /*  821 */
                            cell.setCellValue("");
                            /*      */
                        } else {
                            /*  823 */
                            cell.setCellValue(String.valueOf(listObjectRow.get(m)));
                            /*      */
                        }
                        /*      */
                        /*      */
                        /*  827 */
                        cell.setCellStyle(style);
                        /*      */
                    }
                    /*      */
                }
                /*      */
            }
            /*  831 */
            try (FileOutputStream outputStream = new FileOutputStream(strPathExportFile)) {
                /*  832 */
                xSSFWorkbook.write(outputStream);
                /*  833 */
                xSSFWorkbook.close();
                /*      */
            }
            /*  835 */
            return Boolean.valueOf(true);
            /*  836 */
        } catch (IOException e) {
            /*  837 */
            LOGGER.error(e);
            /*      */
            /*  839 */
            return Boolean.valueOf(false);
            /*      */
        }
        /*      */
    }

    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    /*      */
    private static Boolean checkIsNumber(Object o) {
        /* 1051 */
        return Boolean.valueOf((o instanceof Integer || o instanceof Double || o instanceof Float || o instanceof Long));
        /*      */
    }
    /*      */
}


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\constants\FunctionCommon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */