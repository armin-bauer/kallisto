package yeene.kallisto.gui.simulation;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.fest.assertions.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author yeene
 */
public class ThreadPoolTest {

  private ThreadPool pool;

  @BeforeMethod
  public void setup() {
    pool = new ThreadPool(0);
  }

  @Test
  public void constructor_createsThreads(final @Mocked SystemThread thread) throws Exception {
    // fixture:
    new NonStrictExpectations() {{
      new SystemThread();
      times = 3;
    }};

    // execution: make a new pool.
    pool = new ThreadPool(3);

    // assertion: list of threads should have size 3
    Assertions.assertThat(pool.threads.length).describedAs("length of threads array").isEqualTo(3);
  }

}
