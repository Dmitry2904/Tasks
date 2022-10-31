package by.dmitry.shelepen.task1.converter;

import java.io.BufferedReader;
import java.io.IOException;

public class IoConverter {
    public String convertBufferReaderToString(BufferedReader in) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

}
