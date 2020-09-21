/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package goodman.com.sun.org.apache.xml.internal.serializer.utils;

import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;

import goodman.java.util.ListResourceBundle;
import goodman.java.util.Locale;
import goodman.java.util.MissingResourceException;
import goodman.java.util.ResourceBundle;

/**
 * An instance of this class is a ListResourceBundle that
 * has the required getContents() method that returns
 * an array of message-key/message associations.
 * <p>
 * The message keys are defined in {@link com.sun.org.apache.xml.internal.serializer.utils.MsgKey}. The
 * messages that those keys map to are defined here.
 * <p>
 * The messages in the English version are intended to be
 * translated.
 *
 * This class is not a public API, it is only public because it is
 * used in com.sun.org.apache.xml.internal.serializer.
 *
 * @xsl.usage internal
 */
public class SerializerMessages_it extends ListResourceBundle {

    /*
     * This file contains error and warning messages related to
     * Serializer Error Handling.
     *
     *  General notes to translators:

     *  1) A stylesheet is a description of how to transform an input XML document
     *     into a resultant XML document (or HTML document or text).  The
     *     stylesheet itself is described in the form of an XML document.

     *
     *  2) An element is a mark-up tag in an XML document; an attribute is a
     *     modifier on the tag.  For example, in <elem attr='val' attr2='val2'>
     *     "elem" is an element name, "attr" and "attr2" are attribute names with
     *     the values "val" and "val2", respectively.
     *
     *  3) A namespace declaration is a special attribute that is used to associate
     *     a prefix with a URI (the namespace).  The meanings of element names and
     *     attribute names that use that prefix are defined with respect to that
     *     namespace.
     *
     *
     */

    /** The lookup table for error messages.   */
    public Object[][] getContents() {
        Object[][] contents = new Object[][] {
            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.BAD_MSGKEY,
                "La chiave di messaggio ''{0}'' non si trova nella classe messaggio ''{1}''" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.BAD_MSGFORMAT,
                "Formato di messaggio ''{0}'' in classe messaggio ''{1}'' non riuscito." },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_SERIALIZER_NOT_CONTENTHANDLER,
                "La classe serializzatore ''{0}'' non implementa org.xml.sax.ContentHandler." },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_RESOURCE_COULD_NOT_FIND,
                    "Risorsa [ {0} ] non trovata.\n {1}" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_RESOURCE_COULD_NOT_LOAD,
                    "Impossibile caricare la risorsa [ {0} ]: {1} \n {2} \t {3}" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_BUFFER_SIZE_LESSTHAN_ZERO,
                    "Dimensione buffer <=0" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_INVALID_UTF16_SURROGATE,
                    "Rilevato surrogato UTF-16 non valido: {0}?" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_OIERROR,
                "Errore di I/O" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_ILLEGAL_ATTRIBUTE_POSITION,
                "Impossibile aggiungere l''attributo {0} dopo i nodi figlio o prima che sia prodotto un elemento. L''attributo verr\u00E0 ignorato." },

            /*
             * Note to translators:  The stylesheet contained a reference to a
             * namespace prefix that was undefined.  The value of the substitution
             * text is the name of the prefix.
             */
            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_NAMESPACE_PREFIX,
                "Lo spazio di nomi per il prefisso ''{0}'' non \u00E8 stato dichiarato." },

            /*
             * Note to translators:  This message is reported if the stylesheet
             * being processed attempted to construct an XML document with an
             * attribute in a place other than on an element.  The substitution text
             * specifies the name of the attribute.
             */
            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_STRAY_ATTRIBUTE,
                "Attributo ''{0}'' al di fuori dell''elemento." },

            /*
             * Note to translators:  As with the preceding message, a namespace
             * declaration has the form of an attribute and is only permitted to
             * appear on an element.  The substitution text {0} is the namespace
             * prefix and {1} is the URI that was being used in the erroneous
             * namespace declaration.
             */
            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_STRAY_NAMESPACE,
                "Dichiarazione dello spazio di nomi ''{0}''=''{1}'' al di fuori dell''elemento." },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_COULD_NOT_LOAD_RESOURCE,
                "Impossibile caricare ''{0}'' (verificare CLASSPATH); verranno utilizzati i valori predefiniti" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_ILLEGAL_CHARACTER,
                "Tentativo di eseguire l''output di un carattere di valore integrale {0} non rappresentato nella codifica di output {1} specificata." },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_COULD_NOT_LOAD_METHOD_PROPERTY,
                "Impossibile caricare il file delle propriet\u00E0 ''{0}'' per il metodo di emissione ''{1}'' (verificare CLASSPATH)" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_INVALID_PORT,
                "Numero di porta non valido" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_PORT_WHEN_HOST_NULL,
                "La porta non pu\u00F2 essere impostata se l'host \u00E8 nullo" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_HOST_ADDRESS_NOT_WELLFORMED,
                "Host non \u00E8 un indirizzo corretto" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_SCHEME_NOT_CONFORMANT,
                "Lo schema non \u00E8 conforme." },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_SCHEME_FROM_NULL_STRING,
                "Impossibile impostare lo schema da una stringa nulla" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE,
                "Il percorso contiene sequenza di escape non valida" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_PATH_INVALID_CHAR,
                "Il percorso contiene un carattere non valido: {0}" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_FRAG_INVALID_CHAR,
                "Il frammento contiene un carattere non valido" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_FRAG_WHEN_PATH_NULL,
                "Il frammento non pu\u00F2 essere impostato se il percorso \u00E8 nullo" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_FRAG_FOR_GENERIC_URI,
                "Il frammento pu\u00F2 essere impostato solo per un URI generico" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_NO_SCHEME_IN_URI,
                "Nessuno schema trovato nell'URI" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_CANNOT_INIT_URI_EMPTY_PARMS,
                "Impossibile inizializzare l'URI con i parametri vuoti" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_NO_FRAGMENT_STRING_IN_PATH,
                "Il frammento non pu\u00F2 essere specificato sia nel percorso che nel frammento" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_NO_QUERY_STRING_IN_PATH,
                "La stringa di query non pu\u00F2 essere specificata nella stringa di percorso e query." },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_NO_PORT_IF_NO_HOST,
                "La porta non pu\u00F2 essere specificata se l'host non \u00E8 specificato" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_NO_USERINFO_IF_NO_HOST,
                "Userinfo non pu\u00F2 essere specificato se l'host non \u00E8 specificato" },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_XML_VERSION_NOT_SUPPORTED,
                "Avvertenza: la versione del documento di output deve essere ''{0}''. Questa versione di XML non \u00E8 supportata. La versione del documento di output sar\u00E0 ''1.0''." },

            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_SCHEME_REQUIRED,
                "Lo schema \u00E8 obbligatorio." },

            /*
             * Note to translators:  The words 'Properties' and
             * 'SerializerFactory' in this message are Java class names
             * and should not be translated.
             */
            {   com.sun.org.apache.xml.internal.serializer.utils.MsgKey.ER_FACTORY_PROPERTY_MISSING,
                "L''oggetto Properties passato a SerializerFactory non dispone di una propriet\u00E0 ''{0}''." },

            {   MsgKey.ER_ENCODING_NOT_SUPPORTED,
                "Avvertenza: la codifica ''{0}'' non \u00E8 supportata da Java Runtime." },


        };

        return contents;
    }
}
