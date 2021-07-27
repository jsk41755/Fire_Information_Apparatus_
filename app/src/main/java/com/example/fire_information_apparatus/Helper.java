package com.example.fire_information_apparatus;

public class Helper {
    String Object_Name;
    String By_Place;
    String Old_Address;
    String New_Address;
    String Object_Manager;
    String Manager_General_Telephone;
    String Manager_Cell_Phone;


    public Helper() {
    }

    public Helper(String Object_Name, String By_Place, String Old_Address, String New_Address, String Manager_General_Telephone, String Manager_Cell_Phone) {
        this.Object_Name = Object_Name;
        this.By_Place = By_Place;
        this.Old_Address = Old_Address;
        this.New_Address = New_Address;
        this.Object_Manager = Object_Manager;
        this.Manager_General_Telephone = Manager_General_Telephone;
        this.Manager_Cell_Phone = Manager_Cell_Phone;
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

    public String getObject_Manager() {
        return Object_Manager;
    }

    public void setObject_Manager(String object_Manager) {
        Object_Manager = object_Manager;
    }


    public String getManager_General_Telephone() {
        return Manager_General_Telephone;
    }

    public void setManager_General_Telephone(String manager_General_Telephone) {
        Manager_General_Telephone = manager_General_Telephone;
    }

    public String getManager_Cell_Phone() {
        return Manager_Cell_Phone;
    }

    public void setManager_Cell_Phone(String manager_Cell_Phone) {
        Manager_Cell_Phone = manager_Cell_Phone;
    }
}
