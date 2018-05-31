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
http://localhost:8081/net.mydreamy.privacyehr-0.0.1-SNAPSHOT
* EHR Sharing - Username: doctorgov Password: 123456 (auth service ip: 127.0.0.1 if on localhost)
* Audit - username: tracker1 password:123
* Query Patient ID: 73001 - 73100

## Deploy to Docker SWARM
* docker stack deploy -c docker-stack.yml medshare 
