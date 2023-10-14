package org.example;

import org.example.Task1.*;
import org.example.Task2.PostUtils;
import org.example.Task3.TaskUtils;

import java.io.IOException;
import java.net.URISyntaxException;


public class Demo {


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpUtils httpUtils = new HttpUtils();
        PostUtils postUtils = new PostUtils();
        TaskUtils taskUtils = new TaskUtils();

        User user = new User("Artem Artemov"
                , "AA"
                , "aa1988@gmail.com"
                , new Address("Ewaldstrasse"
                    , "HHHTT", "New Yourk"
                    , "777890-99"
                    , new Geo(23.666f, 98.656f))
                    , "8889900-5544", "www.jd.org"
                    , new Company("Company", "jd", "bs"));

//        httpUtils.postUser(user);
//        httpUtils.putUser(user,5);
//        httpUtils.deleteUser(3);
//        httpUtils.getAllUsers();
//        httpUtils.getUserById(5);
//        httpUtils.getUserByUserName("Samantha");

//        postUtils.lastCommentsToFileByUserId(5);
//        postUtils.lastCommentsToFileByUserId(6);
//        postUtils.lastCommentsToFileByUserId(7);

        taskUtils.printOpenTasks(8);


    }

}
