## How to run

    kubectl apply -f deployment.yaml -f service.yaml -f ingress.yaml

## How to check

    curl arch.homework/otusapp/health --resolve arch.homework:80:(--INSERT MINIKUBE IP--)

## How to stop

    kubectl delete -f deployment.yaml -f service.yaml -f ingress.yaml
