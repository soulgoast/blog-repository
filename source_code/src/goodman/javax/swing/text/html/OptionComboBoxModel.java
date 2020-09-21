/*
 * Copyright (c) 1998, 2011, Oracle and/or its affiliates. All rights reserved.
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
package goodman.javax.swing.text.html;

import goodman.javax.swing.*;
import goodman.javax.swing.text.html.Option;
import goodman.java.io.Serializable;


/**
 * OptionComboBoxModel extends the capabilities of the DefaultComboBoxModel,
 * to store the Option that is initially marked as selected.
 * This is stored, in order to enable an accurate reset of the
 * ComboBox that represents the SELECT form element when the
 * user requests a clear/reset.  Given that a combobox only allow
 * for one item to be selected, the last OPTION that has the
 * attribute set wins.
 *
  @author Sunita Mani
 */

class OptionComboBoxModel<E> extends DefaultComboBoxModel<E> implements Serializable {

    private javax.swing.text.html.Option selectedOption = null;

    /**
     * Stores the Option that has been marked its
     * selected attribute set.
     */
    public void setInitialSelection(javax.swing.text.html.Option option) {
        selectedOption = option;
    }

    /**
     * Fetches the Option item that represents that was
     * initially set to a selected state.
     */
    public Option getInitialSelection() {
        return selectedOption;
    }
}
