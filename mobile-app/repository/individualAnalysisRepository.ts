import { IndividualAnalysisResponse } from '@/types/dto/IndividualAnalysisDTO'

export async function fetchIndividualAnalysisData(): Promise<IndividualAnalysisResponse> {
    return fetch(
        'http://localhost:8080/AnaliseIndividualTodosUltimosPontosDeAtencao',
        {
            method: 'GET',
        }
    )
        .then((response) => {
            if (!response.ok) {
                throw new Error('Erro ao buscar dados da análise individual')
            }

            return response.json()
        })
        .then((data) => {
            return data
        })
}
