package com.fhir.demo.dataparser.dataparser.services;

import org.springframework.stereotype.Service;

@Service
public interface HandleCBC {
    void processCBC(String cbcMessage) throws Exception;
}
