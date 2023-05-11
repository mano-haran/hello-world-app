# Hello World Application

A simple hello world microservice created using DropWizard.

It has 2 endpoints.

1) /hello-world?name=somebody

Outputs a simple hello message

Sample reponse:
{
    "id": 1257,
    "content": "Hello, Stranger!"
}

2) /fib-sequence?num=somenumber

OUtputs fibonacci series for the given number. It takes in a number between 1 and 100.

Sample response:
{
    "sequence": "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]",
    "total": 88,
    "memberCount": 10
}

**Build Steps**

Go to project directory in command prompt and run below command

mvn package

**Running the application:**

java -jar target/hello-world-0.0.1.jar server hello-world.yml

**Building docker image:**

sudo docker build -t hello-worldapp:1.0 -t manoharan150/hello-worldapp:1.0 -t manoharan150/hello-worldapp:latest .

**Pushing to Dockerhub**

sudo docker login

sudo docker push manoharan150/hello-worldapp:1.0

sudo docker push manoharan150/hello-worldapp:latest

sudo docker logout


**Running the container**

sudo docker run -d -p 8080:8080 -p 8081:8081 manoharan150/hello-worldapp


**Verification**

curl http://localhost:8080/fib-sequence?num=10
curl http://localhost:8080/hello-world?name=Mano

**Metrics URL**

Default Metrics endpoint 
curl http://localhost:8081/metrics

Metrics endpoint for prometheus format
curl http://localhost:8081/metrics-prometheus
