## How to run
    
    cd ./deploy
    helm install prom stable/prometheus-operator -f prometheus.yaml --atomic
    kubectl apply -f configuration.yaml -f db-migration.yaml -f deployment.yaml -f grafana.yaml -f ingress.yaml -f postgres.yaml -f service.yaml -f service-monitor.yaml
    kubectl apply -f stresstest.yaml

## How to check

    curl arch.homework/otusapp/health --resolve arch.homework:80:(--INSERT MINIKUBE IP--)

## Postman tests

    newman run crud_user_api_test_collections.json --env-var baseUrl=arch.homework/otusapp

## How to stop

    kubectl delete -f configuration.yaml -f db-migration.yaml -f deployment.yaml -f grafana.yaml -f ingress.yaml -f postgres.yaml -f service.yaml -f service-monitor.yaml
    kubectl delete -f stresstest.yaml
