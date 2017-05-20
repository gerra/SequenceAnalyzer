package ru.sequenceanalyzer;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Utils {
    private static final String PREFERENCES_NAME = "ru.sequenceanalyzer.PREFERENCES";
    private static final String TUTORIAL_PASSED_KEY = "TUTORIAL_PASSED";
    private static final String SEQUENCE_KEY = "SEQUENCE_PASSED";

    public static boolean isTutorialPassed(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getBoolean(TUTORIAL_PASSED_KEY, false);
    }

    public static void setTutorialPassed(Context context) {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(TUTORIAL_PASSED_KEY, true)
                .apply();
    }

    private static File getConfigFile(Context context) {
        String state = Environment.getExternalStorageState();
        File folder;
        if (state.equals(Environment.MEDIA_MOUNTED)) { // using external storage
            folder = context.getApplicationContext().getExternalFilesDir("config");
        } else {
            folder = context.getApplicationContext().getFilesDir();
        }
        return new File(folder, "cfg.txt");
    }

    private static void closeCloseable(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static void saveEnteredSequence(Context context, String sequence) {
        File file = getConfigFile(context);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }
        Writer writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(sequence);
        } catch (IOException ignored) {
        } finally {
            closeCloseable(writer);
        }
    }

    public static String loadEnteredSequence(Context context) {
        File file = getConfigFile(context);
        if (!file.exists()) {
            return "";
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            return reader.readLine();
        } catch (IOException e) {
            return "";
        } finally {
            closeCloseable(reader);
        }
    }
}
