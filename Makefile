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

.PHONY: monitoring-helm
monitoring-helm:
	@helm repo add prometheus-community https://prometheus-community.github.io/helm-charts && helm repo update && helm install prometheus prometheus-community/kube-prometheus-stack

.PHONY: app-helm
app-helm:
	@helm install bank helmchart

.PHONY: stop-helm
stop-helm:
	@helm ls --all --short | xargs -L1 helm uninstall