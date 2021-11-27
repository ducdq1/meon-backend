package com.mrlep.meon.utils;

import com.mrlep.meon.dto.object.BillItem;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;

public class ExportExcel {

    public static void main(String args[]) {
        XSSFWorkbook workbook;
        String path = "D:\\DATA\\HOA_DON.xlsx";
        InputStream fs;
        try {

            fs = new FileInputStream(path);
            workbook = new XSSFWorkbook(fs);

            String fileName = "HOA_DON_" + new Date().getTime() / 1000 + ".xlsx";
            String filePath = "D:\\DATA\\temp" + File.separatorChar + fileName;

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeDate(XSSFWorkbook workbook, BillItem billItem) {
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        XSSFRow cloneRow0 = workbook.getSheet("Sheet2").getRow(10);

        XSSFRow row0 = null;
        int countRow = 0;
        int colNum = 0;
        int countQuotation = 0;

        Font fontBold = workbook.getSheet("Sheet2").getRow(4).getCell(1).getCellStyle().getFont();
        Font fontNormal = workbook.getSheet("Sheet2").getRow(5).getCell(1).getCellStyle().getFont();
        Font fontItalic = workbook.getSheet("Sheet2").getRow(4).getCell(1).getCellStyle().getFont();
        Font fontItalicNormal = workbook.getSheet("Sheet2").getRow(4).getCell(1).getCellStyle().getFont();
        fontNormal.setFontName("Times New Roman");
        fontItalic.setFontName("Times New Roman");
        fontBold.setFontName("Times New Roman");

        sheet.getRow(1).getCell(1).setCellValue(billItem.getShopName());
        sheet.getRow(2).getCell(1).setCellValue("Địa chỉ: " + billItem.getShopAddress() + (billItem.getShopPhone() != null ? (" - " + billItem.getShopPhone()) : ""));

        String KH = "Bàn: ";
        XSSFRichTextString TEN_KH = new XSSFRichTextString(KH + String.join(", ",billItem.getTablesName()));
        TEN_KH.applyFont(0, KH.length(), fontBold);
        TEN_KH.applyFont(KH.length(), TEN_KH.length(), fontNormal);
        sheet.getRow(4).getCell(1).setCellValue(TEN_KH);


    }
}
