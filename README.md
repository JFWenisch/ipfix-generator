# ipfix-generator ![Build](https://github.com/JFWenisch/ipfix-generator/actions/workflows/build.yml/badge.svg) ![Version](https://img.shields.io/github/v/release/jfwenisch/ipfix-generator) ![License](https://img.shields.io/github/license/jfwenisch/ipfix-generator) ![Size](https://img.shields.io/github/repo-size/jfwenisch/ipfix-generator)  [![Artifact Hub](https://img.shields.io/endpoint?url=https://artifacthub.io/badge/repository/jfwenisch)](https://artifacthub.io/packages/search?repo=jfwenisch)
## Overview
This IPFIX (IP Flow Information Export) Generator is a tool designed to create and send IPFIX traffic for testing, demonstration, and analysis purposes. It simulates network flow data by generating IPFIX packets, which can be used to test network monitoring systems, analyze network performance, and ensure the accuracy of flow data collection. It features a graphical user interface (GUI) for ease of use and can also be accessed via its API and REST endpoints.

![IPFIX Generator](https://raw.githubusercontent.com/JFWenisch/ipfix-generator/refs/heads/main/docs/img/preview_home.jpeg)



## Features
- **Graphical User Interface (GUI)**: User-friendly interface for generating and managing IPFIX messages.

- **API Access**: Programmatic access to generate IPFIX messages via REST Endpoints allowing integration with other tools and systems.

- **Customizable Parameters**: Configure various parameters for IPFIX message generation.



## Quickstart


### Prerequisites

- Docker installed on your machine

### Steps to Run

1. **Pull the Docker Image**:
   Run the Docker Container: Use the following command to run the Docker container, making sure to map port 8080
   ```bash
   docker run -p 8080:8080 ghcr.io/jfwenisch/ipfix-generator:latest
2. **Access the GUI** : Open your web browser and navigate to http://localhost:8080. You will see the graphical user interface (GUI) of the IPFIX Message Generator.

3. **Set Parameters and Create a New Generator Job** : Use the GUI to set the desired parameters and create a new generator job. The interface is user-friendly and allows you to configure various parameters for IPFIX message generation.

## Installation [![Artifact Hub](https://img.shields.io/endpoint?url=https://artifacthub.io/badge/repository/jfwenisch)](https://artifacthub.io/packages/search?repo=jfwenisch) 
The IPFIX Generator tool is available via Helm. Follow these steps to install it:

1. **Add the Helm repository**:
   ```bash
   helm repo add jfwenisch https://charts.wenisch.tech
   ```

2. **Install the IPFIX Generator**:
   ```bash
   helm install ipfix-generator jfwenisch/ipfix-generator
   ```
## Contributing
Contributions are welcome! Please fork the repository and submit pull requests.

## License
This project is licensed under the GNU GPL Version 3. See the LICENSE file for details.

## Acknowledgments

This project is influenced by the [jFlowLib](https://github.com/DE-CIX/jFlowLib/tree/master) library, which provides a Java library to parse and generate sFlow and IPFIX data.
