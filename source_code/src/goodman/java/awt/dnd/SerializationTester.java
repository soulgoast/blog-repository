/*
 * Copyright (c) 2001, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.awt.dnd;

import goodman.java.io.OutputStream;
import goodman.java.io.ObjectOutputStream;
import goodman.java.io.IOException;
import goodman.java.io.Serializable;

/**
 * Tests if an object can truly be serialized by serializing it to a null
 * OutputStream.
 *
 * @since 1.4
 */
final class SerializationTester {
    private static ObjectOutputStream stream;
    static {
        try {
            stream = new ObjectOutputStream(new OutputStream() {
                    public void write(int b) {}
                });
        } catch (IOException cannotHappen) {
        }
    }

    static boolean test(Object obj) {
        if (!(obj instanceof Serializable)) {
            return false;
        }

        try {
            stream.writeObject(obj);
        } catch (IOException e) {
            return false;
        } finally {
            // Fix for 4503661.
            // Reset the stream so that it doesn't keep a reference to the
            // written object.
            try {
                stream.reset();
            } catch (IOException e) {
                // Ignore the exception.
            }
        }
        return true;
    }

    private SerializationTester() {}
}
