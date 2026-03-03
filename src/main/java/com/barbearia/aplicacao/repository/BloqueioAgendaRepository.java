package com.barbearia.aplicacao.repository;

import com.barbearia.aplicacao.model.entity.BloqueioAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BloqueioAgendaRepository extends JpaRepository<BloqueioAgenda, UUID> {

    @Query("SELECT b FROM BloqueioAgenda b " +
            "WHERE b.barbeiro.id = :barbeiroId " +
            "AND b.inicio >= :inicio " +
            "AND b.fim <= :fim")
    List<BloqueioAgenda> findBloqueiosDoBarbeiroNaData(
            @Param("barbeiroId") UUID barbeiroId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

}
