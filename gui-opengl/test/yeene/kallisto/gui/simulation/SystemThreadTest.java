package yeene.kallisto.gui.simulation;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import yeene.kallisto.SimulatedSystem;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.fest.assertions.Assertions.assertThat;

/**
 * this thread executes the
 *
 * @author yeene
 */
public class SystemThreadTest {

  private SystemThread thread;

  @BeforeMethod
  public void setup() {
    thread = new SystemThread();
  }

  @Test
  public void loop_returnsFalse_whenSystemIsInExitState() throws Exception {
    // fixture: set state to exit
    thread.state = SystemThreadState.EXIT;

    // execution: call loop.
    final boolean result = thread.loop();

    // assertion: should have returned false
    assertThat(result).describedAs("the thread may continue to loop").isFalse();
  }

  @Test
  public void loop_sleepsFor100Ms_whenThreadWaitsForWork() throws Exception {
    // fixture: set to wait state
    thread.state = SystemThreadState.WAIT_FOR_PACKET;

    final AtomicBoolean hasCalledSleep = new AtomicBoolean(false);
    new MockUp<Thread>() {
      @Mock
      void sleep(long millis) {
        assertThat(millis).describedAs("time to wait").isEqualTo(100l);
        hasCalledSleep.set(true);
      }
    };

    // execution: call loop
    final boolean result = thread.loop();

    // assertion: should have returned true
    assertThat(result).describedAs("thread should continue to loop").isTrue();
    assertThat(hasCalledSleep.get()).describedAs("sleep method was called").isTrue();
  }

  @Test
  public void loop_runsSimulation_whenThreadIsInPerformMode(
    final @Mocked SimulatedSystem systemMock
  ) throws Exception {
    // fixture: set to perform state
    thread.state = SystemThreadState.PERFORM_SIMULATION;
    thread.setSimulatedSystem(systemMock);

    new NonStrictExpectations() {{
      systemMock.step();
      times = 1;
    }};

    // execution: call the loop method
    final boolean result = thread.loop();

    // assertion: should have returned true, otherwise by expectations
    assertThat(result).describedAs("thread should continue to loop").isTrue();
  }

}
