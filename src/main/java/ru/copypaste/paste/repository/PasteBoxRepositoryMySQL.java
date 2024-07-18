package ru.copypaste.paste.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("PasteBoxRepositoryMySQL")
public interface PasteBoxRepositoryMySQL extends CrudRepository<PasteEntity, Integer>, PasteBoxRepository {
    @Query("SELECT * FROM paste_entity WHERE hash = :hash")
    PasteEntity getByHash(String hash);

    @Query("SELECT * FROM paste_entity WHERE is_public = 1 and life_time > CURDATE() LIMIT :amount")
    List<PasteEntity> getListOfPublicAndAlive(int amount);

    @Modifying
    default void add(PasteEntity pasteEntity) {
        pasteEntity.setId(null);
        save(pasteEntity);
    }
}