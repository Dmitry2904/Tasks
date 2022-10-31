package by.dmitry.shelepen.task1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {
    private static final Logger log = LogManager.getLogger(FileWriterUtil.class);

    public void writeDataToTargetHtmlFile(String tagTable, String fileName) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            out.write(tagTable);
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
    }


}
