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

import com.sun.imageio.plugins.jpeg.JPEG;
import com.sun.imageio.plugins.jpeg.JPEGMetadataFormat;

import goodman.javax.imageio.metadata.IIOMetadataFormat;
import goodman.javax.imageio.metadata.IIOMetadataFormatImpl;

public class JPEGStreamMetadataFormat extends com.sun.imageio.plugins.jpeg.JPEGMetadataFormat {

    private static JPEGStreamMetadataFormat theInstance = null;

    private JPEGStreamMetadataFormat() {
        super(JPEG.nativeStreamMetadataFormatName,
              CHILD_POLICY_SEQUENCE);
        addStreamElements(getRootName());
    }

    public static synchronized IIOMetadataFormat getInstance() {
        if (theInstance == null) {
            theInstance = new JPEGStreamMetadataFormat();
        }
        return theInstance;
    }
}
