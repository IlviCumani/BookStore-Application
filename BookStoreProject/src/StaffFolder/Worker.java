package StaffFolder;

import java.io.Serializable;
import java.util.Date;
import StaffFolder.AccessLevels.*;

public class Worker implements Serializable {
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private Date dateOfBirth;
    private AccessLevel accessLevel;
    private double salary;


    public Worker(String fullname, String email, String password, String phone, Date dateOfBirth, AccessLevel accessLevel, double salary) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.accessLevel = accessLevel;
        this.salary = salary;
    }

    public Worker() {

    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public double getSalary() {
        return salary;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevelName() {
        return this.accessLevel.getAccessLevel();
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accessLevel=" + getAccessLevelName() +
                ", salary=" + salary +
                '}';
    }

}
