package model;

import java.util.List;

public class Language {
    final private String name;
    final private List<String> dictionary;

    public Language(String name, List<String> dictionary) {
        this.name = name;
        this.dictionary = dictionary;
    }

    public String getName() {
        String nameWithoutExtension = name.substring(0, name.lastIndexOf('.'));
        return nameWithoutExtension.substring(0, 1).toUpperCase() + nameWithoutExtension.substring(1);
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    @Override
    public String toString() {
        return getName();
    }

}
