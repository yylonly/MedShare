MedShare: Medical Resource Sharing among Autonomous Healthcare Providers

[![Build Status](https://travis-ci.com/yylonly/medshare.svg?branch=master)](https://travis-ci.com/yylonly/medshare)

## Pre-requirements
* Docker CE
* Docker Compose

## Install
* git clone https://github.com/yylonly/medshare.git
* cd medshare
* docker-compose build
* docker-compose up -d

## Test
http://localhost:8081/net.mydreamy.privacyehr/
* EHR Sharing - Username: doctorgov Password: 123456
* Audit - username: tracker1 password:123

## Deploy to Docker SWARM
* docker stack deploy -c docker-stack.yml medshare 
