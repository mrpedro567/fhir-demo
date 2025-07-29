# Trabalho prático: Sistema de Monitoramento de Hemogramas

## Objetivo do projeto
Desenvolver um sistema que recebe e analisa hemogramas de pacientes, identificando padrões anômalos e disparando notificações para gestores de saúde. O sistema utilizará o padrão **FHIR** para garantir a interoperabilidade e permitirá monitorar a saúde de uma população em tempo real.

## Resultado esperado
O trabalho visa gerar valor para a saúde pública, auxiliando gestores a detectar sinais coletivos de problemas de saúde de forma rápida e eficiente, com base nos resultados de hemogramas recebidos periodicamente.

## Descrição do sistema
O sistema será responsável por:

1. Receber e processar hemogramas: a partir de uma fonte centralizada, os resultados de hemogramas serão enviados periodicamente para o sistema por meio do mecanismo **FHIR Subscription**.
2. 
3. Análise automática dos hemogramas: o sistema realizará uma análise simples de parâmetros-chave dos hemogramas para identificar possíveis alertas, como:
   - *leucócitos* (glóbulos brancos): O aumento de leucócitos pode indicar uma infecção ou inflamação.
   - *plaquetas*: uma contagem muito baixa pode indicar risco de sangramentos.
   - *hemoglobina*: níveis abaixo da faixa normal podem indicar anemia.
     
4. Notificação para gestores: quando a análise indicar que há uma anomalia coletiva (como o aumento de leucócitos em várias amostras), o sistema enviará uma notificação em tempo real para gestores por meio de um aplicativo móvel.

### Componentes principais do sistema
- *ingestão de dados*: recebimento de dados de hemogramas em formato FHIR (`Observation`) via **FHIR Subscription**.
- *processamento e análise*: análise automática dos dados para detectar padrões que demandam atenção.
- *alertas e notificações*: envio de alertas aos gestores via app móvel quando os dados indicam anomalias.
- *app móvel*: aplicação para gestores que exibe alertas e informações detalhadas sobre os hemogramas analisados.

## Regras para análise automática de hemogramas
O sistema será responsável por processar os hemogramas e aplicar as seguintes regras básicas de análise automática, com base em faixas de referência típicas para cada parâmetro. A análise será feita de forma dinâmica, com os dados sendo recebidos periodicamente.

1. leucócitos (glóbulos brancos):
   - valor normal: 4.000 - 11.000 leucócitos/microlitro.
   - alerta: Se a contagem de leucócitos estiver **acima de 11.000** ou **abaixo de 4.000**, será gerado um alerta.
   
2. plaquetas:
   - valor normal: 150.000 - 450.000 plaquetas/microlitro.
   - alerta: Se a contagem de plaquetas estiver **abaixo de 150.000** ou **acima de 450.000**, será gerado um alerta.
   
3. hemoglobina:
   - valor normal:
     - homens: 13.8 - 17.2 g/dL.
     - mulheres: 12.1 - 15.1 g/dL.
   - alerta: Se os níveis de hemoglobina estiverem **abaixo do limite inferior** ou **acima do limite superior** para a faixa etária e sexo do paciente.

4. detecção de padrões coletivos:
   - Caso o sistema detecte **múltiplos alertas** (exemplo: aumento significativo de leucócitos em várias amostras de uma mesma região) em um período de tempo **curto**, um alerta coletivo será gerado e enviado para o gestor da saúde.
   - O alerta deve incluir informações como número de casos alertados, média dos valores dos parâmetros, e possíveis recomendações iniciais.

## Requisitos técnicos
- fhir: utilização do padrão FHIR, especificamente os recursos `Observation` para os hemogramas e `Communication` para as notificações.
- fhir subscription: o recebimento dos hemogramas será feito por meio do mecanismo **FHIR Subscription**, permitindo que o sistema seja automaticamente notificado quando novos dados de hemogramas forem disponibilizados.
- backend e armazenamento: o sistema usará um servidor FHIR simples (como HAPI FHIR) para armazenar os dados e gerar as respostas às requisições de dados dos hemogramas.
- app móvel: o aplicativo será responsável por receber notificações e apresentar os alertas aos gestores. Este app deve ser simples, intuitivo, e mostrar informações claras sobre os alertas recebidos.

## Objetivos de aprendizagem
- desenvolvimento de software distribuído: implementar um sistema que se comunique com diversas fontes de dados e que seja escalável.
- trabalho com interoperabilidade: utilizar o padrão FHIR para garantir a troca de informações entre sistemas diferentes, utilizando dados clínicos.
- processamento em tempo real: aplicar técnicas de processamento de dados em tempo real para detectar padrões preocupantes em hemogramas.
- criação de sistemas para a saúde pública: entender as necessidades dos gestores de saúde e como a tecnologia pode ajudá-los a tomar decisões rápidas e informadas.

## Considerações
Este projeto proporciona aos alunos uma experiência prática na aplicação de conceitos de software em um domínio crítico, como a saúde. A utilização do padrão FHIR garante que o sistema seja interoperável, enquanto a análise de dados de hemogramas permitirá a identificação de padrões de saúde importantes, oferecendo valor real ao negócio e à saúde pública.

## Restrições de projeto
- tecnologias: FHIR Subscription (para recepção dos dados), FHIR (Observation e Communication), HAPI FHIR, backend simples.
- plataforma móvel: o aplicativo móvel deve ser desenvolvido para Android e Apple, alternativamente como uma aplicação web responsiva).
- autenticação e autorização: via Gov.BR.

