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
### Copy into 