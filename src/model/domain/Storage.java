package model.domain;
import java.util.List;

public interface Storage {
    List<String> readAllLines();

    void saveAllLines(List<String> lines);
}
