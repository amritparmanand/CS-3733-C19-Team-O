package Observer;

import Datatypes.SearchResult;
import Managers.SearchManager;
import javafx.scene.Node;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IObservable {
    public void update(SearchResult sr) throws SQLException;
    public void setBlank();
    Node getSelf();
}
