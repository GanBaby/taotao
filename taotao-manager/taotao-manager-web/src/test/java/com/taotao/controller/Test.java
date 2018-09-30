package com.taotao.controller;

import org.junit.Ignore;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 甘银道 on 2018/9/25
 */
@Ignore
public class Test {

    public static void main(String[] args){
        String a = null;

        boolean empty = StringUtils.isEmpty(a);
        a = "123";
        empty = StringUtils.isEmpty(a);

    }

}
