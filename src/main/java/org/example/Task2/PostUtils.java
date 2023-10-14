package org.example.Task2;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PostUtils {
    ObjectMapper objectMapper = new ObjectMapper();
    private List<Post> getListOfPosts(int userId) throws URISyntaxException, IOException, InterruptedException {
        String postUri = String.format("https://jsonplaceholder.typicode.com/users/%d/posts",userId);

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(postUri))
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(),new TypeReference<List<Post>>() {});
    }

    private int findMaxPostId(List<Post>list){
        int maxId=list.get(0).getId();
        for (Post post : list) {
            if(post.getId()>maxId){
                maxId=post.getId();
            }
        }
        return maxId;
    }
    public void lastCommentsToFileByUserId(int userId) throws URISyntaxException, IOException, InterruptedException {
        //user-X-post-Y-comments.json де Х - id користувача, Y - номер посту.

        List<Post>postList=getListOfPosts(userId);
        List<Post> lastComment = postList.stream().max(new PostComparator()).stream().toList();
        Post post = lastComment.get(0);
        String json = post.getBody();


        try (FileWriter fw = new FileWriter(String.format("user-%d-post-%d-comments.json",post.getUserId(),post.getId()))){
            fw.write(json);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        PostUtils postUtils = new PostUtils();
        postUtils.lastCommentsToFileByUserId(5);
    }

}
