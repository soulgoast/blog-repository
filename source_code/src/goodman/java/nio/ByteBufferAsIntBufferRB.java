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


import goodman.java.nio.ByteBuffer;
import goodman.java.nio.ByteBufferAsIntBufferB;
import goodman.java.nio.ByteOrder;
import goodman.java.nio.IntBuffer;
import goodman.java.nio.ReadOnlyBufferException;

class ByteBufferAsIntBufferRB                  // package-private
    extends java.nio.ByteBufferAsIntBufferB
{








    ByteBufferAsIntBufferRB(java.nio.ByteBuffer bb) {   // package-private












        super(bb);

    }

    ByteBufferAsIntBufferRB(ByteBuffer bb,
                            int mark, int pos, int lim, int cap,
                            int off)
    {





        super(bb, mark, pos, lim, cap, off);

    }

    public java.nio.IntBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        int off = (pos << 2) + offset;
        assert (off >= 0);
        return new ByteBufferAsIntBufferRB(bb, -1, 0, rem, rem, off);
    }

    public java.nio.IntBuffer duplicate() {
        return new ByteBufferAsIntBufferRB(bb,
                                                    this.markValue(),
                                                    this.position(),
                                                    this.limit(),
                                                    this.capacity(),
                                                    offset);
    }

    public java.nio.IntBuffer asReadOnlyBuffer() {








        return duplicate();

    }























    public java.nio.IntBuffer put(int x) {




        throw new ReadOnlyBufferException();

    }

    public java.nio.IntBuffer put(int i, int x) {




        throw new ReadOnlyBufferException();

    }

    public IntBuffer compact() {

















        throw new ReadOnlyBufferException();

    }

    public boolean isDirect() {
        return bb.isDirect();
    }

    public boolean isReadOnly() {
        return true;
    }











































    public java.nio.ByteOrder order() {

        return ByteOrder.BIG_ENDIAN;




    }

}
