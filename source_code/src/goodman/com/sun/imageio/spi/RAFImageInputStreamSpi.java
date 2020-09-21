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
import goodman.javax.imageio.spi.ImageInputStreamSpi;
import goodman.javax.imageio.stream.ImageInputStream;
import goodman.javax.imageio.stream.FileImageInputStream;

public class RAFImageInputStreamSpi extends ImageInputStreamSpi {

    private static final String vendorName = "Oracle Corporation";

    private static final String version = "1.0";

    private static final Class inputClass = RandomAccessFile.class;

    public RAFImageInputStreamSpi() {
        super(vendorName, version, inputClass);
    }

    public String getDescription(Locale locale) {
        return "Service provider that instantiates a FileImageInputStream from a RandomAccessFile";
    }

    public ImageInputStream createInputStreamInstance(Object input,
                                                      boolean useCache,
                                                      File cacheDir) {
        if (input instanceof RandomAccessFile) {
            try {
                return new FileImageInputStream((RandomAccessFile)input);
            } catch (Exception e) {
                return null;
            }
        } else {
            throw new IllegalArgumentException
                ("input not a RandomAccessFile!");
        }
    }
}
