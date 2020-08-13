package ru.inversionkavkaz.btladmin.limits.entity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  porche
@since   2020/07/05 15:53:17
*/
@Entity (name="ru.inversionkavkaz.btlmanager.limits.entity.PIkBtlLim")
@Table (name="IK_BTL_LIM")
public class PIkBtlLim implements Serializable
{
    private static final long serialVersionUID = 05_07_2020_15_53_17l;

    private String ID;
    private BigDecimal MSUMM;
    private String VALIDITY;
    private String VALIDITY_NM;
    private String NREZCALC;
    private String CDESCR;
    boolean NREZCALC_BOOLEAN;

    public PIkBtlLim(){}

    @Id 
    @Column(name="ID",nullable = false,length = 1)
    public String getID() {
        return ID;
    }
    public void setID(String val) {
        ID = val; 
    }
    @Column(name="MSUMM",nullable = false,length = 0)
    public BigDecimal getMSUMM() {
        return MSUMM;
    }
    public void setMSUMM(BigDecimal val) {
        MSUMM = val; 
    }
    @Column(name="VALIDITY",nullable = false,length = 1)
    public String getVALIDITY() {
        return VALIDITY;
    }

    @Transient
    public String getVALIDITY_NM() {
        if(getVALIDITY().equals("M"))
            return "Месяц";
        else
        if(getVALIDITY().equals("Y"))
            return "Год";
        return "?";
    }

    public void setVALIDITY(String val) {
        VALIDITY = val; 
    }
    @Column(name="NREZCALC",nullable = false,length = 1)
    public String getNREZCALC() {
        return NREZCALC;
    }
    public void setNREZCALC(String val) {
        NREZCALC = val; 
    }
    @Column(name="CDESCR",length = 512)
    public String getCDESCR() {
        return CDESCR;
    }
    public void setCDESCR(String val) {
        CDESCR = val; 
    }

    @Transient
    public Boolean isNREZCALC_BOOLEAN() {
        return getNREZCALC()!=null && getNREZCALC().equals("Y");
    }
}