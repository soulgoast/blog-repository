/*
 * Copyright (c) 2000, 2010, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.imageio.spi;

import goodman.java.io.File;
import goodman.java.io.RandomAccessFile;
import goodman.java.util.Locale;
import goodman.javax.imageio.spi.ImageOutputStreamSpi;
import goodman.javax.imageio.stream.ImageOutputStream;
import goodman.javax.imageio.stream.FileImageOutputStream;

public class RAFImageOutputStreamSpi extends ImageOutputStreamSpi {

    private static final String vendorName = "Oracle Corporation";

    private static final String version = "1.0";

    private static final Class outputClass = RandomAccessFile.class;

    public RAFImageOutputStreamSpi() {
        super(vendorName, version, outputClass);
    }

    public String getDescription(Locale locale) {
        return "Service provider that instantiates a FileImageOutputStream from a RandomAccessFile";
    }

    public ImageOutputStream createOutputStreamInstance(Object output,
                                                        boolean useCache,
                                                        File cacheDir) {
        if (output instanceof RandomAccessFile) {
            try {
                return new FileImageOutputStream((RandomAccessFile)output);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new IllegalArgumentException
                ("input not a RandomAccessFile!");
        }
    }
}
