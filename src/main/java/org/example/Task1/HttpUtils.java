package org.example.Task1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpUtils {
    public static final String USERS_URI = "https://jsonplaceholder.typicode.com/users";
    public static final String POSTS_URI = "https://jsonplaceholder.typicode.com/users";
    ObjectMapper objectMapper = new ObjectMapper();
    public void postUser(User user) throws URISyntaxException, IOException, InterruptedException,JsonProcessingException {

        String jsonUser = objectMapper.writeValueAsString(user);

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(USERS_URI))
                .header("content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonUser))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    public void putUser(User user, int id) throws IOException, URISyntaxException, InterruptedException {
        String uri =USERS_URI+"/"+id;

        String jsonUser = objectMapper.writeValueAsString(user);

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .header("content-type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonUser))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    public void deleteUser(int id) throws IOException, URISyntaxException, InterruptedException {
        String uri =USERS_URI+"/"+id;

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .header("content-type","application/json")
                .DELETE()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }
    public void getAllUsers() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(USERS_URI))
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        List<User>list=objectMapper.readValue(response.body(),new TypeReference<List<User>>() {});
        for (User user : list) {
            System.out.println(user);
        }
    }
    public void getUserById(int id) throws IOException, URISyntaxException, InterruptedException {
        String uri =USERS_URI+"/"+id;

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .header("content-type","application/json")
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    public void getUserByUserName(String username) throws IOException, URISyntaxException, InterruptedException {
        String uri = USERS_URI + "?username=" + username;

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .header("content-type", "application/json")
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
