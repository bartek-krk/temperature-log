package pl.bart.temperaturelog.utilities;

import java.util.ArrayList;

public class ApiKeyGenerator {
    public static String generate() {
        int keyLength = 30;

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i=0; i<keyLength; i++) numbers.add((int) ((Math.random() * (125 - 34)) + 34));

        StringBuilder apiKey = new StringBuilder();
        for(int number : numbers) {
            String currentChar = Character.toString((char) number);
            apiKey.append(currentChar);
        }

        return apiKey.toString();
    }
}
