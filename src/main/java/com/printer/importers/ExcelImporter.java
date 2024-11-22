package com.printer.importers;

import com.printer.controllers.MachineController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelImporter {

    private MachineController machineController;

    public ExcelImporter(MachineController machineController) {
        this.machineController = machineController;
    }

    public void importMachinesFromExcel(String excelFilePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream); 
        Sheet sheet = workbook.getSheetAt(0); 
        
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); 

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String model = row.getCell(0).getStringCellValue();
            String serialNumber = row.getCell(1).getStringCellValue();
            String status = row.getCell(2).getStringCellValue();
            machineController.addMachine(model, serialNumber, status);
        }

        workbook.close();
        fileInputStream.close();
    }
}
