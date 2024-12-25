package com.framework.annotations;

import com.framework.enums.CategoryType;
import com.framework.enums.Modules;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;



import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameWorkPracticeReportAnnotation {

    public Modules module();
    public CategoryType[] category();
    


}
