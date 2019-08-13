/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.communication.article;


import org.neuromorpho.paperbot.search.dto.article.Article;
import org.neuromorpho.paperbot.search.dto.article.Author;
import org.neuromorpho.paperbot.search.service.sciencedirect.model.search.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.*;

public class ArticleAssembler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // ScienceDirect conversion
    public Article createArticle(Entry data) {
        log.debug("Converting article: " + data.toString());
        Article article = new Article();
        article.setTitle(data.getTitle().replaceAll("<[^>]+>", ""));
        article.setDoi(data.getDoi());
        article.setJournal(data.getPublicationName());
        article.setPublishedDate(this.tryParseDate(data.getCoverDate()));
        List<Author> authorList = new ArrayList();
        if (data.getAuthors() != null) {
            for (String authorStr : data.getAuthors().getAuthorList()) {
                Author author = new Author(authorStr, null);
                authorList.add(author);
            }
            article.setAuthorList(authorList);
        }
        article.setLink(data.getUrl());
        return article;

    }


    public LocalDate tryParseDate(String dateStr) {

        String[] formatStrings = {"dd MMM uuuu", "dd MMMMM uuuu", "MMMMM uuuu", "uuuu", "uuuu/MM/dd", "uuuu-MM-dd", "MMM uuuu", "uuuu-MM"};
       
        for (String formatString : formatStrings) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern(formatString)
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .toFormatter();
            try {
                LocalDate localDate = LocalDate.parse(dateStr.trim(), formatter);
                return localDate;
            } catch (DateTimeParseException ex) {
                log.debug("Parsing date=" + dateStr + ", with format=" + formatString);
            }
        }
        return null;
    }
}
