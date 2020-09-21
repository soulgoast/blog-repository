/*
 * Copyright (c) 2002, 2003, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package goodman.com.sun.corba.se.spi.orbutil.fsm;

import com.sun.corba.se.impl.orbutil.fsm.NameBase ;

import goodman.java.util.Map ;
import goodman.java.util.HashMap ;
import goodman.java.util.Set ;
import goodman.java.util.HashSet ;

import com.sun.corba.se.impl.orbutil.fsm.GuardedAction ;
import com.sun.corba.se.impl.orbutil.fsm.NameBase ;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.corba.se.spi.orbutil.fsm.FSM;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.corba.se.spi.orbutil.fsm.State;

/** Base class for all states in a StateEngine.  This must be used
* as the base class for all states in transitions added to a StateEngine.
*/
public class StateImpl extends NameBase implements com.sun.corba.se.spi.orbutil.fsm.State {
    private com.sun.corba.se.spi.orbutil.fsm.Action defaultAction ;
    private com.sun.corba.se.spi.orbutil.fsm.State defaultNextState ;
    private Map inputToGuardedActions ;

    public StateImpl( String name )
    {
        super( name ) ;
        defaultAction = null ;
        inputToGuardedActions = new HashMap() ;
    }

    public void preAction( com.sun.corba.se.spi.orbutil.fsm.FSM fsm )
    {
    }

    public void postAction( FSM fsm )
    {
    }

    // Methods for use only by StateEngineImpl.

    public com.sun.corba.se.spi.orbutil.fsm.State getDefaultNextState()
    {
        return defaultNextState ;
    }

    public void setDefaultNextState( State defaultNextState )
    {
        this.defaultNextState = defaultNextState ;
    }

    public com.sun.corba.se.spi.orbutil.fsm.Action getDefaultAction()
    {
        return defaultAction ;
    }

    public void setDefaultAction( Action defaultAction )
    {
        this.defaultAction = defaultAction ;
    }

    public void addGuardedAction(com.sun.corba.se.spi.orbutil.fsm.Input in, GuardedAction ga )
    {
        Set gas = (Set)inputToGuardedActions.get( in ) ;
        if (gas == null) {
            gas = new HashSet() ;
            inputToGuardedActions.put( in, gas ) ;
        }

        gas.add( ga ) ;
    }

    public Set getGuardedActions( Input in )
    {
        return (Set)inputToGuardedActions.get( in ) ;
    }
}
