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
import goodman.javax.sound.midi.Transmitter;

/**
 * <p>{@code MidiDeviceTransmitter} is a {@code Transmitter} which represents
 * a MIDI input connector of a {@code MidiDevice}
 * (see {@link javax.sound.midi.MidiDevice#getTransmitter()}).
 *
 * @since 1.7
 */
public interface MidiDeviceTransmitter extends Transmitter {

    /**
     * Obtains a MidiDevice object which is an owner of this Transmitter.
     * @return a MidiDevice object which is an owner of this Transmitter
     */
    public MidiDevice getMidiDevice();
}
