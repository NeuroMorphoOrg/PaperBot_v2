/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.exceptions;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String text) {
        super("Duplicated resource found in collection: " + text);
    }
}
