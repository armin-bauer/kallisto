package yeene.kallisto.gui;

import yeene.kallisto.Sattelite;
import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.math.Vector;

import java.awt.*;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static yeene.kallisto.math.Vector.NULLVECTOR;

/**
 * @author yeene
 */
public class Main {

  private static Main instance;
  private final Frame frame;
  private SimulatedSystem simulatedSystem;

  private Main() {
    frame = new Frame("Kallisto Rendering Window (AWT)");
    simulatedSystem = new SimulatedSystem();
  }

  public Frame getFrame() {
    return frame;
  }

  public SimulatedSystem getSimulatedSystem() {
    return simulatedSystem;
  }

  /**
   * run the application.
   */
  private void run() {

    // initialise the system.
    initialiseSystem();

    // make the window visible.
    frame.setVisible(true);
    frame.setSize(1024, 768);
    frame.addWindowListener(new AWTWindowListener());

    // start the calculation and rendering.
    final Thread renderThread = new Thread(new RenderTask());
    renderThread.setDaemon(true);
    renderThread.start();

    final Thread calculationThread = new Thread(new CalculationTask());
    calculationThread.setDaemon(true);
    calculationThread.start();
  }

  /**
   * sets initial parameters of the system to simulate.
   */
  private void initialiseSystem() {

    createObject("sun",     1392700000l,     0l, 1.989E30,             0l);
    createObject("mercury",    2439000l, 47870l, 3.302E23,   57909000000l);
    createObject("venus",      6051000l, 35020l, 4.869E24,  108160000000l);
    createObject("earth",      6378000l, 29780l, 5.974E24,  149600000000l);
    createObject("mars",       3396200l, 24130l, 6.419E23,  227990000000l);
    createObject("jupiter",    7149200l, 13070l, 1.899E27,  778360000000l);
    createObject("saturn",     6023700l,  9690l, 5.685E26, 1433400000000l);
    createObject("uranus",     2555900l,  6810l, 8.683E25, 2872400000000l);
    createObject("neptun",     2676400l,  5430l, 1.024E26, 4495000000000l);
    createObject("pluto",       119500l,  4720l, 1.250E22, 5906400000000l);

//    createObject("moon",             0l,     0l, 7.349E22,            0l);
//    final BigDecimal moonMass      = new BigDecimal("");
//    final BigDecimal moonRadius    = BigDecimal.valueOf(    380000);
//    final Vector moonPosition    = new Vector( 152484400000.0,            0.0,   0.0);
//   final Vector moonVelocity    = new Vector(            0.0,         1012.123, 0.0);

  }

  /**
   * add an object to the system in simulation.
   * @param nameOfObject name of the object to create (aka planet name / id)
   * @param objectRadius radius of the object (only for displaying)
   * @param speed initial speed of the object
   * @param mass mass of the object
   * @param flyRadius distance to the center of the coordinate system
   */
  private void createObject(final String nameOfObject, final long objectRadius, final long speed, final double mass, final long flyRadius) {
    // add a planet / sun to the system
    simulatedSystem.addPlanets(
      new Sattelite(nameOfObject, BigDecimal.valueOf(objectRadius), BigDecimal.valueOf(mass), new Vector(BigDecimal.valueOf(flyRadius), ZERO, ZERO), new Vector(ZERO, BigDecimal.valueOf(speed), ZERO), NULLVECTOR)
    );
  }

  /**
   * @return one singleton instance of the application.
   */
  public static Main getInstance() {
    if(instance == null) {
      instance = new Main();
    }
    return instance;
  }

  public static void main(String[] args) {
    getInstance().run();
  }

}
