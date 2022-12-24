# kubernetes (K8s). Part 5
Replacing the services of kind LoadBalancer with a single ingress controller.

Additional source: https://www.youtube.com/watch?v=ThP-OEjpDZk

## Install ingress controller: nginx
!!! How to solve a problem find in devops/K8s/issue-ingress-installing.md
``
minikube addons enable ingress
``

Result:     
``
...
* The 'ingress' addon is enabled
``

## Configure ingress
### Create users-ingress.yaml

### Create ingress
``
kubectl apply -f users-ingress.yaml
``

Result:
``
???
``

### Configure DNS server in order to every request forwards to the ingress controller


