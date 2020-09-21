/*
 * Copyright (c) 2000, 2004, Oracle and/or its affiliates. All rights reserved.
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
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

import goodman.javax.imageio.spi.ImageWriterSpi;
import goodman.javax.imageio.spi.ServiceRegistry;
import goodman.javax.imageio.spi.IIORegistry;
import goodman.javax.imageio.stream.ImageOutputStream;
import goodman.javax.imageio.ImageWriter;
import goodman.javax.imageio.ImageTypeSpecifier;
import goodman.javax.imageio.IIOException;

import goodman.java.awt.image.ColorModel;
import goodman.java.awt.image.IndexColorModel;
import goodman.java.awt.image.SampleModel;
import goodman.java.util.Locale;

public class JPEGImageWriterSpi extends ImageWriterSpi {

    private static String [] readerSpiNames =
        {"com.sun.imageio.plugins.jpeg.JPEGImageReaderSpi"};

    public JPEGImageWriterSpi() {
        super(com.sun.imageio.plugins.jpeg.JPEG.vendor,
              com.sun.imageio.plugins.jpeg.JPEG.version,
              com.sun.imageio.plugins.jpeg.JPEG.names,
              com.sun.imageio.plugins.jpeg.JPEG.suffixes,
              com.sun.imageio.plugins.jpeg.JPEG.MIMETypes,
              "com.sun.imageio.plugins.jpeg.JPEGImageWriter",
              new Class[] { ImageOutputStream.class },
              readerSpiNames,
              true,
              com.sun.imageio.plugins.jpeg.JPEG.nativeStreamMetadataFormatName,
              com.sun.imageio.plugins.jpeg.JPEG.nativeStreamMetadataFormatClassName,
              null, null,
              true,
              com.sun.imageio.plugins.jpeg.JPEG.nativeImageMetadataFormatName,
              JPEG.nativeImageMetadataFormatClassName,
              null, null
              );
    }

    public String getDescription(Locale locale) {
        return "Standard JPEG Image Writer";
    }

    public boolean isFormatLossless() {
        return false;
    }

    public boolean canEncodeImage(ImageTypeSpecifier type) {
        SampleModel sampleModel = type.getSampleModel();

        // Find the maximum bit depth across all channels
        int[] sampleSize = sampleModel.getSampleSize();
        int bitDepth = sampleSize[0];
        for (int i = 1; i < sampleSize.length; i++) {
            if (sampleSize[i] > bitDepth) {
                bitDepth = sampleSize[i];
            }
        }

        // 4450894: Ensure bitDepth is between 1 and 8
        if (bitDepth < 1 || bitDepth > 8) {
            return false;
        }

        return true;
    }

    public ImageWriter createWriterInstance(Object extension)
        throws IIOException {
        return new JPEGImageWriter(this);
    }
}
