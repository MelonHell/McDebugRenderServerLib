package ru.melonhell.mcdebugrender.core.utils;

public record MdrNamespaceID(String namespace, String key) {
    public String asString() {
        return namespace + ":" + key;
    }

    public static MdrNamespaceID from(String namespaceKey) {
        String[] split = namespaceKey.split(":");
        return new MdrNamespaceID(split[0], split[1]);
    }

    @Override
    public String toString() {
        return asString();
    }
}
