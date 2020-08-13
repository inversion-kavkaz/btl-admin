package ru.inversionkavkaz.btladmin.cancelreason.entity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  porche
@since   2020/07/07 16:46:18
*/
@Entity (name="ru.inversionkavkaz.btlmanager.cancelreason.entity.PIkBtlCancelReason")
@Table (name="IK_BTL_CANCEL_REASON")
public class PIkBtlCancelReason implements Serializable
{
    private static final String serialVersionUID = "07_07_20_16_46_19l";

    private Long ID;
    private String REASON_SHORT;
    private String REASON_FULL;
    private String SUBITEM_SHORT;
    private String SUBITEM_FULL;

    public PIkBtlCancelReason(){}

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
    @Column(name="REASON_FULL",nullable = false,length = 512)
    public String getREASON_FULL() {
        return REASON_FULL;
    }
    public void setREASON_FULL(String val) {
        REASON_FULL = val; 
    }
    @Column(name="SUBITEM_SHORT",nullable = false,length = 64)
    public String getSUBITEM_SHORT() {
        return SUBITEM_SHORT;
    }
    public void setSUBITEM_SHORT(String val) {
        SUBITEM_SHORT = val; 
    }
    @Column(name="SUBITEM_FULL",nullable = false,length = 512)
    public String getSUBITEM_FULL() {
        return SUBITEM_FULL;
    }
    public void setSUBITEM_FULL(String val) {
        SUBITEM_FULL = val; 
    }
}