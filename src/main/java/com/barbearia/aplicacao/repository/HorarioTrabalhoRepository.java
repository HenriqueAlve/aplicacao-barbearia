package com.barbearia.aplicacao.repository;

import com.barbearia.aplicacao.model.entity.HorarioTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HorarioTrabalhoRepository extends JpaRepository<HorarioTrabalho, UUID> {
    Optional<HorarioTrabalho> findByBarbeiroIdAndDiaSemana(UUID barbeiroId, DayOfWeek diaSemana);
}
