package com.mjurik.web.crawler;

import java.util.HashSet;
import java.util.Set;

import com.mjurik.web.crawler.db.IgnoredPathPersistence;

/**
 * Created by Marian Jurik on 19.9.2015.
 */
public enum IgnoredPathList {

    EURONUMIS,
    NUMIZMATIK;

    private Set<String> ignored = new HashSet<>();

    public void init() {
        ignored.addAll(IgnoredPathPersistence.INSTANCE.getIgnoredPaths(this.toString()));
    }

    public boolean isIgnored(String path) {
        return ignored.contains(path);
    }
}
