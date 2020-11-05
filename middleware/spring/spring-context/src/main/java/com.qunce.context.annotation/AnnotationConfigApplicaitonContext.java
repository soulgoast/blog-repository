package com.qunce.context.annotation;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class AnnotationConfigApplicaitonContext {

    public final AnnotatedBeanDefinitionReader reader;

    private final AnnotatedBeanDefinitionScanner scanner;

    public AnnotationConfigApplicaitonContext() {
        this.reader = new AnnotatedBeanDefinitionReader(this);
        this.scanner = new AnnotatedBeanDefinitionScanner(this);
    }

    public AnnotationConfigApplicaitonContext(Class<?>... annotatedClasses) {
        this();
        register(annotatedClasses);
        refresh();
    }

    private void refresh() {
    }

    private void register(Class<?>[] annotatedClasses) {
        
    }
}
