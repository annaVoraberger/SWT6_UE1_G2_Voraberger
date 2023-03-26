package swt6.control.aircondition;

public interface AirConditionApi {
  // switches AC ON or OFF
  void turnOn();
  void turnOff();
  boolean isOn();
  // current room temperature in Celsius (19.0 to 30.0)
  double getRoomTemperature();
}
