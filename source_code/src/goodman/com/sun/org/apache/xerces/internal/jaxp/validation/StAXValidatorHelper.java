/*
 * Copyright (c) 2005, 2017, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.jaxp.validation.JAXPValidationMessageFormatter;
import com.sun.org.apache.xerces.internal.jaxp.validation.ValidatorHandlerImpl;
import com.sun.org.apache.xerces.internal.jaxp.validation.ValidatorHelper;
import com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaValidatorComponentManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import goodman.java.io.IOException;

import goodman.javax.xml.transform.Result;
import goodman.javax.xml.transform.Source;
import goodman.javax.xml.transform.Transformer;
import goodman.javax.xml.transform.TransformerConfigurationException;
import goodman.javax.xml.transform.TransformerException;
import goodman.javax.xml.transform.TransformerFactory;
import goodman.javax.xml.transform.TransformerFactoryConfigurationError;
import goodman.javax.xml.transform.sax.SAXResult;
import goodman.javax.xml.transform.sax.SAXTransformerFactory;
import goodman.javax.xml.transform.sax.TransformerHandler;
import goodman.javax.xml.transform.stax.StAXResult;
import goodman.javax.xml.transform.stax.StAXSource;
import jdk.xml.internal.JdkXmlUtils;

import org.xml.sax.SAXException;

/**
 * <p>A validator helper for <code>StAXSource</code>s.</p>
 *
 * @author <a href="mailto:Sunitha.Reddy@Sun.com">Sunitha Reddy</a>
 */
public final class StAXValidatorHelper implements com.sun.org.apache.xerces.internal.jaxp.validation.ValidatorHelper {

    /** Component manager. **/
    private XMLSchemaValidatorComponentManager fComponentManager;

    private Transformer identityTransformer1 = null;
    private TransformerHandler identityTransformer2 = null;
    private ValidatorHandlerImpl handler = null;

    /** Creates a new instance of StaxValidatorHelper */
    public StAXValidatorHelper(XMLSchemaValidatorComponentManager componentManager) {
        fComponentManager = componentManager;
    }

    public void validate(Source source, Result result)
        throws SAXException, IOException {

        if (result == null || result instanceof StAXResult) {

            if( identityTransformer1==null ) {
                try {
                    SAXTransformerFactory tf = JdkXmlUtils.getSAXTransformFactory(
                            fComponentManager.getFeature(JdkXmlUtils.OVERRIDE_PARSER));

                    XMLSecurityManager securityManager =
                            (XMLSecurityManager)fComponentManager.getProperty(Constants.SECURITY_MANAGER);
                    if (securityManager != null) {
                        for (XMLSecurityManager.Limit limit : XMLSecurityManager.Limit.values()) {
                            if (securityManager.isSet(limit.ordinal())){
                                tf.setAttribute(limit.apiProperty(),
                                        securityManager.getLimitValueAsString(limit));
                            }
                        }
                        if (securityManager.printEntityCountInfo()) {
                            tf.setAttribute(Constants.JDK_ENTITY_COUNT_INFO, "yes");
                        }
                    }

                    identityTransformer1 = tf.newTransformer();
                    identityTransformer2 = tf.newTransformerHandler();
                } catch (TransformerConfigurationException e) {
                    // this is impossible, but again better safe than sorry
                    throw new TransformerFactoryConfigurationError(e);
                }
            }

            handler = new ValidatorHandlerImpl(fComponentManager);
            if( result!=null ) {
                handler.setContentHandler(identityTransformer2);
                identityTransformer2.setResult(result);
            }

            try {
                identityTransformer1.transform( source, new SAXResult(handler) );
            } catch (TransformerException e) {
                if( e.getException() instanceof SAXException )
                    throw (SAXException)e.getException();
                throw new SAXException(e);
            } finally {
                handler.setContentHandler(null);
            }
            return;
        }
        throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(fComponentManager.getLocale(),
                "SourceResultMismatch",
                new Object [] {source.getClass().getName(), result.getClass().getName()}));
    }
}
