package com.mrlep.meon.utils;

import com.mrlep.meon.config.ConfigValue;
import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.BillTablesItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
import com.mrlep.meon.repositories.BillRepository;
import com.mrlep.meon.repositories.OrderItemRepository;
import com.mrlep.meon.repositories.ShopTableRepository;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ExportExcel {
    @Autowired
    private ConfigValue configValue;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ShopTableRepository shopTableRepository;

    public String exportBill(Integer billId) {
        XSSFWorkbook workbook;
        String path = configValue.getTemplateFilePath();
        InputStream fs;
        try {
            System.out.println("Start export bill");
            fs = new FileInputStream(path);
            workbook = new XSSFWorkbook(fs);

            DetailBillResponse billItem = billRepository.getDetailBill(billId);
            if (billItem == null) {
                return null;
            }

            List<BillTablesItem> billTablesItems = shopTableRepository.getTableOfBill(billId);
            List<String> tablesName = new ArrayList<>();
            for (BillTablesItem billTablesItem : billTablesItems) {
                tablesName.add(billTablesItem.getTableName());
            }

            billItem.setTablesName(tablesName);
            List<OrderItem> orderItems = orderItemRepository.getOrderItemOfBill(billId);
            writeData(workbook, billItem, orderItems);
            workbook.removeSheetAt(1);

            String fileName = "HOA_DON_" + new Date().getTime() / 1000 + ".xlsx";
            String filePath = configValue.getExportFilePath() + File.separator + billItem.getShopId().toString() + File.separator + (new Date().getYear() + 1900) + File.separator + (new Date().getMonth() + 1);

            if (!Files.exists(Paths.get(filePath))) {
                Files.createDirectories(Paths.get(filePath));
            }

            String exportFilePath = filePath + File.separatorChar + fileName;

            FileOutputStream fileOut = new FileOutputStream(exportFilePath);
            workbook.write(fileOut);

            fileOut.close();
            String finalFilePath = convertToPdf(exportFilePath,filePath);
            System.out.println("Export bill done....: " + finalFilePath);
            return finalFilePath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void writeData(XSSFWorkbook workbook, DetailBillResponse billItem, List<OrderItem> orderItems) {
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        Font fontBold = workbook.getSheet("Sheet2").getRow(6).getCell(1).getCellStyle().getFont();
        Font fontNormal = workbook.getSheet("Sheet2").getRow(4).getCell(1).getCellStyle().getFont();
        Font fontItalic = workbook.getSheet("Sheet2").getRow(2).getCell(0).getCellStyle().getFont();
        fontNormal.setFontName("Times New Roman");
        fontItalic.setFontName("Times New Roman");
        fontBold.setFontName("Times New Roman");

        sheet.getRow(1).getCell(0).setCellValue(billItem.getShopName());
        sheet.getRow(2).getCell(0).setCellValue("Địa chỉ: " + billItem.getShopAddress() + (billItem.getShopPhone() != null ? (" - " + billItem.getShopPhone()) : ""));

        String KH = "Bàn: ";
        XSSFRichTextString TEN_KH = new XSSFRichTextString(KH + String.join(", ", billItem.getTablesName()));
        TEN_KH.applyFont(0, KH.length(), fontBold);
        TEN_KH.applyFont(KH.length(), TEN_KH.length(), fontNormal);
        sheet.getRow(4).getCell(1).setCellValue(TEN_KH);


        String TIME = "Thời gian: ";
        XSSFRichTextString TIME_TEXT = new XSSFRichTextString(TIME + billItem.getCreateDate());
        TIME_TEXT.applyFont(0, TIME.length(), fontBold);
        TIME_TEXT.applyFont(TIME.length(), TIME_TEXT.length(), fontNormal);
        sheet.getRow(4).getCell(4).setCellValue(TIME_TEXT);

        XSSFRow cloneRow0 = workbook.getSheet("Sheet2").getRow(7);

        XSSFRow row0 = null;
        int colNum = 0;
        int stt = 0;
        int rowNum = 7;
        for (OrderItem orderItem : orderItems) {
            stt++;
            colNum = 0;
            sheet.shiftRows(rowNum, sheet.getLastRowNum(), 1);
            row0 = createRow(rowNum, sheet, cloneRow0);
            rowNum++;
            createCell(colNum++, row0, cloneRow0.getCell(0).getCellStyle(), "" + stt);
            createCell(colNum++, row0, cloneRow0.getCell(1).getCellStyle(), orderItem.getMenuName());
            createCell(colNum++, row0, cloneRow0.getCell(2).getCellStyle(), FnCommon.formatNumber(orderItem.getAmount()));
            createCell(colNum++, row0, cloneRow0.getCell(3).getCellStyle(), FnCommon.formatNumber(orderItem.getPrice()));
            createCell(colNum++, row0, cloneRow0.getCell(4).getCellStyle(), FnCommon.formatNumber(orderItem.getDiscountMoney()));
            createCell(colNum++, row0, cloneRow0.getCell(5).getCellStyle(), FnCommon.formatNumber(orderItem.getMoney()));
        }


        sheet.getRow(rowNum).getCell(5).setCellValue(FnCommon.formatNumber(billItem.getPreMoney()));
        sheet.getRow(rowNum + 1).getCell(5).setCellValue(FnCommon.formatNumber(billItem.getVat()));
        sheet.getRow(rowNum + 2).getCell(5).setCellValue(FnCommon.formatNumber(billItem.getSubMoney()));
        sheet.getRow(rowNum + 3).getCell(5).setCellValue(FnCommon.formatNumber(billItem.getTotalMoney()));
    }

    private static XSSFRow createRow(int rowNum, XSSFSheet sheet, XSSFRow cloneRow) {
        XSSFRow row = sheet.createRow(rowNum);
        row.setHeight(cloneRow.getHeight());

        return row;
    }

    private static void createCell(int cellNum, XSSFRow row, CellStyle style, String content) {
        row.createCell(cellNum).setCellValue(content == null ? "" : !content.equals(": null") ? content : ":");
        row.getCell(cellNum).setCellStyle(style);
        row.getCell(cellNum).setCellStyle(style);


    }


    public static String convertToPdf(String pathInput,String folder) {

        String filePathOut = pathInput.replace(".xlsx", ".pdf");

        String command = "libreoffice --headless --convert-to pdf  --outdir " +folder +" " + pathInput;

        String osName = System.getProperty("os.name");
        String[] params;

        if (osName.toLowerCase().contains("window")) {
            command = "D:\\DATA\\temp\\OfficeToPDF.exe";
            params = new String[3];
            params[0] = command;
            params[1] = pathInput;
            params[2] = filePathOut;
        } else {
            params = new String[2];
            params[0] = command;
            params[1] = pathInput;
        }

        System.out.println("Export PDF Command:  " + command);

        try {
            Runtime.getRuntime().exec(params).waitFor();
            try {
                File f = new File(pathInput);
                if (f.exists()) {
                    f.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return filePathOut;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        XSSFWorkbook workbook;
        String path = "D:\\DATA\\HOA_DON.xlsx";
        InputStream fs;
        try {
            System.out.println("Start export bill");
            fs = new FileInputStream(path);
            workbook = new XSSFWorkbook(fs);

            DetailBillResponse billItem = new DetailBillResponse();
            billItem.setShopName("Quán A Hoàng");
            billItem.setShopAddress("234 nguyễn văn linh");
            billItem.setShopPhone("034555666");
            billItem.setCreateDate("33/44/2021");
            List<String> tableNames = new ArrayList<>();
            tableNames.add("Bàn 01");
            billItem.setTablesName(tableNames);
            billItem.setPreMoney(50000);
            billItem.setTotalMoney(100000);
            billItem.setSubMoney(20000);
            billItem.setVat(10.5);

            List<OrderItem> orderItems = new ArrayList<>();
            OrderItem orderItem = new OrderItem();
            orderItem.setMenuName("Cá hồi");
            orderItem.setPrice(10000);
            orderItem.setAmount(10.0);
            orderItem.setDiscountType(1);
            orderItem.setDiscountValue(10.0);
            orderItem.setDiscountMoney(1000);
            orderItem.setMoney(10000000);

            orderItems.add(orderItem);
            orderItems.add(orderItem);
            orderItems.add(orderItem);
            orderItems.add(orderItem);
            orderItems.add(orderItem);
            orderItems.add(orderItem);

            writeData(workbook, billItem, orderItems);
            workbook.removeSheetAt(1);

            String fileName = "HOA_DON_" + new Date().getTime() / 1000 + ".xlsx";
            String filePath = "D:\\DATA\\temp" + File.separatorChar + fileName;

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);

            fileOut.close();
            System.out.println("Export bill done....");
        } catch (Exception e) {

        }
    }
}
