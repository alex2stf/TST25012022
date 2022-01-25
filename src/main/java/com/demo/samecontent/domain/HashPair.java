package com.demo.samecontent.domain;

public class HashPair {
    private final String name;
    private final String hash;

    public HashPair(String name, String hash) {
        this.name = name;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public String getHash() {
        return hash;
    }
}
