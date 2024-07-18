package ru.copypaste.paste.repository;

import java.util.List;

public interface PasteBoxRepository {
    PasteEntity getByHash(String hash);

    List<PasteEntity> getListOfPublicAndAlive(int amount);

    void add(PasteEntity pasteEntity);
}
