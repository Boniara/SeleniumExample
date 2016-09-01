package com.boniara.seleniumexample.utils;

import com.boniara.seleniumexample.exceptions.LineNotFoundException;
import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackTraceUtil {

    private static final Logger LOG = Logger.getLogger(StackTraceUtil.class);

    private StackTraceElement[] stackTraceElements;

    public StackTraceUtil() {
        this.stackTraceElements = Thread.currentThread().getStackTrace();
    }

    public String getUINameFrom(String packageName) {
        String uiName = "";
        Class aClass = null;
        Field[] fields = null;
        Constructor<?>[] constructors = null;
        StackTraceElement previousElement = stackTraceElements[0];
        int line = 0;
        synchronized(stackTraceElements) {
            for (StackTraceElement element : stackTraceElements) {
                boolean isMatches = matchesPattern(".*\\." + packageName + "\\..*", element.getClassName());
                if (isMatches) {
                    try {
                        line = element.getLineNumber();
                        aClass = Class.forName(element.getClassName());
                        fields = aClass.getDeclaredFields();
                        constructors = Class.forName(previousElement.getClassName()).getConstructors();
                        uiName = getElementNameFromLine(fields, constructors, getLine(element, line));
                        return uiName;
                    } catch (ClassNotFoundException e) {
                        LOG.error(e);
                    } catch (LineNotFoundException e) {
                        LOG.error(e);
                    }
                }
                previousElement = element;
            }
        }
        return uiName;
    }

    private String getElementNameFromLine(Field[] fields, Constructor<?>[] constructors, String line) {
        String constructorName = getPostfix(constructors[0].getName());
        for(Field field: fields) {
            boolean b = matchesPattern(".*" + constructorName + "\\(\\ *" + field.getName() + "[\\)\\,\\ ]*.*", line);
            if(b) {
                return field.getName();
            }
        }
        return "The element not found";
    }

    private String getPostfix(String packageName) {
        int postfixStart = 0;
        int postfixEnd = packageName.length();
        for(int i = packageName.length() - 1; i >=0; i--) {
            if(packageName.charAt(i) == '.') {
                postfixStart = i + 1;
                break;
            }
        }
        return packageName.substring(postfixStart, postfixEnd);
    }

    private String getLine(StackTraceElement element, int lineNumber) throws LineNotFoundException {
        BufferedReader reader = null;
        String currentLine = "";
        try {
            reader = new BufferedReader(new FileReader(new File("src\\main\\java\\" + element.getClassName()
                    .replaceAll("\\.", "\\\\") + ".java").getAbsolutePath()));
            int currentLineNumber = 0;
            while((currentLine = reader.readLine()) != null) {
                currentLineNumber++;
                if(currentLineNumber == lineNumber)
                    return currentLine;
            }
            throw new LineNotFoundException("Line on number " + lineNumber + " not found");
        } catch (IOException e) {
            LOG.error(e);
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                LOG.error(e);
            }
        }
        return currentLine;
    }

    private boolean matchesPattern(String regex, String matc) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(matc);
        return matcher.matches();
    }
}
