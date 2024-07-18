package ru.copypaste.paste.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.copypaste.paste.exeption.NotFountEntityException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Qualifier("PasteBoxRepositoryMap")
public class PasteBoxRepositoryMap implements PasteBoxRepository {
    private final Map<String, PasteEntity> vault = new ConcurrentHashMap<>();

    @Override
    public PasteEntity getByHash(String hash) {
        PasteEntity pasteEntity = vault.get(hash);

        if (pasteEntity == null) {
            throw new NotFountEntityException("PasteBox not found with hash = " + hash);
        }

        return pasteEntity;
    }

    @Override
    public List<PasteEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(PasteEntity::isPublic)
                .filter(pasteEntity -> pasteEntity.getLifeTime().isAfter(now))
                .sorted(Comparator.comparing(PasteEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PasteEntity pasteEntity) {
        vault.put(pasteEntity.getHash(), pasteEntity);
    }
}
