# Missão 1 

Nessa fase, o principal aproveitamento das ferramentas de IA se deram na avaliação, ideação e completude das ideias do grupo. 
Entre elas, a busca por pontos que podem ser melhorados, assim como uso para brainstorm

Chats
https://claude.ai/share/3cb6f60c-5a22-4d26-b5c1-987723c9944e
https://github.com/copilot/share/4a1c1000-0024-84b0-9940-8a40002f486e

# Missão 2

Nesta segunda fase, o desenvolvimento foi o foco 

Primeiro foi entender como funciona o padrão FHIR e o HAPI FHIR. 
Para isso primeiro foi entender o que e como é utilizado o padrão fhir.

### Entendimento e contexto

"Considering a HAPI FHIRI server, how can I specify a complete blood exam on a subscription"

Retornou o uso do LOINC 58410-2

### Geração do Script de subscription 
"Create a shell script that subscribes to a HAPI FHIR server located on localhost:8080 using curl, observing LOINC 58410-2 and using localhost:9099 as a destination for those" 

Gerou o seguinte arquivo .sh

```
#!/bin/bash

# Script to create a FHIR Subscription for LOINC 58410-2
# This subscribes to CBC panel - Blood by Automated count observations

FHIR_BASE_URL="http://localhost:8080/fhir"
SUBSCRIPTION_ENDPOINT="http://kafka:9099/"  # Kafka endpoint

echo "Creating FHIR Subscription for LOINC 58410-2..."

# Create the subscription JSON payload
SUBSCRIPTION_JSON='{
  "resourceType": "Subscription",
  "status": "active",
  "reason": "Subscribe to CBC panel observations (LOINC 58410-2)",
  "criteria": "Observation?code=58410-2",
  "channel": {
    "type": "rest-hook",
    "endpoint": "'$SUBSCRIPTION_ENDPOINT'",
    "payload": "application/json"
  }
}'

curl -X POST "$FHIR_BASE_URL/Subscription" \
  -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d "$SUBSCRIPTION_JSON" \
  
```

### Criação do script de importação de dados IBGE 

"From here, create a script that reads ./tabela9514.xlsx and map this data into an array of objects. Each object must: map column "Município" into name, and the sum of columns E and F into porc"

Criou o seguinte script python

```
# Read the Excel file
df = pd.read_excel('./tabela9514.xlsx')

# Initialize an empty list to store the objects
data = []

# Process each row in the dataframe
for index, row in df.iterrows():
    if index <= 7:  # Skip the first 7 rows
        continue
    # Extract the municipality name
    name = row['Município']
    
    # Sum columns E and F (assuming these are the 5th and 6th columns in Excel)
    # Excel columns are 1-indexed, but pandas is 0-indexed
    col_e_value = row.iloc[4]  # Column E (index 4)
    col_f_value = row.iloc[5]  # Column F (index 5)
    porc = col_e_value + col_f_value
    
    # Create object and append to the list
    data.append({
        'name': name,
        'porc': porc
    })

# Insert data into the database
for item in data:
    insert_values('municipalities', ['name', 'porc'], [item['name'], item['porc']])

print(f"Successfully processed and imported {len(data)} municipalities.")
```

Problemas: 
- O parse deve ignorar as linhas headers - Neste caso, as primeiras 7 linhas 
- O insert_values sendo chamado desse jeito é ineficiente e não foi pedido para fazer isso, apenas o map. 

### Tarefas menores de desenvolvimento 

- Conversão dos nomes das colunas no script de criação do banco para o padrão snake_case

### Desenho da arquitetura do sistema a nivel de componente 
"Create a plant UML document containing the following elements: One database, one component called connector, one component called fhir-server, one component called kafka, one called cbc-processor, and one last component called data-parser. The fhir-server connects with the conector component. The connector component communicates with kafka. From the kafka, the data-parser gets the data and saves it into the database component. The cbc-processot gets data from the database and performs calculations, saving the result into the database"

Gerou o arquivo /architecture.puml 
```
@startuml
title System Architecture - FHIR / Kafka / Processing

' Components
component "FHIR Server" as fhir
component "Connector" as connector
component "Kafka" as kafka
component "Data Parser" as parser
component "CBC Processor" as cbc

database "Database" as DB

' Relationships
fhir -> connector : "Subscription notifications / REST"
connector -> kafka : "Publish messages (FHIR events)"
kafka -> parser : "Consume messages"
parser -> DB : "Save parsed data"
DB --> cbc : "Provide raw data (read)"
cbc -> DB : "Save calculated results (write)"

' Styling (optional)
skinparam componentStyle rectangle
@enduml
```

