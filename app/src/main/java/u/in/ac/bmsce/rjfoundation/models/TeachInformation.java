package u.in.ac.bmsce.rjfoundation.models;

import java.util.ArrayList;

public class TeachInformation {


    ArrayList<Integer> mUserItems = new ArrayList<>();
    String name,age,profession,hqualification,contact,email,hours;

    public TeachInformation(ArrayList<Integer> mUserItems, String name, String age, String profession, String hqualification, String contact, String email, String hours) {
        this.mUserItems = mUserItems;
        this.name = name;
        this.age = age;
        this.profession = profession;
        this.hqualification = hqualification;
        this.contact = contact;
        this.email = email;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "TeachInformation{" +
                "mUserItems=" + mUserItems +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", profession='" + profession + '\'' +
                ", hqualification='" + hqualification + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", hours='" + hours + '\'' +
                '}';
    }

    public ArrayList<Integer> getmUserItems() {
        return mUserItems;
    }

    public void setmUserItems(ArrayList<Integer> mUserItems) {
        this.mUserItems = mUserItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHqualification() {
        return hqualification;
    }

    public void setHqualification(String hqualification) {
        this.hqualification = hqualification;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
