package com.zabello.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class RailwayTicket {

    private Long id;

    private String type;

    private Integer trainNumber;

    private BigDecimal cost;

    private String classOfService;

    public RailwayTicket() {
    }

    public RailwayTicket(Long id, String type, Integer trainNumber, BigDecimal cost, String classOfService) {
        this.id = id;
        this.type = type;
        this.trainNumber = trainNumber;
        this.cost = cost;
        this.classOfService = classOfService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getClassOfService() {
        return classOfService;
    }

    public void setClassOfService(String classOfService) {
        this.classOfService = classOfService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RailwayTicket that = (RailwayTicket) o;
        return id.equals(that.id) &&
                type.equals(that.type) &&
                trainNumber.equals(that.trainNumber) &&
                cost.equals(that.cost) &&
                classOfService.equals(that.classOfService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, trainNumber, cost, classOfService);
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nType: " + type +
                "\nTrainNumber: " + trainNumber +
                "\nCost: " + cost +
                "\nService class: " + classOfService+"\n";
    }
}
