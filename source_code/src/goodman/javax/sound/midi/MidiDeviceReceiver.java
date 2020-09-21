/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
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

import goodman.javax.sound.midi.MidiDevice;
import goodman.javax.sound.midi.Receiver;

/**
 * <p>{@code MidiDeviceReceiver} is a {@code Receiver} which represents
 * a MIDI input connector of a {@code MidiDevice}
 * (see {@link javax.sound.midi.MidiDevice#getReceiver()}).
 *
 * @since 1.7
 */
public interface MidiDeviceReceiver extends Receiver {
    /**
     * Obtains a MidiDevice object which is an owner of this Receiver.
     * @return a MidiDevice object which is an owner of this Receiver
     */
    public MidiDevice getMidiDevice();
}
