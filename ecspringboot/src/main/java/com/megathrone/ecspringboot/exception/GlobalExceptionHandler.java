package com.megathrone.ecspringboot.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public String defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
    e.printStackTrace();
    Class constrainViolationException =
        Class.forName("org.hibernate.exception.ConstraintViolationException");
    if (e.getCause() != null && constrainViolationException == e.getCause().getClass()) {
      return "Violate constrains, maybe foreign key";
    }
    return e.getMessage();
  }
}
