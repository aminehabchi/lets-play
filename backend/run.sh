#!/bin/bash

set -e  # Exit on any error

# Ensure we are in project root with gradlew
if [ ! -f "./gradlew" ]; then
    echo "gradlew not found. Run this script from the Spring Boot project root."
    exit 1
fi

# Make gradlew executable
chmod +x ./gradlew

# Run Spring Boot application in the foreground
exec ./gradlew bootRun
