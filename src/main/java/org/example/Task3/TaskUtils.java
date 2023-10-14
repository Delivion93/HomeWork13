package org.example.Task3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class TaskUtils {
    ObjectMapper objectMapper = new ObjectMapper();
    public void printOpenTasks(int userId) throws URISyntaxException, IOException, InterruptedException {
        String uri = String.format("https://jsonplaceholder.typicode.com/users/%d/todos",userId);

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        List<Todo> resultList = objectMapper.readValue(response.body(), new TypeReference<List<Todo>>() {
        }).stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
        for (Todo todo : resultList) {
            System.out.println(todo);
        }
    }


}
