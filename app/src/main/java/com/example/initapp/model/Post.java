package com.example.initapp.model;

public class Post {
    private String postID;
    private String postTitle;
    private String postContent;
    private String postAuthor;
    private String postAuthorID;
    private String postImage;
    private String postTime;
    private String postClick;
    private String postResID;
    private String postResName;

    public Post() {
    }

    public Post(String postID, String postTitle, String postContent, String postAuthor, String postAuthorID, String postImage, String postTime, String postClick, String postResID, String postResName) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postAuthor = postAuthor;
        this.postAuthorID = postAuthorID;
        setPostImage(postImage);
        this.postTime = postTime;
        this.postClick = postClick;
        this.postResID = postResID;
        this.postResName = postResName;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostAuthorID() {
        return postAuthorID;
    }

    public void setPostAuthorID(String postAuthorID) {
        this.postAuthorID = postAuthorID;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        if(postImage.length()<1){
        this.postImage = "NA";
    }else{
            this.postImage = postImage;
        }

    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostClick() {
        return postClick;
    }

    public void setPostClick(String postClick) {
        this.postClick = postClick;
    }

    public String getPostResID() {
        return postResID;
    }

    public void setPostResID(String postResID) {
        this.postResID = postResID;
    }

    public String getPostResName() {
        return postResName;
    }

    public void setPostResName(String postResName) {
        this.postResName = postResName;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID='" + postID + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postAuthor='" + postAuthor + '\'' +
                ", postAuthorID='" + postAuthorID + '\'' +
                ", postImage='" + postImage + '\'' +
                ", postTime='" + postTime + '\'' +
                ", postClick='" + postClick + '\'' +
                ", postResID='" + postResID + '\'' +
                ", postResName='" + postResName + '\'' +
                '}';
    }
}
