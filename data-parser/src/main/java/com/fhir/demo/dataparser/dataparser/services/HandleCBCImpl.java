package com.fhir.demo.dataparser.dataparser.services;


import java.math.BigDecimal;
import java.util.List;

import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.springframework.stereotype.Service;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;

import com.fhir.demo.dataparser.dataparser.model.ModelHemograma;
import com.fhir.demo.dataparser.dataparser.model.ModelPaciente;
import com.fhir.demo.dataparser.dataparser.model.dto.HemogramaDTO;
import com.fhir.demo.dataparser.dataparser.repository.ModelHemogramaRepositorio;
import com.fhir.demo.dataparser.dataparser.repository.ModelPacienteRepositorio;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

@Service
public class HandleCBCImpl implements HandleCBC {
    private FhirContext ctx;
    private IParser parser;
    private ModelPacienteRepositorio pacienteRepository;
    private ModelHemogramaRepositorio hemogramaRepository;

    public HandleCBCImpl(
        ModelPacienteRepositorio pacienteRepository, 
        ModelHemogramaRepositorio hemogramaRepository
    ) {
        ctx = FhirContext.forR4();
        parser = ctx.newJsonParser();
        this.pacienteRepository = pacienteRepository;
        this.hemogramaRepository = hemogramaRepository;
    }

    @Override
    public void processCBC(String cbcMessage) throws Exception {
        Bundle bundle = this.parser.parseResource(Bundle.class, cbcMessage);

        // Use resource map from bundle to process observations
        HemogramaDTO hemograma = new HemogramaDTO();
        ModelHemograma modelHemograma = new ModelHemograma();

        for (BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.getResource() instanceof Observation) {
                Observation observation = (Observation) entry.getResource();
                List<Resource> resources = observation.getContained();
                for (Resource res : resources) {
                    if(res.getResourceType().compareTo(ResourceType.Patient) == 0) {
                        // Salvar dados do patient 
                        Patient patient = (Patient) res;
                        List<ModelPaciente> patientList = this.pacienteRepository.findByCpf(patient.getIdentifier().get(0).getValue());

                        if(patientList.isEmpty()){
                            ModelPaciente modelPaciente = new ModelPaciente();
                            modelPaciente.setCpf( patient.getIdentifier().get(0).getValue() );
                            modelPaciente.setNome( patient.getNameFirstRep().getNameAsSingleString() );
                            modelPaciente.setSexo( patient.getGender().toCode() );
                            this.pacienteRepository.save(modelPaciente);
                            modelHemograma.setPaciente(modelPaciente);

                        }
                        else{
                            modelHemograma.setPaciente( patientList.get(0) );
                        }
                        // patient.getIdentifier().get(0).getValue();
                        // patient.getBirthDate();
                        // patient.getGender().toCode();
                        // patient.getAddress();
                    }
                }

                String loincCode = observation.getCode().getCodingFirstRep().getCode();
                if(loincCode == null || observation.getValueQuantity() == null || observation.getValueQuantity().getValue() == null){
                    continue;
                }
                BigDecimal value = observation.getValueQuantity().getValue();
                switch (loincCode) {
                    case "6690-2":
                        hemograma.setContagemLeucocitos(value);
                        break;
                    case "739-3":
                        hemograma.setMetamielocito(value);
                        break;
                    case "763-3":
                        hemograma.setBastonete(value);
                        break;
                    case "768-2":
                        hemograma.setSegmentado(value);
                        break;
                    case "731-0":
                        hemograma.setPorcentagemLinfocitos(value);
                        break;
                    case "742-7":
                        hemograma.setPorcentagemMonocitos(value);
                        break;
                    case "711-2":
                        hemograma.setPorcentagemEosinofilos(value);
                        break;
                    case "704-7":
                        hemograma.setPorcentagemBasofilos(value);
                        break;
                    case "789-8":
                        hemograma.setContagemEritrocitos(value);
                        break;
                    case "718-7":
                        hemograma.setHemoglobina(value);
                        break;
                    case "4544-3":
                        hemograma.setHematocrito(value);
                        break;
                    case "787-2":
                        hemograma.setVcm(value);
                        break;
                    case "785-6":
                        hemograma.setHcm(value);
                        break;
                    case "786-4":
                        hemograma.setChcm(value);
                        break;
                    case "788-0":
                        hemograma.setAmplitudeDistribuicaoEritrocitaria(value);
                        break;
                    case "32623-1":
                        hemograma.setVpm(value);
                        break;
                    case "777-3":
                        hemograma.setContagemPlaquetas(value.intValue());
                        break;
                    default:
                        // Unknown LOINC code, ignore or log
                        break;
                }
            }
        }

        modelHemograma.fromDto(hemograma);
        this.hemogramaRepository.save(modelHemograma);
    }

}
