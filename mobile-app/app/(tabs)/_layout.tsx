import { Tabs } from 'expo-router'

export default function TabsLayout() {
    return (
        <Tabs>
            <Tabs.Screen
                name="individual-analysis"
                options={{
                    title: 'Análise Individual',
                    tabBarIcon: () => null,
                }}
            />
            <Tabs.Screen
                name="collective-analysis"
                options={{ title: 'Análise Coletiva', tabBarIcon: () => null }}
            />
        </Tabs>
    )
}
