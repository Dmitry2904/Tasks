package by.dmitry.shelepen.task2;

import by.dmitry.shelepen.task2.service.XlsDownloader;
import by.dmitry.shelepen.task2.service.XlsPrinter;

import java.io.File;


/**
 * Write an application that downloads xls file from
 * <a href="https://www.ote-cr.cz/en/statistics/electricity-imbalances">
 * https://www.ote-cr.cz/en/statistics/electricity-imbalances</a>
 * <p>
 * (bottom of the page – file Imbalances) and prints output to the console.
 * <p>
 * The output should go in the following format:
 * <p>
 * <b>table header (without units);date (with hour);value</b>
 * <p>
 * Example:
 * <b>System Imbalance;19.09.2022 00:00; -1.123</b>
 * <p>
 * <i>* Note that hours are marked from 1 to 24, so hour 1 is the first hour of a day=00:00 and 24 is the last hour
 * of a day=23:00</i>
 */
public class Application2 {

    public static void main(String[] args) {
        File file = new XlsDownloader().downloadXlsFile();
        new XlsPrinter().readXlsAndWriteDataToConsole(file);
    }
}
