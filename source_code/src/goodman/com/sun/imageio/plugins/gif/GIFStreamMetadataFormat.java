/*
 * Copyright (c) 2001, 2004, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.imageio.plugins.gif;

import com.sun.imageio.plugins.gif.GIFStreamMetadata;

import goodman.java.util.Arrays;
import goodman.javax.imageio.ImageTypeSpecifier;
import goodman.javax.imageio.metadata.IIOMetadataFormat;
import goodman.javax.imageio.metadata.IIOMetadataFormatImpl;

public class GIFStreamMetadataFormat extends IIOMetadataFormatImpl {

    private static IIOMetadataFormat instance = null;

    private GIFStreamMetadataFormat() {
        super(com.sun.imageio.plugins.gif.GIFStreamMetadata.nativeMetadataFormatName,
              CHILD_POLICY_SOME);

        // root -> Version
        addElement("Version", com.sun.imageio.plugins.gif.GIFStreamMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("Version", "value",
                     DATATYPE_STRING, true, null,
                     Arrays.asList(com.sun.imageio.plugins.gif.GIFStreamMetadata.versionStrings));

        // root -> LogicalScreenDescriptor
        addElement("LogicalScreenDescriptor",
                   com.sun.imageio.plugins.gif.GIFStreamMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("LogicalScreenDescriptor", "logicalScreenWidth",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        addAttribute("LogicalScreenDescriptor", "logicalScreenHeight",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        addAttribute("LogicalScreenDescriptor", "colorResolution",
                     DATATYPE_INTEGER, true, null,
                     "1", "8", true, true);
        addAttribute("LogicalScreenDescriptor", "pixelAspectRatio",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);

        // root -> GlobalColorTable
        addElement("GlobalColorTable",
                   com.sun.imageio.plugins.gif.GIFStreamMetadata.nativeMetadataFormatName,
                   2, 256);
        addAttribute("GlobalColorTable", "sizeOfGlobalColorTable",
                     DATATYPE_INTEGER, true, null,
                     Arrays.asList(GIFStreamMetadata.colorTableSizes));
        addAttribute("GlobalColorTable", "backgroundColorIndex",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        addBooleanAttribute("GlobalColorTable", "sortFlag",
                            false, false);

        // root -> GlobalColorTable -> ColorTableEntry
        addElement("ColorTableEntry", "GlobalColorTable",
                   CHILD_POLICY_EMPTY);
        addAttribute("ColorTableEntry", "index",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        addAttribute("ColorTableEntry", "red",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        addAttribute("ColorTableEntry", "green",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        addAttribute("ColorTableEntry", "blue",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
    }

    public boolean canNodeAppear(String elementName,
                                 ImageTypeSpecifier imageType) {
        return true;
    }

    public static synchronized IIOMetadataFormat getInstance() {
        if (instance == null) {
            instance = new GIFStreamMetadataFormat();
        }
        return instance;
    }
}
