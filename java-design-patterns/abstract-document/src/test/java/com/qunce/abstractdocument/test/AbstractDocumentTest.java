package com.qunce.abstractdocument.test;

import com.qunce.abstractdocument.AbstractDocument;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @ClassName AbstractDocumentTest
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:45
 * @ModifyDate 2020/9/21 14:45
 * @Version 1.0
 */
public class AbstractDocumentTest {

    public static final String KEY = "key";

    public static final String VALUE = "value";

    private class DocumentImplementation extends AbstractDocument {

        public DocumentImplementation(Map<String, Object> properties) {
            super(properties);
        }
    }

    private DocumentImplementation document = new DocumentImplementation(new HashMap<>());


    @Test
    public void shoudPutAndGetValue() {
        document.put(KEY, VALUE);
        assertEquals(VALUE, document.get(KEY));
    }

    @Test
    public void shouldRetrieveChildren() {
        Map<String, Object> child1 = new HashMap<>();
        Map<String, Object> child2 = new HashMap<>();
        List<Map<String, Object>> children = Arrays.asList(child1, child2);

        document.put(KEY, children);

        Stream<DocumentImplementation> childrenStream = document.children(KEY, DocumentImplementation::new);
        assertNotNull(children);
        assertEquals(2, childrenStream.count());
    }

    @Test
    public void shouldRetrieveEmptyStreamForNonExistingChildren() {
        Stream<DocumentImplementation> children = document.children(KEY, DocumentImplementation::new);
        assertNotNull(children);
        assertEquals(0, children.count());
    }

    @Test
    public void shouldIncludePropsInToString() {
        Map<String, Object> props = new HashMap<>();
        props.put(KEY, VALUE);
        DocumentImplementation document = new DocumentImplementation(props);
        assertTrue(document.toString().contains(KEY));
        assertTrue(document.toString().contains(VALUE));
    }

}
