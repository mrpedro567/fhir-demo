package ufg.es.analisehemograma.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ufg.es.analisehemograma.model.AnaliseColetiva.ResultadoColetivoDTO;
import ufg.es.analisehemograma.model.Paciente.ModelPaciente;

public interface ModelPacienteRepositorio extends JpaRepository<ModelPaciente, String> {

    @Query("SELECT p FROM ModelPaciente p JOIN FETCH p.pontosDeAtencao")
    public List<ModelPaciente> findAllComPontosDeAtencao();

    @Query("SELECT p FROM ModelPaciente p JOIN FETCH p.hemogramas h ORDER BY h.dataColeta DESC")
    public List<ModelPaciente> findHemogramasRelevantes(Pageable pageable);
    
    @Query("SELECT new ufg.es.analisehemograma.model.AnaliseColetiva.ResultadoColetivoDTO( " +
           "  m.nome, COUNT(p), m.populacao1015) " +
           "FROM ModelPaciente p JOIN p.municipio m " +
           "WHERE p.possivelFumante = true " +
           "  AND p.dataNascimento BETWEEN :dataMaior AND :dataMenor " +
           "GROUP BY m.nome, m.populacao1015")
    List<ResultadoColetivoDTO> contarFumantesPorMunicipioFaixaEtaria(
        @Param("dataMaior") Date dataMaior,
        @Param("dataMenor") Date dataMenor
    );

}
