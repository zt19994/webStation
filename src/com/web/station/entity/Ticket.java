package com.web.station.entity;

public class Ticket {
    private Integer id;
    private String startStation;
    private String stopStation;
    private String departureTime;
    private Double price;
    private Integer ticketNum;
    private String createTime;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getStopStation() {
        return stopStation;
    }

    public void setStopStation(String stopStation) {
        this.stopStation = stopStation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", startStation='" + startStation + '\'' +
                ", stopStation='" + stopStation + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", price=" + price +
                ", ticketNum=" + ticketNum +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
