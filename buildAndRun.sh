#!/bin/sh
if [ $(docker ps -a -f name=advance-poker | grep -w advance-poker | wc -l) -eq 1 ]; then
  docker rm -f advance-poker
fi
mvn clean package && docker build -t advance/advance-poker .
docker run -d -p 9080:9080 -p 9443:9443 --name advance-poker advance/advance-poker
