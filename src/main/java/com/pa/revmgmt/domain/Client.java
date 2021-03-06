package com.pa.revmgmt.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Size(max = 10, min=10)
    @NotNull
    private String phoneNumber;

    @Column(nullable = false)
    private String contactName;

    @Column(nullable = false)
    private ClientType clientType;

    public Client(){}

    public Client(String name, @Size(max = 10, min = 10) String phoneNumber, String contactName, ClientType clientType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
        this.clientType = clientType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactName='" + contactName + '\'' +
                ", clientType=" + clientType +
                '}';
    }
}
