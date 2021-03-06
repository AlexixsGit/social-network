docker run --restart=unless-stopped \
        -v /Users/edwardalexisortizagudelo/Documents/alexis/projects/smart_objective/var/data/rabbitmq:/home/client \
        --hostname rabbitmq.social.local \
        --net social_network \
        --name rabbitmq.social.local \
        --memory=256m \
        -e "TZ=America/Bogota" \
        -p 5672:5672 -p 15672:15672 \
        -d rabbitmq:3.7-management
