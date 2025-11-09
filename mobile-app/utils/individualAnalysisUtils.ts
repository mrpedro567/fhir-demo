import {
    IndividualAnalysisCardDisplayData,
    IndividualAnalysisPatientSectionDisplayData,
} from '@/types/displayData/IndividualAnalysisDisplayData'
import { IndividualAnalysisResponse } from '@/types/dto/IndividualAnalysisDTO'
import { formatDate } from './dateUtils'

function generateInvalidSectionDisplayData(
    patientName: string
): IndividualAnalysisPatientSectionDisplayData {
    return {
        nomePaciente: patientName,
        cards: [
            {
                descricao: '',
                hipotese: '',
                date: '',
            },
        ],
        error: 'Dados inválidos para este paciente.',
    }
}

function extractHipoteseList(hipotese: string): string[] {
    const filteredHipoteseList = hipotese
        .split('\n')
        .filter((item) => item.length > 0)
    let resultHipoteseList: string[] = []
    let currentHipotese = ''

    for (let i = 1; i < filteredHipoteseList.length; i++) {
        const isLastIndex = i === filteredHipoteseList.length - 1
        const isNextHemograma = isLastIndex
            ? false
            : filteredHipoteseList[i + 1].includes('Hemograma')
        if (!filteredHipoteseList[i].includes('Hemograma')) {
            currentHipotese += filteredHipoteseList[i]
            if (!isNextHemograma) {
                if (!isLastIndex) {
                    currentHipotese += ' - '
                } else {
                    resultHipoteseList.push(currentHipotese.trim())
                }
            } else {
                resultHipoteseList.push(currentHipotese.trim())
                currentHipotese = ''
            }
        }
        if (filteredHipoteseList[i].includes('Hemograma') && isNextHemograma) {
            resultHipoteseList.push('Hipótese não fornecida.')
        }
        if (filteredHipoteseList[i].includes('Hemograma') && isLastIndex) {
            resultHipoteseList.push('Hipótese não fornecida.')
        }
    }
    return resultHipoteseList
}

function extractCardDisplayDataFromDescription(
    description: string,
    hipotese: string,
    dateList: string[]
): IndividualAnalysisCardDisplayData[] {
    const cardsList: IndividualAnalysisCardDisplayData[] = []

    const hemogramDescriptionList = description
        .split('\n')
        .filter((item) => item.length > 0)
    const hemogramHipoteseList = extractHipoteseList(hipotese)
    let hemogramIndexBeingProcessed = 1

    for (let i = 1; i < hemogramDescriptionList.length; i += 15) {
        const hemogramDescription = hemogramDescriptionList.slice(i, i + 15)
        let hemogramHipotese = ''
        if (
            hemogramHipoteseList.length - 1 >=
            hemogramIndexBeingProcessed - 1
        ) {
            hemogramHipotese =
                hemogramHipoteseList[hemogramIndexBeingProcessed - 1]
        }

        cardsList.push({
            descricao: hemogramDescription.join('\n'),
            hipotese: hemogramHipotese,
            date: formatDate(dateList[hemogramIndexBeingProcessed - 1]),
        })

        hemogramIndexBeingProcessed += 1
    }

    return cardsList
}

export function mapIndividualAnalysisResponseToPatientSectionDisplayData(
    response: IndividualAnalysisResponse
): IndividualAnalysisPatientSectionDisplayData[] {
    let displayDataList: IndividualAnalysisPatientSectionDisplayData[] = []

    response.forEach((patientData) => {
        if (patientData.descricao.includes('Erro')) {
            displayDataList.push(
                generateInvalidSectionDisplayData(patientData.nomePaciente)
            )
            return
        }

        const dateList = patientData.hemogramas.map((item) => item.dataColeta)

        const cardsData = extractCardDisplayDataFromDescription(
            patientData.descricao,
            patientData.hipotese,
            dateList
        )

        displayDataList.push({
            nomePaciente: patientData.nomePaciente,
            cards: cardsData,
        })
    })

    return displayDataList
}
