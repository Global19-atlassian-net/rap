/*******************************************************************************
 * Copyright (c) 2002-2006 Innoopract Informationssysteme GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Innoopract Informationssysteme GmbH - initial API and implementation
 ******************************************************************************/

package org.eclipse.swt.graphics;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.swt.SWT;

// TODO [bm] Javadoc
// TODO [rh] font property (according and LCA functinality) for the following
//      widget missing: TableItem, TreeColumn
public final class Font extends Resource {
  
  private static final Map fonts = new HashMap();
  
  private final FontData[] fontData;
  
  private Font( final FontData data ) {
    this.fontData = new FontData[] { data };
  }
  
  
  /**
   * TODO [fappel]: comment
   */
  public static Font getFont( final String name, 
                              final int height, 
                              final int style ) 
  {
    validate( name, height );
    int checkedStyle = checkStyle( style );
    Font result;
    FontData fontData = new FontData( name, height, checkedStyle );
    synchronized( Font.class ) {
      result = ( Font )fonts.get( fontData );
      if( result == null ) {
        result = new Font( fontData );
        fonts.put( fontData, result );
      }
    }
    return result;
  }
  
  /**
   * TODO [fappel]: comment
   */
  public static Font getFont( final FontData data ) {
    return getFont( data.getName(), data.getHeight(), data.getStyle() );
  }

  /**
   * TODO [fappel]: comment
   */
  public FontData[] getFontData() {
    return fontData;
  }

  
  //////////////////
  // Helping methods
  
  private static void validate( final String name, final int height ) {
    if( name == null ) {
      SWT.error( SWT.ERROR_NULL_ARGUMENT );
    }
    if( height < 0 ) {
      SWT.error( SWT.ERROR_INVALID_ARGUMENT );
    }
  }
  
  private static int checkStyle( final int style ) {
    int result = SWT.NORMAL;
    if( ( style & SWT.BOLD ) != 0 ) {
      result |= SWT.BOLD;
    }
    if( ( style & SWT.ITALIC ) != 0 ) {
      result |= SWT.ITALIC;
    }
    return result;
  }
}
