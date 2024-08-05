package backend.academia.fortaleza.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ActionMapping {
    private static final Map<Pattern, String> actionMap = new HashMap<>();

    static {
        // Usuario Controller

        // Asignatura Controller

        // Simple Courses Controller
        actionMap.put(Pattern.compile("GET /course-relations"), "");
  }

    public static String getActionDescription(String method, String path) {
        return actionMap.entrySet().stream()
                .filter(entry -> entry.getKey().matcher(method + " " + path).matches())
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(method + " " + path);
    }
}
