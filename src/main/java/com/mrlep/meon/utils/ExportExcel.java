package com.mrlep.meon.utils;

import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportExcel {

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

            writeDate(workbook, billItem, orderItems);
            workbook.removeSheetAt(1);

            String fileName = "HOA_DON_" + new Date().getTime() / 1000 + ".xlsx";
            String filePath = "D:\\DATA\\temp" + File.separatorChar + fileName;

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);

            fileOut.close();
            System.out.println("Export bill done....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeDate(XSSFWorkbook workbook, DetailBillResponse billItem, List<OrderItem> orderItems) {
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


        sheet.getRow(rowNum+3).getCell(5).setCellValue(FnCommon.formatNumber(billItem.getTotalMoney()));


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
}
