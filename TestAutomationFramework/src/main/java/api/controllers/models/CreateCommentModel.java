package api.controllers.models;

import java.util.ArrayList;

public class CreateCommentModel {

    public int commentId;
    public String content;
    public ArrayList<Object> likes;
    public String date;
    public boolean liked;
}
