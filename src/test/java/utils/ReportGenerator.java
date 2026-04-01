package utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileOutputStream;

public class ReportGenerator {

    static int totalPassengers = 0;

    public static void addPassengers(int count) {
        totalPassengers += count;
    }

    public static void generateReport() throws Exception {

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Report");

        sheet.createRow(0).createCell(0)
                .setCellValue("Total Passengers Booked Today");

        sheet.getRow(0).createCell(1)
                .setCellValue(totalPassengers);

        FileOutputStream fos = new FileOutputStream("report.xlsx");
        wb.write(fos);
        wb.close();
    }
}