#!/bin/bash
docker run --restart=unless-stopped \
        -v /Users/edwardalexisortizagudelo/Documents/alexis/projects/smart_objective/var/data/mongodb:/data/db \
        --hostname mongodb.social.local \
        --name mongodb.social.local \
        --net social_network \
        --memory=512m \
        -e "TZ=America/Bogota" \
        -p 27017:27017 \
        -d mongo:4.2.1
