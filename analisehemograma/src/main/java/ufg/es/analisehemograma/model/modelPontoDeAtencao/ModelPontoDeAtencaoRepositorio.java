package ufg.es.analisehemograma.model.modelPontoDeAtencao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModelPontoDeAtencaoRepositorio extends JpaRepository<ModelPontoDeAtencao, Long> {

    @Query("SELECT p FROM ModelPontoDeAtencao p WHERE p.paciente.cpf = :cpfPaciente ORDER BY p.dataCriacao DESC")
    public Optional<ModelPontoDeAtencao> findByLastPontoDeAtencao(@Param("cpfPaciente") String cpfPaciente);

}