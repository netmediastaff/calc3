/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurice.calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author oghomwen.aigbedion
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    Boolean error;
    String message;
    Double result;

    public Response() {
    }

    public Response(Double result) {
        this.result = result;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" + "error=" + error + ", message=" + message + ", result=" + result + '}';
    }

}
