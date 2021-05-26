package com.blogapp.blogApp;

import com.blogapp.blogApp.domains.Post;
import com.blogapp.blogApp.domains.Tag;
import com.blogapp.blogApp.domains.User;
import com.blogapp.blogApp.securities.JwtTokenProvider;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
public class Functions {

    public String dateFormat(Date date) {
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
