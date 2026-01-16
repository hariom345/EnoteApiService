package com.enote.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {


    public static <T> GenericResponse<T> buildSuccess(T data, String message) {
        return new GenericResponse<>(true, message, data);
    }

    public static <T> GenericResponse<T> buildSuccess(T data) {
        return new GenericResponse<>(true, "success", data);
    }

    public static <T> GenericResponse<T> buildError(String message) {
        return new GenericResponse<>(false, message, null);
    }


    public static <T> ResponseEntity<GenericResponse<T>> success(String message, HttpStatus status, T data) {
        GenericResponse<T> genericResponse = buildSuccess(data, message);
        return ResponseEntity.status(status).body(genericResponse);
    }

    public static <T> ResponseEntity<GenericResponse<T>> success(T data, String message) {
        return success(message, HttpStatus.OK, data);
    }

    public static <T> ResponseEntity<GenericResponse<T>> success(String message) {
        return success(message, HttpStatus.OK, null);
    }

    public static <T> ResponseEntity<GenericResponse<T>> error(String message, HttpStatus status) {
        GenericResponse<T> genericResponse = buildError(message);
        return ResponseEntity.status(status).body(genericResponse);
    }

    public static <T> ResponseEntity<GenericResponse<T>> error(String message) {
        return error(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public static <T> ResponseEntity<GenericResponse<T>> created(T data, String message) {
        return success(message, HttpStatus.CREATED, data);
    }

    public static <T> ResponseEntity<GenericResponse<T>> created(String message) {
        return success(message, HttpStatus.CREATED, null);
    }

    public static <T> ResponseEntity<GenericResponse<T>> notFound(String message) {
        return error(message, HttpStatus.NOT_FOUND);
    }

    public static <T> ResponseEntity<GenericResponse<T>> badRequest(String message) {
        return error(message, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<GenericResponse<T>> internalServerError(String message) {
        return error(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}