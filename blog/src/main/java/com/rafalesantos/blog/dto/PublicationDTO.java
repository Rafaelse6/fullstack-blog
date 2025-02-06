package com.rafalesantos.blog.dto;

import com.rafalesantos.blog.entities.Publication;

public class PublicationDTO {

    private Long id;
    private String title;
    private String content;
    private String category;

    public PublicationDTO(){

    }
    public PublicationDTO(Long id, String title, String content, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public PublicationDTO(Publication entity){
        id = entity.getId();
        title = entity.getTitle();
        content = entity.getContent();
        category = entity.getCategory();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }
}
