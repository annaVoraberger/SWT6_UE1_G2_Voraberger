import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import swt6.powerplant.inverter.InverterApi;
import swt6.powerplant.inverter.impl.RandomInverter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomInverterTest {
  private static InverterApi inverter;
  @BeforeAll
  public static void setUp(){
    ArrayList<Integer> randomValues = new ArrayList<>();
    //no need to test negative values since Random.nextInt() only returns positive Values
    randomValues.add(0);
    randomValues.add(800);
    randomValues.add(100);
    randomValues.add(280);
    randomValues.add(799);
    randomValues.add(1);
    inverter = new RandomInverter(new RandomStub(randomValues));
  }

  @AfterAll
  public static void tearDown(){
    inverter = null;
  }

  @Test
  public void inverterReturnsCorrectValues(){

    //being zero
    assertEquals(0.000, inverter.getActualCurrent());
    //being the max val of 0.8
    assertEquals(0.800, inverter.getActualCurrent());
    //other values in between
    assertEquals(0.100, inverter.getActualCurrent());
    assertEquals(0.280, inverter.getActualCurrent());
    assertEquals(0.799, inverter.getActualCurrent());
    assertEquals(0.001, inverter.getActualCurrent());

  }

}
