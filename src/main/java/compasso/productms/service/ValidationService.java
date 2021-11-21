package compasso.productms.service;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.springframework.stereotype.Service;

import compasso.productms.exception.ValidationException;

@Service
public class ValidationService {

    public <T> void validate(T objectToValidate) {
        Set<ConstraintViolation<T>> violations = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(objectToValidate);

        if (!isEmpty(violations)) {
            List<String> reasons = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            throw new ValidationException(reasons);
        }
    }
}
