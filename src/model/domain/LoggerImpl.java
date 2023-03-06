package model.domain;

import java.io.FileWriter;
import java.io.IOException;

public class LoggerImpl implements  Logger {
    private String fileName;

    public LoggerImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void log(String message) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + System.lineSeparator());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
