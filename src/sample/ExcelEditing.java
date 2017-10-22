package sample;

/*
 * Created by Matthew Ashley on 1/24/17.
 */

import VTFXcontrols.Functions;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Objects;

public class ExcelEditing {

    private final ConfirmMessages errorCheck = new ConfirmMessages(); /* Initializer for ConfirmMessages class! */
    private final Functions functions = new Functions(); /* Initializer for the VTFXcontrols Function class! */

    /* Method for inputting all data entered into the Estimate Excel file! */
    @SuppressWarnings("ConstantConditions")
    public void estimateExcel(String data[], int estimateNumberOfItems, String fileSavePath) {

        try {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample/CustomerFiles/Excel/EstimateXL.xlsx");

            XSSFWorkbook estimateWorkbook = new XSSFWorkbook(inputStream);

            XSSFSheet estimateSheet = estimateWorkbook.getSheet("Estimate");

            /* Setup for entering data in existing spreadsheet! */
            Cell cellDQPData = estimateSheet.getRow(1).getCell(0);

            if (cellDQPData == null) {
                cellDQPData = estimateSheet.createRow(1).createCell(0);
            }
            cellDQPData.setCellValue(data[0]);

            Cell cellDateData = estimateSheet.getRow(1).getCell(3);

            if (cellDateData == null) {
                cellDateData = estimateSheet.createRow(1).createCell(3);
            }
            cellDateData.setCellValue(data[1]);

            Cell cellInvoiceData = estimateSheet.getRow(1).getCell(5);

            if (cellInvoiceData == null) {
                cellInvoiceData = estimateSheet.createRow(1).createCell(5);
            }
            cellInvoiceData.setCellValue(data[2]);

            Cell cellNameAddressData = estimateSheet.getRow(4).getCell(0);

            if (cellNameAddressData == null) {
                cellNameAddressData = estimateSheet.createRow(4).createCell(0);
            }
            cellNameAddressData.setCellValue(data[3] + " " + data[4]);

            if (estimateNumberOfItems >= 1) {

                Cell cellItem1 = estimateSheet.getRow(8).getCell(0);

                if (cellItem1 == null) {
                    cellItem1 = estimateSheet.createRow(8).createCell(0);
                }
                cellItem1.setCellValue(data[5]);

                Cell cellDescription1 = estimateSheet.getRow(8).getCell(1);

                if (cellDescription1 == null) {
                    cellDescription1 = estimateSheet.createRow(8).createCell(1);
                }
                cellDescription1.setCellValue(data[6]);

                Cell cellQty1 = estimateSheet.getRow(8).getCell(4);

                if (cellQty1 == null) {
                    cellQty1 = estimateSheet.createRow(8).createCell(4);
                }
                cellQty1.setCellValue((int)(Double.parseDouble(functions.removeComas(data[7]))));

                Cell cellRate1 = estimateSheet.getRow(8).getCell(5);

                if (cellRate1 == null) {
                    cellRate1 = estimateSheet.createRow(8).createCell(5);
                }
                cellRate1.setCellValue(Double.parseDouble(functions.removeComas(data[8])));

                Cell cellTotal1 = estimateSheet.getRow(8).getCell(6);

                if (cellTotal1 == null) {
                    cellTotal1 = estimateSheet.createRow(8).createCell(6);
                }
                cellTotal1.setCellFormula("F9*E9");

            }

            if (estimateNumberOfItems >= 2) {

                Cell cellItem2 = estimateSheet.getRow(9).getCell(0);

                if (cellItem2 == null) {
                    cellItem2 = estimateSheet.createRow(9).createCell(0);
                }
                cellItem2.setCellValue(data[9]);

                Cell cellDescription2 = estimateSheet.getRow(9).getCell(1);

                if (cellDescription2 == null) {
                    cellDescription2 = estimateSheet.createRow(9).createCell(1);
                }
                cellDescription2.setCellValue(data[10]);

                Cell cellQty2 = estimateSheet.getRow(9).getCell(4);

                if (cellQty2 == null) {
                    cellQty2 = estimateSheet.createRow(9).createCell(4);
                }
                cellQty2.setCellValue((int)(Double.parseDouble(functions.removeComas(data[11]))));

                Cell cellRate2 = estimateSheet.getRow(9).getCell(5);

                if (cellRate2 == null) {
                    cellRate2 = estimateSheet.createRow(9).createCell(5);
                }
                cellRate2.setCellValue(Double.parseDouble(data[12]));

                Cell cellTotal2 = estimateSheet.getRow(9).getCell(6);

                if (cellTotal2 == null) {
                    cellTotal2 = estimateSheet.createRow(9).createCell(6);
                }
                cellTotal2.setCellFormula("F10*E10");

            }

            if (estimateNumberOfItems >= 3) {

                Cell cellItem3 = estimateSheet.getRow(10).getCell(0);

                if (cellItem3 == null) {
                    cellItem3 = estimateSheet.createRow(10).createCell(0);
                }
                cellItem3.setCellValue(data[13]);

                Cell cellDescription3 = estimateSheet.getRow(10).getCell(1);

                if (cellDescription3 == null) {
                    cellDescription3 = estimateSheet.createRow(10).createCell(1);
                }
                cellDescription3.setCellValue(data[14]);

                Cell cellQty3 = estimateSheet.getRow(10).getCell(4);

                if (cellQty3 == null) {
                    cellQty3 = estimateSheet.createRow(10).createCell(4);
                }
                cellQty3.setCellValue((int)(Double.parseDouble(functions.removeComas(data[15]))));

                Cell cellRate3 = estimateSheet.getRow(10).getCell(5);

                if (cellRate3 == null) {
                    cellRate3 = estimateSheet.createRow(10).createCell(5);
                }
                cellRate3.setCellValue(Double.parseDouble(data[16]));

                Cell cellTotal3 = estimateSheet.getRow(10).getCell(6);

                if (cellTotal3 == null) {
                    cellTotal3 = estimateSheet.createRow(10).createCell(6);
                }
                cellTotal3.setCellFormula("F11*E11");

            }

            if (estimateNumberOfItems >= 4) {

                Cell cellItem4 = estimateSheet.getRow(11).getCell(0);

                if (cellItem4 == null) {
                    cellItem4 = estimateSheet.createRow(11).createCell(0);
                }
                cellItem4.setCellValue(data[17]);

                Cell cellDescription4 = estimateSheet.getRow(11).getCell(1);

                if (cellDescription4 == null) {
                    cellDescription4 = estimateSheet.createRow(11).createCell(1);
                }
                cellDescription4.setCellValue(data[18]);

                Cell cellQty4 = estimateSheet.getRow(11).getCell(4);

                if (cellQty4 == null) {
                    cellQty4 = estimateSheet.createRow(11).createCell(4);
                }
                cellQty4.setCellValue((int)(Double.parseDouble(functions.removeComas(data[19]))));

                Cell cellRate4 = estimateSheet.getRow(11).getCell(5);

                if (cellRate4 == null) {
                    cellRate4 = estimateSheet.createRow(11).createCell(5);
                }
                cellRate4.setCellValue(Double.parseDouble(data[20]));

                Cell cellTotal4 = estimateSheet.getRow(11).getCell(6);

                if (cellTotal4 == null) {
                    cellTotal4 = estimateSheet.createRow(11).createCell(6);
                }
                cellTotal4.setCellFormula("F12*E12");

            }

            if (estimateNumberOfItems >= 5) {

                Cell cellItem5 = estimateSheet.getRow(12).getCell(0);

                if (cellItem5 == null) {
                    cellItem5 = estimateSheet.createRow(12).createCell(0);
                }
                cellItem5.setCellValue(data[21]);

                Cell cellDescription5 = estimateSheet.getRow(12).getCell(1);

                if (cellDescription5 == null) {
                    cellDescription5 = estimateSheet.createRow(12).createCell(1);
                }
                cellDescription5.setCellValue(data[22]);

                Cell cellQty5 = estimateSheet.getRow(12).getCell(4);

                if (cellQty5 == null) {
                    cellQty5 = estimateSheet.createRow(12).createCell(4);
                }
                cellQty5.setCellValue((int)(Double.parseDouble(functions.removeComas(data[23]))));

                Cell cellRate5 = estimateSheet.getRow(12).getCell(5);

                if (cellRate5 == null) {
                    cellRate5 = estimateSheet.createRow(12).createCell(5);
                }
                cellRate5.setCellValue(Double.parseDouble(data[24]));

                Cell cellTotal5 = estimateSheet.getRow(12).getCell(6);

                if (cellTotal5 == null) {
                    cellTotal5 = estimateSheet.createRow(12).createCell(6);
                }
                cellTotal5.setCellFormula("F13*E13");

            }

            if (estimateNumberOfItems >= 6) {

                Cell cellItem6 = estimateSheet.getRow(13).getCell(0);

                if (cellItem6 == null) {
                    cellItem6 = estimateSheet.createRow(13).createCell(0);
                }
                cellItem6.setCellValue(data[25]);

                Cell cellDescription6 = estimateSheet.getRow(13).getCell(1);

                if (cellDescription6 == null) {
                    cellDescription6 = estimateSheet.createRow(13).createCell(1);
                }
                cellDescription6.setCellValue(data[26]);

                Cell cellQty6 = estimateSheet.getRow(13).getCell(4);

                if (cellQty6 == null) {
                    cellQty6 = estimateSheet.createRow(13).createCell(4);
                }
                cellQty6.setCellValue((int)(Double.parseDouble(functions.removeComas(data[27]))));

                Cell cellRate6 = estimateSheet.getRow(13).getCell(5);

                if (cellRate6 == null) {
                    cellRate6 = estimateSheet.createRow(13).createCell(5);
                }
                cellRate6.setCellValue(Double.parseDouble(data[28]));

                Cell cellTotal6 = estimateSheet.getRow(13).getCell(6);

                if (cellTotal6 == null) {
                    cellTotal6 = estimateSheet.createRow(13).createCell(6);
                }
                cellTotal6.setCellFormula("F14*E14");

            }

            if (estimateNumberOfItems >= 7) {

                Cell cellItem7 = estimateSheet.getRow(14).getCell(0);

                if (cellItem7 == null) {
                    cellItem7 = estimateSheet.createRow(14).createCell(0);
                }
                cellItem7.setCellValue(data[29]);

                Cell cellDescription7 = estimateSheet.getRow(14).getCell(1);

                if (cellDescription7 == null) {
                    cellDescription7 = estimateSheet.createRow(14).createCell(1);
                }
                cellDescription7.setCellValue(data[30]);

                Cell cellQty7 = estimateSheet.getRow(14).getCell(4);

                if (cellQty7 == null) {
                    cellQty7 = estimateSheet.createRow(14).createCell(4);
                }
                cellQty7.setCellValue((int)(Double.parseDouble(functions.removeComas(data[31]))));

                Cell cellRate7 = estimateSheet.getRow(14).getCell(5);

                if (cellRate7 == null) {
                    cellRate7 = estimateSheet.createRow(14).createCell(5);
                }
                cellRate7.setCellValue(Double.parseDouble(data[32]));

                Cell cellTotal7 = estimateSheet.getRow(14).getCell(6);

                if (cellTotal7 == null) {
                    cellTotal7 = estimateSheet.createRow(14).createCell(6);
                }
                cellTotal7.setCellFormula("F15*E15");

            }

            if (estimateNumberOfItems >= 8) {

                Cell cellItem8 = estimateSheet.getRow(15).getCell(0);

                if (cellItem8 == null) {
                    cellItem8 = estimateSheet.createRow(15).createCell(0);
                }
                cellItem8.setCellValue(data[33]);

                Cell cellDescription8 = estimateSheet.getRow(15).getCell(1);

                if (cellDescription8 == null) {
                    cellDescription8 = estimateSheet.createRow(15).createCell(1);
                }
                cellDescription8.setCellValue(data[34]);

                Cell cellQty8 = estimateSheet.getRow(15).getCell(4);

                if (cellQty8 == null) {
                    cellQty8 = estimateSheet.createRow(15).createCell(4);
                }
                cellQty8.setCellValue((int)(Double.parseDouble(functions.removeComas(data[35]))));

                Cell cellRate8 = estimateSheet.getRow(15).getCell(5);

                if (cellRate8 == null) {
                    cellRate8 = estimateSheet.createRow(15).createCell(5);
                }
                cellRate8.setCellValue(Double.parseDouble(data[36]));

                Cell cellTotal8 = estimateSheet.getRow(15).getCell(6);

                if (cellTotal8 == null) {
                    cellTotal8 = estimateSheet.createRow(15).createCell(6);
                }
                cellTotal8.setCellFormula("F16*E16");

            }

            if (estimateNumberOfItems >= 9) {

                Cell cellItem9 = estimateSheet.getRow(16).getCell(0);

                if (cellItem9 == null) {
                    cellItem9 = estimateSheet.createRow(16).createCell(0);
                }
                cellItem9.setCellValue(data[37]);

                Cell cellDescription9 = estimateSheet.getRow(16).getCell(1);

                if (cellDescription9 == null) {
                    cellDescription9 = estimateSheet.createRow(16).createCell(1);
                }
                cellDescription9.setCellValue(data[38]);

                Cell cellQty9 = estimateSheet.getRow(16).getCell(4);

                if (cellQty9 == null) {
                    cellQty9 = estimateSheet.createRow(16).createCell(4);
                }
                cellQty9.setCellValue((int)(Double.parseDouble(functions.removeComas(data[39]))));

                Cell cellRate9 = estimateSheet.getRow(16).getCell(5);

                if (cellRate9 == null) {
                    cellRate9 = estimateSheet.createRow(16).createCell(5);
                }
                cellRate9.setCellValue(Double.parseDouble(data[40]));

                Cell cellTotal9 = estimateSheet.getRow(16).getCell(6);

                if (cellTotal9 == null) {
                    cellTotal9 = estimateSheet.createRow(16).createCell(6);
                }
                cellTotal9.setCellFormula("F17*E17");

            }

            if (estimateNumberOfItems >= 10) {

                Cell cellItem10 = estimateSheet.getRow(17).getCell(0);

                if (cellItem10 == null) {
                    cellItem10 = estimateSheet.createRow(17).createCell(0);
                }
                cellItem10.setCellValue(data[41]);

                Cell cellDescription10 = estimateSheet.getRow(17).getCell(1);

                if (cellDescription10 == null) {
                    cellDescription10 = estimateSheet.createRow(17).createCell(1);
                }
                cellDescription10.setCellValue(data[42]);

                Cell cellQty10 = estimateSheet.getRow(17).getCell(4);

                if (cellQty10 == null) {
                    cellQty10 = estimateSheet.createRow(17).createCell(4);
                }
                cellQty10.setCellValue((int)(Double.parseDouble(functions.removeComas(data[43]))));

                Cell cellRate10 = estimateSheet.getRow(17).getCell(5);

                if (cellRate10 == null) {
                    cellRate10 = estimateSheet.createRow(17).createCell(5);
                }
                cellRate10.setCellValue(Double.parseDouble(data[44]));

                Cell cellTotal10 = estimateSheet.getRow(17).getCell(6);

                if (cellTotal10 == null) {
                    cellTotal10 = estimateSheet.createRow(17).createCell(6);
                }
                cellTotal10.setCellFormula("F18*E18");

            }

            if (estimateNumberOfItems >= 11) {

                Cell cellItem11 = estimateSheet.getRow(18).getCell(0);

                if (cellItem11 == null) {
                    cellItem11 = estimateSheet.createRow(18).createCell(0);
                }
                cellItem11.setCellValue(data[45]);

                Cell cellDescription11 = estimateSheet.getRow(18).getCell(1);

                if (cellDescription11 == null) {
                    cellDescription11 = estimateSheet.createRow(18).createCell(1);
                }
                cellDescription11.setCellValue(data[46]);

                Cell cellQty11 = estimateSheet.getRow(18).getCell(4);

                if (cellQty11 == null) {
                    cellQty11 = estimateSheet.createRow(18).createCell(4);
                }
                cellQty11.setCellValue((int)(Double.parseDouble(functions.removeComas(data[47]))));

                Cell cellRate11 = estimateSheet.getRow(18).getCell(5);

                if (cellRate11 == null) {
                    cellRate11 = estimateSheet.createRow(18).createCell(5);
                }
                cellRate11.setCellValue(Double.parseDouble(data[48]));

                Cell cellTotal11 = estimateSheet.getRow(18).getCell(6);

                if (cellTotal11 == null) {
                    cellTotal11 = estimateSheet.createRow(18).createCell(6);
                }
                cellTotal11.setCellFormula("F19*E19");

            }

            if (estimateNumberOfItems >= 12) {

                Cell cellItem12 = estimateSheet.getRow(19).getCell(0);

                if (cellItem12 == null) {
                    cellItem12 = estimateSheet.createRow(19).createCell(0);
                }
                cellItem12.setCellValue(data[49]);

                Cell cellDescription12 = estimateSheet.getRow(19).getCell(1);

                if (cellDescription12 == null) {
                    cellDescription12 = estimateSheet.createRow(19).createCell(1);
                }
                cellDescription12.setCellValue(data[50]);

                Cell cellQty12 = estimateSheet.getRow(19).getCell(4);

                if (cellQty12 == null) {
                    cellQty12 = estimateSheet.createRow(19).createCell(4);
                }
                cellQty12.setCellValue((int)(Double.parseDouble(functions.removeComas(data[51]))));

                Cell cellRate12 = estimateSheet.getRow(19).getCell(5);

                if (cellRate12 == null) {
                    cellRate12 = estimateSheet.createRow(19).createCell(5);
                }
                cellRate12.setCellValue(Double.parseDouble(data[52]));

                Cell cellTotal12 = estimateSheet.getRow(19).getCell(6);

                if (cellTotal12 == null) {
                    cellTotal12 = estimateSheet.createRow(19).createCell(6);
                }
                cellTotal12.setCellFormula("F20*E20");

            }

            if (estimateNumberOfItems >= 13) {

                Cell cellItem13 = estimateSheet.getRow(20).getCell(0);

                if (cellItem13 == null) {
                    cellItem13 = estimateSheet.createRow(20).createCell(0);
                }
                cellItem13.setCellValue(data[53]);

                Cell cellDescription13 = estimateSheet.getRow(20).getCell(1);

                if (cellDescription13 == null) {
                    cellDescription13 = estimateSheet.createRow(20).createCell(1);
                }
                cellDescription13.setCellValue(data[54]);

                Cell cellQty13 = estimateSheet.getRow(20).getCell(4);

                if (cellQty13 == null) {
                    cellQty13 = estimateSheet.createRow(20).createCell(4);
                }
                cellQty13.setCellValue((int)(Double.parseDouble(functions.removeComas(data[55]))));

                Cell cellRate13 = estimateSheet.getRow(20).getCell(5);

                if (cellRate13 == null) {
                    cellRate13 = estimateSheet.createRow(20).createCell(5);
                }
                cellRate13.setCellValue(Double.parseDouble(data[56]));

                Cell cellTotal13 = estimateSheet.getRow(20).getCell(6);

                if (cellTotal13 == null) {
                    cellTotal13 = estimateSheet.createRow(20).createCell(6);
                }
                cellTotal13.setCellFormula("F21*E21");

            }

            if (estimateNumberOfItems >= 14) {

                Cell cellItem14 = estimateSheet.getRow(21).getCell(0);

                if (cellItem14 == null) {
                    cellItem14 = estimateSheet.createRow(21).createCell(0);
                }
                cellItem14.setCellValue(data[57]);

                Cell cellDescription14 = estimateSheet.getRow(21).getCell(1);

                if (cellDescription14 == null) {
                    cellDescription14 = estimateSheet.createRow(21).createCell(1);
                }
                cellDescription14.setCellValue(data[58]);

                Cell cellQty14 = estimateSheet.getRow(21).getCell(4);

                if (cellQty14 == null) {
                    cellQty14 = estimateSheet.createRow(21).createCell(4);
                }
                cellQty14.setCellValue((int)(Double.parseDouble(functions.removeComas(data[59]))));

                Cell cellRate14 = estimateSheet.getRow(21).getCell(5);

                if (cellRate14 == null) {
                    cellRate14 = estimateSheet.createRow(21).createCell(5);
                }
                cellRate14.setCellValue(Double.parseDouble(data[60]));

                Cell cellTotal14 = estimateSheet.getRow(21).getCell(6);

                if (cellTotal14 == null) {
                    cellTotal14 = estimateSheet.createRow(21).createCell(6);
                }
                cellTotal14.setCellFormula("F22*E22");

            }

            if (estimateNumberOfItems >= 15) {

                Cell cellItem15 = estimateSheet.getRow(22).getCell(0);

                if (cellItem15 == null) {
                    cellItem15 = estimateSheet.createRow(22).createCell(0);
                }
                cellItem15.setCellValue(data[61]);

                Cell cellDescription15 = estimateSheet.getRow(22).getCell(1);

                if (cellDescription15 == null) {
                    cellDescription15 = estimateSheet.createRow(22).createCell(1);
                }
                cellDescription15.setCellValue(data[62]);

                Cell cellQty15 = estimateSheet.getRow(22).getCell(4);

                if (cellQty15 == null) {
                    cellQty15 = estimateSheet.createRow(22).createCell(4);
                }
                cellQty15.setCellValue((int)(Double.parseDouble(functions.removeComas(data[63]))));

                Cell cellRate15 = estimateSheet.getRow(22).getCell(5);

                if (cellRate15 == null) {
                    cellRate15 = estimateSheet.createRow(22).createCell(5);
                }
                cellRate15.setCellValue(Double.parseDouble(data[64]));

                Cell cellTotal15 = estimateSheet.getRow(22).getCell(6);

                if (cellTotal15 == null) {
                    cellTotal15 = estimateSheet.createRow(22).createCell(6);
                }
                cellTotal15.setCellFormula("F23*E23");

            }

            Cell cellOverallTotal = estimateSheet.getRow(31).getCell(6);

            if (cellOverallTotal == null) {
                cellOverallTotal = estimateSheet.createRow(31).createCell(6);
            }
            cellOverallTotal.setCellFormula("SUM(G9:G31)");

            FileOutputStream output = new FileOutputStream(new File(fileSavePath));
            estimateWorkbook.write(output);
            output.close();

        } catch ( IOException e ) {

            e.printStackTrace();
            errorCheck.infoEnteredError("Estimate file failed to create", e.getMessage());

        }
    }

    /* Method for inputting all data entered into the DesignQuest Traveler Excel file! */
    @SuppressWarnings("ConstantConditions")
    public void travelerExcel(String data[], int travelerNumberOfProcesses, String fileSavePath) {

        try {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample/CustomerFiles/Excel/ShopTraveler.xlsx");

            XSSFWorkbook travelerWorkbook = new XSSFWorkbook(inputStream);

            XSSFSheet travelerSheet = travelerWorkbook.getSheet("Shop Traveler");

            /* Setup for entering data in existing spreadsheet! */
            Cell jobNumber = travelerSheet.getRow(0).getCell(1);

            if (jobNumber == null) {
                jobNumber = travelerSheet.createRow(0).createCell(1);
            }
            jobNumber.setCellValue(data[0]);

            Cell dateReceived = travelerSheet.getRow(0).getCell(4);

            if (dateReceived == null) {
                dateReceived = travelerSheet.createRow(0).createCell(4);
            }
            dateReceived.setCellValue(data[1]);

            Cell customer = travelerSheet.getRow(1).getCell(1);

            if (customer == null) {
                customer = travelerSheet.createRow(1).createCell(1);
            }
            customer.setCellValue(data[2]);

            Cell quantity = travelerSheet.getRow(1).getCell(4);

            if (quantity == null) {
                quantity = travelerSheet.createRow(1).createCell(4);
            }
            quantity.setCellValue(data[3]);

            Cell partNumber = travelerSheet.getRow(2).getCell(1);

            if (partNumber == null) {
                partNumber = travelerSheet.createRow(2).createCell(1);
            }
            partNumber.setCellValue(data[4]);

            Cell dateRequired = travelerSheet.getRow(2).getCell(4);

            if (dateRequired == null) {
                dateRequired = travelerSheet.createRow(2).createCell(4);
            }
            dateRequired.setCellValue(data[5]);

            Cell poNumber = travelerSheet.getRow(3).getCell(1);

            if (poNumber == null) {
                poNumber = travelerSheet.createRow(3).createCell(1);
            }
            poNumber.setCellValue(data[6]);

            Cell completedDate = travelerSheet.getRow(3).getCell(4);

            if (completedDate == null) {
                completedDate = travelerSheet.createRow(3).createCell(4);
            }
            completedDate.setCellValue(data[7]);

            if (travelerNumberOfProcesses >= 1) {

                Cell cellDescription1 = travelerSheet.getRow(7).getCell(0);

                if (cellDescription1 == null) {
                    cellDescription1 = travelerSheet.createRow(7).createCell(0);
                }
                cellDescription1.setCellValue(data[8]);

                Cell cellPlannedHours1 = travelerSheet.getRow(7).getCell(2);

                if (cellPlannedHours1 == null) {
                    cellPlannedHours1 = travelerSheet.createRow(7).createCell(2);
                }
                cellPlannedHours1.setCellValue((int)(Double.parseDouble(functions.removeComas(data[9]))));

                if (!Objects.equals(data[10], "")) {

                    Cell cellActualHours1 = travelerSheet.getRow(7).getCell(3);

                    if (cellActualHours1 == null) {
                        cellActualHours1 = travelerSheet.createRow(7).createCell(3);
                    }
                    cellActualHours1.setCellValue((int) (Double.parseDouble(functions.removeComas(data[10]))));
                }

                if (!Objects.equals(data[11], "")) {

                    Cell cellCompleted1 = travelerSheet.getRow(7).getCell(5);

                    if (cellCompleted1 == null) {
                        cellCompleted1 = travelerSheet.createRow(7).createCell(5);
                    }
                    cellCompleted1.setCellValue(Double.parseDouble(functions.removeComas(data[11])));
                }
            }

            if (travelerNumberOfProcesses >= 2) {

                Cell cellDescription2 = travelerSheet.getRow(8).getCell(0);

                if (cellDescription2 == null) {
                    cellDescription2 = travelerSheet.createRow(8).createCell(0);
                }
                cellDescription2.setCellValue(data[12]);

                Cell cellPlannedHours2 = travelerSheet.getRow(8).getCell(2);

                if (cellPlannedHours2 == null) {
                    cellPlannedHours2 = travelerSheet.createRow(8).createCell(2);
                }
                cellPlannedHours2.setCellValue((int)(Double.parseDouble(functions.removeComas(data[13]))));

                if (!Objects.equals(data[14], "")) {

                    Cell cellActualHours2 = travelerSheet.getRow(8).getCell(3);

                    if (cellActualHours2 == null) {
                        cellActualHours2 = travelerSheet.createRow(8).createCell(3);
                    }
                    cellActualHours2.setCellValue((int) (Double.parseDouble(functions.removeComas(data[14]))));
                }

                if (!Objects.equals(data[15], "")) {

                    Cell cellCompleted2 = travelerSheet.getRow(8).getCell(5);

                    if (cellCompleted2 == null) {
                        cellCompleted2 = travelerSheet.createRow(8).createCell(5);
                    }
                    cellCompleted2.setCellValue(Double.parseDouble(functions.removeComas(data[15])));
                }
            }

            if (travelerNumberOfProcesses >= 3) {

                Cell cellDescription3 = travelerSheet.getRow(9).getCell(0);

                if (cellDescription3 == null) {
                    cellDescription3 = travelerSheet.createRow(9).createCell(0);
                }
                cellDescription3.setCellValue(data[16]);

                Cell cellPlannedHours3 = travelerSheet.getRow(9).getCell(2);

                if (cellPlannedHours3 == null) {
                    cellPlannedHours3 = travelerSheet.createRow(9).createCell(2);
                }
                cellPlannedHours3.setCellValue((int)(Double.parseDouble(functions.removeComas(data[17]))));

                if (!Objects.equals(data[18], "")) {

                    Cell cellActualHours3 = travelerSheet.getRow(9).getCell(3);

                    if (cellActualHours3 == null) {
                        cellActualHours3 = travelerSheet.createRow(9).createCell(3);
                    }
                    cellActualHours3.setCellValue((int) (Double.parseDouble(functions.removeComas(data[18]))));
                }

                if (!Objects.equals(data[19], "")) {

                    Cell cellCompleted3 = travelerSheet.getRow(9).getCell(5);

                    if (cellCompleted3 == null) {
                        cellCompleted3 = travelerSheet.createRow(9).createCell(5);
                    }
                    cellCompleted3.setCellValue(Double.parseDouble(functions.removeComas(data[19])));
                }
            }

            if (travelerNumberOfProcesses >= 4) {

                Cell cellDescription4 = travelerSheet.getRow(10).getCell(0);

                if (cellDescription4 == null) {
                    cellDescription4 = travelerSheet.createRow(10).createCell(0);
                }
                cellDescription4.setCellValue(data[20]);

                Cell cellPlannedHours4 = travelerSheet.getRow(10).getCell(2);

                if (cellPlannedHours4 == null) {
                    cellPlannedHours4 = travelerSheet.createRow(10).createCell(2);
                }
                cellPlannedHours4.setCellValue((int)(Double.parseDouble(functions.removeComas(data[21]))));

                if (!Objects.equals(data[22], "")) {

                    Cell cellActualHours4 = travelerSheet.getRow(10).getCell(3);

                    if (cellActualHours4 == null) {
                        cellActualHours4 = travelerSheet.createRow(10).createCell(3);
                    }
                    cellActualHours4.setCellValue((int) (Double.parseDouble(functions.removeComas(data[22]))));
                }

                if (!Objects.equals(data[23], "")) {

                    Cell cellCompleted4 = travelerSheet.getRow(10).getCell(5);

                    if (cellCompleted4 == null) {
                        cellCompleted4 = travelerSheet.createRow(10).createCell(5);
                    }
                    cellCompleted4.setCellValue(Double.parseDouble(functions.removeComas(data[23])));
                }
            }

            if (travelerNumberOfProcesses >= 5) {

                Cell cellDescription5 = travelerSheet.getRow(11).getCell(0);

                if (cellDescription5 == null) {
                    cellDescription5 = travelerSheet.createRow(11).createCell(0);
                }
                cellDescription5.setCellValue(data[24]);

                Cell cellPlannedHours5 = travelerSheet.getRow(11).getCell(2);

                if (cellPlannedHours5 == null) {
                    cellPlannedHours5 = travelerSheet.createRow(11).createCell(2);
                }
                cellPlannedHours5.setCellValue((int)(Double.parseDouble(functions.removeComas(data[25]))));

                if (!Objects.equals(data[26], "")) {

                    Cell cellActualHours5 = travelerSheet.getRow(11).getCell(3);

                    if (cellActualHours5 == null) {
                        cellActualHours5 = travelerSheet.createRow(11).createCell(3);
                    }
                    cellActualHours5.setCellValue((int) (Double.parseDouble(functions.removeComas(data[26]))));
                }

                if (!Objects.equals(data[27], "")) {

                    Cell cellCompleted5 = travelerSheet.getRow(11).getCell(5);

                    if (cellCompleted5 == null) {
                        cellCompleted5 = travelerSheet.createRow(11).createCell(5);
                    }
                    cellCompleted5.setCellValue(Double.parseDouble(functions.removeComas(data[27])));
                }
            }

            if (travelerNumberOfProcesses >= 6) {

                Cell cellDescription6 = travelerSheet.getRow(12).getCell(0);

                if (cellDescription6 == null) {
                    cellDescription6 = travelerSheet.createRow(12).createCell(0);
                }
                cellDescription6.setCellValue(data[28]);

                Cell cellPlannedHours6 = travelerSheet.getRow(12).getCell(2);

                if (cellPlannedHours6 == null) {
                    cellPlannedHours6 = travelerSheet.createRow(12).createCell(2);
                }
                cellPlannedHours6.setCellValue((int)(Double.parseDouble(functions.removeComas(data[29]))));

                if (!Objects.equals(data[30], "")) {

                    Cell cellActualHours6 = travelerSheet.getRow(12).getCell(3);

                    if (cellActualHours6 == null) {
                        cellActualHours6 = travelerSheet.createRow(12).createCell(3);
                    }
                    cellActualHours6.setCellValue((int) (Double.parseDouble(functions.removeComas(data[30]))));
                }

                if (!Objects.equals(data[31], "")) {

                    Cell cellCompleted6 = travelerSheet.getRow(12).getCell(5);

                    if (cellCompleted6 == null) {
                        cellCompleted6 = travelerSheet.createRow(12).createCell(5);
                    }
                    cellCompleted6.setCellValue(Double.parseDouble(functions.removeComas(data[31])));
                }
            }

            if (travelerNumberOfProcesses >= 7) {

                Cell cellDescription7 = travelerSheet.getRow(13).getCell(0);

                if (cellDescription7 == null) {
                    cellDescription7 = travelerSheet.createRow(13).createCell(0);
                }
                cellDescription7.setCellValue(data[32]);

                Cell cellPlannedHours7 = travelerSheet.getRow(13).getCell(2);

                if (cellPlannedHours7 == null) {
                    cellPlannedHours7 = travelerSheet.createRow(13).createCell(2);
                }
                cellPlannedHours7.setCellValue((int)(Double.parseDouble(functions.removeComas(data[33]))));

                if (!Objects.equals(data[34], "")) {

                    Cell cellActualHours7 = travelerSheet.getRow(13).getCell(3);

                    if (cellActualHours7 == null) {
                        cellActualHours7 = travelerSheet.createRow(13).createCell(3);
                    }
                    cellActualHours7.setCellValue((int) (Double.parseDouble(functions.removeComas(data[34]))));
                }

                if (!Objects.equals(data[35], "")) {

                    Cell cellCompleted7 = travelerSheet.getRow(13).getCell(5);

                    if (cellCompleted7 == null) {
                        cellCompleted7 = travelerSheet.createRow(13).createCell(5);
                    }
                    cellCompleted7.setCellValue(Double.parseDouble(functions.removeComas(data[35])));
                }
            }

            if (travelerNumberOfProcesses >= 8) {

                Cell cellDescription8 = travelerSheet.getRow(14).getCell(0);

                if (cellDescription8 == null) {
                    cellDescription8 = travelerSheet.createRow(14).createCell(0);
                }
                cellDescription8.setCellValue(data[36]);

                Cell cellPlannedHours8 = travelerSheet.getRow(14).getCell(2);

                if (cellPlannedHours8 == null) {
                    cellPlannedHours8 = travelerSheet.createRow(14).createCell(2);
                }
                cellPlannedHours8.setCellValue((int)(Double.parseDouble(functions.removeComas(data[37]))));

                if (!Objects.equals(data[38], "")) {

                    Cell cellActualHours8 = travelerSheet.getRow(14).getCell(3);

                    if (cellActualHours8 == null) {
                        cellActualHours8 = travelerSheet.createRow(14).createCell(3);
                    }
                    cellActualHours8.setCellValue((int) (Double.parseDouble(functions.removeComas(data[38]))));
                }

                if (!Objects.equals(data[39], "")) {

                    Cell cellCompleted8 = travelerSheet.getRow(14).getCell(5);

                    if (cellCompleted8 == null) {
                        cellCompleted8 = travelerSheet.createRow(14).createCell(5);
                    }
                    cellCompleted8.setCellValue(Double.parseDouble(functions.removeComas(data[39])));
                }
            }

            if (travelerNumberOfProcesses >= 9) {

                Cell cellDescription9 = travelerSheet.getRow(15).getCell(0);

                if (cellDescription9 == null) {
                    cellDescription9 = travelerSheet.createRow(15).createCell(0);
                }
                cellDescription9.setCellValue(data[40]);

                Cell cellPlannedHours9 = travelerSheet.getRow(15).getCell(2);

                if (cellPlannedHours9 == null) {
                    cellPlannedHours9 = travelerSheet.createRow(15).createCell(2);
                }
                cellPlannedHours9.setCellValue((int)(Double.parseDouble(functions.removeComas(data[41]))));

                if (!Objects.equals(data[42], "")) {

                    Cell cellActualHours9 = travelerSheet.getRow(15).getCell(3);

                    if (cellActualHours9 == null) {
                        cellActualHours9 = travelerSheet.createRow(15).createCell(3);
                    }
                    cellActualHours9.setCellValue((int) (Double.parseDouble(functions.removeComas(data[42]))));
                }

                if (!Objects.equals(data[43], "")) {

                    Cell cellCompleted9 = travelerSheet.getRow(15).getCell(5);

                    if (cellCompleted9 == null) {
                        cellCompleted9 = travelerSheet.createRow(15).createCell(5);
                    }
                    cellCompleted9.setCellValue(Double.parseDouble(functions.removeComas(data[43])));
                }
            }

            Cell cellPartTime = travelerSheet.getRow(16).getCell(2);

            if (cellPartTime == null) {
                cellPartTime = travelerSheet.createRow(16).createCell(2);
            }
            cellPartTime.setCellFormula("SUM(C8:C16)");

            Cell cellTotalTime = travelerSheet.getRow(17).getCell(2);

            if (cellTotalTime == null) {
                cellTotalTime = travelerSheet.createRow(17).createCell(2);
            }
            cellTotalTime.setCellFormula("C17*E2");

            Cell notes = travelerSheet.getRow(19).getCell(0);

            if (notes == null) {
                notes = travelerSheet.createRow(19).createCell(0);
            }
            notes.setCellValue("Notes: " + data[44]);

            FileOutputStream output = new FileOutputStream(new File(fileSavePath));
            travelerWorkbook.write(output);
            output.close();

        } catch ( IOException e ) {

            e.printStackTrace();
            errorCheck.infoEnteredError("Traveler file failed to create", e.getMessage());

        }
    }

    /* Method for inputting all data entered into the DesignQuest Packing List Excel file! */
    @SuppressWarnings("ConstantConditions")
    public void packingListExcel(String data[], int packingListNumberOfItems, String fileSavePath) {

        try {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample/CustomerFiles/Excel/DesignQuestPackingListTemplate.xlsx");

            XSSFWorkbook packingListWorkbook = new XSSFWorkbook(inputStream);

            XSSFSheet packingListSheet = packingListWorkbook.getSheet("Packing Slip");

            /* Setup for entering data in existing spreadsheet! */
            Cell shipToAddress = packingListSheet.getRow(8).getCell(0);

            if (shipToAddress == null) {
                shipToAddress = packingListSheet.createRow(8).createCell(0);
            }
            shipToAddress.setCellValue(data[0]);

            Cell billToAddress = packingListSheet.getRow(8).getCell(3);

            if (billToAddress == null) {
                billToAddress = packingListSheet.createRow(8).createCell(3);
            }
            billToAddress.setCellValue(data[1]);

            Cell orderDate = packingListSheet.getRow(15).getCell(0);

            if (orderDate == null) {
                orderDate = packingListSheet.createRow(15).createCell(0);
            }
            orderDate.setCellValue(data[2]);

            Cell orderNumber = packingListSheet.getRow(15).getCell(2);

            if (orderNumber == null) {
                orderNumber = packingListSheet.createRow(15).createCell(2);
            }
            orderNumber.setCellValue((int) (Double.parseDouble(functions.removeComas(data[3]))));

            Cell job = packingListSheet.getRow(15).getCell(4);

            if (job == null) {
                job = packingListSheet.createRow(15).createCell(4);
            }
            job.setCellValue(data[4]);

            if (packingListNumberOfItems >= 1) {

                Cell cellItem1Number = packingListSheet.getRow(18).getCell(0);

                if (cellItem1Number == null) {
                    cellItem1Number = packingListSheet.createRow(18).createCell(0);
                }
                cellItem1Number.setCellValue(data[5]);

                Cell cellItem1Description = packingListSheet.getRow(18).getCell(1);

                if (cellItem1Description == null) {
                    cellItem1Description = packingListSheet.createRow(18).createCell(1);
                }
                cellItem1Description.setCellValue(data[6]);

                Cell cellItem1Quantity = packingListSheet.getRow(18).getCell(5);

                if (cellItem1Quantity == null) {
                    cellItem1Quantity = packingListSheet.createRow(18).createCell(5);
                }
                cellItem1Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[7]))));
            }

            if (packingListNumberOfItems >= 2) {

                Cell cellItem2Number = packingListSheet.getRow(19).getCell(0);

                if (cellItem2Number == null) {
                    cellItem2Number = packingListSheet.createRow(19).createCell(0);
                }
                cellItem2Number.setCellValue(data[8]);

                Cell cellItem2Description = packingListSheet.getRow(19).getCell(1);

                if (cellItem2Description == null) {
                    cellItem2Description = packingListSheet.createRow(19).createCell(1);
                }
                cellItem2Description.setCellValue(data[9]);

                Cell cellItem2Quantity = packingListSheet.getRow(19).getCell(5);

                if (cellItem2Quantity == null) {
                    cellItem2Quantity = packingListSheet.createRow(19).createCell(5);
                }
                cellItem2Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[10]))));
            }

            if (packingListNumberOfItems >= 3) {

                Cell cellItem3Number = packingListSheet.getRow(20).getCell(0);

                if (cellItem3Number == null) {
                    cellItem3Number = packingListSheet.createRow(20).createCell(0);
                }
                cellItem3Number.setCellValue(data[11]);

                Cell cellItem3Description = packingListSheet.getRow(20).getCell(1);

                if (cellItem3Description == null) {
                    cellItem3Description = packingListSheet.createRow(20).createCell(1);
                }
                cellItem3Description.setCellValue(data[12]);

                Cell cellItem3Quantity = packingListSheet.getRow(20).getCell(5);

                if (cellItem3Quantity == null) {
                    cellItem3Quantity = packingListSheet.createRow(20).createCell(5);
                }
                cellItem3Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[13]))));
            }

            if (packingListNumberOfItems >= 4) {

                Cell cellItem4Number = packingListSheet.getRow(21).getCell(0);

                if (cellItem4Number == null) {
                    cellItem4Number = packingListSheet.createRow(21).createCell(0);
                }
                cellItem4Number.setCellValue(data[14]);

                Cell cellItem4Description = packingListSheet.getRow(21).getCell(1);

                if (cellItem4Description == null) {
                    cellItem4Description = packingListSheet.createRow(21).createCell(1);
                }
                cellItem4Description.setCellValue(data[15]);

                Cell cellItem4Quantity = packingListSheet.getRow(21).getCell(5);

                if (cellItem4Quantity == null) {
                    cellItem4Quantity = packingListSheet.createRow(21).createCell(5);
                }
                cellItem4Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[16]))));
            }

            if (packingListNumberOfItems >= 5) {

                Cell cellItem5Number = packingListSheet.getRow(22).getCell(0);

                if (cellItem5Number == null) {
                    cellItem5Number = packingListSheet.createRow(22).createCell(0);
                }
                cellItem5Number.setCellValue(data[17]);

                Cell cellItem5Description = packingListSheet.getRow(22).getCell(1);

                if (cellItem5Description == null) {
                    cellItem5Description = packingListSheet.createRow(22).createCell(1);
                }
                cellItem5Description.setCellValue(data[18]);

                Cell cellItem5Quantity = packingListSheet.getRow(22).getCell(5);

                if (cellItem5Quantity == null) {
                    cellItem5Quantity = packingListSheet.createRow(22).createCell(5);
                }
                cellItem5Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[19]))));
            }

            if (packingListNumberOfItems >= 6) {

                Cell cellItem6Number = packingListSheet.getRow(23).getCell(0);

                if (cellItem6Number == null) {
                    cellItem6Number = packingListSheet.createRow(23).createCell(0);
                }
                cellItem6Number.setCellValue(data[20]);

                Cell cellItem6Description = packingListSheet.getRow(23).getCell(1);

                if (cellItem6Description == null) {
                    cellItem6Description = packingListSheet.createRow(23).createCell(1);
                }
                cellItem6Description.setCellValue(data[21]);

                Cell cellItem6Quantity = packingListSheet.getRow(23).getCell(5);

                if (cellItem6Quantity == null) {
                    cellItem6Quantity = packingListSheet.createRow(23).createCell(5);
                }
                cellItem6Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[22]))));
            }

            if (packingListNumberOfItems >= 7) {

                Cell cellItem7Number = packingListSheet.getRow(24).getCell(0);

                if (cellItem7Number == null) {
                    cellItem7Number = packingListSheet.createRow(24).createCell(0);
                }
                cellItem7Number.setCellValue(data[23]);

                Cell cellItem7Description = packingListSheet.getRow(24).getCell(1);

                if (cellItem7Description == null) {
                    cellItem7Description = packingListSheet.createRow(24).createCell(1);
                }
                cellItem7Description.setCellValue(data[24]);

                Cell cellItem7Quantity = packingListSheet.getRow(24).getCell(5);

                if (cellItem7Quantity == null) {
                    cellItem7Quantity = packingListSheet.createRow(24).createCell(5);
                }
                cellItem7Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[25]))));
            }

            if (packingListNumberOfItems >= 8) {

                Cell cellItem8Number = packingListSheet.getRow(25).getCell(0);

                if (cellItem8Number == null) {
                    cellItem8Number = packingListSheet.createRow(25).createCell(0);
                }
                cellItem8Number.setCellValue(data[26]);

                Cell cellItem8Description = packingListSheet.getRow(25).getCell(1);

                if (cellItem8Description == null) {
                    cellItem8Description = packingListSheet.createRow(25).createCell(1);
                }
                cellItem8Description.setCellValue(data[27]);

                Cell cellItem8Quantity = packingListSheet.getRow(25).getCell(5);

                if (cellItem8Quantity == null) {
                    cellItem8Quantity = packingListSheet.createRow(25).createCell(5);
                }
                cellItem8Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[28]))));
            }

            if (packingListNumberOfItems >= 9) {

                Cell cellItem9Number = packingListSheet.getRow(26).getCell(0);

                if (cellItem9Number == null) {
                    cellItem9Number = packingListSheet.createRow(26).createCell(0);
                }
                cellItem9Number.setCellValue(data[29]);

                Cell cellItem9Description = packingListSheet.getRow(26).getCell(1);

                if (cellItem9Description == null) {
                    cellItem9Description = packingListSheet.createRow(26).createCell(1);
                }
                cellItem9Description.setCellValue(data[30]);

                Cell cellItem9Quantity = packingListSheet.getRow(26).getCell(5);

                if (cellItem9Quantity == null) {
                    cellItem9Quantity = packingListSheet.createRow(26).createCell(5);
                }
                cellItem9Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[31]))));
            }

            if (packingListNumberOfItems >= 10) {

                Cell cellItem10Number = packingListSheet.getRow(27).getCell(0);

                if (cellItem10Number == null) {
                    cellItem10Number = packingListSheet.createRow(27).createCell(0);
                }
                cellItem10Number.setCellValue(data[32]);

                Cell cellItem10Description = packingListSheet.getRow(27).getCell(1);

                if (cellItem10Description == null) {
                    cellItem10Description = packingListSheet.createRow(27).createCell(1);
                }
                cellItem10Description.setCellValue(data[33]);

                Cell cellItem10Quantity = packingListSheet.getRow(27).getCell(5);

                if (cellItem10Quantity == null) {
                    cellItem10Quantity = packingListSheet.createRow(27).createCell(5);
                }
                cellItem10Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[34]))));
            }

            if (packingListNumberOfItems >= 11) {

                Cell cellItem11Number = packingListSheet.getRow(28).getCell(0);

                if (cellItem11Number == null) {
                    cellItem11Number = packingListSheet.createRow(28).createCell(0);
                }
                cellItem11Number.setCellValue(data[35]);

                Cell cellItem11Description = packingListSheet.getRow(28).getCell(1);

                if (cellItem11Description == null) {
                    cellItem11Description = packingListSheet.createRow(28).createCell(1);
                }
                cellItem11Description.setCellValue(data[36]);

                Cell cellItem11Quantity = packingListSheet.getRow(28).getCell(5);

                if (cellItem11Quantity == null) {
                    cellItem11Quantity = packingListSheet.createRow(28).createCell(5);
                }
                cellItem11Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[37]))));
            }

            if (packingListNumberOfItems >= 12) {

                Cell cellItem12Number = packingListSheet.getRow(29).getCell(0);

                if (cellItem12Number == null) {
                    cellItem12Number = packingListSheet.createRow(29).createCell(0);
                }
                cellItem12Number.setCellValue(data[38]);

                Cell cellItem12Description = packingListSheet.getRow(29).getCell(1);

                if (cellItem12Description == null) {
                    cellItem12Description = packingListSheet.createRow(29).createCell(1);
                }
                cellItem12Description.setCellValue(data[39]);

                Cell cellItem12Quantity = packingListSheet.getRow(29).getCell(5);

                if (cellItem12Quantity == null) {
                    cellItem12Quantity = packingListSheet.createRow(29).createCell(5);
                }
                cellItem12Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[40]))));
            }

            if (packingListNumberOfItems >= 13) {

                Cell cellItem13Number = packingListSheet.getRow(30).getCell(0);

                if (cellItem13Number == null) {
                    cellItem13Number = packingListSheet.createRow(30).createCell(0);
                }
                cellItem13Number.setCellValue(data[41]);

                Cell cellItem13Description = packingListSheet.getRow(30).getCell(1);

                if (cellItem13Description == null) {
                    cellItem13Description = packingListSheet.createRow(30).createCell(1);
                }
                cellItem13Description.setCellValue(data[42]);

                Cell cellItem13Quantity = packingListSheet.getRow(30).getCell(5);

                if (cellItem13Quantity == null) {
                    cellItem13Quantity = packingListSheet.createRow(30).createCell(5);
                }
                cellItem13Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[43]))));
            }

            if (packingListNumberOfItems >= 14) {

                Cell cellItem14Number = packingListSheet.getRow(31).getCell(0);

                if (cellItem14Number == null) {
                    cellItem14Number = packingListSheet.createRow(31).createCell(0);
                }
                cellItem14Number.setCellValue(data[44]);

                Cell cellItem14Description = packingListSheet.getRow(31).getCell(1);

                if (cellItem14Description == null) {
                    cellItem14Description = packingListSheet.createRow(31).createCell(1);
                }
                cellItem14Description.setCellValue(data[45]);

                Cell cellItem14Quantity = packingListSheet.getRow(31).getCell(5);

                if (cellItem14Quantity == null) {
                    cellItem14Quantity = packingListSheet.createRow(31).createCell(5);
                }
                cellItem14Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[46]))));
            }

            if (packingListNumberOfItems >= 15) {

                Cell cellItem15Number = packingListSheet.getRow(32).getCell(0);

                if (cellItem15Number == null) {
                    cellItem15Number = packingListSheet.createRow(32).createCell(0);
                }
                cellItem15Number.setCellValue(data[47]);

                Cell cellItem15Description = packingListSheet.getRow(32).getCell(1);

                if (cellItem15Description == null) {
                    cellItem15Description = packingListSheet.createRow(32).createCell(1);
                }
                cellItem15Description.setCellValue(data[48]);

                Cell cellItem15Quantity = packingListSheet.getRow(32).getCell(5);

                if (cellItem15Quantity == null) {
                    cellItem15Quantity = packingListSheet.createRow(32).createCell(5);
                }
                cellItem15Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[49]))));
            }

            if (packingListNumberOfItems >= 16) {

                Cell cellItem16Number = packingListSheet.getRow(33).getCell(0);

                if (cellItem16Number == null) {
                    cellItem16Number = packingListSheet.createRow(33).createCell(0);
                }
                cellItem16Number.setCellValue(data[50]);

                Cell cellItem16Description = packingListSheet.getRow(33).getCell(1);

                if (cellItem16Description == null) {
                    cellItem16Description = packingListSheet.createRow(33).createCell(1);
                }
                cellItem16Description.setCellValue(data[51]);

                Cell cellItem16Quantity = packingListSheet.getRow(33).getCell(5);

                if (cellItem16Quantity == null) {
                    cellItem16Quantity = packingListSheet.createRow(33).createCell(5);
                }
                cellItem16Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[52]))));
            }

            if (packingListNumberOfItems >= 17) {

                Cell cellItem17Number = packingListSheet.getRow(34).getCell(0);

                if (cellItem17Number == null) {
                    cellItem17Number = packingListSheet.createRow(34).createCell(0);
                }
                cellItem17Number.setCellValue(data[53]);

                Cell cellItem17Description = packingListSheet.getRow(34).getCell(1);

                if (cellItem17Description == null) {
                    cellItem17Description = packingListSheet.createRow(34).createCell(1);
                }
                cellItem17Description.setCellValue(data[54]);

                Cell cellItem17Quantity = packingListSheet.getRow(34).getCell(5);

                if (cellItem17Quantity == null) {
                    cellItem17Quantity = packingListSheet.createRow(34).createCell(5);
                }
                cellItem17Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[55]))));
            }

            if (packingListNumberOfItems >= 18) {

                Cell cellItem18Number = packingListSheet.getRow(35).getCell(0);

                if (cellItem18Number == null) {
                    cellItem18Number = packingListSheet.createRow(35).createCell(0);
                }
                cellItem18Number.setCellValue(data[56]);

                Cell cellItem18Description = packingListSheet.getRow(35).getCell(1);

                if (cellItem18Description == null) {
                    cellItem18Description = packingListSheet.createRow(35).createCell(1);
                }
                cellItem18Description.setCellValue(data[57]);

                Cell cellItem18Quantity = packingListSheet.getRow(35).getCell(5);

                if (cellItem18Quantity == null) {
                    cellItem18Quantity = packingListSheet.createRow(35).createCell(5);
                }
                cellItem18Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[58]))));
            }

            if (packingListNumberOfItems >= 19) {

                Cell cellItem19Number = packingListSheet.getRow(36).getCell(0);

                if (cellItem19Number == null) {
                    cellItem19Number = packingListSheet.createRow(36).createCell(0);
                }
                cellItem19Number.setCellValue(data[59]);

                Cell cellItem19Description = packingListSheet.getRow(36).getCell(1);

                if (cellItem19Description == null) {
                    cellItem19Description = packingListSheet.createRow(36).createCell(1);
                }
                cellItem19Description.setCellValue(data[60]);

                Cell cellItem19Quantity = packingListSheet.getRow(36).getCell(5);

                if (cellItem19Quantity == null) {
                    cellItem19Quantity = packingListSheet.createRow(36).createCell(5);
                }
                cellItem19Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[61]))));
            }

            if (packingListNumberOfItems >= 20) {

                Cell cellItem20Number = packingListSheet.getRow(37).getCell(0);

                if (cellItem20Number == null) {
                    cellItem20Number = packingListSheet.createRow(37).createCell(0);
                }
                cellItem20Number.setCellValue(data[62]);

                Cell cellItem20Description = packingListSheet.getRow(37).getCell(1);

                if (cellItem20Description == null) {
                    cellItem20Description = packingListSheet.createRow(37).createCell(1);
                }
                cellItem20Description.setCellValue(data[63]);

                Cell cellItem20Quantity = packingListSheet.getRow(37).getCell(5);

                if (cellItem20Quantity == null) {
                    cellItem20Quantity = packingListSheet.createRow(37).createCell(5);
                }
                cellItem20Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[64]))));
            }

            if (packingListNumberOfItems >= 21) {

                Cell cellItem21Number = packingListSheet.getRow(38).getCell(0);

                if (cellItem21Number == null) {
                    cellItem21Number = packingListSheet.createRow(38).createCell(0);
                }
                cellItem21Number.setCellValue(data[65]);

                Cell cellItem21Description = packingListSheet.getRow(38).getCell(1);

                if (cellItem21Description == null) {
                    cellItem21Description = packingListSheet.createRow(38).createCell(1);
                }
                cellItem21Description.setCellValue(data[66]);

                Cell cellItem21Quantity = packingListSheet.getRow(38).getCell(5);

                if (cellItem21Quantity == null) {
                    cellItem21Quantity = packingListSheet.createRow(38).createCell(5);
                }
                cellItem21Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[67]))));
            }

            if (packingListNumberOfItems >= 22) {

                Cell cellItem22Number = packingListSheet.getRow(39).getCell(0);

                if (cellItem22Number == null) {
                    cellItem22Number = packingListSheet.createRow(39).createCell(0);
                }
                cellItem22Number.setCellValue(data[68]);

                Cell cellItem22Description = packingListSheet.getRow(39).getCell(1);

                if (cellItem22Description == null) {
                    cellItem22Description = packingListSheet.createRow(39).createCell(1);
                }
                cellItem22Description.setCellValue(data[69]);

                Cell cellItem22Quantity = packingListSheet.getRow(39).getCell(5);

                if (cellItem22Quantity == null) {
                    cellItem22Quantity = packingListSheet.createRow(39).createCell(5);
                }
                cellItem22Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[70]))));
            }

            if (packingListNumberOfItems >= 23) {

                Cell cellItem23Number = packingListSheet.getRow(40).getCell(0);

                if (cellItem23Number == null) {
                    cellItem23Number = packingListSheet.createRow(40).createCell(0);
                }
                cellItem23Number.setCellValue(data[71]);

                Cell cellItem23Description = packingListSheet.getRow(40).getCell(1);

                if (cellItem23Description == null) {
                    cellItem23Description = packingListSheet.createRow(40).createCell(1);
                }
                cellItem23Description.setCellValue(data[72]);

                Cell cellItem23Quantity = packingListSheet.getRow(40).getCell(5);

                if (cellItem23Quantity == null) {
                    cellItem23Quantity = packingListSheet.createRow(40).createCell(5);
                }
                cellItem23Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[73]))));
            }

            if (packingListNumberOfItems >= 24) {

                Cell cellItem24Number = packingListSheet.getRow(41).getCell(0);

                if (cellItem24Number == null) {
                    cellItem24Number = packingListSheet.createRow(41).createCell(0);
                }
                cellItem24Number.setCellValue(data[74]);

                Cell cellItem24Description = packingListSheet.getRow(41).getCell(1);

                if (cellItem24Description == null) {
                    cellItem24Description = packingListSheet.createRow(41).createCell(1);
                }
                cellItem24Description.setCellValue(data[75]);

                Cell cellItem24Quantity = packingListSheet.getRow(41).getCell(5);

                if (cellItem24Quantity == null) {
                    cellItem24Quantity = packingListSheet.createRow(41).createCell(5);
                }
                cellItem24Quantity.setCellValue((int) (Double.parseDouble(functions.removeComas(data[76]))));
            }

            FileOutputStream output = new FileOutputStream(new File(fileSavePath));
            packingListWorkbook.write(output);
            output.close();

        } catch ( IOException e ) {

            e.printStackTrace();
            errorCheck.infoEnteredError("Packing List file failed to create", e.getMessage());

        }
    }


}
