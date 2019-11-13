package com.lemycanh.citycriminal;

import java.util.Date;

/**
 * Created by lemycanh on 13/11/2019.
 */

public class Problem {
    String  title;
    String  content;
    String  imageUrl;
    Date    timestamp;
    boolean resolved;

    public Problem() {
    }

    public Problem(String title, String content, boolean resolved) {
        this.title = title;
        this.content = content;
        this.resolved = resolved;
    }

    public Problem(String title, String content, String imageUrl, Date timestamp, boolean resolved) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.resolved = resolved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
