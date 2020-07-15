package br.edu.ufcg.embedded.sam.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Responsible for mapping the {@link ConstraintViolationException}s of the system to an readable error object.
 */
public class ValidationError extends RuntimeException{

    private static final Logger logger = Logger.getLogger(ValidationError.class.getName());

    private Map<String, String> errors;

    public ValidationError() {
        errors = new HashMap<>();
    }

    /**
     * Constructor. Builds this object based on the {@code constraintViolationExcep}.
     *
     * @param constraintViolExcep {@link ConstraintViolationException} upon which this object will be created.
     */
    public ValidationError(ConstraintViolationException constraintViolExcep) {
        errors = new HashMap<>();
        for (ConstraintViolation violation : constraintViolExcep.getConstraintViolations()) {
            String key = "";
            if (violation.getPropertyPath() != null) {
                key = violation.getPropertyPath().toString();
            }
            try {
                errors.put(key, new String(violation.getMessage().getBytes("iso-8859-1"), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                logger.warning(e.getMessage());
            }
        }
    }

    public String addError(String key, String value) {
        return errors.put(key, value);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
