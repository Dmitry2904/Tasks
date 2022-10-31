package by.dmitry.shelepen.task2.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XlsPrinter {
    private static final Logger log = LogManager.getLogger(XlsPrinter.class);

    /**
     * Example for pattern: 24.10.2022
     */
    public static final String REGEX_DATE_PATTERN = "[0-3]\\d[.][0-1]\\d.[0-2]\\d{3}";

    public void readXlsAndWriteDataToConsole(File file) {

        Map<Integer, List<String>> dataRows = getDataFromXlsFile(file);

        String date = getDate(dataRows);
        int startTableRowNumber = getStartTableRowNumber(dataRows);

        int entityCount = dataRows.get(startTableRowNumber).size() - 1;

        for (int entity = 1; entity <= entityCount; entity++) {
            String tableHeaderValue = dataRows.get(startTableRowNumber).get(entity);
            String tableHeader = getTableHeaderWithoutUnits(tableHeaderValue);
            for (int rowNumber = startTableRowNumber + 1; rowNumber <= startTableRowNumber + 24; rowNumber++) {
                List<String> row = dataRows.get(rowNumber);
                String time = getTime(row);
                Object columnValue = row.get(entity);
                double value = Double.parseDouble(columnValue.toString());
                String resultOutput = formatResult(tableHeader, date, time, value);
                log.info(resultOutput);
            }
            log.info("-------------------------------------------------------");
        }
    }

    private String formatResult(String tableHeader, String date, String time, double value) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(tableHeader)
                .append(";")
                .append(date)
                .append(" ")
                .append(time)
                .append("; ")
                .append(value).toString();
    }

    private String getTime(List<String> row) {
        String timeValue = row.get(0);
        int time = (int) Double.parseDouble(timeValue);
        int timeOfADay = time - 1;
        if (timeOfADay < 10) {
            return String.format("0%d:00", timeOfADay);
        } else {
            return String.format("%d:00", timeOfADay);
        }
    }


    /**
     * This method find row in xls table, that contains value "Hour" in first column.
     * And return the number of this row.
     *
     * @param dataRows - input rows of xls-table
     * @return row number, that contains in first column "Hour" value.
     */
    private int getStartTableRowNumber(Map<Integer, List<String>> dataRows) {
        for (int rowNumber = 0; rowNumber < dataRows.size(); rowNumber++) {
            String columnValue = getFirstColumnValueFromRow(dataRows, rowNumber);
            if (columnValue.contains("Hour")) {
                return rowNumber;
            }
        }
        return 0;
    }

    private String getDate(Map<Integer, List<String>> dataRows) {
        for (int rowNumber = 0; rowNumber < dataRows.size(); rowNumber++) {
            String columnValue = getFirstColumnValueFromRow(dataRows, rowNumber);
            if (columnValue.isEmpty()) continue;
            String date = parseDateFromString(columnValue);
            if (!date.isEmpty()) {
                return date;
            }
        }
        return "";
    }

    /**
     * Example 1.
     * input - Daily Settlement of Imbalances (version 0) - 29.10.2022
     * output - 29.10.2022
     * <p>
     * Example 2.
     * input - Daily Settlement of Imbalances (version 0) - 25.11.2021
     * output - 25.11.2021
     *
     * @param text - input String value of row from xls table.
     * @return String value of parsing date, if it founded.
     */
    private String parseDateFromString(String text) {
        Pattern pattern = Pattern.compile(REGEX_DATE_PATTERN);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    private String getFirstColumnValueFromRow(Map<Integer, List<String>> dataRows, int rowNumber) {
        List<String> row = dataRows.get(rowNumber);
        if (CollectionUtils.isNotEmpty(row)) {
            return row.get(0);
        }
        return "";
    }

    private Map<Integer, List<String>> getDataFromXlsFile(File file) {
        Map<Integer, List<String>> dataRows = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(file);
             HSSFWorkbook wb = new HSSFWorkbook(fis)) {

            HSSFSheet sheet = wb.getSheetAt(0);
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

            int i = 0;
            for (Row row : sheet) {
                ArrayList<String> list = new ArrayList<>();
                for (Cell cell : row) {
                    CellType type = formulaEvaluator.evaluateInCell(cell).getCellType();
                    if (type.equals(CellType.NUMERIC)) {
                        double numericCellValue = cell.getNumericCellValue();
                        double roundedDoubleValue = cutDoubleValueTail(numericCellValue);
                        list.add(String.valueOf(roundedDoubleValue));
                    } else if (type.equals(CellType.STRING)) {
                        list.add(cell.getStringCellValue());
                    }
                }
                dataRows.put(i, list);
                i++;
            }
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return dataRows;
    }

    /**
     * This method cut tail of double value.
     * Example 1.
     * input: -9.534000000000006
     * output: -9.534
     * <p>
     * Example 2.
     * input: 36.281000000000006
     * output: 36.281
     *
     * @param numericCellValue - input double value
     * @return double value with cutting tail
     */
    private double cutDoubleValueTail(double numericCellValue) {
        String value = String.valueOf(numericCellValue);
        BigDecimal bigDecimal = new BigDecimal(value, new MathContext(13));
        return bigDecimal.doubleValue();
    }

    /**
     * Example 1.
     * input - Absolute imbalance sum (MWh)
     * output - Absolute imbalance sum
     * <p>
     * Example 2.
     * input - Settlement price - imbalance (CZK/MWh)
     * output - Settlement price - imbalance
     *
     * @param tableHeaderValue - input String value of table header
     * @return String value of table header without Units
     */
    private String getTableHeaderWithoutUnits(String tableHeaderValue) {
        String name = tableHeaderValue.substring(0, tableHeaderValue.indexOf("(") - 1).trim();
        return name.replace("\n", "");
    }


}
