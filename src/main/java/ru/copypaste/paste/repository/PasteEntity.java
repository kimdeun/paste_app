package ru.copypaste.paste.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

public class PasteEntity {
    @Id
    private Integer id;
    private String data;
    private String hash;
    @Column("life_time")
    private LocalDateTime lifeTime;
    @Column("is_public")
    private boolean isPublic;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(LocalDateTime lifeTime) {
        this.lifeTime = lifeTime;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
