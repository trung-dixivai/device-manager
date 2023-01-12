package models;

public class Category {
    private int id;
    private  String name;

    public Category() {
    }

    public Category(int id, String name, Boolean isRent) {
        this.id = id;
        this.name = name;
        this.isRent = isRent;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRent() {
        return isRent;
    }

    public void setRent(Boolean rent) {
        isRent = rent;
    }

    @Override
    public String toString() {
        return  id + " - " + name
                ;
    }

    private Boolean isRent;


}
