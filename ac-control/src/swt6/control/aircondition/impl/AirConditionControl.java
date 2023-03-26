package swt6.control.aircondition.impl;

import swt6.control.aircondition.AirConditionApi;

import java.util.Random;

public class AirConditionControl implements AirConditionApi {
  private boolean airconIsOn = false;
  private static Random randomizer;
  private static int min = 190;
  private static int max = 300;


  public AirConditionControl(Random random) {
    this.randomizer = random;
  }

  @Override
  public void turnOn() {
    airconIsOn = true;
    System.out.println("==== Turn Aircon ON ====");
  }

  @Override
  public void turnOff() {
    airconIsOn = false;
    System.out.println("==== Turn Aircon OFF ====");
  }

  @Override
  public double getRoomTemperature() {
    int rand = randomizer.nextInt(min,max+1);
    return (double)rand / 10;
  }

  public boolean isOn(){
    if (airconIsOn){
      System.out.println("==== Aircon still running ====");
    } else {
      System.out.println("==== Aircon still off ====");
    }
    return airconIsOn;
  }

}
