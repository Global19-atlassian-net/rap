/*******************************************************************************
 * Copyright (c) 2002, 2012 Innoopract Informationssysteme GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Innoopract Informationssysteme GmbH - initial API and implementation
 *    EclipseSource - ongoing development
 ******************************************************************************/
package org.eclipse.swt.internal.widgets.textkit;

import java.io.IOException;

import org.eclipse.rap.rwt.lifecycle.ControlLCAUtil;
import org.eclipse.rap.rwt.lifecycle.WidgetLCAUtil;
import org.eclipse.swt.widgets.Text;


final class SingleTextLCA extends AbstractTextDelegateLCA {

  @Override
  void preserveValues( Text text ) {
    TextLCAUtil.preserveValues( text );
  }

  /* (intentionally non-JavaDoc'ed)
   * readData does not explicitly handle modifyEvents. They are fired implicitly
   * by updating the text property in TextLCAUtil.readText( Text ).
   */
  @Override
  void readData( Text text ) {
    TextLCAUtil.readTextAndSelection( text );
    processSelection( text );
    ControlLCAUtil.processEvents( text );
    ControlLCAUtil.processKeyEvents( text );
    ControlLCAUtil.processMenuDetect( text );
    WidgetLCAUtil.processHelp( text );
  }

  @Override
  void renderInitialization( Text text ) throws IOException {
    TextLCAUtil.renderInitialization( text );
  }

  @Override
  void renderChanges( Text text ) throws IOException {
    TextLCAUtil.renderChanges( text );
  }

  private static void processSelection( Text text ) {
    ControlLCAUtil.processSelection( text, null, false );
    ControlLCAUtil.processDefaultSelection( text, null );
  }

}