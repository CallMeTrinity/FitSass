package com.fitsass.loader;

import com.fitsass.models.Split;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class SplitLoader {
    private static final String SPLITS_FILE = "./src/main/resources/split.json";

    public List<Split> loadSplits() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(SPLITS_FILE)) {
            return gson.fromJson(reader, new TypeToken<List<Split>>() {
            }.getType());
        } catch (Exception e) {
            System.err.println("Error loading splits: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
