package com.task.customer.entity;

import com.task.customer.exception.FieldCorrectException;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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

    private boolean activate;


    public static boolean fullNameIsCorrect(String firstName) {
        return firstName.matches("^[A-Z][a-z]{2,}(?: [A-Z][a-z]*)*$");
    }

    public void setFullName(String fullName) {
        if (fullNameIsCorrect(fullName) == true) {
            this.fullName = fullName;
        } else {
            throw new FieldCorrectException("FullName is not correct");
        }
    }


    public static  boolean emailIsCorrect(String email){
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public void setEmail(String email) {
        if (emailIsCorrect(email) == true){
            this.email = email;
        }else {
            throw new FieldCorrectException("Email is not correct");
        }
    }


    public static boolean phoneNumberIsCorrect(String phoneNumber) {
        boolean correctPhone = false;
        if (phoneNumber != null){
            correctPhone =  phoneNumber.matches("^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$");
        }
        return correctPhone;

    }

    public void setPhone(String phone) {
        if (phone != null) {
            if (phoneNumberIsCorrect(phone) == true || !phone.startsWith("+")) {
                this.phone = "+" + phone;
            }else{
                throw  new FieldCorrectException("Phone number is not correct!");
            }
        }else {
            this.phone = null;
        }

    }
}
