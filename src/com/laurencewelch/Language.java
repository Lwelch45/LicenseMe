package com.laurencewelch;

/**
 * Created by laurencewelch on 11/27/14.
 */
public class Language {
    private String name, startComment, lineComment, endComment;

    public Language(String name, String startComment, String lineComment, String endComment){
        this.name = name;
        this.startComment = startComment;
        this.lineComment = lineComment;
        this.endComment = endComment;
    }

    public String getName(){
        return name;
    }

    public String getStartComment(){
        return startComment;
    }

    public String getLineComment(){
        return lineComment;
    }

    public String getEndComment(){
        return endComment;
    }
}
