package com.laurencewelch;

/**
 * Created by laurencewelch on 11/27/14.
 */
public class Language {
  private String name, startComment, lineComment, endComment, singleComment; //singleComment is for single license files

  public Language(String name, String startComment, String lineComment, String endComment, String singleComment) {
    this.name = name;
    this.startComment = startComment;
    this.lineComment = lineComment;
    this.endComment = endComment;
    this.singleComment = singleComment;
  }

  public String getName() {
    return name;
  }

  public String getStartComment() {
    return startComment;
  }

  public String getLineComment() {
    return lineComment;
  }

  public String getEndComment() {
    return endComment;
  }

  public String getSingleComment() {
    return singleComment;
  }
}
