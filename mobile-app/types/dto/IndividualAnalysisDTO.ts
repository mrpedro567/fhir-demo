interface IndividualAnalysis {
    descricao: string
    hipotese: string
    dataCriacao: string
    cpfPaciente: string
    nomePaciente: string
    hemogramas: [
        {
            dataColeta: string
            contagemLeucocitos: number
            porcentagemNeutrofilos: number
            porcentagemLinfocitos: number
            porcentagemMonocitos: number
            porcentagemEosinofilos: number
            porcentagemBasofilos: number
            contagemEritrocitos: number
            hemoglobina: number
            hematocrito: number
            vcm: number
            hcm: number
            chcm: number
            amplitudeDistribuicaoEritrocitaria: number
            vpm: number
            contagemPlaquetas: number
        }
    ]
}

export type IndividualAnalysisResponse = IndividualAnalysis[]
