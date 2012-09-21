package yeene.kallisto.systembuilder.dsl;

/**
 * in the system builder dsl this implements a step that let's the user tell
 * the name of an object.
 *
 * @author yeene
 */
public interface SystemBuilderDSLNamed {

  /**
   * supply a name for an object to create first.
   */
  SystemBuilderDSLCollisionDetails named(String objectName);

}
