package pl.bart.temperaturelog.utilities;

import java.util.ArrayList;

public class ApiKeyGenerator {
    public static String generate() {
        int keyLength = 30;
        long seed = System.currentTimeMillis();
        long multiplier = 9564L;
        long modulus = 989531124L;
        long increment = 876L;

        ArrayList<Long> numbers = new ArrayList<>();

        for(int i=0; i<keyLength; i++) {
            long currentNumber = 0L;
            if (numbers.isEmpty()) currentNumber = (multiplier * seed + increment) % modulus;
            else currentNumber = (multiplier * numbers.get(i - 1) + increment) % modulus;
            currentNumber = currentNumber/10000;
            while (currentNumber > 126) currentNumber = currentNumber - 7;      //ensures only printable ascii chars are used
            while (currentNumber < 33) currentNumber = currentNumber + 7;
            numbers.add(currentNumber);
        }

        StringBuilder apiKey = new StringBuilder();

        for(Long number : numbers) {
            String currentChar = Character.toString((char) number.intValue());
            apiKey.append(currentChar);
        }
        return apiKey.toString();
    }
}
