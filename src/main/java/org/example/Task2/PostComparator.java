package org.example.Task2;

import java.util.Comparator;

public class PostComparator implements Comparator<Post> {

    @Override
    public int compare(Post o1, Post o2) {
        if(o1.getId()>o2.getId()){
            return 1;
        }
        else if(o2.getId()>o1.getId()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
