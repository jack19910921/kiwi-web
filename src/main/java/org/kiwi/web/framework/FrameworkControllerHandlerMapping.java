package org.kiwi.web.framework;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * The handler mapping for framework controller which annotated by {@link org.kiwi.web.framework.annotation.FrameworkController}
 * </p>
 *
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/7/30.
 */
public class FrameworkControllerHandlerMapping extends RequestMappingHandlerMapping {

    private Map<String, String> mappings = new HashMap<>();

    /**
     * set some custom mapping
     *
     * @param patternMap
     */
    public void setMappings(Map<String, String> patternMap) {
        this.mappings = patternMap;
    }

    public FrameworkControllerHandlerMapping() {
        // Make sure user-supplied mappings take precedence by default (except the resource mapping)
        setOrder(Ordered.LOWEST_PRECEDENCE - 1);
    }

    /**
     * Detects {@code @FrameworkController} annotations in handler beans.
     *
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#isHandler(java.lang.Class)
     */
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotationUtils.findAnnotation(beanType, FrameworkController.class) != null;
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {

        RequestMappingInfo defaultMapping = super.getMappingForMethod(method, handlerType);
        if (defaultMapping == null) {
            return null;
        }

        Set<String> defaultPatterns = defaultMapping.getPatternsCondition().getPatterns();
        String[] patterns = new String[defaultPatterns.size()];

        // replace as custom mapping
        int i = 0;
        for (String pattern : defaultPatterns) {
            patterns[i] = pattern;
            if (mappings.containsKey(pattern)) {
                patterns[i] = mappings.get(pattern);
            }
            i++;
        }
        PatternsRequestCondition patternsInfo = new PatternsRequestCondition(patterns);

        ParamsRequestCondition paramsInfo = defaultMapping.getParamsCondition();

        RequestMappingInfo mapping = new RequestMappingInfo(patternsInfo, defaultMapping.getMethodsCondition(),
                paramsInfo, defaultMapping.getHeadersCondition(), defaultMapping.getConsumesCondition(),
                defaultMapping.getProducesCondition(), defaultMapping.getCustomCondition());
        return mapping;

    }

}
