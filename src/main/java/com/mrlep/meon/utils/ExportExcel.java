package com.mrlep.meon.utils;

import com.mrlep.meon.dto.object.BillItem;
import com.mrlep.meon.dto.object.OrderItem;
import com.mrlep.meon.dto.response.DetailBillResponse;
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


            writeDate( workbook,  billItem);
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

    private static void writeDate(XSSFWorkbook workbook, DetailBillResponse billItem) {
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        XSSFRow cloneRow0 = workbook.getSheet("Sheet2").getRow(10);

        XSSFRow row0 = null;
        int countRow = 0;
        int colNum = 0;
        int countQuotation = 0;

        Font fontBold = workbook.getSheet("Sheet2").getRow(6).getCell(1).getCellStyle().getFont();
        Font fontNormal = workbook.getSheet("Sheet2").getRow(4).getCell(1).getCellStyle().getFont();
        Font fontItalic = workbook.getSheet("Sheet2").getRow(2).getCell(0).getCellStyle().getFont();
        fontNormal.setFontName("Times New Roman");
        fontItalic.setFontName("Times New Roman");
        fontBold.setFontName("Times New Roman");

        sheet.getRow(1).getCell(0).setCellValue(billItem.getShopName());
        sheet.getRow(2).getCell(0).setCellValue("Địa chỉ: " + billItem.getShopAddress() + (billItem.getShopPhone() != null ? (" - " + billItem.getShopPhone()) : ""));

        String KH = "Bàn: ";
        XSSFRichTextString TEN_KH = new XSSFRichTextString(KH + String.join(", ",billItem.getTablesName()));
        TEN_KH.applyFont(0, KH.length(), fontBold);
        TEN_KH.applyFont(KH.length(), TEN_KH.length(), fontNormal);
        sheet.getRow(4).getCell(1).setCellValue(TEN_KH);


        String TIME = "Thời gian: ";
        XSSFRichTextString TIME_TEXT = new XSSFRichTextString(TIME + billItem.getCreateDate());
        TIME_TEXT.applyFont(0, TIME.length(), fontBold);
        TIME_TEXT.applyFont(TIME.length(), TIME_TEXT.length(), fontNormal);
        sheet.getRow(4).getCell(4).setCellValue(TIME_TEXT);


    }


}
