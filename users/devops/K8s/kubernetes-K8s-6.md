# kubernetes (K8s). Part 6
Using Helm Charts for templating the yaml-files replication.

Additional source: https://www.youtube.com/watch?v=-lLT0vlaBpk

## Install helm
Go to http://helm.sh

### Download the last version of helm from
https://github.com/helm/helm/releases/tag/v3.10.3 
find a link on version for Windows: Windows amd64       
download from
https://get.helm.sh/helm-v3.10.3-windows-amd64.zip
into D:\Software\K8s.

### Prepare for using (no installation)
Unpack the zip-file anywhere.
Move helm.exe file into D:\Software\K8s.

### Add the path to the folder into environments variables (here that has been done before)
Path: D:\Software\K8s 

### Check that helm is working
``
helm --help
``

## Configure the app deployment by Helm
### Go to devops/K8s folder
``
cd devops/K8s
``

Result:
``
PS D:\projects\examples\microservices\users\devops\K8s>
``

### Create automatically helm's folders structure with new folder creating
I wish that a new folder name was "helm".
``
helm create helm
``
### Delete all items in the folders: templates and charts
### Update Chart.yaml file
### Copy from project core folder into devops/K8s/helm/templates folder the following files:
users-app-deployment.yaml; users-config.yaml; users-ingress.yaml; users-pg-db-deployment.yaml
### Replace users app values by variables in values.yaml:
files:
users-config.yaml; users-ingress.yaml; users-app-deployment.yaml and users-pg-db-deployment.yaml        

## Installing apps with helm configuration
### Install helm chart
``
helm install users-helm helm/
``

Result:     
``
PS D:\projects\examples\microservices\users\devops\K8s> helm install users-helm helm/
Error: INSTALLATION FAILED: ConfigMap in version "v1" cannot be handled as a ConfigMap: json: cannot unmarshal number into Go struct field ConfigMap.data of type string
``

Find a solution in devops/K8s/issue-helm-configmaps-number-as-string.md

Then upgrade or delete and repeat installation by helm

### Delete the app (if required)
``
helm delete users-helm
``

Result:     
``
PS D:\projects\examples\microservices\users\devops\K8s> helm delete users-helm
release "users-helm" uninstalled
``

### Upgrade the apps by helm
``
helm upgrade users-helm helm/
``

Result:     
``
PS D:\projects\examples\microservices\users\devops\K8s> helm upgrade users-helm helm/
Release "users-helm" has been upgraded. Happy Helming!
NAME: users-helm
LAST DEPLOYED: Sun Dec 25 13:39:04 2022
NAMESPACE: default
STATUS: deployed
REVISION: 4
TEST SUITE: None
``

### Get lists of apps in helm
``
helm list
``

Result:     
``
PS D:\projects\examples\microservices\users\devops\K8s> helm list
NAME            NAMESPACE       REVISION        UPDATED                                 STATUS          CHART                   APP VERSION
users-helm      default         4               2022-12-25 13:39:04.3593219 +0300 MSK   deployed        users-0.0.1-SNAPSHOT    0.0.1
``

### Create schema in users database manually
### Check pods
``
kubectl get pods
``

Result:     
``
PS D:\projects\examples\microservices\users\devops\K8s> kubectl get pods
NAME                                     READY   STATUS    RESTARTS   AGE
users-app-deployment-58b778899c-4zwx2    1/1     Running   0          19m
users-app-deployment-58b778899c-c9slv    1/1     Running   3          19m
users-pg-db-deployment-bdc84b778-2fqnh   1/1     Running   0          19m
users-pg-db-deployment-bdc84b778-pj7hh   1/1     Running   0          19m
``