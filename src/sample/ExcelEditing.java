package sample;

/*
 * Created by Matthew Ashley on 1/24/17.
 */

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

class ExcelEditing {

    private final ConfirmMessages errorCheck = new ConfirmMessages(); /* Initializer for ConfirmMessages class! */
    private final Functions functionCheck = new Functions(); /* Initializer for Functions class! */

    /* Method for inputting all data entered into the Estimate Excel file! */
    @SuppressWarnings("ConstantConditions")
    public void estimateExcel(String data[], int numberOfItems) {

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
            cellNameAddressData.setCellValue(data[3]);

            if (numberOfItems >= 1) {

                Cell cellItem1 = estimateSheet.getRow(8).getCell(0);

                if (cellItem1 == null) {
                    cellItem1 = estimateSheet.createRow(8).createCell(0);
                }
                cellItem1.setCellValue(data[4]);

                Cell cellDescription1 = estimateSheet.getRow(8).getCell(1);

                if (cellDescription1 == null) {
                    cellDescription1 = estimateSheet.createRow(8).createCell(1);
                }
                cellDescription1.setCellValue(data[5]);

                Cell cellQty1 = estimateSheet.getRow(8).getCell(4);

                if (cellQty1 == null) {
                    cellQty1 = estimateSheet.createRow(8).createCell(4);
                }
                cellQty1.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[6]))));

                Cell cellRate1 = estimateSheet.getRow(8).getCell(5);

                if (cellRate1 == null) {
                    cellRate1 = estimateSheet.createRow(8).createCell(5);
                }
                cellRate1.setCellValue(Double.parseDouble(functionCheck.removeComas(data[7])));

                Cell cellTotal1 = estimateSheet.getRow(8).getCell(6);

                if (cellTotal1 == null) {
                    cellTotal1 = estimateSheet.createRow(8).createCell(6);
                }
                cellTotal1.setCellFormula("F9*E9");

            }

            if (numberOfItems >= 2) {

                Cell cellItem2 = estimateSheet.getRow(9).getCell(0);

                if (cellItem2 == null) {
                    cellItem2 = estimateSheet.createRow(9).createCell(0);
                }
                cellItem2.setCellValue(data[8]);

                Cell cellDescription2 = estimateSheet.getRow(9).getCell(1);

                if (cellDescription2 == null) {
                    cellDescription2 = estimateSheet.createRow(9).createCell(1);
                }
                cellDescription2.setCellValue(data[9]);

                Cell cellQty2 = estimateSheet.getRow(9).getCell(4);

                if (cellQty2 == null) {
                    cellQty2 = estimateSheet.createRow(9).createCell(4);
                }
                cellQty2.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[10]))));

                Cell cellRate2 = estimateSheet.getRow(9).getCell(5);

                if (cellRate2 == null) {
                    cellRate2 = estimateSheet.createRow(9).createCell(5);
                }
                cellRate2.setCellValue(Double.parseDouble(data[11]));

                Cell cellTotal2 = estimateSheet.getRow(9).getCell(6);

                if (cellTotal2 == null) {
                    cellTotal2 = estimateSheet.createRow(9).createCell(6);
                }
                cellTotal2.setCellFormula("F10*E10");

            }

            if (numberOfItems >= 3) {

                Cell cellItem3 = estimateSheet.getRow(10).getCell(0);

                if (cellItem3 == null) {
                    cellItem3 = estimateSheet.createRow(10).createCell(0);
                }
                cellItem3.setCellValue(data[12]);

                Cell cellDescription3 = estimateSheet.getRow(10).getCell(1);

                if (cellDescription3 == null) {
                    cellDescription3 = estimateSheet.createRow(10).createCell(1);
                }
                cellDescription3.setCellValue(data[13]);

                Cell cellQty3 = estimateSheet.getRow(10).getCell(4);

                if (cellQty3 == null) {
                    cellQty3 = estimateSheet.createRow(10).createCell(4);
                }
                cellQty3.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[14]))));

                Cell cellRate3 = estimateSheet.getRow(10).getCell(5);

                if (cellRate3 == null) {
                    cellRate3 = estimateSheet.createRow(10).createCell(5);
                }
                cellRate3.setCellValue(Double.parseDouble(data[15]));

                Cell cellTotal3 = estimateSheet.getRow(10).getCell(6);

                if (cellTotal3 == null) {
                    cellTotal3 = estimateSheet.createRow(10).createCell(6);
                }
                cellTotal3.setCellFormula("F11*E11");

            }

            if (numberOfItems >= 4) {

                Cell cellItem4 = estimateSheet.getRow(11).getCell(0);

                if (cellItem4 == null) {
                    cellItem4 = estimateSheet.createRow(11).createCell(0);
                }
                cellItem4.setCellValue(data[16]);

                Cell cellDescription4 = estimateSheet.getRow(11).getCell(1);

                if (cellDescription4 == null) {
                    cellDescription4 = estimateSheet.createRow(11).createCell(1);
                }
                cellDescription4.setCellValue(data[17]);

                Cell cellQty4 = estimateSheet.getRow(11).getCell(4);

                if (cellQty4 == null) {
                    cellQty4 = estimateSheet.createRow(11).createCell(4);
                }
                cellQty4.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[18]))));

                Cell cellRate4 = estimateSheet.getRow(11).getCell(5);

                if (cellRate4 == null) {
                    cellRate4 = estimateSheet.createRow(11).createCell(5);
                }
                cellRate4.setCellValue(Double.parseDouble(data[19]));

                Cell cellTotal4 = estimateSheet.getRow(11).getCell(6);

                if (cellTotal4 == null) {
                    cellTotal4 = estimateSheet.createRow(11).createCell(6);
                }
                cellTotal4.setCellFormula("F12*E12");

            }

            if (numberOfItems >= 5) {

                Cell cellItem5 = estimateSheet.getRow(12).getCell(0);

                if (cellItem5 == null) {
                    cellItem5 = estimateSheet.createRow(12).createCell(0);
                }
                cellItem5.setCellValue(data[20]);

                Cell cellDescription5 = estimateSheet.getRow(12).getCell(1);

                if (cellDescription5 == null) {
                    cellDescription5 = estimateSheet.createRow(12).createCell(1);
                }
                cellDescription5.setCellValue(data[21]);

                Cell cellQty5 = estimateSheet.getRow(12).getCell(4);

                if (cellQty5 == null) {
                    cellQty5 = estimateSheet.createRow(12).createCell(4);
                }
                cellQty5.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[22]))));

                Cell cellRate5 = estimateSheet.getRow(12).getCell(5);

                if (cellRate5 == null) {
                    cellRate5 = estimateSheet.createRow(12).createCell(5);
                }
                cellRate5.setCellValue(Double.parseDouble(data[23]));

                Cell cellTotal5 = estimateSheet.getRow(12).getCell(6);

                if (cellTotal5 == null) {
                    cellTotal5 = estimateSheet.createRow(12).createCell(6);
                }
                cellTotal5.setCellFormula("F13*E13");

            }

            if (numberOfItems >= 6) {

                Cell cellItem6 = estimateSheet.getRow(13).getCell(0);

                if (cellItem6 == null) {
                    cellItem6 = estimateSheet.createRow(13).createCell(0);
                }
                cellItem6.setCellValue(data[24]);

                Cell cellDescription6 = estimateSheet.getRow(13).getCell(1);

                if (cellDescription6 == null) {
                    cellDescription6 = estimateSheet.createRow(13).createCell(1);
                }
                cellDescription6.setCellValue(data[25]);

                Cell cellQty6 = estimateSheet.getRow(13).getCell(4);

                if (cellQty6 == null) {
                    cellQty6 = estimateSheet.createRow(13).createCell(4);
                }
                cellQty6.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[26]))));

                Cell cellRate6 = estimateSheet.getRow(13).getCell(5);

                if (cellRate6 == null) {
                    cellRate6 = estimateSheet.createRow(13).createCell(5);
                }
                cellRate6.setCellValue(Double.parseDouble(data[27]));

                Cell cellTotal6 = estimateSheet.getRow(13).getCell(6);

                if (cellTotal6 == null) {
                    cellTotal6 = estimateSheet.createRow(13).createCell(6);
                }
                cellTotal6.setCellFormula("F14*E14");

            }

            if (numberOfItems >= 7) {

                Cell cellItem7 = estimateSheet.getRow(14).getCell(0);

                if (cellItem7 == null) {
                    cellItem7 = estimateSheet.createRow(14).createCell(0);
                }
                cellItem7.setCellValue(data[28]);

                Cell cellDescription7 = estimateSheet.getRow(14).getCell(1);

                if (cellDescription7 == null) {
                    cellDescription7 = estimateSheet.createRow(14).createCell(1);
                }
                cellDescription7.setCellValue(data[29]);

                Cell cellQty7 = estimateSheet.getRow(14).getCell(4);

                if (cellQty7 == null) {
                    cellQty7 = estimateSheet.createRow(14).createCell(4);
                }
                cellQty7.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[30]))));

                Cell cellRate7 = estimateSheet.getRow(14).getCell(5);

                if (cellRate7 == null) {
                    cellRate7 = estimateSheet.createRow(14).createCell(5);
                }
                cellRate7.setCellValue(Double.parseDouble(data[31]));

                Cell cellTotal7 = estimateSheet.getRow(14).getCell(6);

                if (cellTotal7 == null) {
                    cellTotal7 = estimateSheet.createRow(14).createCell(6);
                }
                cellTotal7.setCellFormula("F15*E15");

            }

            if (numberOfItems >= 8) {

                Cell cellItem8 = estimateSheet.getRow(15).getCell(0);

                if (cellItem8 == null) {
                    cellItem8 = estimateSheet.createRow(15).createCell(0);
                }
                cellItem8.setCellValue(data[32]);

                Cell cellDescription8 = estimateSheet.getRow(15).getCell(1);

                if (cellDescription8 == null) {
                    cellDescription8 = estimateSheet.createRow(15).createCell(1);
                }
                cellDescription8.setCellValue(data[33]);

                Cell cellQty8 = estimateSheet.getRow(15).getCell(4);

                if (cellQty8 == null) {
                    cellQty8 = estimateSheet.createRow(15).createCell(4);
                }
                cellQty8.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[34]))));

                Cell cellRate8 = estimateSheet.getRow(15).getCell(5);

                if (cellRate8 == null) {
                    cellRate8 = estimateSheet.createRow(15).createCell(5);
                }
                cellRate8.setCellValue(Double.parseDouble(data[35]));

                Cell cellTotal8 = estimateSheet.getRow(15).getCell(6);

                if (cellTotal8 == null) {
                    cellTotal8 = estimateSheet.createRow(15).createCell(6);
                }
                cellTotal8.setCellFormula("F16*E16");

            }

            if (numberOfItems >= 9) {

                Cell cellItem9 = estimateSheet.getRow(16).getCell(0);

                if (cellItem9 == null) {
                    cellItem9 = estimateSheet.createRow(16).createCell(0);
                }
                cellItem9.setCellValue(data[36]);

                Cell cellDescription9 = estimateSheet.getRow(16).getCell(1);

                if (cellDescription9 == null) {
                    cellDescription9 = estimateSheet.createRow(16).createCell(1);
                }
                cellDescription9.setCellValue(data[37]);

                Cell cellQty9 = estimateSheet.getRow(16).getCell(4);

                if (cellQty9 == null) {
                    cellQty9 = estimateSheet.createRow(16).createCell(4);
                }
                cellQty9.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[38]))));

                Cell cellRate9 = estimateSheet.getRow(16).getCell(5);

                if (cellRate9 == null) {
                    cellRate9 = estimateSheet.createRow(16).createCell(5);
                }
                cellRate9.setCellValue(Double.parseDouble(data[39]));

                Cell cellTotal9 = estimateSheet.getRow(16).getCell(6);

                if (cellTotal9 == null) {
                    cellTotal9 = estimateSheet.createRow(16).createCell(6);
                }
                cellTotal9.setCellFormula("F17*E17");

            }

            if (numberOfItems >= 10) {

                Cell cellItem10 = estimateSheet.getRow(17).getCell(0);

                if (cellItem10 == null) {
                    cellItem10 = estimateSheet.createRow(17).createCell(0);
                }
                cellItem10.setCellValue(data[40]);

                Cell cellDescription10 = estimateSheet.getRow(17).getCell(1);

                if (cellDescription10 == null) {
                    cellDescription10 = estimateSheet.createRow(17).createCell(1);
                }
                cellDescription10.setCellValue(data[41]);

                Cell cellQty10 = estimateSheet.getRow(17).getCell(4);

                if (cellQty10 == null) {
                    cellQty10 = estimateSheet.createRow(17).createCell(4);
                }
                cellQty10.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[42]))));

                Cell cellRate10 = estimateSheet.getRow(17).getCell(5);

                if (cellRate10 == null) {
                    cellRate10 = estimateSheet.createRow(17).createCell(5);
                }
                cellRate10.setCellValue(Double.parseDouble(data[43]));

                Cell cellTotal10 = estimateSheet.getRow(17).getCell(6);

                if (cellTotal10 == null) {
                    cellTotal10 = estimateSheet.createRow(17).createCell(6);
                }
                cellTotal10.setCellFormula("F18*E18");

            }

            if (numberOfItems >= 11) {

                Cell cellItem11 = estimateSheet.getRow(18).getCell(0);

                if (cellItem11 == null) {
                    cellItem11 = estimateSheet.createRow(18).createCell(0);
                }
                cellItem11.setCellValue(data[44]);

                Cell cellDescription11 = estimateSheet.getRow(18).getCell(1);

                if (cellDescription11 == null) {
                    cellDescription11 = estimateSheet.createRow(18).createCell(1);
                }
                cellDescription11.setCellValue(data[45]);

                Cell cellQty11 = estimateSheet.getRow(18).getCell(4);

                if (cellQty11 == null) {
                    cellQty11 = estimateSheet.createRow(18).createCell(4);
                }
                cellQty11.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[46]))));

                Cell cellRate11 = estimateSheet.getRow(18).getCell(5);

                if (cellRate11 == null) {
                    cellRate11 = estimateSheet.createRow(18).createCell(5);
                }
                cellRate11.setCellValue(Double.parseDouble(data[47]));

                Cell cellTotal11 = estimateSheet.getRow(18).getCell(6);

                if (cellTotal11 == null) {
                    cellTotal11 = estimateSheet.createRow(18).createCell(6);
                }
                cellTotal11.setCellFormula("F19*E19");

            }

            if (numberOfItems >= 12) {

                Cell cellItem12 = estimateSheet.getRow(19).getCell(0);

                if (cellItem12 == null) {
                    cellItem12 = estimateSheet.createRow(19).createCell(0);
                }
                cellItem12.setCellValue(data[48]);

                Cell cellDescription12 = estimateSheet.getRow(19).getCell(1);

                if (cellDescription12 == null) {
                    cellDescription12 = estimateSheet.createRow(19).createCell(1);
                }
                cellDescription12.setCellValue(data[49]);

                Cell cellQty12 = estimateSheet.getRow(19).getCell(4);

                if (cellQty12 == null) {
                    cellQty12 = estimateSheet.createRow(19).createCell(4);
                }
                cellQty12.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[50]))));

                Cell cellRate12 = estimateSheet.getRow(19).getCell(5);

                if (cellRate12 == null) {
                    cellRate12 = estimateSheet.createRow(19).createCell(5);
                }
                cellRate12.setCellValue(Double.parseDouble(data[51]));

                Cell cellTotal12 = estimateSheet.getRow(19).getCell(6);

                if (cellTotal12 == null) {
                    cellTotal12 = estimateSheet.createRow(19).createCell(6);
                }
                cellTotal12.setCellFormula("F20*E20");

            }

            if (numberOfItems >= 13) {

                Cell cellItem13 = estimateSheet.getRow(20).getCell(0);

                if (cellItem13 == null) {
                    cellItem13 = estimateSheet.createRow(20).createCell(0);
                }
                cellItem13.setCellValue(data[52]);

                Cell cellDescription13 = estimateSheet.getRow(20).getCell(1);

                if (cellDescription13 == null) {
                    cellDescription13 = estimateSheet.createRow(20).createCell(1);
                }
                cellDescription13.setCellValue(data[53]);

                Cell cellQty13 = estimateSheet.getRow(20).getCell(4);

                if (cellQty13 == null) {
                    cellQty13 = estimateSheet.createRow(20).createCell(4);
                }
                cellQty13.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[54]))));

                Cell cellRate13 = estimateSheet.getRow(20).getCell(5);

                if (cellRate13 == null) {
                    cellRate13 = estimateSheet.createRow(20).createCell(5);
                }
                cellRate13.setCellValue(Double.parseDouble(data[55]));

                Cell cellTotal13 = estimateSheet.getRow(20).getCell(6);

                if (cellTotal13 == null) {
                    cellTotal13 = estimateSheet.createRow(20).createCell(6);
                }
                cellTotal13.setCellFormula("F21*E21");

            }

            if (numberOfItems >= 14) {

                Cell cellItem14 = estimateSheet.getRow(21).getCell(0);

                if (cellItem14 == null) {
                    cellItem14 = estimateSheet.createRow(21).createCell(0);
                }
                cellItem14.setCellValue(data[56]);

                Cell cellDescription14 = estimateSheet.getRow(21).getCell(1);

                if (cellDescription14 == null) {
                    cellDescription14 = estimateSheet.createRow(21).createCell(1);
                }
                cellDescription14.setCellValue(data[57]);

                Cell cellQty14 = estimateSheet.getRow(21).getCell(4);

                if (cellQty14 == null) {
                    cellQty14 = estimateSheet.createRow(21).createCell(4);
                }
                cellQty14.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[58]))));

                Cell cellRate14 = estimateSheet.getRow(21).getCell(5);

                if (cellRate14 == null) {
                    cellRate14 = estimateSheet.createRow(21).createCell(5);
                }
                cellRate14.setCellValue(Double.parseDouble(data[59]));

                Cell cellTotal14 = estimateSheet.getRow(21).getCell(6);

                if (cellTotal14 == null) {
                    cellTotal14 = estimateSheet.createRow(21).createCell(6);
                }
                cellTotal14.setCellFormula("F22*E22");

            }

            if (numberOfItems >= 15) {

                Cell cellItem15 = estimateSheet.getRow(22).getCell(0);

                if (cellItem15 == null) {
                    cellItem15 = estimateSheet.createRow(22).createCell(0);
                }
                cellItem15.setCellValue(data[60]);

                Cell cellDescription15 = estimateSheet.getRow(22).getCell(1);

                if (cellDescription15 == null) {
                    cellDescription15 = estimateSheet.createRow(22).createCell(1);
                }
                cellDescription15.setCellValue(data[61]);

                Cell cellQty15 = estimateSheet.getRow(22).getCell(4);

                if (cellQty15 == null) {
                    cellQty15 = estimateSheet.createRow(22).createCell(4);
                }
                cellQty15.setCellValue((int)(Double.parseDouble(functionCheck.removeComas(data[62]))));

                Cell cellRate15 = estimateSheet.getRow(22).getCell(5);

                if (cellRate15 == null) {
                    cellRate15 = estimateSheet.createRow(22).createCell(5);
                }
                cellRate15.setCellValue(Double.parseDouble(data[63]));

                Cell cellTotal15 = estimateSheet.getRow(22).getCell(6);

                if (cellTotal15 == null) {
                    cellTotal15 = estimateSheet.createRow(22).createCell(6);
                }
                cellTotal15.setCellFormula("F23*E23");

            }

            String fileName = System.getProperty("user.home") + "/Desktop/" + data[2] + ".xlsx";
            FileOutputStream output = new FileOutputStream(new File(fileName));
            estimateWorkbook.write(output);
            output.close();

        } catch ( IOException e ) {

            e.printStackTrace();
            errorCheck.infoEnteredError("File Creation Error", "Estimate file failed to create", e.getMessage());

        }
    }


}
