/*
 * Copyright (c) 1999, 2010, Oracle and/or its affiliates. All rights reserved.
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
import goodman.javax.sound.midi.MidiEvent;
import goodman.javax.sound.midi.Receiver;

/**
 * A <code>Transmitter</code> sends <code>{@link MidiEvent}</code> objects to one or more
 * <code>{@link javax.sound.midi.Receiver Receivers}</code>. Common MIDI transmitters include sequencers
 * and MIDI input ports.
 *
 * @see javax.sound.midi.Receiver
 *
 * @author Kara Kytle
 */
public interface Transmitter extends AutoCloseable {


    /**
     * Sets the receiver to which this transmitter will deliver MIDI messages.
     * If a receiver is currently set, it is replaced with this one.
     * @param receiver the desired receiver.
     */
    public void setReceiver(javax.sound.midi.Receiver receiver);


    /**
     * Obtains the current receiver to which this transmitter will deliver MIDI messages.
     * @return the current receiver.  If no receiver is currently set,
     * returns <code>null</code>
     */
    public Receiver getReceiver();


    /**
     * Indicates that the application has finished using the transmitter, and
     * that limited resources it requires may be released or made available.
     *
     * <p>If the creation of this <code>Transmitter</code> resulted in
     * implicitly opening the underlying device, the device is
     * implicitly closed by this method. This is true unless the device is
     * kept open by other <code>Receiver</code> or <code>Transmitter</code>
     * instances that opened the device implicitly, and unless the device
     * has been opened explicitly. If the device this
     * <code>Transmitter</code> is retrieved from is closed explicitly
     * by calling {@link MidiDevice#close MidiDevice.close}, the
     * <code>Transmitter</code> is closed, too.  For a detailed
     * description of open/close behaviour see the class description
     * of {@link MidiDevice MidiDevice}.
     *
     * @see javax.sound.midi.MidiSystem#getTransmitter
     */
    public void close();
}