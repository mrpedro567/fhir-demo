# Trabalho Final 
## Grupo

| Nome        | Matricula |
| ---         |---        |
| Pedro Silva | 201904250 | 
| Halleffy    |           | 
| Giovanna    |           | 
| Joao Pedro  | 202204197 | 

## Comandos 

### Iniciar todos os serviços
```bash
docker-compose up -d
```

### Parar todos os serviços
```bash
docker-compose down
```

### Iniciar serviços específicos
```bash
# Iniciar apenas o banco de dados
docker-compose up -d db

# Iniciar Zookeeper e Kafka
docker-compose up -d zookeeper kafka

# Iniciar servidor FHIR HAPI
docker-compose up -d fhir
```

### Portas dos serviços
| Serviço | Porta Host | Porta Container | Descrição |
|---------|------------|-----------------|-----------|
| Zookeeper | 22181 | 2181 | Coordenação do Kafka |
| Kafka | 29092 | 29092 | Broker de mensagens |
| FHIR HAPI | 8080 | 8080 | Servidor FHIR |
| PostgreSQL | 5432 | 5432 | Banco de dados  |

### Logs dos serviços
```bash
# Ver logs de todos os serviços
docker-compose logs -f

# Ver logs de um serviço específico
docker-compose logs -f fhir
docker-compose logs -f kafka
docker-compose logs -f db
```

O Docker compose está organizado da seguinte forma 

## Arquitetura

### Servidor FHIR HAPI

### Fila de mensagens 

### Servidor de processsamento (Backend)

ATENÇÃO: Antes de rodar é necessário criar um database dentro do postgres para ele com o nome processor

#### Comandos Spring Boot

##### Executar a aplicação
```bash
# Usando Maven
./mvnw spring-boot:run

# Ou se o Maven estiver instalado globalmente
mvn spring-boot:run
```

##### Compilar o projeto
```bash
# Usando Maven
./mvnw clean compile

# Ou
mvn clean compile
```

##### Executar testes
```bash
# Usando Maven
./mvnw test

# Ou
mvn test
```

##### Gerar o JAR da aplicação
```bash
# Usando Maven
./mvnw clean package

# Executar o JAR gerado
java -jar target/processor-*.jar
```

##### Executar com perfil específico
```bash
# Perfil de desenvolvimento
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Perfil de produção
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

## Análises

### Individual 

### Coletiva
