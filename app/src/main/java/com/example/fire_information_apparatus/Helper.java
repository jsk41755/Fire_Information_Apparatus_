package com.example.fire_information_apparatus;

public class Helper {
    String Object_Name, By_Place, Old_Address, New_Address;

    public Helper() {
    }

    public Helper(String Object_Name, String By_Place, String Old_Address, String New_Address) {
        this.Object_Name = Object_Name;
        this.By_Place = By_Place;
        this.Old_Address = Old_Address;
        this.New_Address = New_Address;
    }

    public String getObject_Name() {
        return Object_Name;
    }

    public void setObject_Name(String Object_Name) {
        this.Object_Name = Object_Name;
    }

    public String getBy_Place() {
        return By_Place;
    }

    public void setBy_Place(String By_Place) {
        this.By_Place = By_Place;
    }

    public String getOld_Address() {
        return Old_Address;
    }

    public void setOld_Address(String Old_Address) {
        this.Old_Address = Old_Address;
    }

    public String getNew_Address() {
        return New_Address;
    }

    public void setNew_Address(String New_Address) {
        this.New_Address = New_Address;
    }
}
