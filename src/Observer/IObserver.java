package Observer;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.layout.FlowPane;

public interface IObserver {
    public void notifyObservers();
    public void subscribe(IObservable child);
}
