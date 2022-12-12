package com.example.e_cclesia;

public class Voter {
    private String name;
    private String UniqueID;

    public Voter(String name, String uniqueID) {
        this.name = name;
        UniqueID = uniqueID;
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

    @Override
    public String toString() {
        return "Voter{" +
                "name='" + name + '\'' +
                ", UniqueID='" + UniqueID + '\'' +
                '}';
    }
}
