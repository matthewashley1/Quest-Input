package sample;

/*
 * Created by Matthew Ashley on 1/24/17.
 */

import VTFXcontrols.Functions;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.awt.Color;
import java.io.*;
import java.util.Objects;

class ExcelEditing {

    private boolean fileCreated = true;

    private final ConfirmMessages errorCheck = new ConfirmMessages(); /* Initializer for ConfirmMessages class! */
    private final Functions functions = new Functions(); /* Initializer for the VTFXcontrols Function class! */

    /* Method for inputting all data entered into the Estimate Excel file! */
    boolean estimateExcel(String data[], int estimateNumberOfItems, String fileSavePath, Boolean check) {

        try {

            int[] calculatorColNum = {1, 2, 4, 6, 7, 10, 12, 17, 18, 23, 24, 25, 26};
            int inputCount = 7;

            /* References excel file template! */
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample/CustomerFiles/Excel/Estimate.xlsx");

            XSSFWorkbook estimateWorkbook = new XSSFWorkbook(inputStream);
            estimateWorkbook.setForceFormulaRecalculation(true);

            XSSFSheet estimateSheet = estimateWorkbook.getSheet("Estimate");
            XSSFSheet calculatorSheet = estimateWorkbook.getSheet("Calculator");

            /* Setup for default font for cell styles! */
            XSSFFont font = estimateWorkbook.createFont();
            font.setColor(XSSFFont.DEFAULT_FONT_COLOR);
            font.setFontHeight(11);

            /* Setup for formatting cells to Accounting format! */
            XSSFCellStyle accountingStyle = estimateWorkbook.createCellStyle();
            DataFormat accountingFormat = estimateWorkbook.createDataFormat();

            accountingStyle.setDataFormat(accountingFormat.getFormat("_(\"$\"* #,##0.00_);_(\"$\"* \\(#,##0.00\\);_(\"$\"* \"-\"??_);_(@_)"));
            accountingStyle.setAlignment(HorizontalAlignment.CENTER);
            accountingStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            accountingStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            accountingStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            accountingStyle.setBorderLeft(BorderStyle.THIN);
            accountingStyle.setBorderRight(BorderStyle.THIN);
            accountingStyle.setFont(font);

            /* Setup for formatting cells to Number format! */
            XSSFCellStyle numberStyle = estimateWorkbook.createCellStyle();
            DataFormat numberFormat = estimateWorkbook.createDataFormat();

            numberStyle.setDataFormat(numberFormat.getFormat("0"));
            numberStyle.setAlignment(HorizontalAlignment.CENTER);
            numberStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            numberStyle.setFont(font);

            /* Setup for entering data in existing spreadsheet! */
            Cell cellDQPData = estimateSheet.getRow(1).getCell(0);

            if (Objects.isNull(cellDQPData)) {
                cellDQPData = estimateSheet.createRow(1).createCell(0);
            }
            cellDQPData.setCellValue(data[0]);

            Cell cellDateData = estimateSheet.getRow(1).getCell(4);

            if (Objects.isNull(cellDateData)) {
                cellDateData = estimateSheet.createRow(1).createCell(4);
            }
            cellDateData.setCellValue(data[1]);

            Cell cellInvoiceData = estimateSheet.getRow(1).getCell(6);

            if (Objects.isNull(cellInvoiceData)) {
                cellInvoiceData = estimateSheet.createRow(1).createCell(6);
            }
            cellInvoiceData.setCellValue(data[2]);

            Cell cellNameAddressData = estimateSheet.getRow(4).getCell(0);

            if (Objects.isNull(cellNameAddressData)) {
                cellNameAddressData = estimateSheet.createRow(4).createCell(0);
            }
            cellNameAddressData.setCellValue(data[3] + " " + data[4]);

            Cell cellLeadTimeData = calculatorSheet.getRow(27).getCell(10);

            if (Objects.isNull(cellLeadTimeData)) {
                cellLeadTimeData = calculatorSheet.createRow(27).createCell(10);
            }
            cellLeadTimeData.setCellValue(data[5]);

            Cell cellEstimateNotes = estimateSheet.getRow(4).getCell(5);

            if (Objects.isNull(cellEstimateNotes)) {
                cellEstimateNotes = estimateSheet.createRow(4).createCell(5);
            }
            cellEstimateNotes.setCellValue(data[6]);

            /* Logic for doing data inputs for the calculator sheet! */
            for (int x = 3; x < (estimateNumberOfItems + 3); x++) {

                for (int aCalculatorColNum : calculatorColNum) {

                    Cell cellInput = calculatorSheet.getRow(x).getCell(aCalculatorColNum);

                    if (Objects.isNull(cellInput)) {

                        cellInput = calculatorSheet.createRow(x).createCell(aCalculatorColNum);
                    }

                    if (check && (aCalculatorColNum > 2) || (aCalculatorColNum == 10)) {

                        cellInput.setCellStyle(numberStyle);
                        cellInput.setCellValue((int) Double.parseDouble(functions.removeComas(data[inputCount])));

                    } else {

                        cellInput.setCellValue(data[inputCount]);
                    }

                    inputCount += 1;
                }
            }

            FileOutputStream output = new FileOutputStream(new File(fileSavePath));
            estimateWorkbook.write(output);
            output.close();

        } catch ( Exception e ) {
            errorCheck.infoEnteredError("Estimate file failed to create", e.getMessage());
            fileCreated = false;
            e.printStackTrace();
        }

        return fileCreated;
    }

    /* Method for inputting all data entered into the DesignQuest Traveler Excel file! */
    boolean travelerExcel(String data[], int travelerNumberOfProcesses, String fileSavePath, Boolean check) {

        try {

            int[] calculatorColNum = {0, 2, 3, 5};
            int inputCount = 8;

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample/CustomerFiles/Excel/ShopTraveler.xlsx");

            XSSFWorkbook travelerWorkbook = new XSSFWorkbook(inputStream);
            travelerWorkbook.setForceFormulaRecalculation(true);

            XSSFSheet travelerSheet = travelerWorkbook.getSheet("Shop Traveler");

            XSSFFont font = travelerWorkbook.createFont();
            font.setColor(XSSFFont.DEFAULT_FONT_COLOR);
            font.setFontHeight(13);

            DataFormat format = travelerWorkbook.createDataFormat();
            XSSFCellStyle number = travelerWorkbook.createCellStyle();
            number.setDataFormat(format.getFormat("0.0"));
            number.setAlignment(HorizontalAlignment.CENTER);
            number.setVerticalAlignment(VerticalAlignment.CENTER);
            number.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            number.setRightBorderColor(IndexedColors.BLACK.getIndex());
            number.setTopBorderColor(IndexedColors.BLACK.getIndex());
            number.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            number.setBorderLeft(BorderStyle.THIN);
            number.setBorderRight(BorderStyle.THIN);
            number.setBorderTop(BorderStyle.THIN);
            number.setBorderBottom(BorderStyle.THIN);
            number.setFont(font);

            /* Setup for entering data in existing spreadsheet! */
            Cell jobNumber = travelerSheet.getRow(0).getCell(1);

            if (Objects.isNull(jobNumber)) {
                jobNumber = travelerSheet.createRow(0).createCell(1);
            }
            jobNumber.setCellValue(data[0]);

            Cell dateReceived = travelerSheet.getRow(0).getCell(4);

            if (Objects.isNull(dateReceived)) {
                dateReceived = travelerSheet.createRow(0).createCell(4);
            }
            dateReceived.setCellValue(data[1]);

            Cell customer = travelerSheet.getRow(1).getCell(1);

            if (Objects.isNull(customer)) {
                customer = travelerSheet.createRow(1).createCell(1);
            }
            customer.setCellValue(data[2]);

            Cell quantity = travelerSheet.getRow(1).getCell(4);

            if (Objects.isNull(quantity)) {
                quantity = travelerSheet.createRow(1).createCell(4);
            }
            quantity.setCellValue(data[3]);

            Cell partNumber = travelerSheet.getRow(2).getCell(1);

            if (Objects.isNull(partNumber)) {
                partNumber = travelerSheet.createRow(2).createCell(1);
            }
            partNumber.setCellValue(data[4]);

            Cell dateRequired = travelerSheet.getRow(2).getCell(4);

            if (Objects.isNull(dateRequired)) {
                dateRequired = travelerSheet.createRow(2).createCell(4);
            }
            dateRequired.setCellValue(data[5]);

            Cell poNumber = travelerSheet.getRow(3).getCell(1);

            if (Objects.isNull(poNumber)) {
                poNumber = travelerSheet.createRow(3).createCell(1);
            }
            poNumber.setCellValue(data[6]);

            Cell completedDate = travelerSheet.getRow(3).getCell(4);

            if (Objects.isNull(completedDate)) {
                completedDate = travelerSheet.createRow(3).createCell(4);
            }
            completedDate.setCellValue(data[7]);

            for (int x = 7; x < travelerNumberOfProcesses + 7; x++) {

                for (int aCalculatorColNum : calculatorColNum) {

                    Cell cellInput = travelerSheet.getRow(x).getCell(aCalculatorColNum);

                    if (Objects.isNull(cellInput)) {

                        cellInput = travelerSheet.createRow(x).createCell(aCalculatorColNum);
                    }

                    if (check && (aCalculatorColNum == 2)) {

                        cellInput.setCellStyle(number);
                        cellInput.setCellValue((int) (Double.parseDouble(functions.removeComas(data[inputCount]))));

                    } else if (check && (aCalculatorColNum > 2)) {

                        if (functions.checkCurrencyFormat(data[inputCount])) {

                            cellInput.setCellStyle(number);
                            cellInput.setCellValue((int) (Double.parseDouble(functions.removeComas(data[inputCount]))));

                        } else {

                            cellInput.setCellValue(data[inputCount]);
                        }

                    } else {

                        cellInput.setCellValue(data[inputCount]);
                    }

                    inputCount += 1;
                }
            }

            Cell notes = travelerSheet.getRow(19).getCell(0);

            if (notes == null) {
                notes = travelerSheet.createRow(19).createCell(0);
            }
            notes.setCellValue("Notes: " + data[44]);

            FileOutputStream output = new FileOutputStream(new File(fileSavePath));
            travelerWorkbook.write(output);
            output.close();

        } catch ( Exception e ) {
            errorCheck.infoEnteredError("Traveler file failed to create", e.getMessage());
            fileCreated = false;
            e.printStackTrace();
        }

        return fileCreated;
    }

    /* Method for inputting all data entered into the DesignQuest Packing List Excel file! */
    boolean packingListExcel(String data[], int packingListNumberOfItems, String fileSavePath, Boolean check) {

        try {

            int[] calculatorColNum = {0, 1, 5};
            int inputCount = 5;

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample/CustomerFiles/Excel/DesignQuestPackingListTemplate.xlsx");

            XSSFWorkbook packingListWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet packingListSheet = packingListWorkbook.getSheet("Packing Slip");

            /* Setup of font for each cell style! */
            XSSFFont font = packingListWorkbook.createFont();
            font.setColor(XSSFFont.DEFAULT_FONT_COLOR);
            font.setFontHeight(8);

            /* Default colors of cells and border of cells on Sheet of Workbook! */
            XSSFColor cellFillColor = new XSSFColor(new Color(232, 238, 238));
            XSSFColor cellBorderColor = new XSSFColor(new Color(209, 222, 222));

            /* Formatting for cells with a white background! */
            XSSFCellStyle accountingStyleWhite = packingListWorkbook.createCellStyle();
            DataFormat accountingFormatWhite = packingListWorkbook.createDataFormat();

            /* Formatting for cells with a blue background! */
            XSSFCellStyle accountingStyleBlue = packingListWorkbook.createCellStyle();
            DataFormat accountingFormatBlue = packingListWorkbook.createDataFormat();

            XSSFCellStyle[] styles = {accountingStyleWhite, accountingStyleBlue};
            DataFormat[] formats = {accountingFormatWhite, accountingFormatBlue};

            for (int z = 0; z < 2; z++) {

                if (z == 0) {

                    styles[z].setFillForegroundColor(IndexedColors.WHITE.getIndex());

                } else {

                    styles[z].setFillForegroundColor(cellFillColor);
                }

                styles[z].setDataFormat(formats[z].getFormat("0"));
                styles[z].setAlignment(HorizontalAlignment.CENTER);
                styles[z].setVerticalAlignment(VerticalAlignment.CENTER);
                styles[z].setBorderBottom(BorderStyle.THIN);
                styles[z].setBorderTop(BorderStyle.THIN);
                styles[z].setBorderLeft(BorderStyle.THIN);
                styles[z].setBorderRight(BorderStyle.THIN);
                styles[z].setBottomBorderColor(cellBorderColor);
                styles[z].setTopBorderColor(cellBorderColor);
                styles[z].setLeftBorderColor(cellBorderColor);
                styles[z].setRightBorderColor(cellBorderColor);
                styles[z].setFillPattern(FillPatternType.SOLID_FOREGROUND);
                styles[z].setFont(font);
            }

            /* Setup for entering data in existing spreadsheet! */
            Cell shipToAddress = packingListSheet.getRow(8).getCell(0);

            if (Objects.isNull(shipToAddress)) {
                shipToAddress = packingListSheet.createRow(8).createCell(0);
            }
            shipToAddress.setCellValue(data[0]);

            Cell billToAddress = packingListSheet.getRow(8).getCell(3);

            if (Objects.isNull(billToAddress)) {
                billToAddress = packingListSheet.createRow(8).createCell(3);
            }
            billToAddress.setCellValue(data[1]);

            Cell orderDate = packingListSheet.getRow(15).getCell(0);

            if (Objects.isNull(orderDate)) {
                orderDate = packingListSheet.createRow(15).createCell(0);
            }
            orderDate.setCellValue(data[2]);

            Cell orderNumber = packingListSheet.getRow(15).getCell(2);

            if (Objects.isNull(orderNumber)) {
                orderNumber = packingListSheet.createRow(15).createCell(2);
            }

            if (check) {
                orderNumber.setCellStyle(accountingStyleWhite);
                orderNumber.setCellValue((int) (Double.parseDouble(functions.removeComas(data[3]))));
            } else {
                orderNumber.setCellValue(data[3]);
            }

            Cell job = packingListSheet.getRow(15).getCell(4);

            if (Objects.isNull(job)) {
                job = packingListSheet.createRow(15).createCell(4);
            }

            if (check) {
                job.setCellStyle(accountingStyleWhite);
                job.setCellValue((int) (Double.parseDouble(functions.removeComas(data[4]))));
            } else {
                job.setCellValue(data[4]);
            }

            for (int x = 18; x < (packingListNumberOfItems + 18); x++) {

                for (int aCalculatorColNum : calculatorColNum) {

                    Cell cellInput = packingListSheet.getRow(x).getCell(aCalculatorColNum);

                    if (Objects.isNull(cellInput)) {

                        cellInput = packingListSheet.createRow(x).createCell(aCalculatorColNum);
                    }

                    if (check && (aCalculatorColNum == 5)) {

                        if (x % 2 == 0) {

                            cellInput.setCellStyle(accountingStyleWhite);

                        } else {

                            cellInput.setCellStyle(accountingStyleBlue);
                        }

                        cellInput.setCellValue((int) (Double.parseDouble(functions.removeComas(data[inputCount]))));

                    } else {

                        cellInput.setCellValue(data[inputCount]);
                    }
                }
            }

            FileOutputStream output = new FileOutputStream(new File(fileSavePath));
            packingListWorkbook.write(output);
            output.close();

        } catch ( Exception e ) {
            errorCheck.infoEnteredError("Packing List file failed to create", e.getMessage());
            fileCreated = false;
            e.printStackTrace();
        }

        return fileCreated;
    }


} /* Closing bracket for ExcelEditing class! */
