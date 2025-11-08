import IndividualAnalysisPatientSection from '@/components/IndividualAnalysisPatientSection'
import useIndividualAnalysisViewModel from '@/viewmodel/individual-analysisViewModel'
import React from 'react'
import { Pressable, ScrollView, Text, View } from 'react-native'

export default function IndividualAnalysisScreen() {
    const { loading, patientSections, error, refresh } =
        useIndividualAnalysisViewModel()

    if (loading) {
        return (
            <View
                style={{
                    flex: 1,
                    justifyContent: 'center',
                    alignItems: 'center',
                }}
            >
                <Text>Carregando dados...</Text>
            </View>
        )
    }

    if (error) {
        return (
            <View
                style={{
                    flex: 1,
                    justifyContent: 'center',
                    alignItems: 'center',
                }}
            >
                <Text>{`Erro: ${error}`}</Text>
                <Pressable onPress={refresh}>
                    <Text style={{ color: 'blue', marginTop: 16 }}>
                        Tentar novamente
                    </Text>
                </Pressable>
            </View>
        )
    }

    return (
        <ScrollView style={{ flex: 1 }}>
            {patientSections?.map((sectionData, index) => (
                <IndividualAnalysisPatientSection
                    key={index}
                    {...sectionData}
                />
            ))}
        </ScrollView>
    )
}
