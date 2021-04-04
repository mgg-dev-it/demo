package mggdevit.demolibrary.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// ...

	// @Validate For Validating Path Variables and Request Parameters
//    @ExceptionHandler(ConstraintViolationException.class)
//    public void constraintViolationException(HttpServletResponse response) throws IOException {
//    	System.err.println("adasdasdasdasdasdasdasdas");
//        response.sendError(HttpStatus.BAD_REQUEST.value());
//    }

	// @Validate For Validating Path Variables and Request Parameters
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(javax.validation.ConstraintViolationException ex, WebRequest request)
			throws IOException {
		//System.err.println("constraintViolationException");
		// response.sendError(HttpStatus.BAD_REQUEST.value());

		List<String> errors = new ArrayList<String>();
		for (javax.validation.ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.BAD_REQUEST);

//        //Get all fields errors
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(org.hibernate.exception.ConstraintViolationException ex, WebRequest request)
			throws IOException {
		//System.err.println("constraintViolationException");
		// response.sendError(HttpStatus.BAD_REQUEST.value());

		List<String> errors = new ArrayList<String>();
//		for (org.hibernate.exception.ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
//					+ violation.getMessage());
//		}
		errors.add(ex.getMessage());
		errors.add(ex.getSQLException().getMessage());
		

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", HttpStatus.BAD_REQUEST);

//        //Get all fields errors
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
//	public ResponseEntity<Object> constraintViolationException(org.springframework.dao.DataIntegrityViolationException ex, WebRequest request)
//			throws IOException {
//		//System.err.println("constraintViolationException");
//		// response.sendError(HttpStatus.BAD_REQUEST.value());
//		
//		List<String> errors = new ArrayList<String>();
////		for (org.hibernate.exception.ConstraintViolation<?> violation : ex.getConstraintViolations()) {
////			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
////					+ violation.getMessage());
////		}
//		errors.add(ex.getMessage());
//		
//		
//		Map<String, Object> body = new LinkedHashMap<>();
//		body.put("timestamp", new Date());
//		body.put("status", HttpStatus.BAD_REQUEST);
//		
////        //Get all fields errors
////        List<String> errors = ex.getBindingResult()
////                .getFieldErrors()
////                .stream()
////                .map(x -> x.getDefaultMessage())
////                .collect(Collectors.toList());
//		
//		body.put("errors", errors);
//		
//		return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//	}
	
}
