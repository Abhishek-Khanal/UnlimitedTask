package com.example.abhishektask.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "bio_table")
public class Bio {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int age;

    private String name;

    private String address;

    private String gender;

    private String salary;

    private String phoneNumber;

    private Date dob;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;


    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getSalary() {
        return salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getImage() {
        return image;
    }

    public Bio( String name, String address, String gender, String salary, String phoneNumber, Date dob, byte[] image) {
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.image = image;
    }
}
