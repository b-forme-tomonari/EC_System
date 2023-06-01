package com.example.demo.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * アノテーションの実装クラス
 * @author K.Tomonari
 *
 */
@Documented
@Constraint(validatedBy = {UnusedUserIdValidator.class})
@Target(ElementType.FIELD) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface UnusedUserId {

    String message() default "このユーザーIDは既に登録されています";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        UnusedUserId[] value();
    }
}