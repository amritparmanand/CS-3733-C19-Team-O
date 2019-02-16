package edu.wpi.cs3733c19.teamO.Datatypes;

import java.sql.Connection;
/**
 * @author Sam Silver
 * @version It 1
 * Interface for a given account
 */
public interface IAccount {
    void register(Connection conn);
}
