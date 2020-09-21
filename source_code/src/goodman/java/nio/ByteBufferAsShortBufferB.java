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


import goodman.java.nio.Bits;
import goodman.java.nio.ByteBuffer;
import goodman.java.nio.ByteBufferAsShortBufferRB;
import goodman.java.nio.ByteOrder;
import goodman.java.nio.ShortBuffer;

class ByteBufferAsShortBufferB                  // package-private
    extends java.nio.ShortBuffer
{



    protected final java.nio.ByteBuffer bb;
    protected final int offset;



    ByteBufferAsShortBufferB(java.nio.ByteBuffer bb) {   // package-private

        super(-1, 0,
              bb.remaining() >> 1,
              bb.remaining() >> 1);
        this.bb = bb;
        // enforce limit == capacity
        int cap = this.capacity();
        this.limit(cap);
        int pos = this.position();
        assert (pos <= cap);
        offset = pos;



    }

    ByteBufferAsShortBufferB(java.nio.ByteBuffer bb,
                             int mark, int pos, int lim, int cap,
                             int off)
    {

        super(mark, pos, lim, cap);
        this.bb = bb;
        offset = off;



    }

    public java.nio.ShortBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        int off = (pos << 1) + offset;
        assert (off >= 0);
        return new ByteBufferAsShortBufferB(bb, -1, 0, rem, rem, off);
    }

    public java.nio.ShortBuffer duplicate() {
        return new ByteBufferAsShortBufferB(bb,
                                                    this.markValue(),
                                                    this.position(),
                                                    this.limit(),
                                                    this.capacity(),
                                                    offset);
    }

    public java.nio.ShortBuffer asReadOnlyBuffer() {

        return new ByteBufferAsShortBufferRB(bb,
                                                 this.markValue(),
                                                 this.position(),
                                                 this.limit(),
                                                 this.capacity(),
                                                 offset);



    }



    protected int ix(int i) {
        return (i << 1) + offset;
    }

    public short get() {
        return java.nio.Bits.getShortB(bb, ix(nextGetIndex()));
    }

    public short get(int i) {
        return java.nio.Bits.getShortB(bb, ix(checkIndex(i)));
    }









    public java.nio.ShortBuffer put(short x) {

        java.nio.Bits.putShortB(bb, ix(nextPutIndex()), x);
        return this;



    }

    public java.nio.ShortBuffer put(int i, short x) {

        java.nio.Bits.putShortB(bb, ix(checkIndex(i)), x);
        return this;



    }

    public ShortBuffer compact() {

        int pos = position();
        int lim = limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

        java.nio.ByteBuffer db = bb.duplicate();
        db.limit(ix(lim));
        db.position(ix(0));
        ByteBuffer sb = db.slice();
        sb.position(pos << 1);
        sb.compact();
        position(rem);
        limit(capacity());
        discardMark();
        return this;



    }

    public boolean isDirect() {
        return bb.isDirect();
    }

    public boolean isReadOnly() {
        return false;
    }











































    public java.nio.ByteOrder order() {

        return ByteOrder.BIG_ENDIAN;




    }

}
