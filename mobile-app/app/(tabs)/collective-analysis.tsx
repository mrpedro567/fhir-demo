import React from 'react'
import { StyleSheet, Text, View } from 'react-native'

export default function CollectiveAnalysisScreen() {
    return (
        <View style={styles.rootView}>
            <Text>Análise Coletiva Screen</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    rootView: { flex: 1, justifyContent: 'center', alignItems: 'center' },
})
