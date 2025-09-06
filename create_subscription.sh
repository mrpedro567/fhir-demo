#!/bin/bash

# Script to create a FHIR Subscription for LOINC 58410-2
# This subscribes to CBC panel - Blood by Automated count observations

FHIR_BASE_URL="http://localhost:8080/fhir"
SUBSCRIPTION_ENDPOINT="http://kafka:9099/"  # Kafka endpoint

echo "Creating FHIR Subscription for LOINC 58410-2..."

# Create the subscription JSON payload
SUBSCRIPTION_JSON='{
  "resourceType": "Subscription",
  "status": "active",
  "reason": "Subscribe to CBC panel observations (LOINC 58410-2)",
  "criteria": "Observation?code=58410-2",
  "channel": {
    "type": "rest-hook",
    "endpoint": "'$SUBSCRIPTION_ENDPOINT'",
    "payload": "application/json"
  }
}'

curl -X POST "$FHIR_BASE_URL/Subscription" \
  -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d "$SUBSCRIPTION_JSON" \
  
