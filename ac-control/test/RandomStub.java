import java.util.Iterator;
import java.util.Random;

public class RandomStub extends Random {

  private Iterator<Integer> iterator;

  public RandomStub(Iterable<Integer> values){
    iterator = values.iterator();
  }
  @Override
  public int nextInt() {
    return iterator.next();
  }

  @Override
  public int nextInt(int ignored, int ignored2) {
    return iterator.next();
  }
}
