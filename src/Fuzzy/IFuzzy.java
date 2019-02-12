package Fuzzy;

import java.sql.Connection;

public interface IFuzzy {
    int fuzzy(String source, String target);
    String fuzzy(String input, Connection conn);
}
