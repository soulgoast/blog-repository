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
import com.sun.imageio.plugins.jpeg.JPEGImageReader;

import goodman.java.util.Locale;
import goodman.javax.imageio.spi.ImageReaderSpi;
import goodman.javax.imageio.stream.ImageInputStream;
import goodman.javax.imageio.spi.IIORegistry;
import goodman.javax.imageio.spi.ServiceRegistry;
import goodman.java.io.IOException;
import goodman.javax.imageio.ImageReader;
import goodman.javax.imageio.IIOException;

public class JPEGImageReaderSpi extends ImageReaderSpi {

    private static String [] writerSpiNames =
        {"com.sun.imageio.plugins.jpeg.JPEGImageWriterSpi"};

    public JPEGImageReaderSpi() {
        super(com.sun.imageio.plugins.jpeg.JPEG.vendor,
              com.sun.imageio.plugins.jpeg.JPEG.version,
              com.sun.imageio.plugins.jpeg.JPEG.names,
              com.sun.imageio.plugins.jpeg.JPEG.suffixes,
              com.sun.imageio.plugins.jpeg.JPEG.MIMETypes,
              "com.sun.imageio.plugins.jpeg.JPEGImageReader",
              new Class[] { ImageInputStream.class },
              writerSpiNames,
              true,
              com.sun.imageio.plugins.jpeg.JPEG.nativeStreamMetadataFormatName,
              com.sun.imageio.plugins.jpeg.JPEG.nativeStreamMetadataFormatClassName,
              null, null,
              true,
              com.sun.imageio.plugins.jpeg.JPEG.nativeImageMetadataFormatName,
              com.sun.imageio.plugins.jpeg.JPEG.nativeImageMetadataFormatClassName,
              null, null
              );
    }

    public String getDescription(Locale locale) {
        return "Standard JPEG Image Reader";
    }

    public boolean canDecodeInput(Object source) throws IOException {
        if (!(source instanceof ImageInputStream)) {
            return false;
        }
        ImageInputStream iis = (ImageInputStream) source;
        iis.mark();
        // If the first two bytes are a JPEG SOI marker, it's probably
        // a JPEG file.  If they aren't, it definitely isn't a JPEG file.
        int byte1 = iis.read();
        int byte2 = iis.read();
        iis.reset();
        if ((byte1 == 0xFF) && (byte2 == JPEG.SOI)) {
            return true;
        }
        return false;
    }

    public ImageReader createReaderInstance(Object extension)
        throws IIOException {
        return new JPEGImageReader(this);
    }

}
