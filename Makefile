.PHONY: install
install:
	@mvn clean install

.PHONY: run
run:
	@cd bank-api && mvn spring-boot:run

.PHONY: build
build:
	@mvn clean package spring-boot:build-image

.PHONY: start-docker
start-docker:
	@docker-compose up -d