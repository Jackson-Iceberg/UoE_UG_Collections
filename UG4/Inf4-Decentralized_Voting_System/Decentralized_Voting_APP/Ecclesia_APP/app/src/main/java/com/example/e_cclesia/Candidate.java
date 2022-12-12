package com.example.e_cclesia;

public class Candidate {
    private String name;
    private int YearOfBirth;
    private String gender;
    private String UniqueID;

    public Candidate(String name, int Age, String gender,String UniqueID){
        this.name = name;
        this.YearOfBirth = Age;
        this.gender = gender;
        this.UniqueID = UniqueID;
    }

    public String getName() {
        return name;
    }

    public String getUniqueID() {
        return UniqueID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniqueID(String uniqueID) {
        UniqueID = uniqueID;
    }

    public void setAge(int age) {
        YearOfBirth = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public int getAge() {
        return YearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", Age=" + YearOfBirth +
                ", gender='" + gender + '\'' +
                ", UniqueID='" + UniqueID + '\'' +
                '}';
    }
}
