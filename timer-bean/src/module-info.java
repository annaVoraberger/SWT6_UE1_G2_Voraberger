import swt6.modular.beans.impl.HighResTimerProvider;
import swt6.modular.beans.impl.SimpleTimerProvider;
import swt6.modular.beans.TimerProvider;

module swt.modular.beans {
  exports swt6.modular.beans;
  provides TimerProvider with SimpleTimerProvider, HighResTimerProvider;
}