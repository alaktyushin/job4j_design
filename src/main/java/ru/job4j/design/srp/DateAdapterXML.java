package ru.job4j.design.srp;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAdapterXML extends XmlAdapter<String, Calendar> {

    private static final SimpleDateFormat formatter
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    /* Unused and not implemented (placeholder)*/
    @Override
    public Calendar unmarshal(String s) {
        return null;
    }

    @Override
    public String marshal(Calendar calendar) {
        return formatter.format(calendar.getTime());
    }
}
