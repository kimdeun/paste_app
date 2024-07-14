package ru.copypaste.paste.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class HexHashGenerator {
    private static AtomicInteger idGenerator = new AtomicInteger(0);

    public static int generateId() {
        return idGenerator.getAndIncrement();
    }

    public static String getHexHash() {
        return Integer.toHexString(idGenerator.intValue());
    }
}
