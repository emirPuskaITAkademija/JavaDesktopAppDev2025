package com.itakademija.one.icon;

import javax.swing.*;
import java.net.URL;

public class IconLoader {

    private final Class<?> clazz;

    public IconLoader(final Class<?> clazz) {
        this.clazz = clazz;
    }

    public Icon loadIcon(String path) {
        ClassLoader classLoader = clazz.getClassLoader();
        URL url = classLoader.getResource(path);
        if (url == null) {
            System.err.println("No resource on path " + path);
            return null;
        }
        return new ImageIcon(url);
    }
}
