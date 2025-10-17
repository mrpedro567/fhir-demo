package com.fhir.demo.ubiqua.ubiqua.services;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.parser.IParser;

public class HandleCBCImpl implements HandleCBC {
    private FhirContext ctx;
    private IParser parser;


    public HandleCBCImpl() {
        ctx = FhirContext.forDstu2();
        parser = ctx.newJsonParser();
    }

    @Override
    public void processCBC(String cbcMessage) throws Exception {
        Bundle bundle = this.parser.parseResource(Bundle.class, cbcMessage);

        bundle.getEntry().forEach(entry -> {
            Observation obs = (Observation) entry.getResource();
            obs.getCode().getCoding().forEach(coding -> {
                System.out.println("Observation coding: " + coding.getCode());
            });
        });
    }
}
