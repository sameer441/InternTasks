package com.example.demo;

import com.example.demo.ExportModal.ExcelModal;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadDataFromExcel {
    ArrayList<ExcelModal> ExcelModalObj = new ArrayList<ExcelModal>();


    public Workbook getDataFromExcel() throws IOException {

        String file = "https://www.fca.org.uk/publication/data/short-positions-daily-update.xls";
        Workbook sheets = new HSSFWorkbook();
        try {
            URL link = new URL(file);
            HttpsURLConnection getConnection = (HttpsURLConnection) link.openConnection();
            getConnection.setConnectTimeout(10000);
            getConnection.setReadTimeout(10000);
            getConnection.setRequestMethod("GET");
            getConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            try (InputStream inputStream = getConnection.getInputStream()) {
                sheets = WorkbookFactory.create(inputStream);
            }
        } catch (IOException | InvalidFormatException ex) {
            ex.getCause();
        }
        this.writeToFile(sheets);
        return sheets;
    }

    public void writeToFile(Workbook shet) {
        File newFile  = new File("sample.txt");
        int totalSheets = shet.getNumberOfSheets();
        for (int i = 0; i < 1; i++) {
            Sheet sheet = shet.getSheetAt(i);
            int totalRowsInSheet = sheet.getLastRowNum();
            for (int theRowNumber = 0; theRowNumber <= 10; theRowNumber++) {
                Row row = sheet.getRow(theRowNumber);
                if (row == null)
                    continue;
                try {
                    String positionHolder = row.getCell(0).getStringCellValue();
                    String issuerName = row.getCell(1).getStringCellValue();
                    String isin = row.getCell(2).getStringCellValue();
                    Cell netShortingPosition = row.getCell(3);
                    String netShortPosition = netShortingPosition.toString().trim();
                    String positionDate = row.getCell(4).toString();
                    ExcelModal excelModal = new ExcelModal(positionHolder, issuerName, isin,netShortPosition,positionDate);
                    ExcelModalObj.add(excelModal);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
            try{
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Daily Updates");
                int rowNum = 0;
                for(ExcelModal excelobj : ExcelModalObj){
                  Row row = sheet.createRow(rowNum++);
                  createList(excelobj,row);
                }
                FileOutputStream out = new FileOutputStream((new File("dailyupdates.xlsx")));
                workbook.write(out);
                out.close();
            }

            catch (Exception e) {
                e.printStackTrace();
            }
    }

    private static void createList(ExcelModal modalObj, Row row) // creating cells for each row
    {
        Cell cell = row.createCell(0);
        cell.setCellValue(modalObj.getPosHolder());

        cell = row.createCell(1);
        cell.setCellValue(modalObj.getIssuerName());

        cell = row.createCell(2);
        cell.setCellValue(modalObj.getIsIn());

        cell = row.createCell(3);
        cell.setCellValue(modalObj.getNewShortPosition());

        cell = row.createCell(4);
        cell.setCellValue(modalObj.getPositionDate());
    }


}
