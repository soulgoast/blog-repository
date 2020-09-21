/*
 * Copyright (c) 1997, 2003, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.impl.oa.poa;

import goodman.java.util.Set ;
import goodman.java.util.HashSet ;
import goodman.java.util.Map ;
import goodman.java.util.HashMap ;
import goodman.java.util.Iterator ;
import goodman.java.util.Vector ;

import com.sun.corba.se.impl.oa.poa.AOMEntry;
import com.sun.corba.se.impl.oa.poa.POAImpl;
import org.omg.PortableServer.Servant ;
import org.omg.PortableServer.POAPackage.WrongPolicy ;
import org.omg.CORBA.INTERNAL ;

/**  The ActiveObjectMap maintains associations between servants and
 * their keys.  There are two variants, to support whether or not
 * multiple IDs per servant are allowed.  This class suppots bidirectional
 * traversal of the key-servant association.  Access to an instance of this
 * class is serialized by the POA mutex.
 */
public abstract class ActiveObjectMap
{
    public static class Key {
        public byte[] id;

        Key(byte[] id) {
            this.id = id;
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            for(int i = 0; i < id.length; i++) {
                buffer.append(Integer.toString((int) id[i], 16));
                if (i != id.length-1)
                    buffer.append(":");
            }
            return buffer.toString();
        }

        public boolean equals(Object key) {
            if (!(key instanceof Key))
                return false;
            Key k = (Key) key;
            if (k.id.length != this.id.length)
                return false;
            for(int i = 0; i < this.id.length; i++)
                if (this.id[i] != k.id[i])
                    return false;
            return true;
        }

        // Use the same hash function as for String
        public int hashCode() {
            int h = 0;
            for (int i = 0; i < id.length; i++)
                h = 31*h + id[i];
            return h;
        }
    }

    protected com.sun.corba.se.impl.oa.poa.POAImpl poa ;

    protected ActiveObjectMap( com.sun.corba.se.impl.oa.poa.POAImpl poa )
    {
        this.poa = poa ;
    }

    public static ActiveObjectMap create(com.sun.corba.se.impl.oa.poa.POAImpl poa, boolean multipleIDsAllowed )
    {
        if (multipleIDsAllowed)
            return new MultipleObjectMap( poa ) ;
        else
            return new SingleObjectMap(poa ) ;
    }

    private Map keyToEntry = new HashMap() ;     // Map< Key, AOMEntry >
    private Map entryToServant = new HashMap() ; // Map< AOMEntry, Servant >
    private Map servantToEntry = new HashMap() ; // Map< Servant, AOMEntry >

    public final boolean contains(Servant value)
    {
        return servantToEntry.containsKey( value ) ;
    }

    public final boolean containsKey(Key key)
    {
        return keyToEntry.containsKey(key);
    }

    /** get Returbs the entry assigned to the key, or creates a new
    * entry in state INVALID if none is present.
    */
    public final com.sun.corba.se.impl.oa.poa.AOMEntry get(Key key)
    {
        com.sun.corba.se.impl.oa.poa.AOMEntry result = (com.sun.corba.se.impl.oa.poa.AOMEntry)keyToEntry.get(key);
        if (result == null) {
            result = new com.sun.corba.se.impl.oa.poa.AOMEntry( poa ) ;
            putEntry( key, result ) ;
        }

        return result ;
    }

    public final Servant getServant( com.sun.corba.se.impl.oa.poa.AOMEntry entry )
    {
        return (Servant)entryToServant.get( entry ) ;
    }

    public abstract Key getKey(com.sun.corba.se.impl.oa.poa.AOMEntry value) throws WrongPolicy ;

    public Key getKey(Servant value) throws WrongPolicy
    {
        com.sun.corba.se.impl.oa.poa.AOMEntry entry = (com.sun.corba.se.impl.oa.poa.AOMEntry)servantToEntry.get( value ) ;
        return getKey( entry ) ;
    }

    protected void putEntry( Key key, com.sun.corba.se.impl.oa.poa.AOMEntry value )
    {
        keyToEntry.put( key, value ) ;
    }

    public final void putServant( Servant servant, com.sun.corba.se.impl.oa.poa.AOMEntry value )
    {
        entryToServant.put( value, servant ) ;
        servantToEntry.put( servant, value ) ;
    }

    protected abstract void removeEntry(com.sun.corba.se.impl.oa.poa.AOMEntry entry, Key key ) ;

    public final void remove( Key key )
    {
        com.sun.corba.se.impl.oa.poa.AOMEntry entry = (com.sun.corba.se.impl.oa.poa.AOMEntry)keyToEntry.remove( key ) ;
        Servant servant = (Servant)entryToServant.remove( entry ) ;
        if (servant != null)
            servantToEntry.remove( servant ) ;

        removeEntry( entry, key ) ;
    }

    public abstract boolean hasMultipleIDs(com.sun.corba.se.impl.oa.poa.AOMEntry value) ;

    protected  void clear()
    {
        keyToEntry.clear();
    }

    public final Set keySet()
    {
        return keyToEntry.keySet() ;
    }
}

class SingleObjectMap extends ActiveObjectMap
{
    private Map entryToKey = new HashMap() ;    // Map< AOMEntry, Key >

    public SingleObjectMap( com.sun.corba.se.impl.oa.poa.POAImpl poa )
    {
        super( poa ) ;
    }

    public com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key getKey(com.sun.corba.se.impl.oa.poa.AOMEntry value) throws WrongPolicy
    {
        return (com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key)entryToKey.get( value ) ;
    }

    protected void putEntry(com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key key, com.sun.corba.se.impl.oa.poa.AOMEntry value)
    {
        super.putEntry( key, value);

        entryToKey.put( value, key ) ;
    }

    public  boolean hasMultipleIDs(com.sun.corba.se.impl.oa.poa.AOMEntry value)
    {
        return false;
    }

    // This case does not need the key.
    protected void removeEntry(com.sun.corba.se.impl.oa.poa.AOMEntry entry, com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key key)
    {
        entryToKey.remove( entry ) ;
    }

    public  void clear()
    {
        super.clear() ;
        entryToKey.clear() ;
    }
}

class MultipleObjectMap extends ActiveObjectMap
{
    private Map entryToKeys = new HashMap() ;   // Map< AOMEntry, Set< Key > >

    public MultipleObjectMap( POAImpl poa )
    {
        super( poa ) ;
    }

    public com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key getKey(com.sun.corba.se.impl.oa.poa.AOMEntry value) throws WrongPolicy
    {
        throw new WrongPolicy() ;
    }

    protected void putEntry(com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key key, com.sun.corba.se.impl.oa.poa.AOMEntry value)
    {
        super.putEntry( key, value);

        Set set = (Set)entryToKeys.get( value ) ;
        if (set == null) {
            set = new HashSet() ;
            entryToKeys.put( value, set ) ;
        }
        set.add( key ) ;
    }

    public  boolean hasMultipleIDs(com.sun.corba.se.impl.oa.poa.AOMEntry value)
    {
        Set set = (Set)entryToKeys.get( value ) ;
        if (set == null)
            return false ;
        return set.size() > 1 ;
    }

    protected void removeEntry(AOMEntry entry, com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key key)
    {
        Set keys = (Set)entryToKeys.get( entry ) ;
        if (keys != null) {
            keys.remove( key ) ;
            if (keys.isEmpty())
                entryToKeys.remove( entry ) ;
        }
    }

    public  void clear()
    {
        super.clear() ;
        entryToKeys.clear() ;
    }
}
