package com.baeldung.lju;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayNameGenerator;

public class CustomDisplayNameGenerator extends DisplayNameGenerator.Standard {

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        String methodName = testMethod.getName();
        return convertToReadableFormat(methodName);
    }

    private String convertToReadableFormat(String methodName) {
        StringBuilder displayName = new StringBuilder();

        String[] parts = methodName.split("_");
        for (String part : parts) {
            if (!displayName.isEmpty()) {
                displayName.append(", ");
            }

            Matcher matcher = Pattern.compile("([a-z])([A-Z])").matcher(part);
            String readablePart = matcher.replaceAll("$1 $2").toLowerCase();

            if (displayName.isEmpty()) {
                readablePart = readablePart.substring(0, 1).toUpperCase() + readablePart.substring(1);
            }
            displayName.append(readablePart);
        }

        return displayName.toString();
    }
}

