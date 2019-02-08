package UI;

import UI.callableFunction;
import javafx.fxml.FXML;

public class MultiThreadWaitFor implements Runnable{
    private Thread thread;
    public int secs;
    public boolean isRunning = true;
    private callableFunction c;

    public MultiThreadWaitFor(int secs, callableFunction c) {
        this.secs = secs;
        this.c = c;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    @FXML
    public void run() {
        try{
            while(isRunning == true){
                c.call();
                thread.sleep(1000*secs);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onShutDown(){
        this.isRunning = false;
    }
}
