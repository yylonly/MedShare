#!/bin/sh
docker-compose build

docker login
docker tag index-center yylonly/index-center
docker tag index-center-db yylonly/index-center-db
docker tag gov-auth yylonly/gov-auth
docker tag gov-auth-db yylonly/gov-auth-db
docker tag gov-shared yylonly/gov-shared

docker push yylonly/index-center
docker push yylonly/index-center-db
docker push yylonly/gov-auth
docker push yylonly/gov-auth-db
docker push yylonly/gov-shared
