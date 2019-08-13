package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String v) throws Exception {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("uuuu")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        LocalDate localDate = LocalDate.parse(v, formatter);
        return localDate;

    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
