package swt6.control.aircondition;

import swt6.control.aircondition.impl.AirConditionControl;

import java.util.Random;

public class AirConditionApiFactory {

  public static AirConditionApi createAirConditionApi(){
    AirConditionApi ac = new AirConditionControl(new Random());
    return ac;
  }
}
