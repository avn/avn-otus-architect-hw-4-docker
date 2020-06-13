package ru.avn.otus.hw.users.profiles.dto;

public interface Versionable<T> {

    int getVersion();

    T getObject();

    static <T> Versionable<T> of(T object, int version) {
        return new DefaultVersionable<>(object, version);
    }
}
