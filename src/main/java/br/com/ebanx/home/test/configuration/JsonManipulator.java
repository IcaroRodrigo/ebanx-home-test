package br.com.ebanx.home.test.configuration;

public class JsonManipulator {
    public static String addSpacesToJSON(String jsonString) throws Exception {

        StringBuilder result = new StringBuilder();
        int indentLevel = 0;
        boolean inString = false;

        for (char currentChar : jsonString.toCharArray()) {
            if (currentChar == '{' || currentChar == '[') {

                result.append(currentChar);
                indentLevel++;
            }
            else  if (currentChar == ':'){
                if(indentLevel == 1)
                    result.append(currentChar).append(" ");
                else
                    result.append(currentChar);
            }
            else if (currentChar == '}' || currentChar == ']') {
                indentLevel--;
                result.append(currentChar);
            } else if (currentChar == ',') {
                result.append(currentChar).append(" ");
            } else if (currentChar == '"') {
                result.append(currentChar);
                inString = !inString;
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}