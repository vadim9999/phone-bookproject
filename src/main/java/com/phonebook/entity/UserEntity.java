package com.phonebook.entity;

import com.phonebook.annotations.Username;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Vadim on 31.08.2017.
 */
@Entity
@Table(name="users")
public class UserEntity {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min = 5,max = 15, message = "First name must be between 5 and 15 ")
    private String firstName;
    @NotNull
    @Size(min = 5, max = 15, message = "Last name must be between 5 and 15 ")
    private String lastName;
    @NotNull
    @Size(min = 5, max = 15, message = "Middle name must be between 5 and 15 ")
    private String middleName;

    @NotNull
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 ")
    @Username(message = "Incorrect username")
    private String username;

    @NotNull
    @Column(name = "password", length = 61)
    @Size(min = 5, max = 61, message = "Password must be between 5 and 10 ")
    private String password;

    // orphanRemoval = true that means that if you change
    // a List of ContactEntity it will change in DB
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ContactEntity> phoneBook;

    public List<ContactEntity> getPhoneBook() {
        return phoneBook;
    }
    public boolean phoneBookIsEmpty(){

        if(phoneBook != null){
            if (phoneBook.isEmpty()) return true;
            else return false;
        }
        throw new NullPointerException();
    }

    public void removeContact(ContactEntity contactEntity){
        phoneBook.remove(contactEntity);
    }



    public void setPhoneBook(List<ContactEntity> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public void setContact(ContactEntity contactEntity){
        phoneBook.add(contactEntity);
    }

    public UserEntity(List<ContactEntity> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public long getId() {
        return id;
    }

    public UserEntity() {

    }

    public UserEntity(String firstName, String lastName, String middleName, String username, String password
    ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.username = username;
        this.password = password;
//        this.phoneBook = phoneBook;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + middleName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneBook=" + phoneBook +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return phoneBook != null ? phoneBook.equals(that.phoneBook) : that.phoneBook == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phoneBook != null ? phoneBook.hashCode() : 0);
        return result;
    }
}
