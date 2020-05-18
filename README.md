This repository holds API code for https://coderprabhu.com

Git Repo for UI: https://github.com/CoderPraBhu/coderprabhu-ui  
This Repo for API: https://github.com/CoderPraBhu/coderprabhu-api  
Git Repo for K8S: https://github.com/CoderPraBhu/coderprabhu-k8s  

# Commands:  
The app was created using Spring Initializr.   

After making any changes, update following command with new version number.
Execute to build the image and push it:  
````
gradle jibDockerBuild --image=gcr.io/kubegcp-256806/coderprabhu-api:v5
docker push gcr.io/kubegcp-256806/coderprabhu-api:v5
````
Docker Reference: https://spring.io/guides/topicals/spring-boot-docker  
Google Container registry authentication: 
https://cloud.google.com/container-registry/docs/advanced-authentication
```
gcloud auth login
gcloud auth configure-docker
```
You can run the image locally using
````
docker run -p 8080:8080 -t gcr.io/kubegcp-256806/coderprabhu-api:v1  
curl http://localhost:8080
````
To update the k8s deployment with new version, update the api deployment yaml with new 
container image version and execute
````
kubectl apply -f ../../k8s/coderprabhu-k8s/coderprabhu-api-deployment.yaml  
````
coderprabhu-api-deployment.yaml is hosted on [coderprabhu-k8s](https://github.com/CoderPraBhu/coderprabhu-k8s) repository [here](https://github.com/CoderPraBhu/coderprabhu-k8s/blob/master/coderprabhu-api-deployment.yaml)  

# Curl commands:   
````
curl http://coderprabhu.com
curl https://coderprabhu.com
curl http://www.coderprabhu.com
curl https://www.coderprabhu.com
curl http://api.coderprabhu.com
curl https://api.coderprabhu.com
curl https://api.coderprabhu.com/actuator/info
curl https://api.coderprabhu.com/actuator/health
````   