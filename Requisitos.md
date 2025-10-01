# Sistema de Vigilância em Saúde: Requisitos e Arquitetura

Este documento detalha as especificações funcionais, não funcionais e arquiteturais para o Sistema de Vigilância em Saúde.

## 1. Introdução

### 1.1. Propósito

Este documento serve como o guia principal para o desenvolvimento, teste e validação do Sistema de Vigilância em Saúde. Ele define o escopo, os requisitos e a arquitetura técnica do sistema.

### 1.2. Escopo do Projeto

O projeto consiste em desenvolver um sistema de software para processar automaticamente hemogramas provenientes de um servidor FHIR. O sistema identificará desvios individuais e coletivos relevantes e notificará os gestores de vigilância em saúde por meio de um aplicativo móvel.

### 1.3. Prazos e Marcos do Projeto

*Marco 1: Demonstração do receptor FHIR.
*Marco 2: Demonstração do módulo de análise individual.
*Marco 3: Demonstração da base de dados consolidada.
*Marco 4: Demonstração do módulo de análise coletiva.

-   Entrega Final: Apresentação do sistema completo, incluindo API, App Móvel e documentação final.

## 2. Requisitos Funcionais

### 2.1. Recepção e Processamento de Hemogramas

-   RF01: O sistema deve receber notificações de novos hemogramas via mecanismo de subscription de um servidor HL7 FHIR v4.

-   RF02: O sistema deve processar os recursos FHIR recebidos (especificamente Observation) para extrair os parâmetros hematológicos relevantes.

### 2.2. Análise Individual

-   RF03: O sistema atuará como um "copiloto" para os profissionais de saúde, realizando uma análise individual de cada hemograma.

-   RF04: Para cada parâmetro, o sistema deve verificar se o valor está dentro das faixas de referência predefinidas.

-   RF05: O sistema deve analisar combinações de valores alterados para sugerir "Pontos de Atenção", que representam o estado de saúde consolidado do paciente.

-   RF06: O sistema deve manter e consultar o histórico de exames de um paciente (identificado por CPF) para identificar tendências e condições persistentes que suportem os Pontos de Atenção.

#### Tabela de Referência para Hemograma Adulto

Estes são os valores de referência para um hemograma completo em adultos. A interpretação deve ser sempre realizada por um profissional de saúde qualificado.

| Parâmetro                      | Sub-parâmetro        | Faixa de Referência (Adulto)                 | Unidades    |
| ------------------------------ | -------------------- | -------------------------------------------- | ----------- |
| LEUCOGRAMA                     |                      |                                              |             |
| Contagem de Leucócitos (WBC)   |                      | 4.500 - 11.000                               | células/mcL |
|                                | Neutrófilos          | 40% - 75%                                    | %           |
|                                | Linfócitos           | 20% - 45%                                    | %           |
|                                | Monócitos            | 2% - 10%                                     | %           |
|                                | Eosinófilos          | 1% - 6%                                      | %           |
|                                | Basófilos            | 0% - 2%                                      | %           |
| ERITROGRAMA                    |                      |                                              |             |
| Contagem de Eritrócitos (RBC)  |                      | Homem: 4.7 - 6.1 / Mulher: 4.2 - 5.4         | milhões/mcL |
| Hemoglobina (Hgb)              |                      | Homem: 13.8 - 17.2 / Mulher: 12.1 - 15.1     | gramas/dL   |
| Hematócrito (Hct)              |                      | Homem: 40.7% - 50.3% / Mulher: 36.1% - 44.3% | %           |
| Volume Corpuscular Médio (VCM) | Índice Eritrocitário | 80 - 100                                     | femtolitros |
| Hgb Corpuscular Média (HCM)    | Índice Eritrocitário | 27 - 31                                      | picogramas  |
| Conc. Hgb Corp. Média (CHCM)   | Índice Eritrocitário | 32 - 36                                      | gramas/dL   |
| Amplitude de Dist. (RDW)       | Índice Eritrocitário | 12% - 15%                                    | %           |
| PLAQUETOGRAMA                  |                      |                                              |             |
| Contagem de Plaquetas (PLT)    | -                    | 150.000 - 450.000                            | células/mcL |
| Volume Plaquetário Médio (VPM) | -                    | 7.5 - 11.5                                   | femtolitros |

#### Cenários para Pontos de Atenção

As seguintes combinações devem ser sinalizadas como "Pontos de Atenção". Estas são sugestões para investigação, não diagnósticos.

-   Ponto de Atenção 1: Sugestivo de Anemia Microcítica

    -   Combinação: Hemoglobina (Hgb) ou Hematócrito (Hct) baixos E Volume Corpuscular Médio (VCM) baixo (< 80 fL).

    -   Principal Hipótese: Anemia ferropriva.

-   Ponto de Atenção 2: Sugestivo de Anemia Macrocítica

    -   Combinação: Hemoglobina (Hgb) ou Hematócrito (Hct) baixos E Volume Corpuscular Médio (VCM) alto (> 100 fL).

    -   Principal Hipótese: Anemia por deficiência de Vitamina B12 ou Folato.

-   Ponto de Atenção 3: Sugestivo de Processo Infeccioso Bacteriano

    -   Combinação: Contagem de Leucócitos (WBC) alta (> 11.000). Opcionalmente, aumento de Neutrófilos.

    -   Principal Hipótese: Infecção bacteriana aguda.

-   Ponto de Atenção 4: Risco de Sangramento (Plaquetopenia)

    -   Combinação: Contagem de Plaquetas (PLT) baixa (< 150.000).

    -   Principal Hipótese: Trombocitopenia, relevante para vigilância de doenças como Dengue, Zika e Chikungunya.

### 2.3. Análise Coletiva (Populacional)

-   RF07: O sistema deve realizar análises em nível populacional na base de dados consolidada para agregar valor à vigilância em saúde.

-   RF08: A análise deve utilizar janelas de tempo deslizantes (ex: últimos 7 dias) para focar em dados recentes.

-   RF09: O sistema deve calcular estatísticas básicas (média, desvio padrão, tendência) para os parâmetros dentro da janela de tempo.

-   RF10: O sistema deve detectar padrões coletivos anômalos, como um aumento significativo de leucócitos em uma área geográfica específica.

### 2.4. Sistema de Alertas e App Móvel

-   RF13: O sistema deve emitir alertas para os gestores de saúde quando padrões individuais ou coletivos suspeitos forem detectados.

-   RF14: Os alertas devem ser enviados via notificações push para o aplicativo móvel.

-   RF16: Uma aplicação móvel (inicialmente para Android) deve estar disponível para os gestores de saúde.

-   RF17: O aplicativo deve permitir a visualização e consulta dos detalhes dos alertas recebidos.

## 3. Requisitos Não-Funcionais

-   RNF01 (Interoperabilidade): A comunicação deve utilizar o padrão HL7 FHIR R4.

-   RNF02 (Desempenho): O sistema deve ser escalável e projetado para lidar com um alto volume de exames (ex: 65.000 hemogramas) de forma ágil.

-   RNF03 (Segurança): Toda a comunicação entre os componentes deve usar HTTPS com autenticação mTLS.

-   RNF04 (Privacidade): O sistema não deve armazenar informações de identificação pessoal, garantindo a anonimização dos dados para análises coletivas.

-   RNF05 (Usabilidade): O aplicativo móvel deve ter uma interface clara e intuitiva para seus usuários-alvo.

-   RNF06 (Confiabilidade): O mecanismo de recebimento de dados deve ser robusto para evitar a perda de notificações do servidor FHIR.

## 4. Modelo de Dados

O modelo de dados está estruturado em torno de três entidades principais: PACIENTE, HEMOGRAMA e PONTOS_DE_ATENCAO.

### 4.1. Relacionamentos

-   PACIENTE 1 : N HEMOGRAMA: Um paciente possui um histórico de um ou mais hemogramas.

-   PACIENTE 1 : N PONTOS_DE_ATENCAO: Um paciente pode ter zero ou mais "Pontos de Atenção" ativos.

-   PONTOS_DE_ATENCAO N : M HEMOGRAMA: Um ponto de atenção pode ser justificado por múltiplos hemogramas, e um único hemograma pode fornecer evidências para múltiplos pontos de atenção. Este relacionamento é implementado por meio de uma tabela associativa.

### 4.2. Script de Criação das Tabelas (PostgreSQL)

CREATE TABLE PACIENTE (
CPF VARCHAR(11) NOT NULL,
NOME VARCHAR(255) NOT NULL,
SEXO VARCHAR(15) NOT NULL,
PRIMARY KEY (CPF)
);

CREATE TABLE HEMOGRAMA (
ID SERIAL,
DATA_ANALISE DATE,
CPF_PACIENTE VARCHAR(11),
CONTAGEM_LEUCOCITOS INTEGER,
NEUTROFILOS DECIMAL(5, 2),
LINFOCITOS DECIMAL(5, 2),
MONOCITOS DECIMAL(5, 2),
EOSINOFILOS DECIMAL(5, 2),
BASOFILOS DECIMAL(5, 2),
CONTAGEM_ERITROCITOS DECIMAL(5, 2),
HEMOGLOBINA DECIMAL(5, 2),
HEMATOCRITO DECIMAL(5, 2),
VOLUME_CORPUSCULAR_MEDIO DECIMAL(5, 2),
HEMOGLOBINA_CORPUSCULAR_MEDIA DECIMAL(5, 2),
CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA DECIMAL(5, 2),
AMPLITUDE_DIST_ERITROCITARIA DECIMAL(5, 2),
CONTAGEM_PLAQUETAS INTEGER,
VOLUME_PLAQUETARIO_MEDIO DECIMAL(5, 2),
PRIMARY KEY (ID),
CONSTRAINT FK_HEMOGRAMA_PACIENTE FOREIGN KEY (CPF_PACIENTE) REFERENCES PACIENTE(CPF)
);

CREATE TABLE PONTOS_DE_ATENCAO (
ID SERIAL NOT NULL,
DESCRICAO VARCHAR(255),
HIPOTESE TEXT,
CPF_PACIENTE VARCHAR(11),
PRIMARY KEY (ID),
CONSTRAINT FK_PATENCAO_PACIENTE FOREIGN KEY (CPF_PACIENTE) REFERENCES PACIENTE(CPF)
);

CREATE TABLE PATENCAO_HEMOGRAMA (
ID SERIAL NOT NULL,
ID_PONTO_ATENCAO INTEGER,
ID_HEMOGRAMA INTEGER,
PRIMARY KEY (ID),
CONSTRAINT FK_PAH_PATENCAO FOREIGN KEY (ID_PONTO_ATENCAO) REFERENCES PONTOS_DE_ATENCAO(ID),
CONSTRAINT FK_PAH_HEMOGRAMA FOREIGN KEY (ID_HEMOGRAMA) REFERENCES HEMOGRAMA(ID)
);

## 5. Arquitetura de Software

O sistema utiliza uma arquitetura distribuída e orientada a eventos para atender aos requisitos de escalabilidade e desacoplamento, seguindo o modelo C4 para documentação.

### 5.1. Estilos e Padrões Arquiteturais

Arquitetura em Camadas (Layered Architecture):

-   Camada de Apresentação: Aplicativo Móvel.

-   Camada de Aplicação: API Backend.

-   Camada de Mensageria: Broker Kafka.

-   Camada de Persistência: Banco de Dados PostgreSQL.

-   MVC (Model-View-Controller): Utilizado para a interação entre o aplicativo móvel e o servidor.

-   Broker: O Apache Kafka é usado para desacoplar o recebimento de notificações FHIR do seu processamento, aumentando a resiliência.

-   Pipes & Filters: O fluxo de processamento do hemograma segue este padrão:

    -   Pipe: O tópico do Kafka que transporta os dados.

    -   Filters: Componentes sequenciais do backend (Parser, Analyzer, Persister, Alerter).

## 5.2. Diagramas de Arquitetura (C4)

-   Nível 1 (Contexto): Ilustra como o sistema interage com usuários e sistemas externos.

-   Nível 2 (Contêineres): Detalha os principais blocos de construção (aplicações, bancos de dados) do sistema. O diagrama está disponível no seguinte link: Diagrama de Contêineres.

## 5.3. Tecnologias Propostas

-   Backend: Java com Spring Boot.

-   Mensageria: Apache Kafka.

-   Banco de Dados: PostgreSQL.

-   Cliente FHIR: Biblioteca HAPI-FHIR.

-   Notificações Push: Firebase Cloud Messaging (FCM).

-   App Móvel: React Native.
