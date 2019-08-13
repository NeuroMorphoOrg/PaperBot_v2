/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Missing data") //404
public class ExceptionAssigningReconstructions extends RuntimeException {

    public ExceptionAssigningReconstructions(String text) {
        super("Duplicated resource found in collection: " + text);
    }
}
