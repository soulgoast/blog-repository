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
import goodman.java.io.IOException;
import goodman.java.io.OutputStream;
import goodman.java.util.Locale;
import goodman.javax.imageio.spi.ImageOutputStreamSpi;
import goodman.javax.imageio.stream.ImageOutputStream;
import goodman.javax.imageio.stream.FileCacheImageOutputStream;
import goodman.javax.imageio.stream.MemoryCacheImageOutputStream;

public class OutputStreamImageOutputStreamSpi extends ImageOutputStreamSpi {

    private static final String vendorName = "Oracle Corporation";

    private static final String version = "1.0";

    private static final Class outputClass = OutputStream.class;

    public OutputStreamImageOutputStreamSpi() {
        super(vendorName, version, outputClass);
    }

    public String getDescription(Locale locale) {
        return "Service provider that instantiates an OutputStreamImageOutputStream from an OutputStream";
    }

    public boolean canUseCacheFile() {
        return true;
    }

    public boolean needsCacheFile() {
        return false;
    }

    public ImageOutputStream createOutputStreamInstance(Object output,
                                                        boolean useCache,
                                                        File cacheDir)
        throws IOException {
        if (output instanceof OutputStream) {
            OutputStream os = (OutputStream)output;
            if (useCache) {
                return new FileCacheImageOutputStream(os, cacheDir);
            } else {
                return new MemoryCacheImageOutputStream(os);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
