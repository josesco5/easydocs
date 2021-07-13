package com.avilapps.easydocs.data.gateway;

import com.avilapps.easydocs.data.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentGateway extends JpaRepository<DocumentEntity, Integer> {
}
