#!/bin/bash
# Create network
docker network create sample-network

# Create mysql container
docker run  -d --name=springsessiontest \
--network=sample-network \
-e MYSQL_ROOT_PASSWORD=password \
-p 3306:3306 \
mysql:8

# Create phpmyadmin container
docker run -d --name springsessionuitest  \
--network=sample-network \
-e PMA_HOSTS=springsessiontest \
-e PMA_PORT=3306 \
-e PMA_USER=root \
-e PMA_PASSWORD=password \
-p 7000:80 \
phpmyadmin/phpmyadmin