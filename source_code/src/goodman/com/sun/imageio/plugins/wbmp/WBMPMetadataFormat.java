/*
 * Copyright (c) 2003, 2004, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.imageio.plugins.wbmp;

import com.sun.imageio.plugins.wbmp.WBMPMetadata;

import goodman.java.util.Arrays;
import goodman.javax.imageio.ImageTypeSpecifier;
import goodman.javax.imageio.metadata.IIOMetadataFormat;
import goodman.javax.imageio.metadata.IIOMetadataFormatImpl;

public class WBMPMetadataFormat extends IIOMetadataFormatImpl {

    private static IIOMetadataFormat instance = null;

    private WBMPMetadataFormat() {
        super(com.sun.imageio.plugins.wbmp.WBMPMetadata.nativeMetadataFormatName,
              CHILD_POLICY_SOME);

        // root -> ImageDescriptor
        addElement("ImageDescriptor",
                   WBMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);

        addAttribute("ImageDescriptor", "WBMPType",
                     DATATYPE_INTEGER, true, "0");

        addAttribute("ImageDescriptor", "Width",
                     DATATYPE_INTEGER, true, null,
                     "0", "65535", true, true);
        addAttribute("ImageDescriptor", "Height",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
    }



    public boolean canNodeAppear(String elementName,
                                 ImageTypeSpecifier imageType) {
        return true;
    }

    public static synchronized IIOMetadataFormat getInstance() {
        if (instance == null) {
            instance = new WBMPMetadataFormat();
        }
        return instance;
    }
}
