package yeene.kallisto.gui.driver.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import yeene.kallisto.gui.Constants;
import yeene.kallisto.gui.driver.GraphicFrame;

/**
 * sets up and manages a display to use opengl output for the application.
 *
 * @author yeene
 */
public class OpenGLGraphics implements GraphicFrame {

  private float maxx, maxy;
  private static final int NUM_OF_FPS = 35;

  @Override
  public void init(final int desiredMaxx, final int desiredMaxy, final int desiredBpp, final String windowName) {
    try {
      // initialise the display.
      Display.setResizable(true);
      Display.setTitle(Constants.WINDOW_NAME);
      Display.setFullscreen(false);
      Display.setDisplayMode(new DisplayMode(desiredMaxx, desiredMaxy));
      Display.create();

      maxx = desiredMaxx;
      maxy = desiredMaxy;

      //
      // setup opengl parameters.
      GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
      setDefaultPerspective();

      //
      // load identity matrix.
      GL11.glMatrixMode(GL11.GL_MODELVIEW);
      GL11.glLoadIdentity();

      // set the clearcolor.
      GL11.glClearColor(0.0f, 0.0f, 1.0f, 0.5f);
      GL11.glClearDepth(1.0f);

      // set the shade model, perspective correction and culling
      GL11.glShadeModel( GL11.GL_SMOOTH );
      GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
      GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST);
      GL11.glLightModeli(GL11.GL_LIGHT_MODEL_TWO_SIDE, GL11.GL_TRUE);
      GL11.glCullFace(GL11.GL_NONE); // GL_BACK);

      // enabl depth test
      GL11.glEnable(GL11.GL_DEPTH_TEST);            // Enables Depth Testing
      GL11.glDepthFunc(GL11.GL_LEQUAL);             // The Type Of Depth Test To Do

      // enable texturing.
      GL11.glEnable(GL11.GL_TEXTURE);
      GL11.glColor3f(1.0f, 1.0f, 1.0f);


      // TODO: light not implemented.
      GL11.glDisable( GL11.GL_LIGHTING );


      // initialise mouse / keyboard.
      Mouse.create();
      Keyboard.create();
    } catch(LWJGLException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }

  @Override
  public void mainloop() {

    // loop forever.
    while(!Display.isCloseRequested()) {

      // draw
      GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);

      // update display.
      Display.update();
      Display.sync(NUM_OF_FPS);

      // handle all keyboard events...
      while(Keyboard.next()) {
        final int keyId = Keyboard.getEventKey();
        final boolean isDown = Keyboard.getEventKeyState();

        // listener.keyAction(keyId, isDown, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT), false, false);
      }

      // handle all mouse events.
      while(Mouse.next()) {
        final int x = Mouse.getX();
        final int y = Mouse.getY();
        final boolean isDown = Mouse.getEventButtonState();
        final int button = Mouse.getEventButton();

        if(button >= 0) {
          // listener.mouseButtonPressed(button, !isDown);
        }
        // listener.mouseMove(x, y);
      }
    }

    // cleanup.
    Display.destroy();
  }



  /**
   * set the perspective for rendering 3d content.
   */
  private void setDefaultPerspective() {
    GL11.glMatrixMode( GL11.GL_PROJECTION );
    GL11.glLoadIdentity();

    // Calculate The Aspect Ratio Of The Window
    GLU.gluPerspective(45.0f, maxx / ((float) maxy), 0.1f, 100.0f);
  }

}
