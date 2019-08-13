/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unknown resource") //500
public class UnknownTypeException extends RuntimeException {

    public UnknownTypeException(String text) {
        super("Unknown resource: " + text);
    }
}
