# Analise Hemograma

A Java 17 Spring Boot application for hemogram analysis, containerized with Docker and orchestrated using Docker Compose. The application connects to a PostgreSQL database.

## Project Structure

```
.
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       ├── java/
│       └── resources/
└── target/
```

## Prerequisites

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- Java 17 (for local development)
- Maven (for local builds)

## Running with Docker Compose

1. Build and start the application and database:

   ```sh
   docker-compose up --build
   ```

2. The application will be available at [http://localhost:8080](http://localhost:8080).

3. The PostgreSQL database will be accessible at `localhost:5433` (default user: `postgres`, password: `batata`).

4. Debugging is enabled on port `5005`.

## Configuration

- Application properties can be found in `src/main/resources/application.properties`.
- Database connection is configured via environment variables in [docker-compose.yml](docker-compose.yml).

## Useful Commands

- **Build the JAR locally:**
  ```sh
  ./mvnw clean package
  ```
- **Run tests:**
  ```sh
  ./mvnw test
  ```

## Dockerfile

The [Dockerfile](Dockerfile) builds the application image, exposes ports 8080 (HTTP) and 5005 (debug), and runs the Spring Boot JAR.

---

## Data for Postgre Test
INSERT INTO PACIENTE (CPF, NOME, SEXO) VALUES ('12345678900', 'João Silva', 'M');
INSERT INTO HEMOGRAMA (DATA_ANALISE, CPF_PACIENTE, CONTAGEM_LEUCOCITOS, NEUTROFILOS, LINFOCITOS, MONOCITOS, EOSINOFILOS, BASOFILOS, CONTAGEM_ERITROCITOS, HEMOGLOBINA, HEMATOCRITO, VOLUME_CORPUSCULAR_MEDIO, HEMOGLOBINA_CORPUSCULAR_MEDIA, CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA, AMPLITUDE_DIST_ERITROCITARIA, CONTAGEM_PLAQUETAS, VOLUME_PLAQUETARIO_MEDIO) VALUES ('2023-11-01', '12345678900', 6000, 55, 30, 10, 3, 2, 4.5, 14.0, 42.0, 90.0, 30.0, 33.0, 12.0, 250000, 10.0);
INSERT INTO HEMOGRAMA (DATA_ANALISE, CPF_PACIENTE, CONTAGEM_LEUCOCITOS, NEUTROFILOS, LINFOCITOS, MONOCITOS, EOSINOFILOS, BASOFILOS, CONTAGEM_ERITROCITOS, HEMOGLOBINA, HEMATOCRITO, VOLUME_CORPUSCULAR_MEDIO, HEMOGLOBINA_CORPUSCULAR_MEDIA, CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA, AMPLITUDE_DIST_ERITROCITARIA, CONTAGEM_PLAQUETAS, VOLUME_PLAQUETARIO_MEDIO) VALUES ('2023-11-02', '12345678900', 7000, 60, 25, 8, 4, 3, 4.7, 15.0, 45.0, 92.0, 32.0, 34.0, 13.0, 260000, 11.0);
INSERT INTO HEMOGRAMA (DATA_ANALISE, CPF_PACIENTE, CONTAGEM_LEUCOCITOS, NEUTROFILOS, LINFOCITOS, MONOCITOS, EOSINOFILOS, BASOFILOS, CONTAGEM_ERITROCITOS, HEMOGLOBINA, HEMATOCRITO, VOLUME_CORPUSCULAR_MEDIO, HEMOGLOBINA_CORPUSCULAR_MEDIA, CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA, AMPLITUDE_DIST_ERITROCITARIA, CONTAGEM_PLAQUETAS, VOLUME_PLAQUETARIO_MEDIO) VALUES ('2023-11-03', '12345678900', 6500, 58, 28, 9, 3, 2, 4.6, 14.5, 43.0, 91.0, 31.0, 33.5, 12.5, 255000, 10.5);
INSERT INTO HEMOGRAMA (DATA_ANALISE, CPF_PACIENTE, CONTAGEM_LEUCOCITOS, NEUTROFILOS, LINFOCITOS, MONOCITOS, EOSINOFILOS, BASOFILOS, CONTAGEM_ERITROCITOS, HEMOGLOBINA, HEMATOCRITO, VOLUME_CORPUSCULAR_MEDIO, HEMOGLOBINA_CORPUSCULAR_MEDIA, CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA, AMPLITUDE_DIST_ERITROCITARIA, CONTAGEM_PLAQUETAS, VOLUME_PLAQUETARIO_MEDIO) VALUES ('2023-11-04', '12345678900', 7200, 62, 26, 7, 4, 3, 4.8, -15.2, 46.0, 93.0, 32.5, 34.5, 13.5, 265000, 11.5);

INSERT INTO PACIENTE (CPF, NOME, SEXO) VALUES ('12345678997', 'Maria Abreu', 'F');
INSERT INTO HEMOGRAMA (DATA_ANALISE, CPF_PACIENTE, CONTAGEM_LEUCOCITOS, NEUTROFILOS, LINFOCITOS, MONOCITOS, EOSINOFILOS, BASOFILOS, CONTAGEM_ERITROCITOS, HEMOGLOBINA, HEMATOCRITO, VOLUME_CORPUSCULAR_MEDIO, HEMOGLOBINA_CORPUSCULAR_MEDIA, CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA, AMPLITUDE_DIST_ERITROCITARIA, CONTAGEM_PLAQUETAS, VOLUME_PLAQUETARIO_MEDIO) VALUES ('2023-10-15', '12345678997', 5800, 50, 35, 12, 2, 1, 4.4, 13.5, 40.0, 89.0, 29.0, 32.0, 11.5, 240000, 9.5);
INSERT INTO HEMOGRAMA (DATA_ANALISE, CPF_PACIENTE, CONTAGEM_LEUCOCITOS, NEUTROFILOS, LINFOCITOS, MONOCITOS, EOSINOFILOS, BASOFILOS, CONTAGEM_ERITROCITOS, HEMOGLOBINA, HEMATOCRITO, VOLUME_CORPUSCULAR_MEDIO, HEMOGLOBINA_CORPUSCULAR_MEDIA, CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA, AMPLITUDE_DIST_ERITROCITARIA, CONTAGEM_PLAQUETAS, VOLUME_PLAQUETARIO_MEDIO) VALUES ('2023-10-20', '12345678997', 6000, 52, 33, 11, 3, 1, 4.5, 14.0, 42.0, 90.0, 30.0, 33.0, 12.0, 250000, 10.0);
INSERT INTO HEMOGRAMA (DATA_ANALISE, CPF_PACIENTE, CONTAGEM_LEUCOCITOS, NEUTROFILOS, LINFOCITOS, MONOCITOS, EOSINOFILOS, BASOFILOS, CONTAGEM_ERITROCITOS, HEMOGLOBINA, HEMATOCRITO, VOLUME_CORPUSCULAR_MEDIO, HEMOGLOBINA_CORPUSCULAR_MEDIA, CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA, AMPLITUDE_DIST_ERITROCITARIA, CONTAGEM_PLAQUETAS, VOLUME_PLAQUETARIO_MEDIO) VALUES ('2023-10-25', '12345678997', 6200, 54, 31, 10, 3, 2, 4.6, 14.5, 43.0, 91.0, 31.0, 33.5, 12.5, 255000, 10.5);

## Exemplo do Json retornado para notificação (/AnaliseIndividualTodosUltimosPontosDeAtencao, para mais informações, olhar a classe ControllerAnaliseIndividual.java)
[{"descricao":"\nErro: Hemograma inválido encontrado na validação do paciente 12345678900","hipotese":"\nHemograma 2023-11-04\nInválido, Soma das porcentagens dos tipos de leucócitos diferente de 100%.\nInválido, Hemoglobina negativa.","dataCriacao":"2023-11-04","cpfPaciente":"12345678900","nomePaciente":"João Silva","hemogramas":[{"dataColeta":"2023-11-04","contagemLeucocitos":7200,"porcentagemNeutrofilos":62.00,"porcentagemLinfocitos":26.00,"porcentagemMonocitos":7.00,"porcentagemEosinofilos":4.00,"porcentagemBasofilos":3.00,"contagemEritrocitos":4.80,"hemoglobina":-15.20,"hematocrito":46.00,"vcm":93.00,"hcm":32.50,"chcm":34.50,"amplitudeDistribuicaoEritrocitaria":13.50,"vpm":11.50,"contagemPlaquetas":265000}]},{"descricao":"Pacitente: 12345678997 Maria Abreu F\nContagem de leucócitos normal - 6200 células/µL.\nPorcentagem de neutrófilos normal - 54.00%.\nPorcentagem de linfócitos normal - 31.00%.\nPorcentagem de basófilos normal - 2.00%.\nPorcentagem de monócitos normal - 10.00%.\nPorcentagem de eosinófilos normal - 3.00%.\nContagem de eritrócitos normal - 4.60 milhões/µL.\nHemoglobina normal - 14.50 g/dL.\nHematócrito normal - 43.00%.\nVCM normal - 91.00 fL.\nHCM normal - 31.00 pg.\nCHCM normal - 33.50 g/dL.\nVPM normal - 10.50 fL.\nADE normal - 12.50%.\nContagem de plaquetas normal - 255000 células/µL.\nContagem de leucócitos normal - 6000 células/µL.\nPorcentagem de neutrófilos normal - 52.00%.\nPorcentagem de linfócitos normal - 33.00%.\nPorcentagem de basófilos normal - 1.00%.\nPaciente com porcentagem de monócitos alta - 11.00%.\nPorcentagem de eosinófilos normal - 3.00%.\nContagem de eritrócitos normal - 4.50 milhões/µL.\nHemoglobina normal - 14.00 g/dL.\nHematócrito normal - 42.00%.\nVCM normal - 90.00 fL.\nHCM normal - 30.00 pg.\nCHCM normal - 33.00 g/dL.\nVPM normal - 10.00 fL.\nADE normal - 12.00%.\nContagem de plaquetas normal - 250000 células/µL.\nContagem de leucócitos normal - 5800 células/µL.\nPorcentagem de neutrófilos normal - 50.00%.\nPorcentagem de linfócitos normal - 35.00%.\nPorcentagem de basófilos normal - 1.00%.\nPaciente com porcentagem de monócitos alta - 12.00%.\nPorcentagem de eosinófilos normal - 2.00%.\nContagem de eritrócitos normal - 4.40 milhões/µL.\nHemoglobina normal - 13.50 g/dL.\nHematócrito normal - 40.00%.\nVCM normal - 89.00 fL.\nHCM normal - 29.00 pg.\nCHCM normal - 32.00 g/dL.\nVPM normal - 9.50 fL.\nPaciente com ADE baixa - 11.50%.\nContagem de plaquetas normal - 240000 células/µL.","hipotese":"Sugestões:\n\nHemograma 2023-10-25\n\nHemograma 2023-10-20\nMonocitose\n\nHemograma 2023-10-15\nMonocitose\nAmplitude de Distribuição Eritrocitária baixa","dataCriacao":"2023-10-25","cpfPaciente":"12345678997","nomePaciente":"Maria Abreu","hemogramas":[{"dataColeta":"2023-10-15","contagemLeucocitos":5800,"porcentagemNeutrofilos":50.00,"porcentagemLinfocitos":35.00,"porcentagemMonocitos":12.00,"porcentagemEosinofilos":2.00,"porcentagemBasofilos":1.00,"contagemEritrocitos":4.40,"hemoglobina":13.50,"hematocrito":40.00,"vcm":89.00,"hcm":29.00,"chcm":32.00,"amplitudeDistribuicaoEritrocitaria":11.50,"vpm":9.50,"contagemPlaquetas":240000},{"dataColeta":"2023-10-20","contagemLeucocitos":6000,"porcentagemNeutrofilos":52.00,"porcentagemLinfocitos":33.00,"porcentagemMonocitos":11.00,"porcentagemEosinofilos":3.00,"porcentagemBasofilos":1.00,"contagemEritrocitos":4.50,"hemoglobina":14.00,"hematocrito":42.00,"vcm":90.00,"hcm":30.00,"chcm":33.00,"amplitudeDistribuicaoEritrocitaria":12.00,"vpm":10.00,"contagemPlaquetas":250000},{"dataColeta":"2023-10-25","contagemLeucocitos":6200,"porcentagemNeutrofilos":54.00,"porcentagemLinfocitos":31.00,"porcentagemMonocitos":10.00,"porcentagemEosinofilos":3.00,"porcentagemBasofilos":2.00,"contagemEritrocitos":4.60,"hemoglobina":14.50,"hematocrito":43.00,"vcm":91.00,"hcm":31.00,"chcm":33.50,"amplitudeDistribuicaoEritrocitaria":12.50,"vpm":10.50,"contagemPlaquetas":255000}]}]



