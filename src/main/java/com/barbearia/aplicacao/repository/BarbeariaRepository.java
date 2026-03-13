package com.barbearia.aplicacao.repository;

import com.barbearia.aplicacao.model.entity.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BarbeariaRepository extends JpaRepository<Barbearia, UUID> {
}
