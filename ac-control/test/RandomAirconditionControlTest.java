import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import swt6.control.aircondition.AirConditionApi;
import swt6.control.aircondition.impl.AirConditionControl;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomAirconditionControlTest {
  private static AirConditionApi aircon;
  @BeforeAll
  public static void setUp(){
    ArrayList<Integer> randomValues = new ArrayList<>();
    randomValues.add(190);
    randomValues.add(300);
    randomValues.add(240);
    randomValues.add(200);
    aircon = new AirConditionControl(new RandomStub(randomValues));
  }

  @AfterAll
  public static void tearDown(){
    aircon = null;
  }

  @Test
  public void getRoomTemperatureReturnsCorrectValues(){
    //smallest value
    assertEquals(19.0, aircon.getRoomTemperature());
    //largest value
    assertEquals(30.0, aircon.getRoomTemperature());
    //other values in between
    assertEquals(24.0, aircon.getRoomTemperature());
    assertEquals(20.0, aircon.getRoomTemperature());
  }

  @Test
  public void turningAirconOnAndOff(){
    //initially off
    assertEquals(false, aircon.isOn());
    aircon.turnOn();
    assertEquals(true, aircon.isOn());
    aircon.turnOff();
    assertEquals(false, aircon.isOn());
    aircon.turnOff();
    assertEquals(false, aircon.isOn()); //no problem with double "turn off"
    aircon.turnOn();
    assertEquals(true, aircon.isOn());
    aircon.turnOn();
    assertEquals(true, aircon.isOn()); // no problem with double "turn on"
  }

}
