package com.barbearia.aplicacao.repository;

import com.barbearia.aplicacao.model.entity.Agendamento;
import com.barbearia.aplicacao.model.entity.BloqueioAgenda;
import com.barbearia.aplicacao.model.entity.HorarioTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {


    @Query("""
    SELECT COUNT(a) > 0 
    FROM Agendamento a 
    WHERE a.barbeiro.id = :barbeiroId 
      AND a.statusAgendamento != com.barbearia.aplicacao.model.enums.StatusAgendamento.CANCELADO
      AND a.dataHoraInicio < :fim 
      AND a.dataHoraFim > :inicio
""")
    boolean existeConflitoDeHorario(@Param("barbeiroId") UUID barbeiroId,
                                    @Param("inicio") LocalDateTime inicio,
                                    @Param("fim") LocalDateTime fim);

    @Query("""
SELECT a FROM Agendamento a
WHERE a.barbeiro.id = :barbeiroId
AND FUNCTION('DATE', a.dataHoraInicio) = :data
AND a.statusAgendamento != com.barbearia.aplicacao.model.enums.StatusAgendamento.CANCELADO
""")
    List<Agendamento> findAgendamentosDoBarbeiroNaData(
            @Param("barbeiroId") UUID barbeiroId,
            @Param("data") LocalDate data
    );


}
