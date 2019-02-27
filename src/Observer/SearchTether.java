package Observer;

import Datatypes.SearchResult;
import com.jfoenix.controls.JFXButton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SearchTether {
    private LinkedList<SearchResult> srArray;
    private ArrayList<IObservable> children = new ArrayList<>();
    private Boolean beer;
    private Boolean wine;
    private Boolean liquor;

    private int resultLen;

    public void setBools(Boolean beer, Boolean wine, Boolean liquor) {
        this.beer = beer;
        this.wine = wine;
        this.liquor = liquor;

    }

    public ArrayList<IObservable> getChildren() {
        return children;
    }

    public void setSrArray(LinkedList<SearchResult> srArray) {
        this.srArray = srArray;
        this.resultLen = this.srArray.size();
    }

    public void subscribe(IObservable child) {
        this.children.add(child);
    }

    public void notifyObservers(int page, JFXButton next) throws SQLException {
        int cursor = page * 15;
        next.setDisable(false);
        for(IObservable child : children) {
//            System.out.println(child);
            if(cursor < resultLen) {
                child.update(srArray.get(cursor));
                cursor++;
            }
            else {
                child.setBlank();
                next.setDisable(true);
            }

        }
    }
}
