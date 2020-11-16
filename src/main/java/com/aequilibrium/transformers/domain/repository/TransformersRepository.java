package com.aequilibrium.transformers.domain.repository;

import com.aequilibrium.transformers.domain.model.Transformers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransformersRepository extends JpaRepository<Transformers,Long> {
    Transformers findByName(String name);
}
