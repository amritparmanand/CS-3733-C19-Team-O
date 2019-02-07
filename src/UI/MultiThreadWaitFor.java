package UI;

import UI.callableFunction;

public class MultiThreadWaitFor implements Runnable{
    private Thread thread;
    public int secs;
    public boolean isRunning = true;
    private UI.callableFunction callableFunction;

    public MultiThreadWaitFor(int secs, callableFunction callableFunction) {
        this.secs = secs;
        this.callableFunction = callableFunction;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try{
            while(isRunning == true){
                this.callableFunction.callFunction();
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
