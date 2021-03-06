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
import goodman.java.nio.ByteBufferAsCharBufferL;
import goodman.java.nio.ByteOrder;
import goodman.java.nio.CharBuffer;
import goodman.java.nio.ReadOnlyBufferException;

class ByteBufferAsCharBufferRL                  // package-private
    extends java.nio.ByteBufferAsCharBufferL
{








    ByteBufferAsCharBufferRL(java.nio.ByteBuffer bb) {   // package-private












        super(bb);

    }

    ByteBufferAsCharBufferRL(ByteBuffer bb,
                             int mark, int pos, int lim, int cap,
                             int off)
    {





        super(bb, mark, pos, lim, cap, off);

    }

    public java.nio.CharBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        int off = (pos << 1) + offset;
        assert (off >= 0);
        return new ByteBufferAsCharBufferRL(bb, -1, 0, rem, rem, off);
    }

    public java.nio.CharBuffer duplicate() {
        return new ByteBufferAsCharBufferRL(bb,
                                                    this.markValue(),
                                                    this.position(),
                                                    this.limit(),
                                                    this.capacity(),
                                                    offset);
    }

    public java.nio.CharBuffer asReadOnlyBuffer() {








        return duplicate();

    }























    public java.nio.CharBuffer put(char x) {




        throw new java.nio.ReadOnlyBufferException();

    }

    public java.nio.CharBuffer put(int i, char x) {




        throw new java.nio.ReadOnlyBufferException();

    }

    public java.nio.CharBuffer compact() {

















        throw new ReadOnlyBufferException();

    }

    public boolean isDirect() {
        return bb.isDirect();
    }

    public boolean isReadOnly() {
        return true;
    }



    public String toString(int start, int end) {
        if ((end > limit()) || (start > end))
            throw new IndexOutOfBoundsException();
        try {
            int len = end - start;
            char[] ca = new char[len];
            java.nio.CharBuffer cb = java.nio.CharBuffer.wrap(ca);
            java.nio.CharBuffer db = this.duplicate();
            db.position(start);
            db.limit(end);
            cb.put(db);
            return new String(ca);
        } catch (StringIndexOutOfBoundsException x) {
            throw new IndexOutOfBoundsException();
        }
    }


    // --- Methods to support CharSequence ---

    public CharBuffer subSequence(int start, int end) {
        int pos = position();
        int lim = limit();
        assert (pos <= lim);
        pos = (pos <= lim ? pos : lim);
        int len = lim - pos;

        if ((start < 0) || (end > len) || (start > end))
            throw new IndexOutOfBoundsException();
        return new ByteBufferAsCharBufferRL(bb,
                                                  -1,
                                                  pos + start,
                                                  pos + end,
                                                  capacity(),
                                                  offset);
    }




    public java.nio.ByteOrder order() {




        return ByteOrder.LITTLE_ENDIAN;

    }

}
