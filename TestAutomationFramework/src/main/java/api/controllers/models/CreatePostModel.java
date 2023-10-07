package api.controllers.models;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePostModel {
    public static class Category{
        public int id;
        public String name;
    }

    public static class Root{
        public int postId;
        public String content;
        public String picture;
        public String date;
        public ArrayList<Object> likes;
        public ArrayList<Object> comments;
        public int rank;
        public boolean liked;
        public Category category;
        @JsonProperty("public")
        public boolean mypublic;
    }

}
