### Step by Step

## 0. Prerequisites

- JDK 11
- MariaDB
    - Configured with `root` user and `root` password

## 1. Install Docker 

- [Windows](https://docs.docker.com/docker-for-windows/install/)
- [Mac](https://docs.docker.com/docker-for-mac/install/)
- [Linux](https://runnable.com/docker/install-docker-on-linux)
- Verify docker is present by starting terminal or cmd and enter `docker --version`
- If old instalation of docker is present - [update](https://docs.docker.com/docker-for-windows/install/#updates) it. 

## 2. Verify Docker Compose is present or install it (this step is needed for Linux OS)

- [Install Docker Compose](https://docs.docker.com/compose/install/)

## 3. Start terminal or cmd in the directory containing the `docker-compose.yml` file

## 5. Start/stop the application

- Run this command to start: `docker-compose up`
    - Images will be build/downloaded and start. If the terminal is closed - the app will stop!
- Run this command to start: `docker-compose up -d`
    - Images will be build/downloaded and started in containers in a detached mode (closing the terminal won't stop the application)
- Run this command to stop: `docker-compose down` 

## 6. Verify the app is running

- Open [http://localhost:8081/](http://localhost:8081/) in a browser


