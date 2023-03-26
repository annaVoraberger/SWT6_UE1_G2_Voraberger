package swt6.modular.beans.impl;

import swt6.modular.beans.Timer;
import swt6.modular.beans.TimerEvent;
import swt6.modular.beans.TimerListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleTimer implements Timer {

  private final int interval;
  private final int numTicks;
  private Thread tickerThread;
  private final List<TimerListener> listeners = new CopyOnWriteArrayList<>();

  //intervall in Millisekunden
  //numTicks wie oft soll der Timer ticken bevor er abläuft
  public SimpleTimer( int interval, int numTicks) {
    this.interval = interval;
    this.numTicks = numTicks;
  }

  @Override
  public synchronized void start() {
    if (isRunning()){
      return;
    }
    final int interval = this.getInterval();
    final int numTicks = this.getNumTicks();

    tickerThread = new Thread(() -> {
      //THREAD START
      int tickCount = 0;

      while (tickCount < numTicks && !Thread.currentThread().isInterrupted()){
        // TODO
        try {
          Thread.sleep(interval);
        } catch (InterruptedException e) {
          //e.printStackTrace();
          //restore interruption flag
          Thread.currentThread().interrupt();
        }
        fireEvent(new TimerEvent(this, tickCount, numTicks));
        tickCount ++;
      }
      tickerThread = null;
      //THREAD END
    });
    synchronized (this){
      tickerThread.start();
    }
  }

  private void fireEvent(TimerEvent event) {
    listeners.forEach(listener -> {
      listener.expired(event);
    });
  }

  private int getNumTicks() {
    return this.numTicks;
  }

  @Override
  public synchronized void stop() {
    if (tickerThread != null){
      tickerThread.interrupt();
      tickerThread = null;
    }
  }


  @Override
  public synchronized boolean isRunning(){ return tickerThread != null; }

  @Override
  public int getInterval(){ return this.interval; }

  @Override
  public void addTimerListener(TimerListener listener) {
    listeners.add(listener);
  }
  @Override
  public void removeTimerListener(TimerListener listener) {
    listeners.remove(listener);
  }
}
