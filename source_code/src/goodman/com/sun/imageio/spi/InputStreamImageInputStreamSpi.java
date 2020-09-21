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
import goodman.java.io.InputStream;
import goodman.java.io.IOException;
import goodman.java.util.Locale;
import goodman.javax.imageio.spi.ImageInputStreamSpi;
import goodman.javax.imageio.stream.ImageInputStream;
import goodman.javax.imageio.stream.FileCacheImageInputStream;
import goodman.javax.imageio.stream.MemoryCacheImageInputStream;

public class InputStreamImageInputStreamSpi extends ImageInputStreamSpi {

    private static final String vendorName = "Oracle Corporation";

    private static final String version = "1.0";

    private static final Class inputClass = InputStream.class;

    public InputStreamImageInputStreamSpi() {
        super(vendorName, version, inputClass);
    }

    public String getDescription(Locale locale) {
        return "Service provider that instantiates a FileCacheImageInputStream or MemoryCacheImageInputStream from an InputStream";
    }

    public boolean canUseCacheFile() {
        return true;
    }

    public boolean needsCacheFile() {
        return false;
    }

    public ImageInputStream createInputStreamInstance(Object input,
                                                      boolean useCache,
                                                      File cacheDir)
        throws IOException {
        if (input instanceof InputStream) {
            InputStream is = (InputStream)input;

            if (useCache) {
                return new FileCacheImageInputStream(is, cacheDir);
            } else {
                return new MemoryCacheImageInputStream(is);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
