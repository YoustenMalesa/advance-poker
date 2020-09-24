@echo off
call mvn clean package
call docker build -t advance/advance-poker .
call docker rm -f advance-poker
call docker run -d -p 9080:9080 -p 9443:9443 --name advance-poker advance/advance-poker