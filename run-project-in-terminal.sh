#!/bin/sh
export $(cat .env | xargs)
mvn spring-boot:run
