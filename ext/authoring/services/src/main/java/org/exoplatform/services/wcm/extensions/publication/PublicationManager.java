package org.exoplatform.services.wcm.extensions.publication;

import java.util.List;

import javax.jcr.Node;

import org.exoplatform.container.component.ComponentPlugin;
import org.exoplatform.services.wcm.extensions.publication.context.impl.ContextConfig.Context;
import org.exoplatform.services.wcm.extensions.publication.lifecycle.impl.LifecyclesConfig.Lifecycle;

/**
 * Publication service is used to manage the publication.
 * 
 * Created by The eXo Platform MEA Author : haikel.thamri@exoplatform.com
 */
public interface PublicationManager {
	
  /**
    * Add publication plugin to the publication service.
    * 
    * @param plugin
  */
  public void addLifecycle(ComponentPlugin plugin);

  /**
   * Remove publication plugin from the publication service.
   * 
   * @param plugin
   */
  public void removeLifecycle(ComponentPlugin plugin);

  /**
   * Add publication plugin context to the publication service.
   * 
   * @param plugin
   */
  public void addContext(ComponentPlugin plugin);

  /**
   * Remove publication plugin context from the publication service.
   * 
   * @param plugin
   */
  public void removeContext(ComponentPlugin plugin);

  /**
   * Get all the lifecycles which were added to service instances.
   * 
   * @return
   */
  public List<Lifecycle> getLifecycles();

  /**
   * Get all the contexts which were added to service instances.
   * 
   * @return
   */
  public List<Context> getContexts();

  /**
   * Get a specific context with the given names.
   * 
   * @param name
   * @return
   */
  public Context getContext(String name);

  /**
   * Get a specific lifecycle with the given name. 
   * 
   * @param name The name of the wanted lifecycle
   * @return
   */
  public Lifecycle getLifecycle(String name);

  /**
   * Get all the Lifecycle of a specific user.
   * 
   * @param remoteUser The current user of publication service.
   * @param state The current state of the node.
   * @return
   */
  public List<Lifecycle> getLifecyclesFromUser(String remoteUser, String state);

  /**
   * Get all the nodes.
   * 
   * @param fromstate/tostate The current range state of node.
   * @param date
   * @param user The last user of node
   * @param lang The node's language.
   * @param workspace The Workspace of the node's location.
   * @return
   * @throws Exception
   */
  public List<Node> getContents(String fromstate,
      String tostate,
      String date,
      String user,
      String lang,
      String workspace) throws Exception;
}
