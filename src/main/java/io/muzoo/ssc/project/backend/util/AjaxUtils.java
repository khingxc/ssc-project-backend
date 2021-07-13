package io.muzoo.ssc.project.backend.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class AjaxUtils {

    public static String convertToString(Object objVal){
        StringWriter writer = new StringWriter();
        ObjectMapper objMap = new ObjectMapper();
        try {
            objMap.writeValue(writer, objVal);
            return writer.toString();
        } catch (IOException e) {
            return "[bad obj/conversion]";
        }
    }

}
