package com.aleksandrova.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String libraryName;

    @Column(name = "readers_count")
    private int readersCount;

    @Column(name = "status")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public int getReadersCount() {
        return readersCount;
    }

    public void setReadersCount(int readersCount) {
        this.readersCount = readersCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + libraryName + '\'' +
                ", readersCount=" + readersCount +
                ", status='" + status + '\'' +
                '}';
    }
}
