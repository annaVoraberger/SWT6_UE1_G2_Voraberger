package swt6.modular.beans.impl;

import swt6.modular.beans.Timer;
import swt6.modular.beans.TimerProvider;

public class SimpleTimerProvider implements TimerProvider {
  @Override
  public Timer createTimer(int interval, int numTicks) {
    return new SimpleTimer(interval, numTicks);
  }

  @Override
  public double getResolution() {
    return 1/1000.0; //ich kann in tausendstel auflösen
  }
}
