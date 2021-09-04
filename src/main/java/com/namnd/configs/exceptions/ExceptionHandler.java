package com.namnd.configs.exceptions;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.namnd.configs.enums.MessageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.namnd.dtos.response.ResponseDTO;

@ControllerAdvice
public class ExceptionHandler extends RequestBodyAdviceAdapter {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    private HttpServletRequest request;

    public ExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Handle exception for validate input data field and custom validation;
     */

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getField() + " " + e.getDefaultMessage()));

        String description = String.join("; ", new ArrayList<>(errors.values()));

        ResponseDTO response = ResponseDTO.error(MessageEnum.VALIDATE_FAIL, description);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle common exception all system;
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> processRuntimeException(Exception ex) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.error("[URI: {}][NG][EXCEPTION]: {}", this.request.getRequestURI(),
                    mapper.writeValueAsString(ex.getMessage()));

            logger.error("[URI: {}][NG][EXCEPTION]: {}", this.request.getRequestURI(),
                    mapper.writeValueAsString(ex.getClass()));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        ResponseDTO response = ResponseDTO.error(MessageEnum.INTERNAL_API_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        if (!request.getRequestURI().contains("medias")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                logger.info("[URI: {}][REQUEST_BODY]: {}", this.request.getRequestURI(),
                        mapper.writeValueAsString(body));

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

}
