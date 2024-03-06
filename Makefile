.PHONY: install
install:
	@mvn clean install

.PHONY: run
run:
	@cd bank-api && mvn spring-boot:run