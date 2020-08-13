package ru.inversionkavkaz.btladmin.rejectreason.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import javax.persistence.*;

/**
@author  porche
@since   2020/07/06 10:55:41
*/
@Entity (name="ru.inversionkavkaz.btlmanager.rejectreason.entity.PIkBtlRejectReason")
@Table (name="IK_BTL_REJECT_REASON")
public class PIkBtlRejectReason implements Serializable
{
    private static final long serialVersionUID = 06_07_2020_10_55_41l;

    private Long ID;
    private String REASON_SHORT;
    private String REASON_FULL;

    public PIkBtlRejectReason(){}

    @Id 
    @Column(name="ID",nullable = false,length = 0)
    public Long getID() {
        return ID;
    }
    public void setID(Long val) {
        ID = val; 
    }
    @Column(name="REASON_SHORT",nullable = false,length = 64)
    public String getREASON_SHORT() {
        return REASON_SHORT;
    }
    public void setREASON_SHORT(String val) {
        REASON_SHORT = val; 
    }
    @Column(name="REASON_FULL",length = 512)
    public String getREASON_FULL() {
        return REASON_FULL;
    }
    public void setREASON_FULL(String val) {
        REASON_FULL = val; 
    }
}