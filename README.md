# Trabalho Final 

# Tópicos em engenharia de software

Os documentos referentes a análises e tarefas realizadas com o apoio de ferramentas de Inteligência Artificial se encontram na pasta docs

## Grupo Software para computação ubíqua

| Nome          | Matricula |
| ---           |---        |
| Pedro Silva   | 201904250 | 
| Halleffy      | 201802766 | 
| Giovanna Lyssa| 202004754 | 
| Joao Pedro    | 202204197 | 

## Projeto

Repositório para o projeto de análise de hemogramas utilizando o padrão FHIR.

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

# Iniciar Kafka Connector
docker-compose up -d kafka-connector

# Iniciar servidor FHIR HAPI
docker-compose up -d fhir
```

### Portas dos serviços
| Serviço | Porta Host | Porta Container | Descrição |
|---------|------------|-----------------|-----------|
| Zookeeper | 22181 | 2181 | Coordenação do Kafka |
| Kafka | 29092 | 29092 | Broker de mensagens (Host) |
| Kafka | 9092 | 9092 | Broker de mensagens (Inter-container) |
| Kafka Connector | 9099 | 9099 | Conector personalizado do Kafka |
| FHIR HAPI | 8080 | 8080 | Servidor FHIR |
| PostgreSQL | 5432 | 5432 | Banco de dados |

### Links e conectividade entre containers
| Container | Links/Depends On | Descrição |
|-----------|------------------|-----------|
| kafka | zookeeper | Kafka depende do Zookeeper |
| kafka-connector | kafka, zookeeper | Conector acessa Kafka e Zookeeper |
| fhir | db, kafka-connector | FHIR conecta ao banco e ao conector Kafka |
| db | - | Banco independente |

### Logs dos serviços
```bash
# Ver logs de todos os serviços
docker-compose logs -f

# Ver logs de um serviço específico
docker-compose logs -f fhir
docker-compose logs -f kafka
docker-compose logs -f kafka-connector
docker-compose logs -f db
```

O Docker compose está organizado da seguinte forma 

### Scripts disponíveis

#### Criar subscrição FHIR
```bash
# Executar script para criar subscrição para LOINC 58410-2
./create_subscription.sh
```

Este script cria uma subscrição no servidor FHIR para observações com código LOINC 58410-2 (CBC panel - Blood by Automated count). 

## Arquitetura

### Servidor FHIR HAPI
- **Porta**: 8080
- **Função**: Servidor FHIR R4 para armazenamento e gerenciamento de recursos de saúde
- **Conectividade**: Conectado ao banco PostgreSQL e ao Kafka Connector

### Fila de mensagens (Kafka)
- **Portas**: 
  - 29092 (acesso externo/host)
  - 9092 (comunicação inter-container)
- **Função**: Sistema de mensageria distribuída para processamento de eventos
- **Dependências**: Zookeeper para coordenação

### Kafka Connector
- **Porta**: 9099
- **Função**: Conector personalizado para integração entre FHIR e Kafka
- **Conectividade**: Conectado ao Kafka e Zookeeper
- **Localização**: `./kafka-connector/` (aplicação Node.js)

### Zookeeper
- **Porta**: 22181
- **Função**: Coordenação e configuração do cluster Kafka

### Banco de Dados (PostgreSQL)
- **Porta**: 5432
- **Função**: Armazenamento persistente para o servidor FHIR
- **Credenciais**: admin/admin
- **Database**: hapi 

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
