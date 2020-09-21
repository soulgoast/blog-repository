/*
 * Copyright (c) 2017, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.lang.ref;


import goodman.java.lang.ref.Finalizer;
import goodman.java.lang.ref.ReferenceQueue;
import goodman.java.util.Map;
import goodman.java.util.HashMap;
import goodman.java.util.Arrays;
import goodman.java.util.Comparator;

/**
 * This FinalizerHistogram class is for GC.finalizer_info diagnostic command support.
 * It is invoked by the VM.
 */

final class FinalizerHistogram {

    private static final class Entry {
        private int instanceCount;
        private final String className;

        int getInstanceCount() {
            return instanceCount;
        }

        void increment() {
            instanceCount += 1;
        }

        Entry(String className) {
            this.className = className;
        }
    }

    // Method below is called by VM and VM expect certain
    // entry class layout.

    static Entry[] getFinalizerHistogram() {
        Map<String, Entry> countMap = new HashMap<>();
        ReferenceQueue<Object> queue = java.lang.ref.Finalizer.getQueue();
        queue.forEach(r -> {
            Object referent = r.get();
            if (referent != null) {
                countMap.computeIfAbsent(
                    referent.getClass().getName(), Entry::new).increment();
                /* Clear stack slot containing this variable, to decrease
                   the chances of false retention with a conservative GC */
                referent = null;
            }
        });

        Entry fhe[] = countMap.values().toArray(new Entry[countMap.size()]);
        Arrays.sort(fhe,
                Comparator.comparingInt(Entry::getInstanceCount).reversed());
        return fhe;
    }
}
