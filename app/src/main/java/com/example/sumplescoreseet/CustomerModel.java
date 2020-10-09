package com.example.sumplescoreseet;

public class CustomerModel {

    private int id;
    private String name;
    private int age;
    private boolean isAction;

    public CustomerModel(int id, String name, int age, boolean isAction) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isAction = isAction;
    }

    public CustomerModel() {
    }
    //toStrings


    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isAction=" + isAction +
                '}';
    }

    //getter setter
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAction() {
        return isAction;
    }

    public void setAction(boolean action) {
        isAction = action;
    }
}
