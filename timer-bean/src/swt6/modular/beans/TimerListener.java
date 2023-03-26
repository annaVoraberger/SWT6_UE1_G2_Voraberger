package swt6.modular.beans;

@FunctionalInterface
public interface TimerListener {
  public void expired (TimerEvent event);
}
