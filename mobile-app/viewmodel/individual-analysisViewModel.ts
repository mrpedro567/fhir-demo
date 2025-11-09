import { IndividualAnalysisPatientSectionDisplayData } from '@/types/displayData/IndividualAnalysisDisplayData'
import * as IndividualAnalysisUtils from '@/utils/individualAnalysisUtils'
import { useEffect, useState } from 'react'
import * as IndividualAnalysisService from '../service/individualAnalysisService'

export default function useIndividualAnalysisViewModel() {
    const [loading, setLoading] = useState(false)
    const [patientSections, setPatientSections] =
        useState<IndividualAnalysisPatientSectionDisplayData[]>()
    const [error, setError] = useState<string>()

    const getIndividualAnalysisData = async () => {
        setLoading(true)
        try {
            const reponse =
                await IndividualAnalysisService.getIndividualAnalysisData()
            const mappedPatientData =
                IndividualAnalysisUtils.mapIndividualAnalysisResponseToPatientSectionDisplayData(
                    reponse
                )
            setPatientSections(mappedPatientData)
        } catch (error) {
            setError(
                error instanceof Error
                    ? error.message
                    : 'Erro ao buscar dados de análise individual.'
            )
        } finally {
            setLoading(false)
        }
    }

    useEffect(() => {
        getIndividualAnalysisData()
    }, [])

    return {
        loading,
        patientSections,
        error,
        refresh: getIndividualAnalysisData,
    }
}
