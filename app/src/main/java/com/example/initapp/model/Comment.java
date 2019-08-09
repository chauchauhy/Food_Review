package com.example.initapp.model;

public class Comment {
    private String commentID;
    private String commentContent;
    private String commentAuthor;
    private String commentAuthorID;
    private String commentTime;
    private String commentClick;
    private String postID;

    public Comment() {
    }

    public Comment(String commentID, String commentContent, String commentAuthor, String commentAuthorID, String commentTime, String commentClick, String postID) {
        this.commentID = commentID;
        this.commentContent = commentContent;
        this.commentAuthor = commentAuthor;
        this.commentAuthorID = commentAuthorID;
        this.commentTime = commentTime;
        this.commentClick = commentClick;
        this.postID = postID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentAuthorID() {
        return commentAuthorID;
    }

    public void setCommentAuthorID(String commentAuthorID) {
        this.commentAuthorID = commentAuthorID;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentClick() {
        return commentClick;
    }

    public void setCommentClick(String commentClick) {
        this.commentClick = commentClick;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID='" + commentID + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentAuthor='" + commentAuthor + '\'' +
                ", commentAuthorID='" + commentAuthorID + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", commentClick='" + commentClick + '\'' +
                ", postID='" + postID + '\'' +
                '}';
    }
}
