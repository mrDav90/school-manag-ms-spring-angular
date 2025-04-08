#!/bin/bash

echo "Waiting for Kafka to be on line..."

# Attendre que Kafka soit prêt
cub kafka-ready -b kafka:9092 1 20

# Création des topics
kafka-topics.sh --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --topic payments
kafka-topics.sh --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --topic registrations
kafka-topics.sh --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --topic payment-gateway-logs

echo "Kafka topics created successfully"

# Maintenir le conteneur en cours d'exécution
sleep infinity
