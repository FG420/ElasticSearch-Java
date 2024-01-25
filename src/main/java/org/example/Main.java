package org.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try{
            // Your Elastic Search DB
            String serverUrl = "";
            // Your Elastic Search Connection Api
            String apiKey = "";
            String index = "users";

            RestClient restClient = RestClient
                    .builder(HttpHost.create(serverUrl))
                    .setDefaultHeaders(new Header[]{
                            new BasicHeader("Authorization", "ApiKey " + apiKey)
                    })
                    .build();
            ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
            ElasticsearchClient client = new ElasticsearchClient(transport);

            if (restClient.isRunning()){
                System.out.println("Connected");
            }



            Scanner scanner = new Scanner(System.in);
            System.out.println("choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 0:
                    System.out.println("You closed the program");
                    System.exit(0);
                    break;

                case 1:
                    try {
                        System.out.println("Create new User");
                        System.out.println("Insert Id : ");
                        var id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Insert Name : ");
                        var name = scanner.nextLine();
                        System.out.println("Insert Surname : ");
                        var surname = scanner.nextLine();
                        User user = new User();
                        user.id = id;
                        user.name = name;
                        user.surname = surname;
                        IndexResponse response = client.index(i -> i
                                .index(index)
                                .id(String.valueOf(user.id))
                                .document(user));
                        System.out.println("------------------------");
                        System.out.println("User successfully created : " + user.name + " " + user.surname );
                    }catch (ElasticsearchException e){
                        System.out.println(e.getMessage());
                    }
                    System.exit(0);
                    break;

                case 2:
                    System.out.println("View by Id");
                    System.out.println("Input an ID to view");
                    int searchId = scanner.nextInt();
                    GetResponse<User> getResponse = client.get(s -> s
                            .index(index)
                            .id(String.valueOf(searchId)), User.class);
                    User source = getResponse.source();
                    System.out.println("------------------------");
                    System.out.println("The searched user is : " +  source.name + " " + source.surname);
                    System.exit(0);
                    break;

                case 3:
                    System.out.println("Delete by Id");
                    System.out.println("Input an ID to delete");
                    int deleteId = scanner.nextInt();
                    DeleteResponse response = client.delete(i -> i
                            .index(index)
                            .id(String.valueOf(deleteId)));
                    System.out.println("------------------------");
                    System.out.println("The user was successfully deleted");
                    System.exit(0);
                    break;

                case 4:
                    System.out.println("Update the data of a user");
                    System.out.println("Input the ID of the user to update");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Input the name of a user to update");
                    String upName = scanner.nextLine();
                    System.out.println("Input the surname of a user to update");
                    String upSurname = scanner.nextLine();

                    User userToUp = new User();

                    if (upName != null && !upName.isEmpty()) {
                        userToUp.name = upName;
                    } else {
                        userToUp.name = userToUp.name;
                    }

                    if (upSurname != null && !upSurname.isEmpty()) {
                        userToUp.surname = upSurname;
                    } else {
                        userToUp.surname = userToUp.surname;
                    }

                    client.update(u -> u
                                    .index(index)
                                    .id(String.valueOf(updateId))
                                    .upsert(userToUp).doc(userToUp),
                            User.class
                    );

                    System.out.println("User successfully updated");
                    System.exit(0);

                    break;

                case 5:
                    System.out.println("View all data");
                    SearchResponse<User> searchResponse = client.search(s -> s.index(index), User.class);
                    HitsMetadata<User> hits = searchResponse.hits();
                    for (Hit<User> hit : hits.hits()) {
                        User user = hit.source();
                        String id = hit.id();
                        System.out.println("------------------------");
                        System.out.println("ID: " + id);
                        System.out.println("User: " + user.name + " " + user.surname);
                    }
                    System.exit(0);
                    break;

                case 6:
                    System.out.println("Delete Index");
                    client.indices().delete(c -> c
                            .index(index)
                    );

                    System.out.println("Congrats you deleted all the docs inside the index");
                    System.exit(0);
                    break;

                default: System.out.println("Operation error!");
            }

        } catch (ElasticsearchException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}