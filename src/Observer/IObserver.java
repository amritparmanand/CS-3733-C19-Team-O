package Observer;

import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.layout.FlowPane;

public interface IObserver {
    public void notifyObserver(int row, JFXButton next);
    public void subscribe(IObservable child);
}
