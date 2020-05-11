## How to run
    
    cd ./deploy
    kubectl apply -f configuration.yaml -f postgres.yaml -f deployment.yaml -f service.yaml -f ingress.yaml -f db-migration.yaml

## How to check

    curl arch.homework/otusapp/health --resolve arch.homework:80:(--INSERT MINIKUBE IP--)

## Postman tests

- /postman/Otus HW1 Architect (Users API).postman_collection.json
- before testing change **baseurl** property    

## How to stop

    kubectl delete -f configuration.yaml -f postgres.yaml -f deployment.yaml -f service.yaml -f ingress.yaml -f db-migration.yaml

