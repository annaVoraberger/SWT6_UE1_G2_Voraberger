package swt6.powerplant.inverter;

import swt6.powerplant.inverter.impl.RandomInverter;

import java.util.Random;

// used to generate an InverterApi based on random Values
public class InverterApiFactory {

  public static InverterApi createInverterApi(){
    InverterApi result = new RandomInverter(new Random());
    return result;
  }
}
