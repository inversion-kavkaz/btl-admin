package ru.inversionkavkaz.btladmin.entity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  bvv
@since   2020/08/07 18:17:42
*/
@Entity (name="ru.inversionkavkaz.btladmin.fog.entity.PIkBtlFog")
@Table (name="IK_BTL_FOG")
public class PIkBtlFog extends UMarkable implements Serializable
{
    private static final String serialVersionUID = "07_08_2020_18_17_42l";

    private String CFOGBIC;
    private String CFOGNAME;
    private Boolean ENABLED;
    private String PASSWORD;

    public PIkBtlFog(){}

    @Id 
    @Column(name="CFOGBIC",nullable = false,length = 9)
    public String getCFOGBIC() {
        return CFOGBIC;
    }
    public void setCFOGBIC(String val) {
        CFOGBIC = val; 
    }
    @Column(name="CFOGNAME",nullable = false,length = 160)
    public String getCFOGNAME() {
        return CFOGNAME;
    }
    public void setCFOGNAME(String val) {
        CFOGNAME = val; 
    }
    @Column(name="ENABLED",nullable = false,length = 1)
    public Boolean getENABLED() {
        return ENABLED;
    }
    public void setENABLED(Boolean val) {
        ENABLED = val; 
    }
    @Column(name="PASSWORD",nullable = false,length = 34)
    public String getPASSWORD() {
        return PASSWORD;
    }
    public void setPASSWORD(String val) {
        PASSWORD = val; 
    }
    @Transient
    @Override
    public String getMarkStringID() {
        return getCFOGBIC();
    }
    @Override
    public boolean isMark() {
        return super.isMark();
    }
}