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
import goodman.java.nio.HeapLongBuffer;
import goodman.java.nio.LongBuffer;
import goodman.java.nio.ReadOnlyBufferException;

/**



 * A read-only HeapLongBuffer.  This class extends the corresponding
 * read/write class, overriding the mutation methods to throw a {@link
 * ReadOnlyBufferException} and overriding the view-buffer methods to return an
 * instance of this class rather than of the superclass.

 */

class HeapLongBufferR
    extends java.nio.HeapLongBuffer
{

    // For speed these fields are actually declared in X-Buffer;
    // these declarations are here as documentation
    /*




    */

    HeapLongBufferR(int cap, int lim) {            // package-private







        super(cap, lim);
        this.isReadOnly = true;

    }

    HeapLongBufferR(long[] buf, int off, int len) { // package-private







        super(buf, off, len);
        this.isReadOnly = true;

    }

    protected HeapLongBufferR(long[] buf,
                                   int mark, int pos, int lim, int cap,
                                   int off)
    {







        super(buf, mark, pos, lim, cap, off);
        this.isReadOnly = true;

    }

    public java.nio.LongBuffer slice() {
        return new HeapLongBufferR(hb,
                                        -1,
                                        0,
                                        this.remaining(),
                                        this.remaining(),
                                        this.position() + offset);
    }

    public java.nio.LongBuffer duplicate() {
        return new HeapLongBufferR(hb,
                                        this.markValue(),
                                        this.position(),
                                        this.limit(),
                                        this.capacity(),
                                        offset);
    }

    public java.nio.LongBuffer asReadOnlyBuffer() {








        return duplicate();

    }




































    public boolean isReadOnly() {
        return true;
    }

    public java.nio.LongBuffer put(long x) {




        throw new ReadOnlyBufferException();

    }

    public java.nio.LongBuffer put(int i, long x) {




        throw new ReadOnlyBufferException();

    }

    public java.nio.LongBuffer put(long[] src, int offset, int length) {








        throw new ReadOnlyBufferException();

    }

    public java.nio.LongBuffer put(java.nio.LongBuffer src) {























        throw new ReadOnlyBufferException();

    }

    public LongBuffer compact() {







        throw new ReadOnlyBufferException();

    }






































































































































































































































































































































































    public java.nio.ByteOrder order() {
        return ByteOrder.nativeOrder();
    }



}
