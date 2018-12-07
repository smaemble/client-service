package com.pa.revmgmt.domain;

import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;

public class ClientId implements Serializable {

    private Integer id;

    private String phoneNumber;

    public ClientId(){}

    public ClientId(Integer id){
        this.id = id;
    }

    public ClientId(Integer id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientId clientId = (ClientId) o;
        return Objects.equals(id, clientId.id) &&
                Objects.equals(phoneNumber, clientId.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber);
    }
}
