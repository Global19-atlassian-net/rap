/*******************************************************************************
 * Copyright (c) 2011, 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/

rwt.protocol.HandlerRegistry.add( "rwt.widgets.DropTarget", {

  factory : function( properties ) {
    var control = rwt.protocol.ObjectRegistry.getObject( properties.control );
    var result = { "control" : control };
    rwt.remote.DNDSupport.getInstance().registerDropTarget( control, properties.style );
    rwt.protocol.HandlerUtil.addDestroyableChild( control, result );
    return result;
  },

  destructor : function( source ) {
    rwt.protocol.HandlerUtil.removeDestroyableChild( source.control, source );
    rwt.remote.DNDSupport.getInstance().deregisterDropTarget( source.control );
  },

  properties : [ "transfer" ],

  propertyHandler : {
    "transfer" : function( source, value ) {
      var control = source.control;
      rwt.remote.DNDSupport.getInstance().setDropTargetTransferTypes( control, value );
    }
  }

} );