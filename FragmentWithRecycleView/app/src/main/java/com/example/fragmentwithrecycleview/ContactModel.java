package com.example.fragmentwithrecycleview;

public class ContactModel {
  private String Name;
  private String Phone;
  private int photo;


    @Override
    public String toString() {
        return "ContactModel{" +
                "Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", photo=" + photo +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public ContactModel(String name, String phone, int photo) {
        Name = name;
        Phone = phone;
        this.photo = photo;
    }



}
