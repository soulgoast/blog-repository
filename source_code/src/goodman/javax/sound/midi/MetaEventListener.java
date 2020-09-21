/*
 * Copyright (c) 1999, 2002, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.sound.midi;

import goodman.javax.sound.midi.MetaMessage;
import goodman.javax.sound.midi.Sequence;
import goodman.javax.sound.midi.Sequencer;
import goodman.java.util.EventListener;


/**
 * The <code>MetaEventListener</code> interface should be implemented
 * by classes whose instances need to be notified when a <code>{@link javax.sound.midi.Sequencer}</code>
 * has processed a <code>{@link javax.sound.midi.MetaMessage}</code>.
 * To register a <code>MetaEventListener</code> object to receive such
 * notifications, pass it as the argument to the
 * <code>{@link javax.sound.midi.Sequencer#addMetaEventListener(MetaEventListener) addMetaEventListener}</code>
 * method of <code>Sequencer</code>.
 *
 * @author Kara Kytle
 */
public interface MetaEventListener extends EventListener {

    /**
     * Invoked when a <code>{@link Sequencer}</code> has encountered and processed
     * a <code>MetaMessage</code> in the <code>{@link Sequence}</code> it is processing.
     * @param meta the meta-message that the sequencer encountered
     */
    public void meta(MetaMessage meta);
}
