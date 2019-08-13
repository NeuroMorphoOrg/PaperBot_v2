package org.neuromorpho.paperbot.search.model;

import java.time.LocalDate;
import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Log {
    
    private LocalDate start;
    private LocalDate stop;
    private Long threadId;
    private String cause;

    public Log() {
        this.start = LocalDate.now();
        this.cause = "Executing ...";
    }
    
    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getStop() {
        return stop;
    }

    public void setStop(LocalDate stop) {
        this.stop = stop;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }
    

    public String getCause() {
        return cause;
    }
    public void setCause(String cause) {
        this.cause = cause;
        this.stop = LocalDate.now();

    }

}
