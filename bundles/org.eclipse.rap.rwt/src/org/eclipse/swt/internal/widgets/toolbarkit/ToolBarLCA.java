/*******************************************************************************
 * Copyright (c) 2002, 2011 Innoopract Informationssysteme GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Innoopract Informationssysteme GmbH - initial API and implementation
 *    EclipseSource - ongoing development
 ******************************************************************************/
package org.eclipse.swt.internal.widgets.toolbarkit;

import java.io.IOException;

import org.eclipse.rwt.internal.protocol.ClientObjectFactory;
import org.eclipse.rwt.internal.protocol.IClientObject;
import org.eclipse.rwt.lifecycle.*;
import org.eclipse.swt.widgets.*;


public class ToolBarLCA extends AbstractWidgetLCA {

  private static final String TYPE = "rwt.widgets.ToolBar";

  public void preserveValues( Widget widget ) {
    ToolBar toolBar = ( ToolBar )widget;
    ControlLCAUtil.preserveValues( toolBar );
    WidgetLCAUtil.preserveCustomVariant( toolBar );
  }

  public void readData( Widget widget ) {
    ControlLCAUtil.processMouseEvents( ( Control )widget );
    ControlLCAUtil.processKeyEvents( ( Control )widget );
    ControlLCAUtil.processMenuDetect( ( Control )widget );
    WidgetLCAUtil.processHelp( widget );
  }

  public void renderInitialization( Widget widget ) throws IOException {
    ToolBar toolBar = ( ToolBar )widget;
    IClientObject clientObject = ClientObjectFactory.getForWidget( toolBar );
    clientObject.create( TYPE );
    clientObject.setProperty( "parent", WidgetUtil.getId( toolBar.getParent() ) );
    clientObject.setProperty( "style", WidgetLCAUtil.getStyles( toolBar ) );
  }

  public void renderChanges( Widget widget ) throws IOException {
    ToolBar toolBar = ( ToolBar )widget;
    ControlLCAUtil.renderChanges( toolBar );
    WidgetLCAUtil.renderCustomVariant( toolBar );
  }

  public void renderDispose( Widget widget ) throws IOException {
    ClientObjectFactory.getForWidget( widget ).destroy();
  }

}
