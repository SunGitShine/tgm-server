package com.juma.tgm.common;

import java.util.HashMap;
import java.util.Map;

public class StringTemplateUtils {

    public static String render(String template, Map<String,String> context) {
        if (template == null || context.isEmpty()) return null;
        for ( String s : context.keySet() ) {
            template = template.replaceAll("\\$\\{".concat(s).concat("\\}"),context.get(s));
        }
        return template;
    }

    public static void main(String[] args) {
        String template = "亲爱的用户${name}";
        Map<String,String> context = new HashMap<>();
        context.put("name","qq");
        System.out.println(render(template,context));
    }

}
