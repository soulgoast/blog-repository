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

import goodman.java.io.FileDescriptor;
import goodman.java.nio.ByteOrder;
import goodman.java.nio.DirectShortBufferU;
import goodman.java.nio.ReadOnlyBufferException;
import goodman.java.nio.ShortBuffer;

import sun.misc.Cleaner;
import sun.misc.Unsafe;
import sun.misc.VM;
import sun.nio.ch.DirectBuffer;


class DirectShortBufferRU



    extends java.nio.DirectShortBufferU

    implements DirectBuffer
{















































































































































    // For duplicates and slices
    //
    DirectShortBufferRU(DirectBuffer db,         // package-private
                               int mark, int pos, int lim, int cap,
                               int off)
    {








        super(db, mark, pos, lim, cap, off);

    }

    public java.nio.ShortBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        int off = (pos << 1);
        assert (off >= 0);
        return new DirectShortBufferRU(this, -1, 0, rem, rem, off);
    }

    public java.nio.ShortBuffer duplicate() {
        return new DirectShortBufferRU(this,
                                              this.markValue(),
                                              this.position(),
                                              this.limit(),
                                              this.capacity(),
                                              0);
    }

    public java.nio.ShortBuffer asReadOnlyBuffer() {








        return duplicate();

    }


























































    public java.nio.ShortBuffer put(short x) {




        throw new ReadOnlyBufferException();

    }

    public java.nio.ShortBuffer put(int i, short x) {




        throw new ReadOnlyBufferException();

    }

    public java.nio.ShortBuffer put(java.nio.ShortBuffer src) {




































        throw new ReadOnlyBufferException();

    }

    public java.nio.ShortBuffer put(short[] src, int offset, int length) {




























        throw new ReadOnlyBufferException();

    }

    public ShortBuffer compact() {












        throw new ReadOnlyBufferException();

    }

    public boolean isDirect() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }















































    public java.nio.ByteOrder order() {





        return ((java.nio.ByteOrder.nativeOrder() != java.nio.ByteOrder.BIG_ENDIAN)
                ? java.nio.ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);

    }


























}
