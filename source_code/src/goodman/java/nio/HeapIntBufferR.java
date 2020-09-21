/*
 * Copyright (c) 2000, 2013, Oracle and/or its affiliates. All rights reserved.
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

// -- This file was mechanically generated: Do not edit! -- //

package goodman.java.nio;


import goodman.java.nio.ByteOrder;
import goodman.java.nio.HeapIntBuffer;
import goodman.java.nio.IntBuffer;
import goodman.java.nio.ReadOnlyBufferException;

/**



 * A read-only HeapIntBuffer.  This class extends the corresponding
 * read/write class, overriding the mutation methods to throw a {@link
 * ReadOnlyBufferException} and overriding the view-buffer methods to return an
 * instance of this class rather than of the superclass.

 */

class HeapIntBufferR
    extends java.nio.HeapIntBuffer
{

    // For speed these fields are actually declared in X-Buffer;
    // these declarations are here as documentation
    /*




    */

    HeapIntBufferR(int cap, int lim) {            // package-private







        super(cap, lim);
        this.isReadOnly = true;

    }

    HeapIntBufferR(int[] buf, int off, int len) { // package-private







        super(buf, off, len);
        this.isReadOnly = true;

    }

    protected HeapIntBufferR(int[] buf,
                                   int mark, int pos, int lim, int cap,
                                   int off)
    {







        super(buf, mark, pos, lim, cap, off);
        this.isReadOnly = true;

    }

    public java.nio.IntBuffer slice() {
        return new HeapIntBufferR(hb,
                                        -1,
                                        0,
                                        this.remaining(),
                                        this.remaining(),
                                        this.position() + offset);
    }

    public java.nio.IntBuffer duplicate() {
        return new HeapIntBufferR(hb,
                                        this.markValue(),
                                        this.position(),
                                        this.limit(),
                                        this.capacity(),
                                        offset);
    }

    public java.nio.IntBuffer asReadOnlyBuffer() {








        return duplicate();

    }




































    public boolean isReadOnly() {
        return true;
    }

    public java.nio.IntBuffer put(int x) {




        throw new ReadOnlyBufferException();

    }

    public java.nio.IntBuffer put(int i, int x) {




        throw new ReadOnlyBufferException();

    }

    public java.nio.IntBuffer put(int[] src, int offset, int length) {








        throw new ReadOnlyBufferException();

    }

    public java.nio.IntBuffer put(java.nio.IntBuffer src) {























        throw new ReadOnlyBufferException();

    }

    public IntBuffer compact() {







        throw new ReadOnlyBufferException();

    }






































































































































































































































































































































































    public java.nio.ByteOrder order() {
        return ByteOrder.nativeOrder();
    }



}
