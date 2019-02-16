package edu.wpi.cs3733c19.teamO.Fuzzy;

import java.sql.Connection;

public interface IFuzzy {
    String fuzzy(String input, Connection conn);
}
