import { IndividualAnalysisResponse } from '@/types/dto/IndividualAnalysisDTO'
import * as IndividualAnalysisRepository from '../repository/individualAnalysisRepository'

export async function getIndividualAnalysisData(): Promise<IndividualAnalysisResponse> {
    try {
        const data =
            await IndividualAnalysisRepository.fetchIndividualAnalysisData()
        return data
    } catch (error) {
        throw error
    }
}
