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

package goodman.com.sun.imageio.plugins.bmp;

import com.sun.imageio.plugins.bmp.BMPMetadata;

import goodman.java.util.Arrays;
import goodman.javax.imageio.ImageTypeSpecifier;
import goodman.javax.imageio.metadata.IIOMetadataFormat;
import goodman.javax.imageio.metadata.IIOMetadataFormatImpl;

public class BMPMetadataFormat extends IIOMetadataFormatImpl {

    private static IIOMetadataFormat instance = null;

    private BMPMetadataFormat() {
        super(com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
              CHILD_POLICY_SOME);

        // root -> ImageDescriptor
        addElement("ImageDescriptor",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("ImageDescriptor", "bmpVersion",
                     DATATYPE_STRING, true, null);
        addAttribute("ImageDescriptor", "width",
                     DATATYPE_INTEGER, true, null,
                     "0", "65535", true, true);
        addAttribute("ImageDescriptor", "height",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        addAttribute("ImageDescriptor", "bitsPerPixel",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);
        addAttribute("ImageDescriptor", "compression",
                      DATATYPE_INTEGER, false, null);
        addAttribute("ImageDescriptor", "imageSize",
                     DATATYPE_INTEGER, true, null,
                     "1", "65535", true, true);

        addElement("PixelsPerMeter",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("PixelsPerMeter", "X",
                     DATATYPE_INTEGER, false, null,
                     "1", "65535", true, true);
        addAttribute("PixelsPerMeter", "Y",
                     DATATYPE_INTEGER, false, null,
                     "1", "65535", true, true);

        addElement("ColorsUsed",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("ColorsUsed", "value",
                     DATATYPE_INTEGER, true, null,
                     "0", "65535", true, true);

        addElement("ColorsImportant",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("ColorsImportant", "value",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);

        addElement("BI_BITFIELDS_Mask",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("BI_BITFIELDS_Mask", "red",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);
        addAttribute("BI_BITFIELDS_Mask", "green",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);
        addAttribute("BI_BITFIELDS_Mask", "blue",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);

        addElement("ColorSpace",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("ColorSpace", "value",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);

        addElement("LCS_CALIBRATED_RGB",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);

        /// Should the max value be 1.7976931348623157e+308 ?
        addAttribute("LCS_CALIBRATED_RGB", "redX",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "redY",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "redZ",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "greenX",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "greenY",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "greenZ",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "blueX",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "blueY",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB", "blueZ",
                     DATATYPE_DOUBLE, false, null,
                     "0", "65535", true, true);

        addElement("LCS_CALIBRATED_RGB_GAMMA",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("LCS_CALIBRATED_RGB_GAMMA","red",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB_GAMMA","green",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);
        addAttribute("LCS_CALIBRATED_RGB_GAMMA","blue",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);

        addElement("Intent",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   CHILD_POLICY_EMPTY);
        addAttribute("Intent", "value",
                     DATATYPE_INTEGER, false, null,
                     "0", "65535", true, true);

        // root -> Palette
        addElement("Palette",
                   com.sun.imageio.plugins.bmp.BMPMetadata.nativeMetadataFormatName,
                   2, 256);
        addAttribute("Palette", "sizeOfPalette",
                     DATATYPE_INTEGER, true, null);
        addBooleanAttribute("Palette", "sortFlag",
                            false, false);

        // root -> Palette -> PaletteEntry
        addElement("PaletteEntry", "Palette",
                   CHILD_POLICY_EMPTY);
        addAttribute("PaletteEntry", "index",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        addAttribute("PaletteEntry", "red",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        addAttribute("PaletteEntry", "green",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);
        addAttribute("PaletteEntry", "blue",
                     DATATYPE_INTEGER, true, null,
                     "0", "255", true, true);


        // root -> CommentExtensions
        addElement("CommentExtensions",
                   BMPMetadata.nativeMetadataFormatName,
                   1, Integer.MAX_VALUE);

        // root -> CommentExtensions -> CommentExtension
        addElement("CommentExtension", "CommentExtensions",
                   CHILD_POLICY_EMPTY);
        addAttribute("CommentExtension", "value",
                     DATATYPE_STRING, true, null);
    }

    public boolean canNodeAppear(String elementName,
                                 ImageTypeSpecifier imageType) {
        return true;
    }

    public static synchronized IIOMetadataFormat getInstance() {
        if (instance == null) {
            instance = new BMPMetadataFormat();
        }
        return instance;
    }
}
