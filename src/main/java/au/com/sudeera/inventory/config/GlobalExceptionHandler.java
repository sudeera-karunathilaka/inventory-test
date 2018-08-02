package au.com.sudeera.inventory.config;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.JsonMappingException;

import au.com.sudeera.inventory.wrapper.ErrorResult;
import au.com.sudeera.inventory.wrapper.Result;
import au.com.sudeera.inventory.wrapper.ResultType;
import au.com.sudeera.inventory.wrapper.exception.ItemExistException;

/**
 * The Class GlobalExceptionHandler. Act as a advisor and translate all exceptions
 * in to readable format
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * Process validation error.
	 *
	 * @param ex the ex
	 * @return the result
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result processValidationError(MethodArgumentNotValidException ex) {
		logger.error("Validation Error", ex);
		BindingResult result = ex.getBindingResult();
		List<String> fieldErrors = result.getFieldErrors().stream().map(fieldError -> fieldError.getObjectName() + "."
				+ fieldError.getField() + " " + fieldError.getDefaultMessage()).collect(Collectors.toList());
		return buildErrorResult("Error : Validation", fieldErrors);
	}

	/**
	 * Handle http message not readable.
	 *
	 * @param ex the ex
	 * @return the result
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected Result handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		logger.error("Http Message Not Readable", ex);
		String field = "";
		Throwable throwable = ex.getCause();

		if (throwable instanceof JsonMappingException) {
			JsonMappingException jsonMappingException = ((JsonMappingException) throwable);
			List<JsonMappingException.Reference> references = jsonMappingException.getPath();
			for (JsonMappingException.Reference reference : references) {
				if (reference.getFieldName() != null) {
					field += reference.getFieldName() + " ";
				}
			}
			return buildErrorResult("'" + field.trim() + "' contains invalid values", null);
		}
		return buildErrorResult(ex.getRootCause().getMessage(), null);
	}

	/**
	 * Process illegal argument exception.
	 *
	 * @param ex the ex
	 * @return the result
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected Result processIllegalArgumentException(IllegalArgumentException ex) {
		logger.error("Illegal Argument Exception", ex);
		return buildErrorResult(ex.getMessage(), null);
	}

	/**
	 * Process constraint violation exception.
	 *
	 * @param ex the ex
	 * @return the result
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected Result processConstraintViolationException(ConstraintViolationException ex) {
		logger.error("Constraint Violation Exception", ex);
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		String clientErrorMessage = "";
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			clientErrorMessage += constraintViolation.getMessage();
		}
		return buildErrorResult(clientErrorMessage, null);
	}

	/**
	 * Process method not supported exception.
	 *
	 * @param exception the exception
	 * @return the result
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Result processMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		logger.error("Method Not Supported Excpetion", ex);
		return buildErrorResult("Error : Method Not Allowed", null);
	}
	
	
	/**
	 * Process entity not found exception.
	 *
	 * @param exception the exception
	 * @return the result
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Result processEntityNotFoundException(EntityNotFoundException ex) {
		logger.error("Entity Not Found Excpetion", ex);
		return buildErrorResult(ex.getMessage(), null);
	}
	
	/**
	 * Process validation exception.
	 *
	 * @param exception the exception
	 * @return the result
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.CONFLICT)
	public Result processValidationException(ValidationException ex) {
		logger.error("Validation Excpetion", ex);
		return buildErrorResult(ex.getMessage(), null);
	}
	
	/**
	 * Process item exist exception.
	 *
	 * @param ex the ex
	 * @return the result
	 */
	@ExceptionHandler(ItemExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result processItemExistException(ItemExistException ex) {
		logger.error("Item Exist Exception", ex);
		return buildErrorResult(ex.getMessage(), null);
	}

	/**
	 * Process runtime exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Result> processRuntimeException(Exception ex) throws Exception {
		logger.error("Runtime Excpetion", ex);
		BodyBuilder builder;
		Result errorDTO;
		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		if (responseStatus != null) {
			builder = ResponseEntity.status(responseStatus.value());
			errorDTO = buildErrorResult(responseStatus.reason(), null);
		} else {
			builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			errorDTO = buildErrorResult("Internal Server Error", null);
		}
		return builder.body(errorDTO);
	}
	
	private ErrorResult buildErrorResult(String msg, List<String> fieldErrors) {
		ErrorResult errorResult = new ErrorResult();
		errorResult.setFieldErrors(fieldErrors);
		errorResult.setMsg(msg);
		errorResult.setType(ResultType.ERROR);

		return errorResult;
	}
}
