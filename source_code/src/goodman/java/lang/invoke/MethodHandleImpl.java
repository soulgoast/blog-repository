/*
 * Copyright (c) 2008, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.lang.invoke;

import goodman.java.lang.invoke.*;
import goodman.java.lang.invoke.ForceInline;
import goodman.java.lang.invoke.LambdaForm;
import goodman.java.lang.invoke.MemberName;
import goodman.java.lang.invoke.MethodHandle;
import goodman.java.lang.invoke.MethodHandles;
import goodman.java.lang.invoke.MethodType;
import goodman.java.lang.invoke.MethodTypeForm;
import goodman.java.lang.invoke.Stable;
import goodman.java.security.AccessController;
import goodman.java.security.PrivilegedAction;
import goodman.java.util.ArrayList;
import goodman.java.util.Arrays;
import goodman.java.util.Collections;
import goodman.java.util.function.Function;

import sun.invoke.empty.Empty;
import sun.invoke.util.ValueConversions;
import sun.invoke.util.VerifyType;
import sun.invoke.util.Wrapper;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import static java.lang.invoke.LambdaForm.*;
import static java.lang.invoke.MethodHandleStatics.*;
import static java.lang.invoke.MethodHandles.Lookup.IMPL_LOOKUP;

/**
 * Trusted implementation code for MethodHandle.
 * @author jrose
 */
/*non-public*/ abstract class MethodHandleImpl {
    // Do not adjust this except for special platforms:
    private static final int MAX_ARITY;
    static {
        final Object[] values = { 255 };
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                values[0] = Integer.getInteger(MethodHandleImpl.class.getName()+".MAX_ARITY", 255);
                return null;
            }
        });
        MAX_ARITY = (Integer) values[0];
    }

    /// Factory methods to create method handles:

    static void initStatics() {
        // Trigger selected static initializations.
        java.lang.invoke.MemberName.Factory.INSTANCE.getClass();
    }

    static java.lang.invoke.MethodHandle makeArrayElementAccessor(Class<?> arrayClass, boolean isSetter) {
        if (arrayClass == Object[].class)
            return (isSetter ? ArrayAccessor.OBJECT_ARRAY_SETTER : ArrayAccessor.OBJECT_ARRAY_GETTER);
        if (!arrayClass.isArray())
            throw newIllegalArgumentException("not an array: "+arrayClass);
        java.lang.invoke.MethodHandle[] cache = ArrayAccessor.TYPED_ACCESSORS.get(arrayClass);
        int cacheIndex = (isSetter ? ArrayAccessor.SETTER_INDEX : ArrayAccessor.GETTER_INDEX);
        java.lang.invoke.MethodHandle mh = cache[cacheIndex];
        if (mh != null)  return mh;
        mh = ArrayAccessor.getAccessor(arrayClass, isSetter);
        java.lang.invoke.MethodType correctType = ArrayAccessor.correctType(arrayClass, isSetter);
        if (mh.type() != correctType) {
            assert(mh.type().parameterType(0) == Object[].class);
            assert((isSetter ? mh.type().parameterType(2) : mh.type().returnType()) == Object.class);
            assert(isSetter || correctType.parameterType(0).getComponentType() == correctType.returnType());
            // safe to view non-strictly, because element type follows from array type
            mh = mh.viewAsType(correctType, false);
        }
        mh = makeIntrinsic(mh, (isSetter ? Intrinsic.ARRAY_STORE : Intrinsic.ARRAY_LOAD));
        // Atomically update accessor cache.
        synchronized(cache) {
            if (cache[cacheIndex] == null) {
                cache[cacheIndex] = mh;
            } else {
                // Throw away newly constructed accessor and use cached version.
                mh = cache[cacheIndex];
            }
        }
        return mh;
    }

    static final class ArrayAccessor {
        /// Support for array element access
        static final int GETTER_INDEX = 0, SETTER_INDEX = 1, INDEX_LIMIT = 2;
        static final ClassValue<java.lang.invoke.MethodHandle[]> TYPED_ACCESSORS
                = new ClassValue<java.lang.invoke.MethodHandle[]>() {
                    @Override
                    protected java.lang.invoke.MethodHandle[] computeValue(Class<?> type) {
                        return new java.lang.invoke.MethodHandle[INDEX_LIMIT];
                    }
                };
        static final java.lang.invoke.MethodHandle OBJECT_ARRAY_GETTER, OBJECT_ARRAY_SETTER;
        static {
            java.lang.invoke.MethodHandle[] cache = TYPED_ACCESSORS.get(Object[].class);
            cache[GETTER_INDEX] = OBJECT_ARRAY_GETTER = makeIntrinsic(getAccessor(Object[].class, false), Intrinsic.ARRAY_LOAD);
            cache[SETTER_INDEX] = OBJECT_ARRAY_SETTER = makeIntrinsic(getAccessor(Object[].class, true),  Intrinsic.ARRAY_STORE);

            assert(InvokerBytecodeGenerator.isStaticallyInvocable(ArrayAccessor.OBJECT_ARRAY_GETTER.internalMemberName()));
            assert(InvokerBytecodeGenerator.isStaticallyInvocable(ArrayAccessor.OBJECT_ARRAY_SETTER.internalMemberName()));
        }

        static int     getElementI(int[]     a, int i)            { return              a[i]; }
        static long    getElementJ(long[]    a, int i)            { return              a[i]; }
        static float   getElementF(float[]   a, int i)            { return              a[i]; }
        static double  getElementD(double[]  a, int i)            { return              a[i]; }
        static boolean getElementZ(boolean[] a, int i)            { return              a[i]; }
        static byte    getElementB(byte[]    a, int i)            { return              a[i]; }
        static short   getElementS(short[]   a, int i)            { return              a[i]; }
        static char    getElementC(char[]    a, int i)            { return              a[i]; }
        static Object  getElementL(Object[]  a, int i)            { return              a[i]; }

        static void    setElementI(int[]     a, int i, int     x) {              a[i] = x; }
        static void    setElementJ(long[]    a, int i, long    x) {              a[i] = x; }
        static void    setElementF(float[]   a, int i, float   x) {              a[i] = x; }
        static void    setElementD(double[]  a, int i, double  x) {              a[i] = x; }
        static void    setElementZ(boolean[] a, int i, boolean x) {              a[i] = x; }
        static void    setElementB(byte[]    a, int i, byte    x) {              a[i] = x; }
        static void    setElementS(short[]   a, int i, short   x) {              a[i] = x; }
        static void    setElementC(char[]    a, int i, char    x) {              a[i] = x; }
        static void    setElementL(Object[]  a, int i, Object  x) {              a[i] = x; }

        static String name(Class<?> arrayClass, boolean isSetter) {
            Class<?> elemClass = arrayClass.getComponentType();
            if (elemClass == null)  throw newIllegalArgumentException("not an array", arrayClass);
            return (!isSetter ? "getElement" : "setElement") + Wrapper.basicTypeChar(elemClass);
        }
        static java.lang.invoke.MethodType type(Class<?> arrayClass, boolean isSetter) {
            Class<?> elemClass = arrayClass.getComponentType();
            Class<?> arrayArgClass = arrayClass;
            if (!elemClass.isPrimitive()) {
                arrayArgClass = Object[].class;
                elemClass = Object.class;
            }
            return !isSetter ?
                    java.lang.invoke.MethodType.methodType(elemClass,  arrayArgClass, int.class) :
                    java.lang.invoke.MethodType.methodType(void.class, arrayArgClass, int.class, elemClass);
        }
        static java.lang.invoke.MethodType correctType(Class<?> arrayClass, boolean isSetter) {
            Class<?> elemClass = arrayClass.getComponentType();
            return !isSetter ?
                    java.lang.invoke.MethodType.methodType(elemClass,  arrayClass, int.class) :
                    java.lang.invoke.MethodType.methodType(void.class, arrayClass, int.class, elemClass);
        }
        static java.lang.invoke.MethodHandle getAccessor(Class<?> arrayClass, boolean isSetter) {
            String     name = name(arrayClass, isSetter);
            java.lang.invoke.MethodType type = type(arrayClass, isSetter);
            try {
                return IMPL_LOOKUP.findStatic(ArrayAccessor.class, name, type);
            } catch (ReflectiveOperationException ex) {
                throw uncaughtException(ex);
            }
        }
    }

    /**
     * Create a JVM-level adapter method handle to conform the given method
     * handle to the similar newType, using only pairwise argument conversions.
     * For each argument, convert incoming argument to the exact type needed.
     * The argument conversions allowed are casting, boxing and unboxing,
     * integral widening or narrowing, and floating point widening or narrowing.
     * @param srcType required call type
     * @param target original method handle
     * @param strict if true, only asType conversions are allowed; if false, explicitCastArguments conversions allowed
     * @param monobox if true, unboxing conversions are assumed to be exactly typed (Integer to int only, not long or double)
     * @return an adapter to the original handle with the desired new type,
     *          or the original target if the types are already identical
     *          or null if the adaptation cannot be made
     */
    static java.lang.invoke.MethodHandle makePairwiseConvert(java.lang.invoke.MethodHandle target, java.lang.invoke.MethodType srcType,
                                                             boolean strict, boolean monobox) {
        java.lang.invoke.MethodType dstType = target.type();
        if (srcType == dstType)
            return target;
        return makePairwiseConvertByEditor(target, srcType, strict, monobox);
    }

    private static int countNonNull(Object[] array) {
        int count = 0;
        for (Object x : array) {
            if (x != null)  ++count;
        }
        return count;
    }

    static java.lang.invoke.MethodHandle makePairwiseConvertByEditor(java.lang.invoke.MethodHandle target, java.lang.invoke.MethodType srcType,
                                                                     boolean strict, boolean monobox) {
        Object[] convSpecs = computeValueConversions(srcType, target.type(), strict, monobox);
        int convCount = countNonNull(convSpecs);
        if (convCount == 0)
            return target.viewAsType(srcType, strict);
        java.lang.invoke.MethodType basicSrcType = srcType.basicType();
        java.lang.invoke.MethodType midType = target.type().basicType();
        BoundMethodHandle mh = target.rebind();
        // FIXME: Reduce number of bindings when there is more than one Class conversion.
        // FIXME: Reduce number of bindings when there are repeated conversions.
        for (int i = 0; i < convSpecs.length-1; i++) {
            Object convSpec = convSpecs[i];
            if (convSpec == null)  continue;
            java.lang.invoke.MethodHandle fn;
            if (convSpec instanceof Class) {
                fn = Lazy.MH_castReference.bindTo(convSpec);
            } else {
                fn = (java.lang.invoke.MethodHandle) convSpec;
            }
            Class<?> newType = basicSrcType.parameterType(i);
            if (--convCount == 0)
                midType = srcType;
            else
                midType = midType.changeParameterType(i, newType);
            java.lang.invoke.LambdaForm form2 = mh.editor().filterArgumentForm(1+i, BasicType.basicType(newType));
            mh = mh.copyWithExtendL(midType, form2, fn);
            mh = mh.rebind();
        }
        Object convSpec = convSpecs[convSpecs.length-1];
        if (convSpec != null) {
            java.lang.invoke.MethodHandle fn;
            if (convSpec instanceof Class) {
                if (convSpec == void.class)
                    fn = null;
                else
                    fn = Lazy.MH_castReference.bindTo(convSpec);
            } else {
                fn = (java.lang.invoke.MethodHandle) convSpec;
            }
            Class<?> newType = basicSrcType.returnType();
            assert(--convCount == 0);
            midType = srcType;
            if (fn != null) {
                mh = mh.rebind();  // rebind if too complex
                java.lang.invoke.LambdaForm form2 = mh.editor().filterReturnForm(BasicType.basicType(newType), false);
                mh = mh.copyWithExtendL(midType, form2, fn);
            } else {
                java.lang.invoke.LambdaForm form2 = mh.editor().filterReturnForm(BasicType.basicType(newType), true);
                mh = mh.copyWith(midType, form2);
            }
        }
        assert(convCount == 0);
        assert(mh.type().equals(srcType));
        return mh;
    }

    static java.lang.invoke.MethodHandle makePairwiseConvertIndirect(java.lang.invoke.MethodHandle target, java.lang.invoke.MethodType srcType,
                                                                     boolean strict, boolean monobox) {
        assert(target.type().parameterCount() == srcType.parameterCount());
        // Calculate extra arguments (temporaries) required in the names array.
        Object[] convSpecs = computeValueConversions(srcType, target.type(), strict, monobox);
        final int INARG_COUNT = srcType.parameterCount();
        int convCount = countNonNull(convSpecs);
        boolean retConv = (convSpecs[INARG_COUNT] != null);
        boolean retVoid = srcType.returnType() == void.class;
        if (retConv && retVoid) {
            convCount -= 1;
            retConv = false;
        }

        final int IN_MH         = 0;
        final int INARG_BASE    = 1;
        final int INARG_LIMIT   = INARG_BASE + INARG_COUNT;
        final int NAME_LIMIT    = INARG_LIMIT + convCount + 1;
        final int RETURN_CONV   = (!retConv ? -1         : NAME_LIMIT - 1);
        final int OUT_CALL      = (!retConv ? NAME_LIMIT : RETURN_CONV) - 1;
        final int RESULT        = (retVoid ? -1 : NAME_LIMIT - 1);

        // Now build a LambdaForm.
        java.lang.invoke.MethodType lambdaType = srcType.basicType().invokerType();
        Name[] names = arguments(NAME_LIMIT - INARG_LIMIT, lambdaType);

        // Collect the arguments to the outgoing call, maybe with conversions:
        final int OUTARG_BASE = 0;  // target MH is Name.function, name Name.arguments[0]
        Object[] outArgs = new Object[OUTARG_BASE + INARG_COUNT];

        int nameCursor = INARG_LIMIT;
        for (int i = 0; i < INARG_COUNT; i++) {
            Object convSpec = convSpecs[i];
            if (convSpec == null) {
                // do nothing: difference is trivial
                outArgs[OUTARG_BASE + i] = names[INARG_BASE + i];
                continue;
            }

            Name conv;
            if (convSpec instanceof Class) {
                Class<?> convClass = (Class<?>) convSpec;
                conv = new Name(Lazy.MH_castReference, convClass, names[INARG_BASE + i]);
            } else {
                java.lang.invoke.MethodHandle fn = (java.lang.invoke.MethodHandle) convSpec;
                conv = new Name(fn, names[INARG_BASE + i]);
            }
            assert(names[nameCursor] == null);
            names[nameCursor++] = conv;
            assert(outArgs[OUTARG_BASE + i] == null);
            outArgs[OUTARG_BASE + i] = conv;
        }

        // Build argument array for the call.
        assert(nameCursor == OUT_CALL);
        names[OUT_CALL] = new Name(target, outArgs);

        Object convSpec = convSpecs[INARG_COUNT];
        if (!retConv) {
            assert(OUT_CALL == names.length-1);
        } else {
            Name conv;
            if (convSpec == void.class) {
                conv = new Name(java.lang.invoke.LambdaForm.constantZero(BasicType.basicType(srcType.returnType())));
            } else if (convSpec instanceof Class) {
                Class<?> convClass = (Class<?>) convSpec;
                conv = new Name(Lazy.MH_castReference, convClass, names[OUT_CALL]);
            } else {
                java.lang.invoke.MethodHandle fn = (java.lang.invoke.MethodHandle) convSpec;
                if (fn.type().parameterCount() == 0)
                    conv = new Name(fn);  // don't pass retval to void conversion
                else
                    conv = new Name(fn, names[OUT_CALL]);
            }
            assert(names[RETURN_CONV] == null);
            names[RETURN_CONV] = conv;
            assert(RETURN_CONV == names.length-1);
        }

        java.lang.invoke.LambdaForm form = new java.lang.invoke.LambdaForm("convert", lambdaType.parameterCount(), names, RESULT);
        return SimpleMethodHandle.make(srcType, form);
    }

    /**
     * Identity function, with reference cast.
     * @param t an arbitrary reference type
     * @param x an arbitrary reference value
     * @return the same value x
     */
    @java.lang.invoke.ForceInline
    @SuppressWarnings("unchecked")
    static <T,U> T castReference(Class<? extends T> t, U x) {
        // inlined Class.cast because we can't ForceInline it
        if (x != null && !t.isInstance(x))
            throw newClassCastException(t, x);
        return (T) x;
    }

    private static ClassCastException newClassCastException(Class<?> t, Object obj) {
        return new ClassCastException("Cannot cast " + obj.getClass().getName() + " to " + t.getName());
    }

    static Object[] computeValueConversions(java.lang.invoke.MethodType srcType, java.lang.invoke.MethodType dstType,
                                            boolean strict, boolean monobox) {
        final int INARG_COUNT = srcType.parameterCount();
        Object[] convSpecs = new Object[INARG_COUNT+1];
        for (int i = 0; i <= INARG_COUNT; i++) {
            boolean isRet = (i == INARG_COUNT);
            Class<?> src = isRet ? dstType.returnType() : srcType.parameterType(i);
            Class<?> dst = isRet ? srcType.returnType() : dstType.parameterType(i);
            if (!VerifyType.isNullConversion(src, dst, /*keepInterfaces=*/ strict)) {
                convSpecs[i] = valueConversion(src, dst, strict, monobox);
            }
        }
        return convSpecs;
    }
    static java.lang.invoke.MethodHandle makePairwiseConvert(java.lang.invoke.MethodHandle target, java.lang.invoke.MethodType srcType,
                                                             boolean strict) {
        return makePairwiseConvert(target, srcType, strict, /*monobox=*/ false);
    }

    /**
     * Find a conversion function from the given source to the given destination.
     * This conversion function will be used as a LF NamedFunction.
     * Return a Class object if a simple cast is needed.
     * Return void.class if void is involved.
     */
    static Object valueConversion(Class<?> src, Class<?> dst, boolean strict, boolean monobox) {
        assert(!VerifyType.isNullConversion(src, dst, /*keepInterfaces=*/ strict));  // caller responsibility
        if (dst == void.class)
            return dst;
        java.lang.invoke.MethodHandle fn;
        if (src.isPrimitive()) {
            if (src == void.class) {
                return void.class;  // caller must recognize this specially
            } else if (dst.isPrimitive()) {
                // Examples: int->byte, byte->int, boolean->int (!strict)
                fn = ValueConversions.convertPrimitive(src, dst);
            } else {
                // Examples: int->Integer, boolean->Object, float->Number
                Wrapper wsrc = Wrapper.forPrimitiveType(src);
                fn = ValueConversions.boxExact(wsrc);
                assert(fn.type().parameterType(0) == wsrc.primitiveType());
                assert(fn.type().returnType() == wsrc.wrapperType());
                if (!VerifyType.isNullConversion(wsrc.wrapperType(), dst, strict)) {
                    // Corner case, such as int->Long, which will probably fail.
                    java.lang.invoke.MethodType mt = java.lang.invoke.MethodType.methodType(dst, src);
                    if (strict)
                        fn = fn.asType(mt);
                    else
                        fn = MethodHandleImpl.makePairwiseConvert(fn, mt, /*strict=*/ false);
                }
            }
        } else if (dst.isPrimitive()) {
            Wrapper wdst = Wrapper.forPrimitiveType(dst);
            if (monobox || src == wdst.wrapperType()) {
                // Use a strongly-typed unboxer, if possible.
                fn = ValueConversions.unboxExact(wdst, strict);
            } else {
                // Examples:  Object->int, Number->int, Comparable->int, Byte->int
                // must include additional conversions
                // src must be examined at runtime, to detect Byte, Character, etc.
                fn = (strict
                        ? ValueConversions.unboxWiden(wdst)
                        : ValueConversions.unboxCast(wdst));
            }
        } else {
            // Simple reference conversion.
            // Note:  Do not check for a class hierarchy relation
            // between src and dst.  In all cases a 'null' argument
            // will pass the cast conversion.
            return dst;
        }
        assert(fn.type().parameterCount() <= 1) : "pc"+Arrays.asList(src.getSimpleName(), dst.getSimpleName(), fn);
        return fn;
    }

    static java.lang.invoke.MethodHandle makeVarargsCollector(java.lang.invoke.MethodHandle target, Class<?> arrayType) {
        java.lang.invoke.MethodType type = target.type();
        int last = type.parameterCount() - 1;
        if (type.parameterType(last) != arrayType)
            target = target.asType(type.changeParameterType(last, arrayType));
        target = target.asFixedArity();  // make sure this attribute is turned off
        return new AsVarargsCollector(target, arrayType);
    }

    private static final class AsVarargsCollector extends DelegatingMethodHandle {
        private final java.lang.invoke.MethodHandle target;
        private final Class<?> arrayType;
        private @java.lang.invoke.Stable
        java.lang.invoke.MethodHandle asCollectorCache;

        AsVarargsCollector(java.lang.invoke.MethodHandle target, Class<?> arrayType) {
            this(target.type(), target, arrayType);
        }
        AsVarargsCollector(java.lang.invoke.MethodType type, java.lang.invoke.MethodHandle target, Class<?> arrayType) {
            super(type, target);
            this.target = target;
            this.arrayType = arrayType;
            this.asCollectorCache = target.asCollector(arrayType, 0);
        }

        @Override
        public boolean isVarargsCollector() {
            return true;
        }

        @Override
        protected java.lang.invoke.MethodHandle getTarget() {
            return target;
        }

        @Override
        public java.lang.invoke.MethodHandle asFixedArity() {
            return target;
        }

        @Override
        java.lang.invoke.MethodHandle setVarargs(java.lang.invoke.MemberName member) {
            if (member.isVarargs())  return this;
            return asFixedArity();
        }

        @Override
        public java.lang.invoke.MethodHandle asTypeUncached(java.lang.invoke.MethodType newType) {
            java.lang.invoke.MethodType type = this.type();
            int collectArg = type.parameterCount() - 1;
            int newArity = newType.parameterCount();
            if (newArity == collectArg+1 &&
                type.parameterType(collectArg).isAssignableFrom(newType.parameterType(collectArg))) {
                // if arity and trailing parameter are compatible, do normal thing
                return asTypeCache = asFixedArity().asType(newType);
            }
            // check cache
            java.lang.invoke.MethodHandle acc = asCollectorCache;
            if (acc != null && acc.type().parameterCount() == newArity)
                return asTypeCache = acc.asType(newType);
            // build and cache a collector
            int arrayLength = newArity - collectArg;
            java.lang.invoke.MethodHandle collector;
            try {
                collector = asFixedArity().asCollector(arrayType, arrayLength);
                assert(collector.type().parameterCount() == newArity) : "newArity="+newArity+" but collector="+collector;
            } catch (IllegalArgumentException ex) {
                throw new WrongMethodTypeException("cannot build collector", ex);
            }
            asCollectorCache = collector;
            return asTypeCache = collector.asType(newType);
        }

        @Override
        boolean viewAsTypeChecks(java.lang.invoke.MethodType newType, boolean strict) {
            super.viewAsTypeChecks(newType, true);
            if (strict) return true;
            // extra assertion for non-strict checks:
            assert (type().lastParameterType().getComponentType()
                    .isAssignableFrom(
                            newType.lastParameterType().getComponentType()))
                    : Arrays.asList(this, newType);
            return true;
        }
    }

    /** Factory method:  Spread selected argument. */
    static java.lang.invoke.MethodHandle makeSpreadArguments(java.lang.invoke.MethodHandle target,
                                                             Class<?> spreadArgType, int spreadArgPos, int spreadArgCount) {
        java.lang.invoke.MethodType targetType = target.type();

        for (int i = 0; i < spreadArgCount; i++) {
            Class<?> arg = VerifyType.spreadArgElementType(spreadArgType, i);
            if (arg == null)  arg = Object.class;
            targetType = targetType.changeParameterType(spreadArgPos + i, arg);
        }
        target = target.asType(targetType);

        java.lang.invoke.MethodType srcType = targetType
                .replaceParameterTypes(spreadArgPos, spreadArgPos + spreadArgCount, spreadArgType);
        // Now build a LambdaForm.
        java.lang.invoke.MethodType lambdaType = srcType.invokerType();
        Name[] names = arguments(spreadArgCount + 2, lambdaType);
        int nameCursor = lambdaType.parameterCount();
        int[] indexes = new int[targetType.parameterCount()];

        for (int i = 0, argIndex = 1; i < targetType.parameterCount() + 1; i++, argIndex++) {
            Class<?> src = lambdaType.parameterType(i);
            if (i == spreadArgPos) {
                // Spread the array.
                java.lang.invoke.MethodHandle aload = java.lang.invoke.MethodHandles.arrayElementGetter(spreadArgType);
                Name array = names[argIndex];
                names[nameCursor++] = new Name(Lazy.NF_checkSpreadArgument, array, spreadArgCount);
                for (int j = 0; j < spreadArgCount; i++, j++) {
                    indexes[i] = nameCursor;
                    names[nameCursor++] = new Name(aload, array, j);
                }
            } else if (i < indexes.length) {
                indexes[i] = argIndex;
            }
        }
        assert(nameCursor == names.length-1);  // leave room for the final call

        // Build argument array for the call.
        Name[] targetArgs = new Name[targetType.parameterCount()];
        for (int i = 0; i < targetType.parameterCount(); i++) {
            int idx = indexes[i];
            targetArgs[i] = names[idx];
        }
        names[names.length - 1] = new Name(target, (Object[]) targetArgs);

        java.lang.invoke.LambdaForm form = new java.lang.invoke.LambdaForm("spread", lambdaType.parameterCount(), names);
        return SimpleMethodHandle.make(srcType, form);
    }

    static void checkSpreadArgument(Object av, int n) {
        if (av == null) {
            if (n == 0)  return;
        } else if (av instanceof Object[]) {
            int len = ((Object[])av).length;
            if (len == n)  return;
        } else {
            int len = java.lang.reflect.Array.getLength(av);
            if (len == n)  return;
        }
        // fall through to error:
        throw newIllegalArgumentException("array is not of length "+n);
    }

    /**
     * Pre-initialized NamedFunctions for bootstrapping purposes.
     * Factored in an inner class to delay initialization until first usage.
     */
    static class Lazy {
        private static final Class<?> MHI = MethodHandleImpl.class;

        private static final java.lang.invoke.MethodHandle[] ARRAYS;
        private static final java.lang.invoke.MethodHandle[] FILL_ARRAYS;

        static final NamedFunction NF_checkSpreadArgument;
        static final NamedFunction NF_guardWithCatch;
        static final NamedFunction NF_throwException;
        static final NamedFunction NF_profileBoolean;

        static final java.lang.invoke.MethodHandle MH_castReference;
        static final java.lang.invoke.MethodHandle MH_selectAlternative;
        static final java.lang.invoke.MethodHandle MH_copyAsPrimitiveArray;
        static final java.lang.invoke.MethodHandle MH_fillNewTypedArray;
        static final java.lang.invoke.MethodHandle MH_fillNewArray;
        static final java.lang.invoke.MethodHandle MH_arrayIdentity;

        static {
            ARRAYS      = makeArrays();
            FILL_ARRAYS = makeFillArrays();

            try {
                NF_checkSpreadArgument = new NamedFunction(MHI.getDeclaredMethod("checkSpreadArgument", Object.class, int.class));
                NF_guardWithCatch      = new NamedFunction(MHI.getDeclaredMethod("guardWithCatch", java.lang.invoke.MethodHandle.class, Class.class,
                                                                                 java.lang.invoke.MethodHandle.class, Object[].class));
                NF_throwException      = new NamedFunction(MHI.getDeclaredMethod("throwException", Throwable.class));
                NF_profileBoolean      = new NamedFunction(MHI.getDeclaredMethod("profileBoolean", boolean.class, int[].class));

                NF_checkSpreadArgument.resolve();
                NF_guardWithCatch.resolve();
                NF_throwException.resolve();
                NF_profileBoolean.resolve();

                MH_castReference        = IMPL_LOOKUP.findStatic(MHI, "castReference",
                                            java.lang.invoke.MethodType.methodType(Object.class, Class.class, Object.class));
                MH_copyAsPrimitiveArray = IMPL_LOOKUP.findStatic(MHI, "copyAsPrimitiveArray",
                                            java.lang.invoke.MethodType.methodType(Object.class, Wrapper.class, Object[].class));
                MH_arrayIdentity        = IMPL_LOOKUP.findStatic(MHI, "identity",
                                            java.lang.invoke.MethodType.methodType(Object[].class, Object[].class));
                MH_fillNewArray         = IMPL_LOOKUP.findStatic(MHI, "fillNewArray",
                                            java.lang.invoke.MethodType.methodType(Object[].class, Integer.class, Object[].class));
                MH_fillNewTypedArray    = IMPL_LOOKUP.findStatic(MHI, "fillNewTypedArray",
                                            java.lang.invoke.MethodType.methodType(Object[].class, Object[].class, Integer.class, Object[].class));

                MH_selectAlternative    = makeIntrinsic(
                        IMPL_LOOKUP.findStatic(MHI, "selectAlternative",
                                java.lang.invoke.MethodType.methodType(java.lang.invoke.MethodHandle.class, boolean.class, java.lang.invoke.MethodHandle.class, java.lang.invoke.MethodHandle.class)),
                        Intrinsic.SELECT_ALTERNATIVE);
            } catch (ReflectiveOperationException ex) {
                throw newInternalError(ex);
            }
        }
    }

    /** Factory method:  Collect or filter selected argument(s). */
    static java.lang.invoke.MethodHandle makeCollectArguments(java.lang.invoke.MethodHandle target,
                                                              java.lang.invoke.MethodHandle collector, int collectArgPos, boolean retainOriginalArgs) {
        java.lang.invoke.MethodType targetType = target.type();          // (a..., c, [b...])=>r
        java.lang.invoke.MethodType collectorType = collector.type();    // (b...)=>c
        int collectArgCount = collectorType.parameterCount();
        Class<?> collectValType = collectorType.returnType();
        int collectValCount = (collectValType == void.class ? 0 : 1);
        java.lang.invoke.MethodType srcType = targetType                 // (a..., [b...])=>r
                .dropParameterTypes(collectArgPos, collectArgPos+collectValCount);
        if (!retainOriginalArgs) {                      // (a..., b...)=>r
            srcType = srcType.insertParameterTypes(collectArgPos, collectorType.parameterList());
        }
        // in  arglist: [0: ...keep1 | cpos: collect...  | cpos+cacount: keep2... ]
        // out arglist: [0: ...keep1 | cpos: collectVal? | cpos+cvcount: keep2... ]
        // out(retain): [0: ...keep1 | cpos: cV? coll... | cpos+cvc+cac: keep2... ]

        // Now build a LambdaForm.
        java.lang.invoke.MethodType lambdaType = srcType.invokerType();
        Name[] names = arguments(2, lambdaType);
        final int collectNamePos = names.length - 2;
        final int targetNamePos  = names.length - 1;

        Name[] collectorArgs = Arrays.copyOfRange(names, 1 + collectArgPos, 1 + collectArgPos + collectArgCount);
        names[collectNamePos] = new Name(collector, (Object[]) collectorArgs);

        // Build argument array for the target.
        // Incoming LF args to copy are: [ (mh) headArgs collectArgs tailArgs ].
        // Output argument array is [ headArgs (collectVal)? (collectArgs)? tailArgs ].
        Name[] targetArgs = new Name[targetType.parameterCount()];
        int inputArgPos  = 1;  // incoming LF args to copy to target
        int targetArgPos = 0;  // fill pointer for targetArgs
        int chunk = collectArgPos;  // |headArgs|
        System.arraycopy(names, inputArgPos, targetArgs, targetArgPos, chunk);
        inputArgPos  += chunk;
        targetArgPos += chunk;
        if (collectValType != void.class) {
            targetArgs[targetArgPos++] = names[collectNamePos];
        }
        chunk = collectArgCount;
        if (retainOriginalArgs) {
            System.arraycopy(names, inputArgPos, targetArgs, targetArgPos, chunk);
            targetArgPos += chunk;   // optionally pass on the collected chunk
        }
        inputArgPos += chunk;
        chunk = targetArgs.length - targetArgPos;  // all the rest
        System.arraycopy(names, inputArgPos, targetArgs, targetArgPos, chunk);
        assert(inputArgPos + chunk == collectNamePos);  // use of rest of input args also
        names[targetNamePos] = new Name(target, (Object[]) targetArgs);

        java.lang.invoke.LambdaForm form = new java.lang.invoke.LambdaForm("collect", lambdaType.parameterCount(), names);
        return SimpleMethodHandle.make(srcType, form);
    }

    @java.lang.invoke.LambdaForm.Hidden
    static java.lang.invoke.MethodHandle selectAlternative(boolean testResult, java.lang.invoke.MethodHandle target, java.lang.invoke.MethodHandle fallback) {
        if (testResult) {
            return target;
        } else {
            return fallback;
        }
    }

    // Intrinsified by C2. Counters are used during parsing to calculate branch frequencies.
    @java.lang.invoke.LambdaForm.Hidden
    static
    boolean profileBoolean(boolean result, int[] counters) {
        // Profile is int[2] where [0] and [1] correspond to false and true occurrences respectively.
        int idx = result ? 1 : 0;
        try {
            counters[idx] = Math.addExact(counters[idx], 1);
        } catch (ArithmeticException e) {
            // Avoid continuous overflow by halving the problematic count.
            counters[idx] = counters[idx] / 2;
        }
        return result;
    }

    static java.lang.invoke.MethodHandle makeGuardWithTest(java.lang.invoke.MethodHandle test,
                                                           java.lang.invoke.MethodHandle target,
                                                           java.lang.invoke.MethodHandle fallback) {
        java.lang.invoke.MethodType type = target.type();
        assert(test.type().equals(type.changeReturnType(boolean.class)) && fallback.type().equals(type));
        java.lang.invoke.MethodType basicType = type.basicType();
        java.lang.invoke.LambdaForm form = makeGuardWithTestForm(basicType);
        BoundMethodHandle mh;
        try {
            if (PROFILE_GWT) {
                int[] counts = new int[2];
                mh = (BoundMethodHandle)
                        BoundMethodHandle.speciesData_LLLL().constructor().invokeBasic(type, form,
                                (Object) test, (Object) profile(target), (Object) profile(fallback), counts);
            } else {
                mh = (BoundMethodHandle)
                        BoundMethodHandle.speciesData_LLL().constructor().invokeBasic(type, form,
                                (Object) test, (Object) profile(target), (Object) profile(fallback));
            }
        } catch (Throwable ex) {
            throw uncaughtException(ex);
        }
        assert(mh.type() == type);
        return mh;
    }


    static java.lang.invoke.MethodHandle profile(java.lang.invoke.MethodHandle target) {
        if (DONT_INLINE_THRESHOLD >= 0) {
            return makeBlockInlningWrapper(target);
        } else {
            return target;
        }
    }

    /**
     * Block inlining during JIT-compilation of a target method handle if it hasn't been invoked enough times.
     * Corresponding LambdaForm has @DontInline when compiled into bytecode.
     */
    static java.lang.invoke.MethodHandle makeBlockInlningWrapper(java.lang.invoke.MethodHandle target) {
        java.lang.invoke.LambdaForm lform = PRODUCE_BLOCK_INLINING_FORM.apply(target);
        return new CountingWrapper(target, lform,
                PRODUCE_BLOCK_INLINING_FORM, PRODUCE_REINVOKER_FORM,
                                   DONT_INLINE_THRESHOLD);
    }

    /** Constructs reinvoker lambda form which block inlining during JIT-compilation for a particular method handle */
    private static final Function<java.lang.invoke.MethodHandle, java.lang.invoke.LambdaForm> PRODUCE_BLOCK_INLINING_FORM = new Function<java.lang.invoke.MethodHandle, java.lang.invoke.LambdaForm>() {
        @Override
        public java.lang.invoke.LambdaForm apply(java.lang.invoke.MethodHandle target) {
            return DelegatingMethodHandle.makeReinvokerForm(target,
                               java.lang.invoke.MethodTypeForm.LF_DELEGATE_BLOCK_INLINING, CountingWrapper.class, "reinvoker.dontInline", false,
                               DelegatingMethodHandle.NF_getTarget, CountingWrapper.NF_maybeStopCounting);
        }
    };

    /** Constructs simple reinvoker lambda form for a particular method handle */
    private static final Function<java.lang.invoke.MethodHandle, java.lang.invoke.LambdaForm> PRODUCE_REINVOKER_FORM = new Function<java.lang.invoke.MethodHandle, java.lang.invoke.LambdaForm>() {
        @Override
        public java.lang.invoke.LambdaForm apply(java.lang.invoke.MethodHandle target) {
            return DelegatingMethodHandle.makeReinvokerForm(target,
                    java.lang.invoke.MethodTypeForm.LF_DELEGATE, DelegatingMethodHandle.class, DelegatingMethodHandle.NF_getTarget);
        }
    };

    /**
     * Counting method handle. It has 2 states: counting and non-counting.
     * It is in counting state for the first n invocations and then transitions to non-counting state.
     * Behavior in counting and non-counting states is determined by lambda forms produced by
     * countingFormProducer & nonCountingFormProducer respectively.
     */
    static class CountingWrapper extends DelegatingMethodHandle {
        private final java.lang.invoke.MethodHandle target;
        private int count;
        private Function<java.lang.invoke.MethodHandle, java.lang.invoke.LambdaForm> countingFormProducer;
        private Function<java.lang.invoke.MethodHandle, java.lang.invoke.LambdaForm> nonCountingFormProducer;
        private volatile boolean isCounting;

        private CountingWrapper(java.lang.invoke.MethodHandle target, java.lang.invoke.LambdaForm lform,
                                Function<java.lang.invoke.MethodHandle, java.lang.invoke.LambdaForm> countingFromProducer,
                                Function<java.lang.invoke.MethodHandle, java.lang.invoke.LambdaForm> nonCountingFormProducer,
                                int count) {
            super(target.type(), lform);
            this.target = target;
            this.count = count;
            this.countingFormProducer = countingFromProducer;
            this.nonCountingFormProducer = nonCountingFormProducer;
            this.isCounting = (count > 0);
        }

        @Hidden
        @Override
        protected java.lang.invoke.MethodHandle getTarget() {
            return target;
        }

        @Override
        public java.lang.invoke.MethodHandle asTypeUncached(java.lang.invoke.MethodType newType) {
            java.lang.invoke.MethodHandle newTarget = target.asType(newType);
            java.lang.invoke.MethodHandle wrapper;
            if (isCounting) {
                java.lang.invoke.LambdaForm lform;
                lform = countingFormProducer.apply(newTarget);
                wrapper = new CountingWrapper(newTarget, lform, countingFormProducer, nonCountingFormProducer, DONT_INLINE_THRESHOLD);
            } else {
                wrapper = newTarget; // no need for a counting wrapper anymore
            }
            return (asTypeCache = wrapper);
        }

        boolean countDown() {
            if (count <= 0) {
                // Try to limit number of updates. MethodHandle.updateForm() doesn't guarantee LF update visibility.
                if (isCounting) {
                    isCounting = false;
                    return true;
                } else {
                    return false;
                }
            } else {
                --count;
                return false;
            }
        }

        @Hidden
        static void maybeStopCounting(Object o1) {
             CountingWrapper wrapper = (CountingWrapper) o1;
             if (wrapper.countDown()) {
                 // Reached invocation threshold. Replace counting behavior with a non-counting one.
                 java.lang.invoke.LambdaForm lform = wrapper.nonCountingFormProducer.apply(wrapper.target);
                 lform.compileToBytecode(); // speed up warmup by avoiding LF interpretation again after transition
                 wrapper.updateForm(lform);
             }
        }

        static final NamedFunction NF_maybeStopCounting;
        static {
            Class<?> THIS_CLASS = CountingWrapper.class;
            try {
                NF_maybeStopCounting = new NamedFunction(THIS_CLASS.getDeclaredMethod("maybeStopCounting", Object.class));
            } catch (ReflectiveOperationException ex) {
                throw newInternalError(ex);
            }
        }
    }

    static java.lang.invoke.LambdaForm makeGuardWithTestForm(java.lang.invoke.MethodType basicType) {
        java.lang.invoke.LambdaForm lform = basicType.form().cachedLambdaForm(java.lang.invoke.MethodTypeForm.LF_GWT);
        if (lform != null)  return lform;
        final int THIS_MH      = 0;  // the BMH_LLL
        final int ARG_BASE     = 1;  // start of incoming arguments
        final int ARG_LIMIT    = ARG_BASE + basicType.parameterCount();
        int nameCursor = ARG_LIMIT;
        final int GET_TEST     = nameCursor++;
        final int GET_TARGET   = nameCursor++;
        final int GET_FALLBACK = nameCursor++;
        final int GET_COUNTERS = PROFILE_GWT ? nameCursor++ : -1;
        final int CALL_TEST    = nameCursor++;
        final int PROFILE      = (GET_COUNTERS != -1) ? nameCursor++ : -1;
        final int TEST         = nameCursor-1; // previous statement: either PROFILE or CALL_TEST
        final int SELECT_ALT   = nameCursor++;
        final int CALL_TARGET  = nameCursor++;
        assert(CALL_TARGET == SELECT_ALT+1);  // must be true to trigger IBG.emitSelectAlternative

        java.lang.invoke.MethodType lambdaType = basicType.invokerType();
        Name[] names = arguments(nameCursor - ARG_LIMIT, lambdaType);

        BoundMethodHandle.SpeciesData data =
                (GET_COUNTERS != -1) ? BoundMethodHandle.speciesData_LLLL()
                                     : BoundMethodHandle.speciesData_LLL();
        names[THIS_MH] = names[THIS_MH].withConstraint(data);
        names[GET_TEST]     = new Name(data.getterFunction(0), names[THIS_MH]);
        names[GET_TARGET]   = new Name(data.getterFunction(1), names[THIS_MH]);
        names[GET_FALLBACK] = new Name(data.getterFunction(2), names[THIS_MH]);
        if (GET_COUNTERS != -1) {
            names[GET_COUNTERS] = new Name(data.getterFunction(3), names[THIS_MH]);
        }
        Object[] invokeArgs = Arrays.copyOfRange(names, 0, ARG_LIMIT, Object[].class);

        // call test
        java.lang.invoke.MethodType testType = basicType.changeReturnType(boolean.class).basicType();
        invokeArgs[0] = names[GET_TEST];
        names[CALL_TEST] = new Name(testType, invokeArgs);

        // profile branch
        if (PROFILE != -1) {
            names[PROFILE] = new Name(Lazy.NF_profileBoolean, names[CALL_TEST], names[GET_COUNTERS]);
        }
        // call selectAlternative
        names[SELECT_ALT] = new Name(Lazy.MH_selectAlternative, names[TEST], names[GET_TARGET], names[GET_FALLBACK]);

        // call target or fallback
        invokeArgs[0] = names[SELECT_ALT];
        names[CALL_TARGET] = new Name(basicType, invokeArgs);

        lform = new java.lang.invoke.LambdaForm("guard", lambdaType.parameterCount(), names, /*forceInline=*/true);

        return basicType.form().setCachedLambdaForm(java.lang.invoke.MethodTypeForm.LF_GWT, lform);
    }

    /**
     * The LambaForm shape for catchException combinator is the following:
     * <blockquote><pre>{@code
     *  guardWithCatch=Lambda(a0:L,a1:L,a2:L)=>{
     *    t3:L=BoundMethodHandle$Species_LLLLL.argL0(a0:L);
     *    t4:L=BoundMethodHandle$Species_LLLLL.argL1(a0:L);
     *    t5:L=BoundMethodHandle$Species_LLLLL.argL2(a0:L);
     *    t6:L=BoundMethodHandle$Species_LLLLL.argL3(a0:L);
     *    t7:L=BoundMethodHandle$Species_LLLLL.argL4(a0:L);
     *    t8:L=MethodHandle.invokeBasic(t6:L,a1:L,a2:L);
     *    t9:L=MethodHandleImpl.guardWithCatch(t3:L,t4:L,t5:L,t8:L);
     *   t10:I=MethodHandle.invokeBasic(t7:L,t9:L);t10:I}
     * }</pre></blockquote>
     *
     * argL0 and argL2 are target and catcher method handles. argL1 is exception class.
     * argL3 and argL4 are auxiliary method handles: argL3 boxes arguments and wraps them into Object[]
     * (ValueConversions.array()) and argL4 unboxes result if necessary (ValueConversions.unbox()).
     *
     * Having t8 and t10 passed outside and not hardcoded into a lambda form allows to share lambda forms
     * among catchException combinators with the same basic type.
     */
    private static java.lang.invoke.LambdaForm makeGuardWithCatchForm(java.lang.invoke.MethodType basicType) {
        java.lang.invoke.MethodType lambdaType = basicType.invokerType();

        java.lang.invoke.LambdaForm lform = basicType.form().cachedLambdaForm(java.lang.invoke.MethodTypeForm.LF_GWC);
        if (lform != null) {
            return lform;
        }
        final int THIS_MH      = 0;  // the BMH_LLLLL
        final int ARG_BASE     = 1;  // start of incoming arguments
        final int ARG_LIMIT    = ARG_BASE + basicType.parameterCount();

        int nameCursor = ARG_LIMIT;
        final int GET_TARGET       = nameCursor++;
        final int GET_CLASS        = nameCursor++;
        final int GET_CATCHER      = nameCursor++;
        final int GET_COLLECT_ARGS = nameCursor++;
        final int GET_UNBOX_RESULT = nameCursor++;
        final int BOXED_ARGS       = nameCursor++;
        final int TRY_CATCH        = nameCursor++;
        final int UNBOX_RESULT     = nameCursor++;

        Name[] names = arguments(nameCursor - ARG_LIMIT, lambdaType);

        BoundMethodHandle.SpeciesData data = BoundMethodHandle.speciesData_LLLLL();
        names[THIS_MH]          = names[THIS_MH].withConstraint(data);
        names[GET_TARGET]       = new Name(data.getterFunction(0), names[THIS_MH]);
        names[GET_CLASS]        = new Name(data.getterFunction(1), names[THIS_MH]);
        names[GET_CATCHER]      = new Name(data.getterFunction(2), names[THIS_MH]);
        names[GET_COLLECT_ARGS] = new Name(data.getterFunction(3), names[THIS_MH]);
        names[GET_UNBOX_RESULT] = new Name(data.getterFunction(4), names[THIS_MH]);

        // FIXME: rework argument boxing/result unboxing logic for LF interpretation

        // t_{i}:L=MethodHandle.invokeBasic(collectArgs:L,a1:L,...);
        java.lang.invoke.MethodType collectArgsType = basicType.changeReturnType(Object.class);
        java.lang.invoke.MethodHandle invokeBasic = java.lang.invoke.MethodHandles.basicInvoker(collectArgsType);
        Object[] args = new Object[invokeBasic.type().parameterCount()];
        args[0] = names[GET_COLLECT_ARGS];
        System.arraycopy(names, ARG_BASE, args, 1, ARG_LIMIT-ARG_BASE);
        names[BOXED_ARGS] = new Name(makeIntrinsic(invokeBasic, Intrinsic.GUARD_WITH_CATCH), args);

        // t_{i+1}:L=MethodHandleImpl.guardWithCatch(target:L,exType:L,catcher:L,t_{i}:L);
        Object[] gwcArgs = new Object[] {names[GET_TARGET], names[GET_CLASS], names[GET_CATCHER], names[BOXED_ARGS]};
        names[TRY_CATCH] = new Name(Lazy.NF_guardWithCatch, gwcArgs);

        // t_{i+2}:I=MethodHandle.invokeBasic(unbox:L,t_{i+1}:L);
        java.lang.invoke.MethodHandle invokeBasicUnbox = java.lang.invoke.MethodHandles.basicInvoker(java.lang.invoke.MethodType.methodType(basicType.rtype(), Object.class));
        Object[] unboxArgs  = new Object[] {names[GET_UNBOX_RESULT], names[TRY_CATCH]};
        names[UNBOX_RESULT] = new Name(invokeBasicUnbox, unboxArgs);

        lform = new java.lang.invoke.LambdaForm("guardWithCatch", lambdaType.parameterCount(), names);

        return basicType.form().setCachedLambdaForm(java.lang.invoke.MethodTypeForm.LF_GWC, lform);
    }

    static java.lang.invoke.MethodHandle makeGuardWithCatch(java.lang.invoke.MethodHandle target,
                                                            Class<? extends Throwable> exType,
                                                            java.lang.invoke.MethodHandle catcher) {
        java.lang.invoke.MethodType type = target.type();
        java.lang.invoke.LambdaForm form = makeGuardWithCatchForm(type.basicType());

        // Prepare auxiliary method handles used during LambdaForm interpreation.
        // Box arguments and wrap them into Object[]: ValueConversions.array().
        java.lang.invoke.MethodType varargsType = type.changeReturnType(Object[].class);
        java.lang.invoke.MethodHandle collectArgs = varargsArray(type.parameterCount()).asType(varargsType);
        // Result unboxing: ValueConversions.unbox() OR ValueConversions.identity() OR ValueConversions.ignore().
        java.lang.invoke.MethodHandle unboxResult;
        Class<?> rtype = type.returnType();
        if (rtype.isPrimitive()) {
            if (rtype == void.class) {
                unboxResult = ValueConversions.ignore();
            } else {
                Wrapper w = Wrapper.forPrimitiveType(type.returnType());
                unboxResult = ValueConversions.unboxExact(w);
            }
        } else {
            unboxResult = java.lang.invoke.MethodHandles.identity(Object.class);
        }

        BoundMethodHandle.SpeciesData data = BoundMethodHandle.speciesData_LLLLL();
        BoundMethodHandle mh;
        try {
            mh = (BoundMethodHandle)
                    data.constructor().invokeBasic(type, form, (Object) target, (Object) exType, (Object) catcher,
                                                   (Object) collectArgs, (Object) unboxResult);
        } catch (Throwable ex) {
            throw uncaughtException(ex);
        }
        assert(mh.type() == type);
        return mh;
    }

    /**
     * Intrinsified during LambdaForm compilation
     * (see {@link InvokerBytecodeGenerator#emitGuardWithCatch emitGuardWithCatch}).
     */
    @java.lang.invoke.LambdaForm.Hidden
    static Object guardWithCatch(java.lang.invoke.MethodHandle target, Class<? extends Throwable> exType, java.lang.invoke.MethodHandle catcher,
                                 Object... av) throws Throwable {
        // Use asFixedArity() to avoid unnecessary boxing of last argument for VarargsCollector case.
        try {
            return target.asFixedArity().invokeWithArguments(av);
        } catch (Throwable t) {
            if (!exType.isInstance(t)) throw t;
            return catcher.asFixedArity().invokeWithArguments(prepend(t, av));
        }
    }

    /** Prepend an element {@code elem} to an {@code array}. */
    @java.lang.invoke.LambdaForm.Hidden
    private static Object[] prepend(Object elem, Object[] array) {
        Object[] newArray = new Object[array.length+1];
        newArray[0] = elem;
        System.arraycopy(array, 0, newArray, 1, array.length);
        return newArray;
    }

    static java.lang.invoke.MethodHandle throwException(java.lang.invoke.MethodType type) {
        assert(Throwable.class.isAssignableFrom(type.parameterType(0)));
        int arity = type.parameterCount();
        if (arity > 1) {
            java.lang.invoke.MethodHandle mh = throwException(type.dropParameterTypes(1, arity));
            mh = java.lang.invoke.MethodHandles.dropArguments(mh, 1, type.parameterList().subList(1, arity));
            return mh;
        }
        return makePairwiseConvert(Lazy.NF_throwException.resolvedHandle(), type, false, true);
    }

    static <T extends Throwable> Empty throwException(T t) throws T { throw t; }

    static java.lang.invoke.MethodHandle[] FAKE_METHOD_HANDLE_INVOKE = new java.lang.invoke.MethodHandle[2];
    static java.lang.invoke.MethodHandle fakeMethodHandleInvoke(java.lang.invoke.MemberName method) {
        int idx;
        assert(method.isMethodHandleInvoke());
        switch (method.getName()) {
        case "invoke":       idx = 0; break;
        case "invokeExact":  idx = 1; break;
        default:             throw new InternalError(method.getName());
        }
        java.lang.invoke.MethodHandle mh = FAKE_METHOD_HANDLE_INVOKE[idx];
        if (mh != null)  return mh;
        java.lang.invoke.MethodType type = java.lang.invoke.MethodType.methodType(Object.class, UnsupportedOperationException.class,
                                                java.lang.invoke.MethodHandle.class, Object[].class);
        mh = throwException(type);
        mh = mh.bindTo(new UnsupportedOperationException("cannot reflectively invoke MethodHandle"));
        if (!method.getInvocationType().equals(mh.type()))
            throw new InternalError(method.toString());
        mh = mh.withInternalMemberName(method, false);
        mh = mh.asVarargsCollector(Object[].class);
        assert(method.isVarargs());
        FAKE_METHOD_HANDLE_INVOKE[idx] = mh;
        return mh;
    }

    /**
     * Create an alias for the method handle which, when called,
     * appears to be called from the same class loader and protection domain
     * as hostClass.
     * This is an expensive no-op unless the method which is called
     * is sensitive to its caller.  A small number of system methods
     * are in this category, including Class.forName and Method.invoke.
     */
    static java.lang.invoke.MethodHandle bindCaller(java.lang.invoke.MethodHandle mh, Class<?> hostClass) {
        return BindCaller.bindCaller(mh, hostClass);
    }

    // Put the whole mess into its own nested class.
    // That way we can lazily load the code and set up the constants.
    private static class BindCaller {
        static java.lang.invoke.MethodHandle bindCaller(java.lang.invoke.MethodHandle mh, Class<?> hostClass) {
            // Do not use this function to inject calls into system classes.
            if (hostClass == null
                ||    (hostClass.isArray() ||
                       hostClass.isPrimitive() ||
                       hostClass.getName().startsWith("java.") ||
                       hostClass.getName().startsWith("sun."))) {
                throw new InternalError();  // does not happen, and should not anyway
            }
            // For simplicity, convert mh to a varargs-like method.
            java.lang.invoke.MethodHandle vamh = prepareForInvoker(mh);
            // Cache the result of makeInjectedInvoker once per argument class.
            java.lang.invoke.MethodHandle bccInvoker = CV_makeInjectedInvoker.get(hostClass);
            return restoreToType(bccInvoker.bindTo(vamh), mh, hostClass);
        }

        private static java.lang.invoke.MethodHandle makeInjectedInvoker(Class<?> hostClass) {
            Class<?> bcc = UNSAFE.defineAnonymousClass(hostClass, T_BYTES, null);
            if (hostClass.getClassLoader() != bcc.getClassLoader())
                throw new InternalError(hostClass.getName()+" (CL)");
            try {
                if (hostClass.getProtectionDomain() != bcc.getProtectionDomain())
                    throw new InternalError(hostClass.getName()+" (PD)");
            } catch (SecurityException ex) {
                // Self-check was blocked by security manager.  This is OK.
                // In fact the whole try body could be turned into an assertion.
            }
            try {
                java.lang.invoke.MethodHandle init = IMPL_LOOKUP.findStatic(bcc, "init", java.lang.invoke.MethodType.methodType(void.class));
                init.invokeExact();  // force initialization of the class
            } catch (Throwable ex) {
                throw uncaughtException(ex);
            }
            java.lang.invoke.MethodHandle bccInvoker;
            try {
                java.lang.invoke.MethodType invokerMT = java.lang.invoke.MethodType.methodType(Object.class, java.lang.invoke.MethodHandle.class, Object[].class);
                bccInvoker = IMPL_LOOKUP.findStatic(bcc, "invoke_V", invokerMT);
            } catch (ReflectiveOperationException ex) {
                throw uncaughtException(ex);
            }
            // Test the invoker, to ensure that it really injects into the right place.
            try {
                java.lang.invoke.MethodHandle vamh = prepareForInvoker(MH_checkCallerClass);
                Object ok = bccInvoker.invokeExact(vamh, new Object[]{hostClass, bcc});
            } catch (Throwable ex) {
                throw new InternalError(ex);
            }
            return bccInvoker;
        }
        private static ClassValue<java.lang.invoke.MethodHandle> CV_makeInjectedInvoker = new ClassValue<java.lang.invoke.MethodHandle>() {
            @Override protected java.lang.invoke.MethodHandle computeValue(Class<?> hostClass) {
                return makeInjectedInvoker(hostClass);
            }
        };

        // Adapt mh so that it can be called directly from an injected invoker:
        private static java.lang.invoke.MethodHandle prepareForInvoker(java.lang.invoke.MethodHandle mh) {
            mh = mh.asFixedArity();
            java.lang.invoke.MethodType mt = mh.type();
            int arity = mt.parameterCount();
            java.lang.invoke.MethodHandle vamh = mh.asType(mt.generic());
            vamh.internalForm().compileToBytecode();  // eliminate LFI stack frames
            vamh = vamh.asSpreader(Object[].class, arity);
            vamh.internalForm().compileToBytecode();  // eliminate LFI stack frames
            return vamh;
        }

        // Undo the adapter effect of prepareForInvoker:
        private static java.lang.invoke.MethodHandle restoreToType(java.lang.invoke.MethodHandle vamh,
                                                                   java.lang.invoke.MethodHandle original,
                                                                   Class<?> hostClass) {
            java.lang.invoke.MethodType type = original.type();
            java.lang.invoke.MethodHandle mh = vamh.asCollector(Object[].class, type.parameterCount());
            java.lang.invoke.MemberName member = original.internalMemberName();
            mh = mh.asType(type);
            mh = new WrappedMember(mh, type, member, original.isInvokeSpecial(), hostClass);
            return mh;
        }

        private static final java.lang.invoke.MethodHandle MH_checkCallerClass;
        static {
            final Class<?> THIS_CLASS = BindCaller.class;
            assert(checkCallerClass(THIS_CLASS, THIS_CLASS));
            try {
                MH_checkCallerClass = IMPL_LOOKUP
                    .findStatic(THIS_CLASS, "checkCallerClass",
                                java.lang.invoke.MethodType.methodType(boolean.class, Class.class, Class.class));
                assert((boolean) MH_checkCallerClass.invokeExact(THIS_CLASS, THIS_CLASS));
            } catch (Throwable ex) {
                throw new InternalError(ex);
            }
        }

        @CallerSensitive
        private static boolean checkCallerClass(Class<?> expected, Class<?> expected2) {
            // This method is called via MH_checkCallerClass and so it's
            // correct to ask for the immediate caller here.
            Class<?> actual = Reflection.getCallerClass();
            if (actual != expected && actual != expected2)
                throw new InternalError("found "+actual.getName()+", expected "+expected.getName()
                                        +(expected == expected2 ? "" : ", or else "+expected2.getName()));
            return true;
        }

        private static final byte[] T_BYTES;
        static {
            final Object[] values = {null};
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        try {
                            Class<T> tClass = T.class;
                            String tName = tClass.getName();
                            String tResource = tName.substring(tName.lastIndexOf('.')+1)+".class";
                            java.net.URLConnection uconn = tClass.getResource(tResource).openConnection();
                            int len = uconn.getContentLength();
                            byte[] bytes = new byte[len];
                            try (java.io.InputStream str = uconn.getInputStream()) {
                                int nr = str.read(bytes);
                                if (nr != len)  throw new java.io.IOException(tResource);
                            }
                            values[0] = bytes;
                        } catch (java.io.IOException ex) {
                            throw new InternalError(ex);
                        }
                        return null;
                    }
                });
            T_BYTES = (byte[]) values[0];
        }

        // The following class is used as a template for Unsafe.defineAnonymousClass:
        private static class T {
            static void init() { }  // side effect: initializes this class
            static Object invoke_V(java.lang.invoke.MethodHandle vamh, Object[] args) throws Throwable {
                return vamh.invokeExact(args);
            }
        }
    }


    /** This subclass allows a wrapped method handle to be re-associated with an arbitrary member name. */
    private static final class WrappedMember extends DelegatingMethodHandle {
        private final java.lang.invoke.MethodHandle target;
        private final java.lang.invoke.MemberName member;
        private final Class<?> callerClass;
        private final boolean isInvokeSpecial;

        private WrappedMember(java.lang.invoke.MethodHandle target, java.lang.invoke.MethodType type,
                              java.lang.invoke.MemberName member, boolean isInvokeSpecial,
                              Class<?> callerClass) {
            super(type, target);
            this.target = target;
            this.member = member;
            this.callerClass = callerClass;
            this.isInvokeSpecial = isInvokeSpecial;
        }

        @Override
        java.lang.invoke.MemberName internalMemberName() {
            return member;
        }
        @Override
        Class<?> internalCallerClass() {
            return callerClass;
        }
        @Override
        boolean isInvokeSpecial() {
            return isInvokeSpecial;
        }
        @Override
        protected java.lang.invoke.MethodHandle getTarget() {
            return target;
        }
        @Override
        public java.lang.invoke.MethodHandle asTypeUncached(java.lang.invoke.MethodType newType) {
            // This MH is an alias for target, except for the MemberName
            // Drop the MemberName if there is any conversion.
            return asTypeCache = target.asType(newType);
        }
    }

    static java.lang.invoke.MethodHandle makeWrappedMember(java.lang.invoke.MethodHandle target, java.lang.invoke.MemberName member, boolean isInvokeSpecial) {
        if (member.equals(target.internalMemberName()) && isInvokeSpecial == target.isInvokeSpecial())
            return target;
        return new WrappedMember(target, target.type(), member, isInvokeSpecial, null);
    }

    /** Intrinsic IDs */
    /*non-public*/
    enum Intrinsic {
        SELECT_ALTERNATIVE,
        GUARD_WITH_CATCH,
        NEW_ARRAY,
        ARRAY_LOAD,
        ARRAY_STORE,
        IDENTITY,
        ZERO,
        NONE // no intrinsic associated
    }

    /** Mark arbitrary method handle as intrinsic.
     * InvokerBytecodeGenerator uses this info to produce more efficient bytecode shape. */
    private static final class IntrinsicMethodHandle extends DelegatingMethodHandle {
        private final java.lang.invoke.MethodHandle target;
        private final Intrinsic intrinsicName;

        IntrinsicMethodHandle(java.lang.invoke.MethodHandle target, Intrinsic intrinsicName) {
            super(target.type(), target);
            this.target = target;
            this.intrinsicName = intrinsicName;
        }

        @Override
        protected java.lang.invoke.MethodHandle getTarget() {
            return target;
        }

        @Override
        Intrinsic intrinsicName() {
            return intrinsicName;
        }

        @Override
        public java.lang.invoke.MethodHandle asTypeUncached(java.lang.invoke.MethodType newType) {
            // This MH is an alias for target, except for the intrinsic name
            // Drop the name if there is any conversion.
            return asTypeCache = target.asType(newType);
        }

        @Override
        String internalProperties() {
            return super.internalProperties() +
                    "\n& Intrinsic="+intrinsicName;
        }

        @Override
        public java.lang.invoke.MethodHandle asCollector(Class<?> arrayType, int arrayLength) {
            if (intrinsicName == Intrinsic.IDENTITY) {
                java.lang.invoke.MethodType resultType = type().asCollectorType(arrayType, arrayLength);
                java.lang.invoke.MethodHandle newArray = MethodHandleImpl.varargsArray(arrayType, arrayLength);
                return newArray.asType(resultType);
            }
            return super.asCollector(arrayType, arrayLength);
        }
    }

    static java.lang.invoke.MethodHandle makeIntrinsic(java.lang.invoke.MethodHandle target, Intrinsic intrinsicName) {
        if (intrinsicName == target.intrinsicName())
            return target;
        return new IntrinsicMethodHandle(target, intrinsicName);
    }

    static java.lang.invoke.MethodHandle makeIntrinsic(java.lang.invoke.MethodType type, java.lang.invoke.LambdaForm form, Intrinsic intrinsicName) {
        return new IntrinsicMethodHandle(SimpleMethodHandle.make(type, form), intrinsicName);
    }

    /// Collection of multiple arguments.

    private static java.lang.invoke.MethodHandle findCollector(String name, int nargs, Class<?> rtype, Class<?>... ptypes) {
        java.lang.invoke.MethodType type = java.lang.invoke.MethodType.genericMethodType(nargs)
                .changeReturnType(rtype)
                .insertParameterTypes(0, ptypes);
        try {
            return IMPL_LOOKUP.findStatic(MethodHandleImpl.class, name, type);
        } catch (ReflectiveOperationException ex) {
            return null;
        }
    }

    private static final Object[] NO_ARGS_ARRAY = {};
    private static Object[] makeArray(Object... args) { return args; }
    private static Object[] array() { return NO_ARGS_ARRAY; }
    private static Object[] array(Object a0)
                { return makeArray(a0); }
    private static Object[] array(Object a0, Object a1)
                { return makeArray(a0, a1); }
    private static Object[] array(Object a0, Object a1, Object a2)
                { return makeArray(a0, a1, a2); }
    private static Object[] array(Object a0, Object a1, Object a2, Object a3)
                { return makeArray(a0, a1, a2, a3); }
    private static Object[] array(Object a0, Object a1, Object a2, Object a3,
                                  Object a4)
                { return makeArray(a0, a1, a2, a3, a4); }
    private static Object[] array(Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5)
                { return makeArray(a0, a1, a2, a3, a4, a5); }
    private static Object[] array(Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5, Object a6)
                { return makeArray(a0, a1, a2, a3, a4, a5, a6); }
    private static Object[] array(Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5, Object a6, Object a7)
                { return makeArray(a0, a1, a2, a3, a4, a5, a6, a7); }
    private static Object[] array(Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5, Object a6, Object a7,
                                  Object a8)
                { return makeArray(a0, a1, a2, a3, a4, a5, a6, a7, a8); }
    private static Object[] array(Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5, Object a6, Object a7,
                                  Object a8, Object a9)
                { return makeArray(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9); }
    private static java.lang.invoke.MethodHandle[] makeArrays() {
        ArrayList<java.lang.invoke.MethodHandle> mhs = new ArrayList<>();
        for (;;) {
            java.lang.invoke.MethodHandle mh = findCollector("array", mhs.size(), Object[].class);
            if (mh == null)  break;
            mh = makeIntrinsic(mh, Intrinsic.NEW_ARRAY);
            mhs.add(mh);
        }
        assert(mhs.size() == 11);  // current number of methods
        return mhs.toArray(new java.lang.invoke.MethodHandle[MAX_ARITY+1]);
    }

    // filling versions of the above:
    // using Integer len instead of int len and no varargs to avoid bootstrapping problems
    private static Object[] fillNewArray(Integer len, Object[] /*not ...*/ args) {
        Object[] a = new Object[len];
        fillWithArguments(a, 0, args);
        return a;
    }
    private static Object[] fillNewTypedArray(Object[] example, Integer len, Object[] /*not ...*/ args) {
        Object[] a = Arrays.copyOf(example, len);
        assert(a.getClass() != Object[].class);
        fillWithArguments(a, 0, args);
        return a;
    }
    private static void fillWithArguments(Object[] a, int pos, Object... args) {
        System.arraycopy(args, 0, a, pos, args.length);
    }
    // using Integer pos instead of int pos to avoid bootstrapping problems
    private static Object[] fillArray(Integer pos, Object[] a, Object a0)
                { fillWithArguments(a, pos, a0); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1)
                { fillWithArguments(a, pos, a0, a1); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2)
                { fillWithArguments(a, pos, a0, a1, a2); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3)
                { fillWithArguments(a, pos, a0, a1, a2, a3); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3,
                                  Object a4)
                { fillWithArguments(a, pos, a0, a1, a2, a3, a4); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5)
                { fillWithArguments(a, pos, a0, a1, a2, a3, a4, a5); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5, Object a6)
                { fillWithArguments(a, pos, a0, a1, a2, a3, a4, a5, a6); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5, Object a6, Object a7)
                { fillWithArguments(a, pos, a0, a1, a2, a3, a4, a5, a6, a7); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5, Object a6, Object a7,
                                  Object a8)
                { fillWithArguments(a, pos, a0, a1, a2, a3, a4, a5, a6, a7, a8); return a; }
    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3,
                                  Object a4, Object a5, Object a6, Object a7,
                                  Object a8, Object a9)
                { fillWithArguments(a, pos, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9); return a; }

    private static final int FILL_ARRAYS_COUNT = 11; // current number of fillArray methods

    private static java.lang.invoke.MethodHandle[] makeFillArrays() {
        ArrayList<java.lang.invoke.MethodHandle> mhs = new ArrayList<>();
        mhs.add(null);  // there is no empty fill; at least a0 is required
        for (;;) {
            java.lang.invoke.MethodHandle mh = findCollector("fillArray", mhs.size(), Object[].class, Integer.class, Object[].class);
            if (mh == null)  break;
            mhs.add(mh);
        }
        assert(mhs.size() == FILL_ARRAYS_COUNT);
        return mhs.toArray(new java.lang.invoke.MethodHandle[0]);
    }

    private static Object copyAsPrimitiveArray(Wrapper w, Object... boxes) {
        Object a = w.makeArray(boxes.length);
        w.copyArrayUnboxing(boxes, 0, a, 0, boxes.length);
        return a;
    }

    /** Return a method handle that takes the indicated number of Object
     *  arguments and returns an Object array of them, as if for varargs.
     */
    static java.lang.invoke.MethodHandle varargsArray(int nargs) {
        java.lang.invoke.MethodHandle mh = Lazy.ARRAYS[nargs];
        if (mh != null)  return mh;
        mh = findCollector("array", nargs, Object[].class);
        if (mh != null)  mh = makeIntrinsic(mh, Intrinsic.NEW_ARRAY);
        if (mh != null)  return Lazy.ARRAYS[nargs] = mh;
        mh = buildVarargsArray(Lazy.MH_fillNewArray, Lazy.MH_arrayIdentity, nargs);
        assert(assertCorrectArity(mh, nargs));
        mh = makeIntrinsic(mh, Intrinsic.NEW_ARRAY);
        return Lazy.ARRAYS[nargs] = mh;
    }

    private static boolean assertCorrectArity(java.lang.invoke.MethodHandle mh, int arity) {
        assert(mh.type().parameterCount() == arity) : "arity != "+arity+": "+mh;
        return true;
    }

    // Array identity function (used as Lazy.MH_arrayIdentity).
    static <T> T[] identity(T[] x) {
        return x;
    }

    private static java.lang.invoke.MethodHandle buildVarargsArray(java.lang.invoke.MethodHandle newArray, java.lang.invoke.MethodHandle finisher, int nargs) {
        // Build up the result mh as a sequence of fills like this:
        //   finisher(fill(fill(newArrayWA(23,x1..x10),10,x11..x20),20,x21..x23))
        // The various fill(_,10*I,___*[J]) are reusable.
        int leftLen = Math.min(nargs, LEFT_ARGS);  // absorb some arguments immediately
        int rightLen = nargs - leftLen;
        java.lang.invoke.MethodHandle leftCollector = newArray.bindTo(nargs);
        leftCollector = leftCollector.asCollector(Object[].class, leftLen);
        java.lang.invoke.MethodHandle mh = finisher;
        if (rightLen > 0) {
            java.lang.invoke.MethodHandle rightFiller = fillToRight(LEFT_ARGS + rightLen);
            if (mh == Lazy.MH_arrayIdentity)
                mh = rightFiller;
            else
                mh = java.lang.invoke.MethodHandles.collectArguments(mh, 0, rightFiller);
        }
        if (mh == Lazy.MH_arrayIdentity)
            mh = leftCollector;
        else
            mh = java.lang.invoke.MethodHandles.collectArguments(mh, 0, leftCollector);
        return mh;
    }

    private static final int LEFT_ARGS = FILL_ARRAYS_COUNT - 1;
    private static final java.lang.invoke.MethodHandle[] FILL_ARRAY_TO_RIGHT = new java.lang.invoke.MethodHandle[MAX_ARITY+1];
    /** fill_array_to_right(N).invoke(a, argL..arg[N-1])
     *  fills a[L]..a[N-1] with corresponding arguments,
     *  and then returns a.  The value L is a global constant (LEFT_ARGS).
     */
    private static java.lang.invoke.MethodHandle fillToRight(int nargs) {
        java.lang.invoke.MethodHandle filler = FILL_ARRAY_TO_RIGHT[nargs];
        if (filler != null)  return filler;
        filler = buildFiller(nargs);
        assert(assertCorrectArity(filler, nargs - LEFT_ARGS + 1));
        return FILL_ARRAY_TO_RIGHT[nargs] = filler;
    }
    private static java.lang.invoke.MethodHandle buildFiller(int nargs) {
        if (nargs <= LEFT_ARGS)
            return Lazy.MH_arrayIdentity;  // no args to fill; return the array unchanged
        // we need room for both mh and a in mh.invoke(a, arg*[nargs])
        final int CHUNK = LEFT_ARGS;
        int rightLen = nargs % CHUNK;
        int midLen = nargs - rightLen;
        if (rightLen == 0) {
            midLen = nargs - (rightLen = CHUNK);
            if (FILL_ARRAY_TO_RIGHT[midLen] == null) {
                // build some precursors from left to right
                for (int j = LEFT_ARGS % CHUNK; j < midLen; j += CHUNK)
                    if (j > LEFT_ARGS)  fillToRight(j);
            }
        }
        if (midLen < LEFT_ARGS) rightLen = nargs - (midLen = LEFT_ARGS);
        assert(rightLen > 0);
        java.lang.invoke.MethodHandle midFill = fillToRight(midLen);  // recursive fill
        java.lang.invoke.MethodHandle rightFill = Lazy.FILL_ARRAYS[rightLen].bindTo(midLen);  // [midLen..nargs-1]
        assert(midFill.type().parameterCount()   == 1 + midLen - LEFT_ARGS);
        assert(rightFill.type().parameterCount() == 1 + rightLen);

        // Combine the two fills:
        //   right(mid(a, x10..x19), x20..x23)
        // The final product will look like this:
        //   right(mid(newArrayLeft(24, x0..x9), x10..x19), x20..x23)
        if (midLen == LEFT_ARGS)
            return rightFill;
        else
            return java.lang.invoke.MethodHandles.collectArguments(rightFill, 0, midFill);
    }

    // Type-polymorphic version of varargs maker.
    private static final ClassValue<java.lang.invoke.MethodHandle[]> TYPED_COLLECTORS
        = new ClassValue<java.lang.invoke.MethodHandle[]>() {
            @Override
            protected java.lang.invoke.MethodHandle[] computeValue(Class<?> type) {
                return new java.lang.invoke.MethodHandle[256];
            }
    };

    static final int MAX_JVM_ARITY = 255;  // limit imposed by the JVM

    /** Return a method handle that takes the indicated number of
     *  typed arguments and returns an array of them.
     *  The type argument is the array type.
     */
    static java.lang.invoke.MethodHandle varargsArray(Class<?> arrayType, int nargs) {
        Class<?> elemType = arrayType.getComponentType();
        if (elemType == null)  throw new IllegalArgumentException("not an array: "+arrayType);
        // FIXME: Need more special casing and caching here.
        if (nargs >= MAX_JVM_ARITY/2 - 1) {
            int slots = nargs;
            final int MAX_ARRAY_SLOTS = MAX_JVM_ARITY - 1;  // 1 for receiver MH
            if (slots <= MAX_ARRAY_SLOTS && elemType.isPrimitive())
                slots *= Wrapper.forPrimitiveType(elemType).stackSlots();
            if (slots > MAX_ARRAY_SLOTS)
                throw new IllegalArgumentException("too many arguments: "+arrayType.getSimpleName()+", length "+nargs);
        }
        if (elemType == Object.class)
            return varargsArray(nargs);
        // other cases:  primitive arrays, subtypes of Object[]
        java.lang.invoke.MethodHandle cache[] = TYPED_COLLECTORS.get(elemType);
        java.lang.invoke.MethodHandle mh = nargs < cache.length ? cache[nargs] : null;
        if (mh != null)  return mh;
        if (nargs == 0) {
            Object example = java.lang.reflect.Array.newInstance(arrayType.getComponentType(), 0);
            mh = MethodHandles.constant(arrayType, example);
        } else if (elemType.isPrimitive()) {
            java.lang.invoke.MethodHandle builder = Lazy.MH_fillNewArray;
            java.lang.invoke.MethodHandle producer = buildArrayProducer(arrayType);
            mh = buildVarargsArray(builder, producer, nargs);
        } else {
            Class<? extends Object[]> objArrayType = arrayType.asSubclass(Object[].class);
            Object[] example = Arrays.copyOf(NO_ARGS_ARRAY, 0, objArrayType);
            java.lang.invoke.MethodHandle builder = Lazy.MH_fillNewTypedArray.bindTo(example);
            java.lang.invoke.MethodHandle producer = Lazy.MH_arrayIdentity; // must be weakly typed
            mh = buildVarargsArray(builder, producer, nargs);
        }
        mh = mh.asType(MethodType.methodType(arrayType, Collections.<Class<?>>nCopies(nargs, elemType)));
        mh = makeIntrinsic(mh, Intrinsic.NEW_ARRAY);
        assert(assertCorrectArity(mh, nargs));
        if (nargs < cache.length)
            cache[nargs] = mh;
        return mh;
    }

    private static java.lang.invoke.MethodHandle buildArrayProducer(Class<?> arrayType) {
        Class<?> elemType = arrayType.getComponentType();
        assert(elemType.isPrimitive());
        return Lazy.MH_copyAsPrimitiveArray.bindTo(Wrapper.forPrimitiveType(elemType));
    }

    /*non-public*/ static void assertSame(Object mh1, Object mh2) {
        if (mh1 != mh2) {
            String msg = String.format("mh1 != mh2: mh1 = %s (form: %s); mh2 = %s (form: %s)",
                    mh1, ((java.lang.invoke.MethodHandle)mh1).form,
                    mh2, ((MethodHandle)mh2).form);
            throw newInternalError(msg);
        }
    }
}
