#!/bin/bash

#git pull

#mvn clean install

cd ui

npm run build

rm -rf ../src/main/resources/public/*

cp -r build/* ../src/main/resources/public/


