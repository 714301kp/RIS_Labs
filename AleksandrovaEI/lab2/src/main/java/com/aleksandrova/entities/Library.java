package com.zabello.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Library {

    private Long id;

    private String name;

    private String status;

    private BigDecimal readersCount;

    public Library() {
    }

    public Library(Long id, String name, String status, BigDecimal readerCount) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.readersCount = readerCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getReadersCount() {
        return readersCount;
    }

    public void setReadersCount(BigDecimal readersCount) {
        this.readersCount = readersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library that = (Library) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                status.equals(that.status) &&
                readersCount.equals(that.readersCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, readersCount);
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nType: " + name +
                "\nTrainNumber: " + status +
                "\nCost: " + readersCount +"\n";
    }
}
