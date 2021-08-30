package com.light.paas.zk.web.util;

import com.light.paas.zk.web.constant.StringConstants;
import com.light.paas.zk.web.exception.ExceptionFactory;
import org.hibernate.validator.HibernateValidator;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorUtils {

    private ValidatorUtils() {

    }
    private static final Validator validatorFast = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    private static final Validator validatorAll = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();
    public static <T> Mono<T> validateFast(Mono<T> domain){
        return domain.flatMap($v ->{
            Set<ConstraintViolation<T>> validate = validatorFast.validate($v);
            return getMono($v, validate);
        });
    }
    public static <T> Mono<T> validateFast(T domain){
        return validateFast(Mono.just(domain));
    }
    private static <T> Mono<T> getMono(T $v, Set<ConstraintViolation<T>> validate) {
        if (validate.size() > 0) {
            return Mono.error(ExceptionFactory.bizException(validate.stream().map($i -> $i.getPropertyPath() +
                    StringConstants.PATH_SEPARATOR +
                    $i.getMessage()).collect(Collectors.joining(StringConstants.LINE_SEPARATOR))));
        } else {
            return Mono.just($v);
        }
    }
    public static <T> Mono<T> validateAll(Mono<T> domain){
        return domain.flatMap($v -> {
            Set<ConstraintViolation<T>> validate = validatorAll.validate($v);
            if (validate.size() > 0) {
                return Mono.error(ExceptionFactory.bizException(validate.stream().map($i -> $i.getPropertyPath() +
                        StringConstants.PATH_SEPARATOR +
                        $i.getMessage()).collect(Collectors.joining(StringConstants.LINE_SEPARATOR))));
            } else {
                return Mono.just($v);
            }
        });
    }
    public static <T> Mono<T> validateAll(T domain){
        return validateAll(Mono.just(domain));
    }

}
