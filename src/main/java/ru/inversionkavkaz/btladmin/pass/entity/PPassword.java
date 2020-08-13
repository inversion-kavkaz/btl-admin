package ru.inversionkavkaz.btladmin.pass.entity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  porche
@since   2020/08/09 17:29:24
*/
//@Entity (name="ru.inversionkavkaz.btladmin.pass.entity.PDualpasswd")
//@Table (name="DUALPASSWD")
public class PPassword implements Serializable
{
    private static final String serialVersionUID = "09_08_2020_17_29_24l";

    private String PASS1;
    private String PASS2;
    private Boolean ENCODEPASSWORD;

    public PPassword(){}

    public String getPASS1() {
        return PASS1;
    }
    public void setPASS1(String val) {
        PASS1 = val; 
    }

    public String getPASS2() {
        return PASS2;
    }
    public void setPASS2(String val) {
        PASS2 = val; 
    }

    public Boolean getENCODEPASSWORD() {
        return ENCODEPASSWORD;
    }

    public void setEENCODEPASSWORD(Boolean encodePassword) {
        this.ENCODEPASSWORD = encodePassword;
    }
}