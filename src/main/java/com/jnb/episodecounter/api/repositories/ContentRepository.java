package com.jnb.episodecounter.api.repositories;

import com.jnb.episodecounter.api.models.ContentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContentRepository extends JpaRepository<ContentModel, UUID> {
}
