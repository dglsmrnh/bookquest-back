package io.bookquest.usecase.util;

import java.util.Map;

public class JsonUtil {

    public static String normalizeChatSonicJson(Map<String, String> json) {
        var array = json.get("message").split("json");
        var jsonStart = array[array.length - 1];
        return jsonStart.substring(jsonStart.indexOf("["))
                .replace("```JSON", "")
                .replace("```", "");
    }
}
