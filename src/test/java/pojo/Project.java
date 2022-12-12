package pojo;

import com.fasterxml.jackson.annotation.*;

@JsonPropertyOrder({"id","name", "color", "favorite"})
public class Project {

    private Long id;
    private String name;
    private String color;
    private Boolean favorite;

    public Project(){

       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}