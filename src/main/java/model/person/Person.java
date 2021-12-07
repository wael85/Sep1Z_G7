package model.person;

import java.util.Objects;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;

    public Person(String firstName, String lastName, String email, String telephoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    public abstract String getFullName();
    public String toString(){
        return "First name" + firstName + " Last Name" + lastName + " Email" + email + " Telephone number" + telephoneNumber;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName) && Objects.equals(email, other.email) && Objects.equals(telephoneNumber, other.telephoneNumber);
    }
}
