// formatDate: formata uma string de data para DD/MM/YYYY
// Observação: strings no formato 'YYYY-MM-DD' são interpretadas como UTC
// por `new Date('YYYY-MM-DD')`, o que pode resultar no dia anterior em fusos
// horários negativos. Para esse caso, parseamos manualmente como data local.
export const formatDate = (dateString: string): string => {
    let date: Date

    // Se o input estiver no formato exato YYYY-MM-DD, construir como data local
    if (/^\d{4}-\d{2}-\d{2}$/.test(dateString)) {
        const [yearStr, monthStr, dayStr] = dateString.split('-')
        const year = Number(yearStr)
        const month = Number(monthStr) - 1 // monthIndex: 0-11
        const day = Number(dayStr)
        date = new Date(year, month, day)
    } else {
        // Caso contrário, delegar ao Date — aceita ISO com timezone, timestamp, etc.
        date = new Date(dateString)
    }

    if (isNaN(date.getTime())) {
        // Em caso de input inválido, retornar string vazia (ou jogar erro se preferir)
        return ''
    }

    const day = String(date.getDate()).padStart(2, '0')
    const month = String(date.getMonth() + 1).padStart(2, '0')
    const year = date.getFullYear()
    return `${day}/${month}/${year}`
}
