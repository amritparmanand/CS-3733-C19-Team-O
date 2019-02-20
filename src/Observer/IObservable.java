package Observer;

import Datatypes.SearchResult;
import javafx.scene.Node;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IObservable {
    public void update(ResultSet rs) throws SQLException;
    Node getSelf();
}
