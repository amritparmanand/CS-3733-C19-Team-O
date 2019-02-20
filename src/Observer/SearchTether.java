package Observer;

import Datatypes.SearchResult;
import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.layout.FlowPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchTether {
    private ResultSet rs;
    private ArrayList<IObservable> children = new ArrayList<>();
    private Boolean beer;
    private Boolean wine;
    private Boolean liquor;

    public void setBools(Boolean beer, Boolean wine, Boolean liquor) {
        this.beer = beer;
        this.wine = wine;
        this.liquor = liquor;

    }

    public ArrayList<IObservable> getChildren() {
        return children;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }


    public void subscribe(IObservable child) {
        this.children.add(child);
    }

    public void notifyObservers(int row, JFXButton next) throws SQLException {
        rs.absolute(row * 15);
        next.setDisable(false);
        for(IObservable child : children) {
            System.out.println(child);
            if(rs.next()) {
                child.update(rs);
            }
            else {
                child.setBlank();
                next.setDisable(true);
            }

        }
    }
}
