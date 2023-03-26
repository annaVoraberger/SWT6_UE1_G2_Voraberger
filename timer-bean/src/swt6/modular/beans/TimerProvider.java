package swt6.modular.beans;

public interface TimerProvider {
  double getResolution(); //z.B. Ich bin ein Timer, der kann ticks für sekunden machen,...
  Timer createTimer(int interval, int numTicks);
}
