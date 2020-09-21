/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.lang;

/**
 * The {@code Compiler} class is provided to support Java-to-native-code
 * compilers and related services. By design, the {@code Compiler} class does
 * nothing; it serves as a placeholder for a JIT compiler implementation.
 *
 * <p> When the Java Virtual Machine first starts, it determines if the system
 * property {@code java.compiler} exists. (System properties are accessible
 * through {@link java.lang.System#getProperty(java.lang.String)} and {@link
 * java.lang.System#getProperty(java.lang.String, java.lang.String)}.  If so, it is assumed to be the name of
 * a library (with a platform-dependent exact location and type); {@link
 * java.lang.System#loadLibrary} is called to load that library. If this loading
 * succeeds, the function named {@code java_lang_Compiler_start()} in that
 * library is called.
 *
 * <p> If no compiler is available, these methods do nothing.
 *
 * @author  Frank Yellin
 * @since   JDK1.0
 */
public final class Compiler  {
    private Compiler() {}               // don't make instances

    private static native void initialize();

    private static native void registerNatives();

    static {
        registerNatives();
        java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<java.lang.Void>() {
                public java.lang.Void run() {
                    boolean loaded = false;
                    java.lang.String jit = java.lang.System.getProperty("java.compiler");
                    if ((jit != null) && (!jit.equals("NONE")) &&
                        (!jit.equals("")))
                    {
                        try {
                            java.lang.System.loadLibrary(jit);
                            initialize();
                            loaded = true;
                        } catch (UnsatisfiedLinkError e) {
                            java.lang.System.err.println("Warning: JIT compiler \"" +
                              jit + "\" not found. Will use interpreter.");
                        }
                    }
                    java.lang.String info = java.lang.System.getProperty("java.vm.info");
                    if (loaded) {
                        java.lang.System.setProperty("java.vm.info", info + ", " + jit);
                    } else {
                        java.lang.System.setProperty("java.vm.info", info + ", nojit");
                    }
                    return null;
                }
            });
    }

    /**
     * Compiles the specified class.
     *
     * @param  clazz
     *         A class
     *
     * @return  {@code true} if the compilation succeeded; {@code false} if the
     *          compilation failed or no compiler is available
     *
     * @throws  NullPointerException
     *          If {@code clazz} is {@code null}
     */
    public static native boolean compileClass(java.lang.Class<?> clazz);

    /**
     * Compiles all classes whose name matches the specified string.
     *
     * @param  string
     *         The name of the classes to compile
     *
     * @return  {@code true} if the compilation succeeded; {@code false} if the
     *          compilation failed or no compiler is available
     *
     * @throws  NullPointerException
     *          If {@code string} is {@code null}
     */
    public static native boolean compileClasses(java.lang.String string);

    /**
     * Examines the argument type and its fields and perform some documented
     * operation.  No specific operations are required.
     *
     * @param  any
     *         An argument
     *
     * @return  A compiler-specific value, or {@code null} if no compiler is
     *          available
     *
     * @throws  NullPointerException
     *          If {@code any} is {@code null}
     */
    public static native java.lang.Object command(java.lang.Object any);

    /**
     * Cause the Compiler to resume operation.
     */
    public static native void enable();

    /**
     * Cause the Compiler to cease operation.
     */
    public static native void disable();
}
