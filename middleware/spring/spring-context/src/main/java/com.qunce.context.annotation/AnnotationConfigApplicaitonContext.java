package com.qunce.context.annotation;

/**
 * @Description
 * 独立的应用程序上下文，接受注解类作为输入。尤其是通过注解@Configuration标注的类。
 *
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
