package Fuzzy;

import java.sql.Connection;

public interface IFuzzy {
    String fuzzy(String input, Connection conn);
    String fuzzy(String filter, String input, Connection conn);
}
