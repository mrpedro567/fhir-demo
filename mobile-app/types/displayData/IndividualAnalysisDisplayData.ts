export interface IndividualAnalysisCardDisplayData {
    descricao: string
    hipotese: string
    date: string
}

export interface IndividualAnalysisPatientSectionDisplayData {
    nomePaciente: string
    cards: IndividualAnalysisCardDisplayData[]
    error?: string
}
