package swt6.modular.beans;

import java.util.EventObject;

public class TimerEvent extends EventObject {

  private final int tickCount;
  private final int numTicks;

  public TimerEvent(Timer timer, int tickCount, int numTicks) {
    super(timer);
    this.tickCount = tickCount;
    this.numTicks = numTicks;
  }

  @Override
  public Timer getSource() {return (Timer)super.getSource();}

  public int getNumTicks() {
    return numTicks;
  }

  public int getTickCount() {
    return tickCount;
  }
}
