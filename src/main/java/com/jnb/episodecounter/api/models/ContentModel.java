package com.jnb.episodecounter.api.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_content")
public class ContentModel extends RepresentationModel<ContentModel> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cod;
    private String name;
    private int content_group;
    private int content_unit;
    private int releaseDay;
    private boolean releasing;
    private int personalStatus;
    private int category;

    public UUID getCod() {
        return cod;
    }
    public void setCod(UUID cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getContent_group() {
        return content_group;
    }
    public void setContent_group(int content_group) {
        this.content_group = content_group;
    }

    public int getContent_unit() {
        return content_unit;
    }
    public void setContent_unit(int content_unit) {
        this.content_unit = content_unit;
    }

    public int getReleaseDay() {
        return releaseDay;
    }
    public void setReleaseDay(int releaseDay) {
        this.releaseDay = releaseDay;
    }

    public boolean isReleasing() {
        return releasing;
    }
    public void setReleasing(boolean releasing) {
        this.releasing = releasing;
    }

    public int getPersonalStatus() {
        return personalStatus;
    }
    public void setPersonalStatus(int personalStatus) {
        this.personalStatus = personalStatus;
    }

    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
}
