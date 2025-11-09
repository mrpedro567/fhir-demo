import { IndividualAnalysisCardDisplayData } from '@/types/displayData/IndividualAnalysisDisplayData'
import React from 'react'
import { StyleSheet, Text, View } from 'react-native'

export default function IndividualAnalysisCard(
    props: IndividualAnalysisCardDisplayData
) {
    return (
        <View style={styles.cardContainer}>
            <Text>{`Data: ${props.date}`}</Text>
            <Text>{props.descricao}</Text>
            <Text>{`Hipótese: ${props.hipotese}`}</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    cardContainer: {
        backgroundColor: '#fff',
        borderRadius: 8,
        padding: 16,
        gap: 8,
        alignItems: 'flex-start',
    },
})
