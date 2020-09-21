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

package goodman.com.sun.imageio.plugins.jpeg;

import goodman.javax.imageio.metadata.IIOInvalidTreeException;
import goodman.javax.imageio.metadata.IIOMetadataNode;
import goodman.javax.imageio.stream.ImageOutputStream;

import goodman.java.io.IOException;

import com.sun.imageio.plugins.jpeg.JPEG;
import com.sun.imageio.plugins.jpeg.JPEGBuffer;
import com.sun.imageio.plugins.jpeg.MarkerSegment;
import org.w3c.dom.Node;

/**
     * A DRI (Define Restart Interval) marker segment.
     */
class DRIMarkerSegment extends com.sun.imageio.plugins.jpeg.MarkerSegment {
    /**
     * Restart interval, or 0 if none is specified.
     */
    int restartInterval = 0;

    DRIMarkerSegment(com.sun.imageio.plugins.jpeg.JPEGBuffer buffer)
        throws IOException {
        super(buffer);
        restartInterval = (buffer.buf[buffer.bufPtr++] & 0xff) << 8;
        restartInterval |= buffer.buf[buffer.bufPtr++] & 0xff;
        buffer.bufAvail -= length;
    }

    DRIMarkerSegment(Node node) throws IIOInvalidTreeException {
        super(JPEG.DRI);
        updateFromNativeNode(node, true);
    }

    IIOMetadataNode getNativeNode() {
        IIOMetadataNode node = new IIOMetadataNode("dri");
        node.setAttribute("interval", Integer.toString(restartInterval));
        return node;
    }

    void updateFromNativeNode(Node node, boolean fromScratch)
        throws IIOInvalidTreeException {
        restartInterval = getAttributeValue(node, null, "interval",
                                            0, 65535, true);
    }

    /**
     * Writes the data for this segment to the stream in
     * valid JPEG format.
     */
    void write(ImageOutputStream ios) throws IOException {
        // We don't write DRI segments; the IJG library does.
    }

    void print() {
        printTag("DRI");
        System.out.println("Interval: "
                           + Integer.toString(restartInterval));
    }
}
