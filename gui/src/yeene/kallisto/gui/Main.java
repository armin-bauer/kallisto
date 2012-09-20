package yeene.kallisto.gui;

import yeene.kallisto.Sattelite;
import yeene.kallisto.SimulatedSystem;
import yeene.kallisto.math.Vector;

import java.awt.*;
import java.math.BigDecimal;

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
    final BigDecimal sunMass       = new BigDecimal("1.989E30");      // alle in kg
    final BigDecimal earthMass     = new BigDecimal("5.974E24");
    final BigDecimal moonMass      = new BigDecimal("7.349E22");
    final BigDecimal venusMass     = new BigDecimal("4.869E24");
    final BigDecimal mercuryMass   = new BigDecimal("3.302E23");
    final BigDecimal sunRadius     = BigDecimal.valueOf(1392700000); // in m
    final BigDecimal earthRadius   = BigDecimal.valueOf(   6378000);
    final BigDecimal moonRadius    = BigDecimal.valueOf(    380000);
    final BigDecimal venusRadius   = BigDecimal.valueOf(   6051000);
    final BigDecimal mercuryRadius = BigDecimal.valueOf(   4879400);

    final Vector earthPosition   = new Vector( 152100000000.0,            0.0,   0.0);
    final Vector moonPosition    = new Vector( 152484400000.0,            0.0,   0.0);
    final Vector venusPosition   = new Vector(-108160000000.0,            0.0,   0.0);
    final Vector mercuryPosition = new Vector(            0.0,  57909000000.0,   0.0);

    final Vector earthVelocity   = new Vector(            0.0,       -29780.0,   0.0);   // alle in m/s
    final Vector moonVelocity    = new Vector(            0.0,         1012.123, 0.0);
    final Vector venusVelocity   = new Vector(            0.0,        35020.0,   0.0);
    final Vector mercuryVelocity = new Vector(        47870.0,            0.0,   0.0);

    final Sattelite sun     = new Sattelite("sun",     sunRadius,     sunMass,     Vector.NULLVECTOR,   Vector.NULLVECTOR,   Vector.NULLVECTOR);
    final Sattelite earth   = new Sattelite("earth",   earthRadius,   earthMass,   earthPosition,       earthVelocity,       Vector.NULLVECTOR);
    final Sattelite venus   = new Sattelite("venus",   venusRadius,   venusMass,   venusPosition,       venusVelocity,       Vector.NULLVECTOR);
    final Sattelite mercury = new Sattelite("mercury", mercuryRadius, mercuryMass, mercuryPosition,     mercuryVelocity,     Vector.NULLVECTOR);
    final Sattelite moon    = new Sattelite("moon",    moonRadius,    moonMass,    moonPosition,        moonVelocity,        Vector.NULLVECTOR);

    simulatedSystem.addPlanets(sun, earth, venus, mercury);//, moon);
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
