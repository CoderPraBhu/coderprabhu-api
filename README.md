This repository holds API code for https://coderprabhu.com

Git Repo for UI: https://github.com/CoderPraBhu/coderprabhu-ui  
This Repo for API: https://github.com/CoderPraBhu/coderprabhu-api  
Git Repo for K8S: https://github.com/CoderPraBhu/coderprabhu-k8s  

# Commands:  
The app was created using Spring Initializr.   
## JIB Build:
After making any changes, update following command with new version number.
Execute to build the image and push it:  
```
./gradlew jibDockerBuild --image=gcr.io/kubegcp-256806/coderprabhu-api:v23
docker push gcr.io/kubegcp-256806/coderprabhu-api:v23
kubectl apply -f k8s/coderprabhu-api-deployment.yaml 
```
## Spring boot image build:
After making any changes, choose version in build.gradle, update docker push command and update deployment yaml.
Execute to build the image and push it:  
```
./gradlew bootBuildImage
docker push gcr.io/all-projects-292200/coderprabhu-api:0.0.16-SNAPSHOT
  Auth first: https://cloud.google.com/container-registry/docs/advanced-authentication
  Run gcloud auth login
docker run -p 8080:8080 -t gcr.io/all-projects-292200/coderprabhu-api:0.0.16-SNAPSHOT
kubectl apply -f k8s/coderprabhu-api-deployment.yaml
````
Docker Reference: https://spring.io/guides/topicals/spring-boot-docker  
Google Container registry authentication: 
https://cloud.google.com/container-registry/docs/advanced-authentication
```
gcloud auth login
gcloud auth configure-docker
less $HOME/.docker/config.json
```
You can run the image locally using
````
docker run -p 8080:8080 -t gcr.io/kubegcp-256806/coderprabhu-api:v9 
curl http://localhost:8080
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=prod" -t gcr.io/all-projects-292200/coderprabhu-api:0.0.15-SNAPSHOT
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=prod" -t gcr.io/kubegcp-256806/coderprabhu-api:0.0.6-SNAPSHOT 
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=prod" -e "BPL_JVM_THREAD_COUNT=20" -t gcr.io/kubegcp-256806/coderprabhu-api:0.0.6-SNAPSHOT 

docker run -p 8080:8080 -t gcr.io/kubegcp-256806/coderprabhu-api:0.0.11-SNAPSHOT 
docker run -p 8080:8080 --env JAVA_OPTS="-Xmx300m -Xms200m" -t gcr.io/projectname/appname:0.0.1-SNAPSHOT 
docker run -p 8080:8080 --env JAVA_OPTS="-Xmx300m -XX:ReservedCodeCacheSize=100M -XX:MaxMetaspaceSize=80M" -t gcr.io/kubegcp-256806/coderprabhu-api:0.0.6-SNAPSHOT 

./gradlew bootRun
./gradlew bootRun -Pargs=--logging.level.org.springframework=DEBUG   
./gradlew bootRun --args='--spring.profiles.active=dev'
./gradlew bootRun --args='--spring.profiles.active=prod'

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
curl https://api.coderprabhu.com/actuator/metrics
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/metrics
curl -I localhost:8080/actuator/health/readiness
curl -I localhost:8080/actuator/health/liveness
````   
# more commands:   
````
gcloud container images list
kubectl exec -it coderprabhu-api-app-77477b6fbb-n8xmr bash
kubectl exec -it coderprabhu-api-app-6f458c588-tw929 -c coderprabhu-api-app bash
kubectl logs coderprabhu-api-app-59ccbb7866-8hrbq
watch 'kubectl get pods|grep coderprabhu-api & kubectl top pods|grep coderprabhu-api' 
watch 'kubectl logs coderprabhu-api-app-6f458c588-tw929 --tail=150 | grep GC |grep heap'
watch 'kubectl get pods & kubectl top pods'
watch 'kubectl logs coderprabhu-api-app-59ccbb7866-8hrbq --tail=60'
watch 'kubectl logs coderprabhu-api-app-59ccbb7866-8hrbq --tail=60 | grep -v gc'
watch 'kubectl describe pod coderprabhu-api-app-5dd8b9d9cf-dk5ff | grep kubelet'
watch -n 2 'kubectl logs coderprabhu-api-app-57c6bb7f-szph8 --tail=30 | grep gc | wc'
ab -n 100 -c 2 https://api.coderprabhu.com/count
ab -n 100 -c 2 http://localhost:8080/count
 ab -n 5000 -c 2 https://api.coderprabhu.com/hello
 kubectl describe pod coderprabhu-api-app-789cbd7455-45hdk | tail -5
 ab -n 3000 -c 5 https://api.coderprabhu.com/visit
 ab -n 2000 -c 6 https://api.coderprabhu.com/count
 ab -n 2000 -c 6 https://api.coderprabhu.com/count

jps
jmap -histo 80272 | head
jmap -clstats 80272 
````   

#Config Map for log level
```
kubectl create configmap coderprabhu-api-log-level --from-literal=LOGGING_LEVEL_ORG_SPRINGFRAMEWORK=DEBUG
kubectl get configmap log-level -o yaml
kubectl scale deployment coderprabhu-api-app --replicas=0 
kubectl scale deployment coderprabhu-api-app --replicas=1
kubectl create configmap log-level --from-literal=LOGGING_LEVEL_ORG_SPRINGFRAMEWORK=INFO -o yaml --dry-run | kubectl replace -f -
kubectl create configmap log-level --from-literal=LOGGING_LEVEL_ORG_SPRINGFRAMEWORK=DEBUG -o yaml --dry-run | kubectl replace -f -

```

kubectl apply -f k8s/coderprabhu-api-dot-com-cert.yaml
kubectl describe -f k8s/coderprabhu-api-dot-com-cert.yaml
kubectl delete -f k8s/coderprabhu-api-dot-com-cert.yaml
