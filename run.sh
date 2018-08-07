#!/bin/bash

git pull

mvn clean install

cd ui

npm install

npm run build

rm -rf ../src/main/resources/public/*

cp -r build/* ../src/main/resources/public/

cd ..

mvn exec:java

