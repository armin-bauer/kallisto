package yeene.kallisto.gui;

import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.systembuilder.SystemBuilder;

import java.awt.*;

import static yeene.kallisto.math.Vector.NULLVECTOR;

/**
 * @author yeene
 */
public class Main extends Frame {

  private static Main instance;
  private SimulatedSystem simulatedSystem;

  private Main() {
    setName("Kallisto Rendering Window (AWT)");


    simulatedSystem = new SimulatedSystem();
  }

  public Frame getFrame() {
    return this;
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
    setVisible(true);
    setSize(1024, 768);
    addWindowListener(new AWTWindowListener());

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

    this.simulatedSystem = new SystemBuilder() {{
      // generate the sun.
      createObject().named("sun").withRadius(1392700000l).withMass(1.989E30).withPosition(NULLVECTOR);

      // generate the inner planets
      createObject().named("mercury").withRadius(2439000l).withMass(3.302E23).withEclipticInclination(7.0).withBigHalfAxis(57909000000l).withThetaInDegrees(90).withStartSpeed(47870l);
      createObject().named("venus").withRadius(6051000l).withMass(4.869E24).withEclipticInclination(3.395).withBigHalfAxis(108160000000l).withThetaInDegrees(200).withStartSpeed(35020l);
      createObject().named("earth").withRadius(6378000l).withMass(5.974E24).withEclipticInclination(0.0).withBigHalfAxis(149600000000l).withThetaInDegrees(0).withStartSpeed(29780l);
      createObject().named("mars").withRadius(3396200l).withMass(6.419E23).withEclipticInclination(1.850).withBigHalfAxis(227990000000l).withThetaInDegrees(0).withStartSpeed(24130l);

      // create the outer planets
      createObject().named("jupiter").withRadius(7149200l).withMass(1.899E27).withEclipticInclination(1.305).withBigHalfAxis(778360000000l).withThetaInDegrees(270).withStartSpeed(13070l);
      createObject().named("saturn").withRadius(6023700l).withMass(5.685E26).withEclipticInclination(2.484).withBigHalfAxis(1433400000000l).withThetaInDegrees(270).withStartSpeed(9690l);
      createObject().named("uranus").withRadius(2555900l).withMass(8.683E25).withEclipticInclination(0.7709).withBigHalfAxis(2872400000000l).withThetaInDegrees(90).withStartSpeed(6810l);
      createObject().named("neptun").withRadius(2676400l).withMass(1.024E26).withEclipticInclination(1.769).withBigHalfAxis(4495000000000l).withThetaInDegrees(0).withStartSpeed(5430l);

      // kuiper belt
      createObject().named("pluto").withRadius(119500l).withMass(1.250E22).withEclipticInclination(17.16).withBigHalfAxis(5906400000000l).withThetaInDegrees(0).withStartSpeed(4720l);
    }}.getSystem();
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
