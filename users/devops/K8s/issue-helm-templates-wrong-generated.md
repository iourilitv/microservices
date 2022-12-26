# issue-helm-templates-wrong-generated

## Description
See in devops/K8s/logs/helm-templates-wrong-generated.log

Between users-app-service and users-pg-db-deployment blocks the following part is missed:       
``
---
.. Source: users/templates/apps.yaml
``

in the part:        
``      
apiVersion: v1
kind: Service
metadata:
  name: users-app-service       
..      
      targetPort: 8080
      apiVersion: apps/v1
kind: Deployment
metadata:
  name: users-pg-db-deployment
  labels:
    app: users-pg-db        
..      
``