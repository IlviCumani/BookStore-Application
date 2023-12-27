package StaffFolder;

import StaffFolder.AccessLevels.AccessLevel;

import java.io.Serializable;
import java.util.Date;

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
        return this.accessLevel == null ? null : this.accessLevel.getAccessLevel();
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Worker))
            return false;

        return (this.fullname == null ? ((Worker)o).getFullname() == null : this.fullname.equals(((Worker)o).getFullname())) &&
                (this.email == null ? ((Worker)o).getEmail() == null : this.email.equals(((Worker)o).getEmail())) &&
                (this.phone == null ? ((Worker)o).getPhone() == null : this.phone.equals(((Worker)o).getPhone())) &&
                (this.password == null ? ((Worker)o).getPassword() == null : this.password.equals(((Worker)o).getPassword())) &&
                (this.dateOfBirth == null ? ((Worker)o).getDateOfBirth() == null : this.dateOfBirth.equals(((Worker)o).getDateOfBirth())) &&
                (this.getAccessLevelName() == null ? ((Worker)o).getAccessLevelName() == null : this.getAccessLevelName().equals(((Worker)o).getAccessLevelName())) &&
                (this.salary == ((Worker)o).getSalary());
    }

}
