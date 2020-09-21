/*
 * Copyright (c) 1998, 2000, Oracle and/or its affiliates. All rights reserved.
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

import goodman.java.awt.*;
import goodman.java.awt.event.*;
import goodman.java.net.URLEncoder;
import goodman.java.net.MalformedURLException;
import goodman.java.io.IOException;
import goodman.java.net.URL;
import goodman.javax.swing.text.*;
import goodman.javax.swing.*;
import goodman.javax.swing.text.html.HTML;
import goodman.javax.swing.text.html.HTMLDocument;


/**
 * A view that supports the &lt;ISINDEX&lt; tag.  This is implemented
 * as a JPanel that contains
 *
 * @author Sunita Mani
 */

class IsindexView extends ComponentView implements ActionListener {

    JTextField textField;

    /**
     * Creates an IsindexView
     */
    public IsindexView(Element elem) {
        super(elem);
    }

    /**
     * Creates the components necessary to to implement
     * this view.  The component returned is a <code>JPanel</code>,
     * that contains the PROMPT to the left and <code>JTextField</code>
     * to the right.
     */
    public Component createComponent() {
        AttributeSet attr = getElement().getAttributes();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(null);

        String prompt = (String)attr.getAttribute(javax.swing.text.html.HTML.Attribute.PROMPT);
        if (prompt == null) {
            prompt = UIManager.getString("IsindexView.prompt");
        }
        JLabel label = new JLabel(prompt);

        textField = new JTextField();
        textField.addActionListener(this);
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        panel.setAlignmentY(1.0f);
        panel.setOpaque(false);
        return panel;
    }

    /**
     * Responsible for processing the ActionEvent.
     * In this case this is hitting enter/return
     * in the text field.  This will construct the
     * URL from the base URL of the document.
     * To the URL is appended a '?' followed by the
     * contents of the JTextField.  The search
     * contents are URLEncoded.
     */
    public void actionPerformed(ActionEvent evt) {

        String data = textField.getText();
        if (data != null) {
            data = URLEncoder.encode(data);
        }


        AttributeSet attr = getElement().getAttributes();
        HTMLDocument hdoc = (HTMLDocument)getElement().getDocument();

        String action = (String) attr.getAttribute(HTML.Attribute.ACTION);
        if (action == null) {
            action = hdoc.getBase().toString();
        }
        try {
            URL url = new URL(action+"?"+data);
            JEditorPane pane = (JEditorPane)getContainer();
            pane.setPage(url);
        } catch (MalformedURLException e1) {
        } catch (IOException e2) {
        }
    }
}
