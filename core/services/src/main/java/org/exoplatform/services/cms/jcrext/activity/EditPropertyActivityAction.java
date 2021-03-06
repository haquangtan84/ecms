/*
 * Copyright (C) 2003-2013 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.services.cms.jcrext.activity;

import javax.jcr.Item;
import javax.jcr.Node;
import javax.jcr.Property;

import org.apache.commons.chain.Context;
import org.exoplatform.services.command.action.Action;
import org.exoplatform.services.listener.ListenerService;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.wcm.utils.WCMCoreUtils;

/**
 * Created by The eXo Platform SAS
 * Author : Nguyen The Vinh From ECM Of eXoPlatform
 *          vinh_nguyen@exoplatform.com
 * 11 Jan 2013  
 */
public class EditPropertyActivityAction implements Action{
  private ListenerService listenerService=null;
  private ActivityCommonService activityService = null;
  public EditPropertyActivityAction() {
    listenerService =  WCMCoreUtils.getService(ListenerService.class);
    activityService = WCMCoreUtils.getService(ActivityCommonService.class);
  }
  @Override
  public boolean execute(Context context) throws Exception {
    Object item = context.get("currentItem");
    Node node = (item instanceof Property) ?((Property)item).getParent():(Node)item;
    Node nodeTemp = activityService.isSpecialContentNodeType((Item) item);
    String propertyName = (item instanceof Property) ?((Property)item).getName():((Node)item).getName();
    if (nodeTemp !=null) {
      if (!activityService.isCreating(nodeTemp)) { 
        //Consider this special case as changing of content
        String itemPath = ((Item) item).getPath();
        String nodePath = nodeTemp.getPath();
        String referencePath = itemPath.substring(nodePath.length());
        if (referencePath.startsWith("/")) {
          referencePath = referencePath.substring(1);
        }
        listenerService.broadcast(ActivityCommonService.EDIT_ACTIVITY, nodeTemp, referencePath);
      }
    }
    nodeTemp = node;
    // Do not create / update activity for bellow cases
    if (!activityService.isAcceptedProperties(propertyName)) return false;
    if (ConversationState.getCurrent() == null) return false;
    
    if(node.isNodeType("nt:resource")) node = node.getParent();
    //filter node type
    if (activityService.isAcceptedNode(node)) {
      //Notify to update activity
      if (!activityService.isCreating(node)) {
        listenerService.broadcast(ActivityCommonService.EDIT_ACTIVITY, node, propertyName);
      }      
    }
    return false;
  }

}
