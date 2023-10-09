package api.controllers.models;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostModel {

        public int postId;
        public String content;
        public String picture;
        public String date;
        public ArrayList<Object> likes;
        public ArrayList<Object> comments;
        public int rank;
        @JsonProperty("public")
        public boolean mypublic;
        public boolean liked;
        public Object category;
}
