package com.example.recycleviewwithgrid;

public class BookModel {

    private String Title;
    private String Category;
    private String  Description;
    private int Thumbnail;

    public BookModel(String title, String category, String description, int thumbnail) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "Title='" + Title + '\'' +
                ", Category='" + Category + '\'' +
                ", Description='" + Description + '\'' +
                ", Thumbnail=" + Thumbnail +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }


}
