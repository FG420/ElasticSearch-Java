# Console Application with Elasticsearch Integration

This is a console application implemented in Java, which integrates with Elasticsearch to perform various operations on data stored in a search index. The application leverages the Elasticsearch Java High-Level REST Client to communicate with Elasticsearch.

## Prerequisites

- Java Development Kit (JDK) 8 or above
- Elasticsearch server running locally or remotely
- Elasticsearch Java High-Level REST Client library

## Setup

1. Clone or download the project from the GitHub repository.

2. Make sure you have Elasticsearch up and running. If not, follow the official Elasticsearch documentation to install and configure Elasticsearch server.

3. [Download](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-getting-started-initialization.html) the Elasticsearch Java High-Level REST Client library JAR file and add it to the project's classpath.

## Configuration

Open the `src/main/resources/application.properties` file and configure the following settings:

- **elasticsearch.host**: Specify the hostname or IP address of the Elasticsearch server.
- **elasticsearch.port**: Specify the port number on which Elasticsearch is running.
- **elasticsearch.username** (optional): If you have enabled security on Elasticsearch, provide the username for authentication.
- **elasticsearch.password** (optional): If you have enabled security on Elasticsearch, provide the password for authentication.

## Usage

1. Build the project using your preferred build tool, such as Maven or Gradle.

2. Run the generated JAR file or execute the application using the build tool.

3. The console application will present you with a menu to choose various operations available for Elasticsearch integration.

4. Follow the instructions provided by the application for each operation.

## Features

The console application provides the following features:

1. **Indexing Data**: Allows you to add new documents/indexes to the Elasticsearch index.

2. **Searching Data**: Enables you to search for documents in the Elasticsearch index using various search queries.

3. **Updating Data**: Provides functionality to update an existing document in the Elasticsearch index.

4. **Deleting Data**: Allows you to delete a document from the Elasticsearch index.

## Contributing

Contributions are always welcome! If you find any issues with the application or have any suggestions for improvement, please open an issue or submit a pull request on the GitHub repository.

## License

This project is licensed under the [MIT license](https://opensource.org/licenses/MIT).

## Disclaimer

This application is developed as a demonstration of integrating Elasticsearch in a console application and should not be used as a production-ready solution without proper modifications and handling of security, error scenarios, and optimizations.
