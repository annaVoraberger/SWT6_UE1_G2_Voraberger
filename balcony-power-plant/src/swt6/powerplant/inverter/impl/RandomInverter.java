package swt6.powerplant.inverter.impl;

import lombok.Setter;
import swt6.powerplant.inverter.InverterApi;

import java.util.Random;

public class RandomInverter implements InverterApi {
  @Setter
  static Random randomizer;

  // gets a generator for random numbers which is used to generate the value for actual current
  public RandomInverter(Random randomizer) {
    RandomInverter.randomizer = randomizer;
  }

  // returns a random value between 0.000 and 0.800
  // uses a randomizer to generate a value between 0 and 800 and returns
  // the generated value divided by 1000
  @Override
  public double getActualCurrent() {
    int randomNumber = randomizer.nextInt(801);
    return ((double)randomNumber) / 1000;
  }
}
