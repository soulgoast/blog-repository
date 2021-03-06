/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
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
package goodman.java.util.stream;

import goodman.java.util.LongSummaryStatistics;
import goodman.java.util.Objects;
import goodman.java.util.OptionalDouble;
import goodman.java.util.OptionalLong;
import goodman.java.util.PrimitiveIterator;
import goodman.java.util.Spliterator;
import goodman.java.util.Spliterators;
import goodman.java.util.function.BiConsumer;
import goodman.java.util.function.BinaryOperator;
import goodman.java.util.function.IntFunction;
import goodman.java.util.function.LongBinaryOperator;
import goodman.java.util.function.LongConsumer;
import goodman.java.util.function.LongFunction;
import goodman.java.util.function.LongPredicate;
import goodman.java.util.function.LongToDoubleFunction;
import goodman.java.util.function.LongToIntFunction;
import goodman.java.util.function.LongUnaryOperator;
import goodman.java.util.function.ObjLongConsumer;
import goodman.java.util.function.Supplier;
import goodman.java.util.stream.*;
import goodman.java.util.stream.DoubleStream;
import goodman.java.util.stream.FindOps;
import goodman.java.util.stream.ForEachOps;
import goodman.java.util.stream.IntPipeline;
import goodman.java.util.stream.IntStream;
import goodman.java.util.stream.LongStream;
import goodman.java.util.stream.MatchOps;
import goodman.java.util.stream.Node;
import goodman.java.util.stream.Nodes;
import goodman.java.util.stream.ReduceOps;
import goodman.java.util.stream.Sink;
import goodman.java.util.stream.SliceOps;
import goodman.java.util.stream.SortedOps;
import goodman.java.util.stream.Stream;
import goodman.java.util.stream.StreamShape;
import goodman.java.util.stream.Tripwire;

/**
 * Abstract base class for an intermediate pipeline stage or pipeline source
 * stage implementing whose elements are of type {@code long}.
 *
 * @param <E_IN> type of elements in the upstream source
 * @since 1.8
 */
abstract class LongPipeline<E_IN>
        extends AbstractPipeline<E_IN, Long, java.util.stream.LongStream>
        implements java.util.stream.LongStream {

    /**
     * Constructor for the head of a stream pipeline.
     *
     * @param source {@code Supplier<Spliterator>} describing the stream source
     * @param sourceFlags the source flags for the stream source, described in
     *        {@link StreamOpFlag}
     * @param parallel {@code true} if the pipeline is parallel
     */
    LongPipeline(Supplier<? extends Spliterator<Long>> source,
                 int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    /**
     * Constructor for the head of a stream pipeline.
     *
     * @param source {@code Spliterator} describing the stream source
     * @param sourceFlags the source flags for the stream source, described in
     *        {@link StreamOpFlag}
     * @param parallel {@code true} if the pipeline is parallel
     */
    LongPipeline(Spliterator<Long> source,
                 int sourceFlags, boolean parallel) {
        super(source, sourceFlags, parallel);
    }

    /**
     * Constructor for appending an intermediate operation onto an existing pipeline.
     *
     * @param upstream the upstream element source.
     * @param opFlags the operation flags
     */
    LongPipeline(AbstractPipeline<?, E_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }

    /**
     * Adapt a {@code Sink<Long> to an {@code LongConsumer}, ideally simply
     * by casting.
     */
    private static LongConsumer adapt(java.util.stream.Sink<Long> sink) {
        if (sink instanceof LongConsumer) {
            return (LongConsumer) sink;
        } else {
            if (java.util.stream.Tripwire.ENABLED)
                java.util.stream.Tripwire.trip(AbstractPipeline.class,
                              "using LongStream.adapt(Sink<Long> s)");
            return sink::accept;
        }
    }

    /**
     * Adapt a {@code Spliterator<Long>} to a {@code Spliterator.OfLong}.
     *
     * @implNote
     * The implementation attempts to cast to a Spliterator.OfLong, and throws
     * an exception if this cast is not possible.
     */
    private static Spliterator.OfLong adapt(Spliterator<Long> s) {
        if (s instanceof Spliterator.OfLong) {
            return (Spliterator.OfLong) s;
        } else {
            if (java.util.stream.Tripwire.ENABLED)
                java.util.stream.Tripwire.trip(AbstractPipeline.class,
                              "using LongStream.adapt(Spliterator<Long> s)");
            throw new UnsupportedOperationException("LongStream.adapt(Spliterator<Long> s)");
        }
    }


    // Shape-specific methods

    @Override
    final java.util.stream.StreamShape getOutputShape() {
        return java.util.stream.StreamShape.LONG_VALUE;
    }

    @Override
    final <P_IN> java.util.stream.Node<Long> evaluateToNode(PipelineHelper<Long> helper,
                                                            Spliterator<P_IN> spliterator,
                                                            boolean flattenTree,
                                                            IntFunction<Long[]> generator) {
        return java.util.stream.Nodes.collectLong(helper, spliterator, flattenTree);
    }

    @Override
    final <P_IN> Spliterator<Long> wrap(PipelineHelper<Long> ph,
                                        Supplier<Spliterator<P_IN>> supplier,
                                        boolean isParallel) {
        return new StreamSpliterators.LongWrappingSpliterator<>(ph, supplier, isParallel);
    }

    @Override
    @SuppressWarnings("unchecked")
    final Spliterator.OfLong lazySpliterator(Supplier<? extends Spliterator<Long>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator.OfLong((Supplier<Spliterator.OfLong>) supplier);
    }

    @Override
    final void forEachWithCancel(Spliterator<Long> spliterator, java.util.stream.Sink<Long> sink) {
        Spliterator.OfLong spl = adapt(spliterator);
        LongConsumer adaptedSink =  adapt(sink);
        do { } while (!sink.cancellationRequested() && spl.tryAdvance(adaptedSink));
    }

    @Override
    final java.util.stream.Node.Builder<Long> makeNodeBuilder(long exactSizeIfKnown, IntFunction<Long[]> generator) {
        return java.util.stream.Nodes.longBuilder(exactSizeIfKnown);
    }


    // LongStream

    @Override
    public final PrimitiveIterator.OfLong iterator() {
        return Spliterators.iterator(spliterator());
    }

    @Override
    public final Spliterator.OfLong spliterator() {
        return adapt(super.spliterator());
    }

    // Stateless intermediate ops from LongStream

    @Override
    public final java.util.stream.DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp<Long>(this, java.util.stream.StreamShape.LONG_VALUE,
                                                    StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<Double> sink) {
                return new java.util.stream.Sink.ChainedLong<Double>(sink) {
                    @Override
                    public void accept(long t) {
                        downstream.accept((double) t);
                    }
                };
            }
        };
    }

    @Override
    public final java.util.stream.Stream<Long> boxed() {
        return mapToObj(Long::valueOf);
    }

    @Override
    public final java.util.stream.LongStream map(LongUnaryOperator mapper) {
        Objects.requireNonNull(mapper);
        return new StatelessOp<Long>(this, java.util.stream.StreamShape.LONG_VALUE,
                                     StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<Long> sink) {
                return new java.util.stream.Sink.ChainedLong<Long>(sink) {
                    @Override
                    public void accept(long t) {
                        downstream.accept(mapper.applyAsLong(t));
                    }
                };
            }
        };
    }

    @Override
    public final <U> Stream<U> mapToObj(LongFunction<? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return new ReferencePipeline.StatelessOp<Long, U>(this, java.util.stream.StreamShape.LONG_VALUE,
                                                          StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<U> sink) {
                return new java.util.stream.Sink.ChainedLong<U>(sink) {
                    @Override
                    public void accept(long t) {
                        downstream.accept(mapper.apply(t));
                    }
                };
            }
        };
    }

    @Override
    public final IntStream mapToInt(LongToIntFunction mapper) {
        Objects.requireNonNull(mapper);
        return new java.util.stream.IntPipeline.StatelessOp<Long>(this, java.util.stream.StreamShape.LONG_VALUE,
                                                 StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<Integer> sink) {
                return new java.util.stream.Sink.ChainedLong<Integer>(sink) {
                    @Override
                    public void accept(long t) {
                        downstream.accept(mapper.applyAsInt(t));
                    }
                };
            }
        };
    }

    @Override
    public final DoubleStream mapToDouble(LongToDoubleFunction mapper) {
        Objects.requireNonNull(mapper);
        return new DoublePipeline.StatelessOp<Long>(this, java.util.stream.StreamShape.LONG_VALUE,
                                                    StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<Double> sink) {
                return new java.util.stream.Sink.ChainedLong<Double>(sink) {
                    @Override
                    public void accept(long t) {
                        downstream.accept(mapper.applyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override
    public final java.util.stream.LongStream flatMap(LongFunction<? extends java.util.stream.LongStream> mapper) {
        return new StatelessOp<Long>(this, java.util.stream.StreamShape.LONG_VALUE,
                                     StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<Long> sink) {
                return new java.util.stream.Sink.ChainedLong<Long>(sink) {
                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(long t) {
                        try (java.util.stream.LongStream result = mapper.apply(t)) {
                            // We can do better that this too; optimize for depth=0 case and just grab spliterator and forEach it
                            if (result != null)
                                result.sequential().forEach(i -> downstream.accept(i));
                        }
                    }
                };
            }
        };
    }

    @Override
    public java.util.stream.LongStream unordered() {
        if (!isOrdered())
            return this;
        return new StatelessOp<Long>(this, java.util.stream.StreamShape.LONG_VALUE, StreamOpFlag.NOT_ORDERED) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<Long> sink) {
                return sink;
            }
        };
    }

    @Override
    public final java.util.stream.LongStream filter(LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<Long>(this, java.util.stream.StreamShape.LONG_VALUE,
                                     StreamOpFlag.NOT_SIZED) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<Long> sink) {
                return new java.util.stream.Sink.ChainedLong<Long>(sink) {
                    @Override
                    public void begin(long size) {
                        downstream.begin(-1);
                    }

                    @Override
                    public void accept(long t) {
                        if (predicate.test(t))
                            downstream.accept(t);
                    }
                };
            }
        };
    }

    @Override
    public final java.util.stream.LongStream peek(LongConsumer action) {
        Objects.requireNonNull(action);
        return new StatelessOp<Long>(this, java.util.stream.StreamShape.LONG_VALUE,
                                     0) {
            @Override
            java.util.stream.Sink<Long> opWrapSink(int flags, java.util.stream.Sink<Long> sink) {
                return new java.util.stream.Sink.ChainedLong<Long>(sink) {
                    @Override
                    public void accept(long t) {
                        action.accept(t);
                        downstream.accept(t);
                    }
                };
            }
        };
    }

    // Stateful intermediate ops from LongStream

    @Override
    public final java.util.stream.LongStream limit(long maxSize) {
        if (maxSize < 0)
            throw new IllegalArgumentException(Long.toString(maxSize));
        return java.util.stream.SliceOps.makeLong(this, 0, maxSize);
    }

    @Override
    public final java.util.stream.LongStream skip(long n) {
        if (n < 0)
            throw new IllegalArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else
            return java.util.stream.SliceOps.makeLong(this, n, -1);
    }

    @Override
    public final java.util.stream.LongStream sorted() {
        return java.util.stream.SortedOps.makeLong(this);
    }

    @Override
    public final LongStream distinct() {
        // While functional and quick to implement, this approach is not very efficient.
        // An efficient version requires a long-specific map/set implementation.
        return boxed().distinct().mapToLong(i -> (long) i);
    }

    // Terminal ops from LongStream

    @Override
    public void forEach(LongConsumer action) {
        evaluate(java.util.stream.ForEachOps.makeLong(action, false));
    }

    @Override
    public void forEachOrdered(LongConsumer action) {
        evaluate(java.util.stream.ForEachOps.makeLong(action, true));
    }

    @Override
    public final long sum() {
        // use better algorithm to compensate for intermediate overflow?
        return reduce(0, Long::sum);
    }

    @Override
    public final OptionalLong min() {
        return reduce(Math::min);
    }

    @Override
    public final OptionalLong max() {
        return reduce(Math::max);
    }

    @Override
    public final OptionalDouble average() {
        long[] avg = collect(() -> new long[2],
                             (ll, i) -> {
                                 ll[0]++;
                                 ll[1] += i;
                             },
                             (ll, rr) -> {
                                 ll[0] += rr[0];
                                 ll[1] += rr[1];
                             });
        return avg[0] > 0
               ? OptionalDouble.of((double) avg[1] / avg[0])
               : OptionalDouble.empty();
    }

    @Override
    public final long count() {
        return map(e -> 1L).sum();
    }

    @Override
    public final LongSummaryStatistics summaryStatistics() {
        return collect(LongSummaryStatistics::new, LongSummaryStatistics::accept,
                       LongSummaryStatistics::combine);
    }

    @Override
    public final long reduce(long identity, LongBinaryOperator op) {
        return evaluate(java.util.stream.ReduceOps.makeLong(identity, op));
    }

    @Override
    public final OptionalLong reduce(LongBinaryOperator op) {
        return evaluate(java.util.stream.ReduceOps.makeLong(op));
    }

    @Override
    public final <R> R collect(Supplier<R> supplier,
                               ObjLongConsumer<R> accumulator,
                               BiConsumer<R, R> combiner) {
        BinaryOperator<R> operator = (left, right) -> {
            combiner.accept(left, right);
            return left;
        };
        return evaluate(java.util.stream.ReduceOps.makeLong(supplier, accumulator, operator));
    }

    @Override
    public final boolean anyMatch(LongPredicate predicate) {
        return evaluate(java.util.stream.MatchOps.makeLong(predicate, java.util.stream.MatchOps.MatchKind.ANY));
    }

    @Override
    public final boolean allMatch(LongPredicate predicate) {
        return evaluate(java.util.stream.MatchOps.makeLong(predicate, java.util.stream.MatchOps.MatchKind.ALL));
    }

    @Override
    public final boolean noneMatch(LongPredicate predicate) {
        return evaluate(java.util.stream.MatchOps.makeLong(predicate, java.util.stream.MatchOps.MatchKind.NONE));
    }

    @Override
    public final OptionalLong findFirst() {
        return evaluate(java.util.stream.FindOps.makeLong(true));
    }

    @Override
    public final OptionalLong findAny() {
        return evaluate(java.util.stream.FindOps.makeLong(false));
    }

    @Override
    public final long[] toArray() {
        return java.util.stream.Nodes.flattenLong((java.util.stream.Node.OfLong) evaluateToArrayNode(Long[]::new))
                .asPrimitiveArray();
    }


    //

    /**
     * Source stage of a LongPipeline.
     *
     * @param <E_IN> type of elements in the upstream source
     * @since 1.8
     */
    static class Head<E_IN> extends LongPipeline<E_IN> {
        /**
         * Constructor for the source stage of a LongStream.
         *
         * @param source {@code Supplier<Spliterator>} describing the stream
         *               source
         * @param sourceFlags the source flags for the stream source, described
         *                    in {@link StreamOpFlag}
         * @param parallel {@code true} if the pipeline is parallel
         */
        Head(Supplier<? extends Spliterator<Long>> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        /**
         * Constructor for the source stage of a LongStream.
         *
         * @param source {@code Spliterator} describing the stream source
         * @param sourceFlags the source flags for the stream source, described
         *                    in {@link StreamOpFlag}
         * @param parallel {@code true} if the pipeline is parallel
         */
        Head(Spliterator<Long> source,
             int sourceFlags, boolean parallel) {
            super(source, sourceFlags, parallel);
        }

        @Override
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override
        final java.util.stream.Sink<E_IN> opWrapSink(int flags, java.util.stream.Sink<Long> sink) {
            throw new UnsupportedOperationException();
        }

        // Optimized sequential terminal operations for the head of the pipeline

        @Override
        public void forEach(LongConsumer action) {
            if (!isParallel()) {
                adapt(sourceStageSpliterator()).forEachRemaining(action);
            } else {
                super.forEach(action);
            }
        }

        @Override
        public void forEachOrdered(LongConsumer action) {
            if (!isParallel()) {
                adapt(sourceStageSpliterator()).forEachRemaining(action);
            } else {
                super.forEachOrdered(action);
            }
        }
    }

    /** Base class for a stateless intermediate stage of a LongStream.
     *
     * @param <E_IN> type of elements in the upstream source
     * @since 1.8
     */
    abstract static class StatelessOp<E_IN> extends LongPipeline<E_IN> {
        /**
         * Construct a new LongStream by appending a stateless intermediate
         * operation to an existing stream.
         * @param upstream The upstream pipeline stage
         * @param inputShape The stream shape for the upstream pipeline stage
         * @param opFlags Operation flags for the new stage
         */
        StatelessOp(AbstractPipeline<?, E_IN, ?> upstream,
                    java.util.stream.StreamShape inputShape,
                    int opFlags) {
            super(upstream, opFlags);
            assert upstream.getOutputShape() == inputShape;
        }

        @Override
        final boolean opIsStateful() {
            return false;
        }
    }

    /**
     * Base class for a stateful intermediate stage of a LongStream.
     *
     * @param <E_IN> type of elements in the upstream source
     * @since 1.8
     */
    abstract static class StatefulOp<E_IN> extends LongPipeline<E_IN> {
        /**
         * Construct a new LongStream by appending a stateful intermediate
         * operation to an existing stream.
         * @param upstream The upstream pipeline stage
         * @param inputShape The stream shape for the upstream pipeline stage
         * @param opFlags Operation flags for the new stage
         */
        StatefulOp(AbstractPipeline<?, E_IN, ?> upstream,
                   java.util.stream.StreamShape inputShape,
                   int opFlags) {
            super(upstream, opFlags);
            assert upstream.getOutputShape() == inputShape;
        }

        @Override
        final boolean opIsStateful() {
            return true;
        }

        @Override
        abstract <P_IN> java.util.stream.Node<Long> opEvaluateParallel(PipelineHelper<Long> helper,
                                                                       Spliterator<P_IN> spliterator,
                                                                       IntFunction<Long[]> generator);
    }
}
