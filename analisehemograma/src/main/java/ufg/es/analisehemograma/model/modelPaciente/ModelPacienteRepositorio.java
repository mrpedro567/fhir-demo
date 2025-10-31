package ufg.es.analisehemograma.model.modelPaciente;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModelPacienteRepositorio extends JpaRepository<ModelPaciente, String> {

    @Query("SELECT p FROM ModelPaciente p JOIN FETCH p.pontosDeAtencao")
    public List<ModelPaciente> findAllComPontosDeAtencao();

    @Query("SELECT p FROM ModelPaciente p JOIN FETCH p.hemogramas h ORDER BY h.dataColeta DESC")
    public List<ModelPaciente> findHemogramasRelevantes(Pageable pageable);

}
