package com.blogapp.blogApp.configs;

import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@NoArgsConstructor
public class Functions {

    public String dateFormat(Instant date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy hh-mm-ss");
        String dateToString = formatter.format(date);
        return dateToString;
    }

    public String update(String old, String update) {
        if(update.equals(""))
            return old;
        old = update;
        return old;
    }
}
