package com.tul.carshop.controllers.products

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.net.http.HttpHeaders
/*
@ControllerAdvice
class RestResponseEntityErrorHandler: ResponseEntityErrorHandler() {
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, request: WebRequest, status:){
        val result: Map<String, List<String>> = ex.bindingResult.fieldErrors.groupBy({it.field},{it.defaultMessage})
        return ResponseEntity.status(status).headers(headers).body(result)

    }
}
*/
