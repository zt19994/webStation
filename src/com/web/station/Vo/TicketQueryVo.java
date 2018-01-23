package com.web.station.Vo;

import org.apache.commons.lang.StringUtils;

/**
 * 车票页面查询对象
 */
public class TicketQueryVo {
    private String startStation;
    private String stopStation;
    private String minTime;
    private String maxTime;

    private Integer pageNum = 1;
    private Integer pageSize = 5;

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

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMaxTime() {
        //最大时间需要到23:59:59
        if (StringUtils.isNotBlank(maxTime)){
            maxTime = maxTime + " 23:59:59";
        }
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "TicketQueryVo{" +
                "startStation='" + startStation + '\'' +
                ", stopStation='" + stopStation + '\'' +
                ", minTime='" + minTime + '\'' +
                ", maxTime='" + maxTime + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
