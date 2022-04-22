package com.task.customer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    private long id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    private String phone;

    private long created;

    private long updated;

    private boolean is_active;


    public static boolean firstName(String firstName) {
        return firstName.matches("^[A-Z][a-z]{2,}(?: [A-Z][a-z]*)*$");
    }

    public void setFullName(String fullName) {
        if (firstName(fullName) == true) {
            this.fullName = fullName;
        } else {
            throw new RuntimeException("FullName is not correct");
        }
    }


    public void setCreated(long created) {
        Date dateNow = new Date();
        created = dateNow.getTime();
        this.created = created;
    }


    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new RuntimeException("\n" +
                    "Email must have the @ symbol");
        }
        this.email = email;
    }

    public void setIs_NoActive(boolean is_noActive) {
        this.is_active = false;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = true;
    }

    public static boolean phoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");

    }

    public void setPhone(String phone) {
        if (phoneNumber(phone) == true) {
            this.phone = phone;
        } else {
            throw new RuntimeException("Phone number is not correct");
        }
    }
}
