package ru.inversionkavkaz.btladmin.statusau.entity;

import java.time.*;
import java.io.Serializable;
import javax.persistence.*;

/**
@author  bvv
@since   2020/08/13 10:49:36
*/
@Entity (name="ru.inversionkavkaz.btladmin.statusau.entity.PIkBtlBaseStatsAu")
@Table (name="IK_BTL_BASE_STATS_AU")
public class PIkBtlBaseStatsAu implements Serializable
{
    private static final long serialVersionUID = 13_08_2020_10_49_36l;

    private Long ID;
    private Long BASEID;
    private LocalDateTime DATE_OP;
    private Long STATUS_CODE;
    private String STATUS_MESSAGE;
    private String USER_OP;

    public PIkBtlBaseStatsAu(){}

    @Id 
    @Column(name="ID",nullable = false,length = 0)
    public Long getID() {
        return ID;
    }
    public void setID(Long val) {
        ID = val; 
    }
    @Column(name="BASEID",nullable = false,length = 0)
    public Long getBASEID() {
        return BASEID;
    }
    public void setBASEID(Long val) {
        BASEID = val; 
    }
    @Column(name="DATE_OP",nullable = false)
    public LocalDateTime getDATE_OP() {
        return DATE_OP;
    }
    public void setDATE_OP(LocalDateTime val) {
        DATE_OP = val; 
    }
    @Column(name="STATUS_CODE",nullable = false,length = 0)
    public Long getSTATUS_CODE() {
        return STATUS_CODE;
    }
    public void setSTATUS_CODE(Long val) {
        STATUS_CODE = val; 
    }
    @Column(name="STATUS_MESSAGE",length = 255)
    public String getSTATUS_MESSAGE() {
        return STATUS_MESSAGE;
    }
    public void setSTATUS_MESSAGE(String val) {
        STATUS_MESSAGE = val; 
    }
    @Column(name="USER_OP",nullable = false,length = 32)
    public String getUSER_OP() {
        return USER_OP;
    }
    public void setUSER_OP(String val) {
        USER_OP = val; 
    }
}