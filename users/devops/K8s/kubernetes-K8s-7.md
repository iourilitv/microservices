# kubernetes (K8s). Part 7
Using namespaces.

## Creating namespaces
### Create namespace users-dev manually
``
kubectl create namespace users-dev 
``

Result:         
``
namespace/users-dev created
``

### Check namespaces
``
kubectl get namespaces
``

Result:     
``
PS D:\projects\examples\microservices\users\devops\K8s\helm> kubectl get namespaces
NAME                   STATUS   AGE
default                Active   2d19h
ingress-nginx          Active   2d19h
kube-node-lease        Active   2d19h
kube-public            Active   2d19h
kube-system            Active   2d19h
kubernetes-dashboard   Active   2d19h
users-dev              Active   77s
``

### Delete namespace (if required)
``
kubectl delete namespace users-dev
``

Result:     
``
namespace "users-dev" deleted
``

### Create namespace users-test and other ones by yaml-file
Create file namespaces.yaml

``
kubectl apply -f namespaces.yaml
``

Result:     
``
namespace/users-test created
namespace/users-dev created
namespace/users-prod created
``

## Update helm templates etc. with namespaces using
### Update files:
_template-deployment.yaml
_template-service.yaml
users-config.yaml
users-ingress.yaml
values.yaml

### Created values-test file


### Added users-test.local into ./etc/hosts file


## Deploy apps with test namespace
###
``
helm install users-test-helm . -f values-test.yaml
``


