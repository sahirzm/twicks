/**
 * 
 */
package in.ac.vit.twicks.exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author sahir
 * 
 */
public class ValidationException extends ConstraintViolationException {

	private Map<String, String> errors = new HashMap<>();

	/**
	 * @param constraintViolations
	 */
	public ValidationException(Set<ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}

	public ValidationException(
			Set<ConstraintViolation<?>> constraintViolations,
			Map<String, String> errors) {
		super(constraintViolations);
		this.errors = errors;
	}

	/**
	 * @param message
	 * @param constraintViolations
	 */
	public ValidationException(String message,
			Set<ConstraintViolation<?>> constraintViolations) {
		super(message, constraintViolations);
	}

	public ValidationException(String message,
			Set<ConstraintViolation<?>> constraintViolations,
			Map<String, String> errors) {
		super(message, constraintViolations);
		this.errors = errors;
	}

	public String getAsJSON() {
		Set<ConstraintViolation<?>> violations = this.getConstraintViolations();
		Iterator<ConstraintViolation<?>> it = violations.iterator();

		while (it.hasNext()) {
			ConstraintViolation<?> vio = it.next();
			errors.put(vio.getPropertyPath().toString(), vio.getMessage());
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(errors);
		} catch (IOException e) {

		}
		return "{\"error\":\"Some Error while validatin\"}";
	}

}
