MedShare: Medical Resource Sharing among Autonomous Healthcare Providers

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

## Please cite our paper about this project:

* Yilong Yang, Xiaoshan Li, Nafees Qamar, Peng Liu, Wei Ke, Bingqing Shen, Zhiming Liu. [MedShare: A Novel Hybrid Cloud for Medical Resource Sharing among Autonomous Healthcare Providers](https://www.researchgate.net/publication/327027574_MedShare_A_Novel_Hybrid_Cloud_for_Medical_Resource_Sharing_among_Autonomous_Healthcare_Providers). IEEE Access. 2018(6). doi: 10.1109/ACCESS.2018.2865535
