# Under construction 🔨

### Prerequisites

* Java 21
* Docker
* Kubernetes
* Helm

### Installing the project

Before diving into the project it is recommended to run the command below to install the modules dependencies in `.m2`
folder

```sh
make install
```

### Building de project

This is required to build the image needed to run the docker-compose and helm commands

```sh
make build
```

### Running the project

* You can just simply run the **project locally**

    ```sh
    make run
    ```
* **By using docker-compose**
    ```sh 
    make start-docker
    ```
* **By using helm**
    ```sh
    make monitoring-helm
    make app-helm
    ```
  This way you can stop all related to helm
    ```sh
    make stop-helm
    ```