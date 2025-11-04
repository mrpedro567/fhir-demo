package ufg.es.analisehemograma.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ufg.es.analisehemograma.model.PontoDeAtencao.ModelPontoDeAtencao;

public interface ModelPontoDeAtencaoRepositorio extends JpaRepository<ModelPontoDeAtencao, Long> {

    @Query("SELECT p FROM ModelPontoDeAtencao p WHERE p.paciente.cpf = :cpfPaciente ORDER BY p.dataCriacao DESC")
    public Optional<ModelPontoDeAtencao> findByLastPontoDeAtencao(@Param("cpfPaciente") String cpfPaciente);

    @Query(value = "SELECT * FROM ( " +
            "  SELECT poa.*, " +
            "  ROW_NUMBER() OVER(PARTITION BY poa.CPF_PACIENTE ORDER BY poa.DATA_CRIACAO DESC) as rn " +
            "  FROM PONTOS_DE_ATENCAO poa " +
            ") AS RankedPOAs " +
            "WHERE rn = 1", nativeQuery = true)
    public ArrayList<ModelPontoDeAtencao> findAllLastPontoDeAtencao();

}