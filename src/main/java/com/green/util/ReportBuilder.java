package com.green.util;

import com.green.entity.Activity;
import com.green.entity.Member;
import com.green.exception.CustomApplicationException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReportBuilder {

    public String generateReportFile(LocalDate date) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float bottomMargin = 70;
            float yPosition = yStart;
            int rows = 10;
            int cols = 3;
            float rowHeight = 20f;
            float tableHeight = rowHeight * (rows + 1); // высота таблицы

            String[] headers = {"Member", "Activity", "Time"}; // пример заголовков

            float[] columnWidths = {tableWidth / cols, tableWidth / cols, tableWidth / cols};

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            for (int i = 0; i < cols; i++) {
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(margin + i * columnWidths[i] + 5, yStart - 15);
                contentStream.showText(headers[i]);
                contentStream.endText();
            }

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String cellValue = "Row " + i + ", Col " + j; // пример данных
                    contentStream.beginText();
                    contentStream.moveTextPositionByAmount(margin + j * columnWidths[j] + 5, yStart - (i + 2) * rowHeight - 15);
                    contentStream.showText(cellValue);
                    contentStream.endText();
                }
            }

            contentStream.close();

            String pdfFilePath = "src/main/resources/report-test.pdf";
            document.save(pdfFilePath);
            document.close();

            System.out.println("PDF created: " + pdfFilePath);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return "src/main/resources/report-test.pdf";
    }


    public String generateReportFileExcel(LocalDate date) {
        Workbook workBook = null;
        try {
            workBook = new XSSFWorkbook(OPCPackage.open("src/main/resources/report-template.xlsx"));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            throw new CustomApplicationException("Report template file missing! Check resources/report-template.xlsx", e);
        }

        Sheet sheet = workBook.getSheetAt(0);
        System.out.println(sheet.getLastRowNum());
        int startRow = 2;
        int row = startRow;
        int cell = 1;

        Map<Member, List<Activity>> map = DataFetch.getActivitiesByDate(date);
        for (Map.Entry<Member, List<Activity>> entry : map.entrySet()) {
            Member key = entry.getKey();
            List<Activity> value = entry.getValue();
            Row memberRow = sheet.createRow(row);
            memberRow.createCell(cell).setCellValue(key.getName());
            row++;

            for (Activity activity : value) {
                Row activityRow = sheet.createRow(row);
                Cell activityCellSubject = activityRow.createCell(cell + 1);
                activityCellSubject.setCellValue(activity.getSubject());

                Cell activityCellTime = activityRow.createCell(cell + 2);
                activityCellTime.setCellValue(String.valueOf(activity.getTookTime()));

                row++;
            }
        }

        CellRangeAddress dataRange = new CellRangeAddress(startRow, row - 1, cell, cell + 2);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, dataRange, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, dataRange, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, dataRange, sheet);

        String excelFilePath = "src/main/resources/tmp/report.xlsx";
        try {
            FileOutputStream fileOut = new FileOutputStream(excelFilePath);
            workBook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return excelFilePath;
    }

    private String convertExcelToPdf(String excelFilePath) throws IOException {
        FileInputStream fis = new FileInputStream(excelFilePath);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
        xssfWorkbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

        fis.close();
        xssfWorkbook.close();
        return "src/main/resources/tmp/report.pdf";
    }


}
