package com.barbearia.aplicacao.repository;

import com.barbearia.aplicacao.model.entity.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, UUID> {
}
