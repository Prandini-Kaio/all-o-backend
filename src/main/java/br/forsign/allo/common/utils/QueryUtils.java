package br.forsign.allo.common.utils;

import java.util.Map;

/*
 * Created By Kaio Prandini
 * Date: 3/16/24
 * Time: 3:38 PM
 */
public class QueryUtils {

    public static void safeAddParams(Map<String, Object> params, String name, Object value, StringBuilder sb, String queryPart){
        if(value != null){
            params.put(name, value);
            sb.append(queryPart);
        }
    }
}
