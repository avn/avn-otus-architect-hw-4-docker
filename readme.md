## How to run
    
    cd ./deploy
    kubectl apply -f configuration.yaml -f postgres.yaml -f deployment.yaml -f service.yaml -f ingress.yaml -f db-migration.yaml

## How to check

    curl arch.homework/otusapp/health --resolve arch.homework:80:(--INSERT MINIKUBE IP--)

## Postman tests

- newman run crud_user_api_test_collections.json --env-var baseUrl=arch.homework/otusapp

## How to stop

    kubectl delete -f configuration.yaml -f postgres.yaml -f deployment.yaml -f service.yaml -f ingress.yaml -f db-migration.yaml

