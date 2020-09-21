/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.impl.orbutil.graph;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.corba.se.impl.orbutil.graph.Node;
import com.sun.corba.se.impl.orbutil.graph.NodeData;

import goodman.java.util.Collection ;
import goodman.java.util.AbstractSet ;
import goodman.java.util.Iterator ;
import goodman.java.util.Map ;
import goodman.java.util.HashMap ;
import goodman.java.util.Set ;
import goodman.java.util.HashSet ;

public class GraphImpl extends AbstractSet implements com.sun.corba.se.impl.orbutil.graph.Graph
{
    private Map /* Map<Node,NodeData> */ nodeToData ;

    public GraphImpl()
    {
        nodeToData = new HashMap() ;
    }

    public GraphImpl( Collection coll )
    {
        this() ;
        addAll( coll ) ;
    }

/***********************************************************************************/
/************ AbstractSet implementation *******************************************/
/***********************************************************************************/

    // Required for AbstractSet
    public boolean add( Object obj ) // obj must be a Node
    {
        if (!(obj instanceof com.sun.corba.se.impl.orbutil.graph.Node))
            throw new IllegalArgumentException( "Graphs must contain only Node instances" ) ;

        com.sun.corba.se.impl.orbutil.graph.Node node = (com.sun.corba.se.impl.orbutil.graph.Node)obj ;
        boolean found = nodeToData.keySet().contains( obj ) ;

        if (!found) {
            com.sun.corba.se.impl.orbutil.graph.NodeData nd = new com.sun.corba.se.impl.orbutil.graph.NodeData() ;
            nodeToData.put( node, nd ) ;
        }

        return !found ;
    }

    // Required for AbstractSet
    public Iterator iterator()
    {
        return nodeToData.keySet().iterator() ;
    }

    // Required for AbstractSet
    public int size()
    {
        return nodeToData.keySet().size() ;
    }

/***********************************************************************************/

    public com.sun.corba.se.impl.orbutil.graph.NodeData getNodeData(com.sun.corba.se.impl.orbutil.graph.Node node )
    {
        return (com.sun.corba.se.impl.orbutil.graph.NodeData)nodeToData.get( node ) ;
    }

    private void clearNodeData()
    {
        // Clear every node
        Iterator iter = nodeToData.entrySet().iterator() ;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next() ;
            com.sun.corba.se.impl.orbutil.graph.NodeData nd = (com.sun.corba.se.impl.orbutil.graph.NodeData)(entry.getValue()) ;
            nd.clear( ) ;
        }
    }

    interface NodeVisitor
    {
        void visit(com.sun.corba.se.impl.orbutil.graph.Graph graph, com.sun.corba.se.impl.orbutil.graph.Node node, com.sun.corba.se.impl.orbutil.graph.NodeData nd) ;
    }

    // This visits every node in the graph exactly once.  A
    // visitor is allowed to modify the graph during the
    // traversal.
    void visitAll( NodeVisitor nv )
    {
        boolean done = false ;

        // Repeat the traversal until every node has been visited.  Since
        // it takes one pass to determine whether or not each node has
        // already been visited, this loop always runs at least once.
        do {
            done = true ;

            // Copy entries to array to avoid concurrent modification
            // problem with iterator if the visitor is updating the graph.
            Map.Entry[] entries =
                (Map.Entry[])nodeToData.entrySet().toArray( new Map.Entry[0] ) ;

            // Visit each node in the graph that has not already been visited.
            // If any node is visited in this pass, we must run at least one more
            // pass.
            for (int ctr=0; ctr<entries.length; ctr++) {
                Map.Entry current = entries[ctr] ;
                com.sun.corba.se.impl.orbutil.graph.Node node = (com.sun.corba.se.impl.orbutil.graph.Node)current.getKey() ;
                com.sun.corba.se.impl.orbutil.graph.NodeData nd = (com.sun.corba.se.impl.orbutil.graph.NodeData)current.getValue() ;

                if (!nd.isVisited()) {
                    nd.visited() ;
                    done = false ;

                    nv.visit( this, node, nd ) ;
                }
            }
        } while (!done) ;
    }

    private void markNonRoots()
    {
        visitAll(
            new NodeVisitor() {
                public void visit(Graph graph, com.sun.corba.se.impl.orbutil.graph.Node node, com.sun.corba.se.impl.orbutil.graph.NodeData nd )
                {
                    Iterator iter = node.getChildren().iterator() ; // Iterator<Node>
                    while (iter.hasNext()) {
                        com.sun.corba.se.impl.orbutil.graph.Node child = (com.sun.corba.se.impl.orbutil.graph.Node)iter.next() ;

                        // Make sure the child is in the graph so it can be
                        // visited later if necessary.
                        graph.add( child ) ;

                        // Mark the child as a non-root, since a child is never a root.
                        com.sun.corba.se.impl.orbutil.graph.NodeData cnd = graph.getNodeData( child ) ;
                        cnd.notRoot() ;
                    }
                }
            } ) ;
    }

    private Set collectRootSet()
    {
        final Set result = new HashSet() ;

        Iterator iter = nodeToData.entrySet().iterator() ;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next() ;
            com.sun.corba.se.impl.orbutil.graph.Node node = (Node)entry.getKey() ;
            com.sun.corba.se.impl.orbutil.graph.NodeData nd = (NodeData)entry.getValue() ;
            if (nd.isRoot())
                result.add( node ) ;
        }

        return result ;
    }

    public Set /* Set<Node> */ getRoots()
    {
        clearNodeData() ;
        markNonRoots() ;
        return collectRootSet() ;
    }
}
