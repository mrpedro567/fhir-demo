import { IndividualAnalysisPatientSectionDisplayData } from '@/types/displayData/IndividualAnalysisDisplayData'
import React from 'react'
import { StyleSheet, Text, View } from 'react-native'
import IndividualAnalysisCard from './IndividualAnalysisCard'

export default function IndividualAnalysisPatientSection(
    props: IndividualAnalysisPatientSectionDisplayData
) {
    return (
        <View style={styles.rootView}>
            <Text>{`Paciente: ${props.nomePaciente}`}</Text>
            {props.error ? (
                <Text style={{ color: 'red' }}>{`Erro: ${props.error}`}</Text>
            ) : (
                props.cards.map((cardData, index) => (
                    <IndividualAnalysisCard key={index} {...cardData} />
                ))
            )}
        </View>
    )
}

const styles = StyleSheet.create({
    rootView: {
        width: '100%',
        padding: 16,
        gap: 12,
    },
})
