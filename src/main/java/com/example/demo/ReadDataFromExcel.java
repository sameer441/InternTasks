package com.example.demo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ReadDataFromExcel {

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
//        System.out.println(url);
                sheets = WorkbookFactory.create(inputStream);
            }
        } catch (IOException | InvalidFormatException ex) {
            ex.getCause();
        }
        this.writeToFile(sheets);
        return sheets;



//        try {
//            FileInputStream file = new FileInputStream(new File("C:\\Users\\DELL #\\Desktop\\Practice\\src\\main\\resources\\sample.xls"));
//            //Create Workbook instance holding reference to .xlsx file
//            HSSFWorkbook workbook = new HSSFWorkbook(file);
//
//            //Get first/desired sheet from the workbook
//        HSSFSheet sheet = workbook.getSheetAt(0);
//
//            //Iterate through each rows one by one
//            Iterator<Row> rowIterator = sheet.iterator();
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                //For each row, iterate through all the columns
//                Iterator<Cell> cellIterator = row.cellIterator();
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    //Check the cell type and format accordingly
//                    switch (cell.getCellType()) {
//                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                    }
//                }
//                System.out.println("");
//            }
////            file.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    private void writeToFile(Workbook sheets) throws IOException {
        File newFile = new File("sample.txt");
        boolean value = newFile.createNewFile();
        if(value){
            System.out.println("New File is created");
        }else{
            System.out.println("This file already exists");
        }
        try{
            FileWriter output = new FileWriter("sample.txt");
            output.write(String.valueOf(sheets));
            output.close();
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }


}
