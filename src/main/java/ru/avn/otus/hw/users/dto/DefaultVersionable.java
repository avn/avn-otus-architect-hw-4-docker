package ru.avn.otus.hw.users.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultVersionable<T> implements Versionable<T> {

    private final T object;

    private final int version;

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public T getObject() {
        return object;
    }
}
