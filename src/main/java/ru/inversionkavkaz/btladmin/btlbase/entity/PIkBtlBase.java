package ru.inversionkavkaz.btladmin.btlbase.entity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  porche
@since   2020/08/10 13:38:52
*/
@Entity (name="ru.inversionkavkaz.btladmin.btlbase.entity.PIkBtlBase")
@Table (name="IK_BTL_BASE")
public class PIkBtlBase extends IDMarkable implements Serializable
{
    private static final long serialVersionUID = 10_08_2020_13_38_52L;

    private Long ID;
    private LocalDateTime CREATED;
    private Long NUM;
    private Long REQ_TYPE;
    private LocalDateTime REQ_DATE;
    private BigDecimal MSUMM;
    private BigDecimal MSUMMR;
    private String CCURCODE;
    private String LIMIT_CODE;
    private String PAYER_NAME;
    private String PAYER_INN;
    private String PAYER_BANK_BIC;
    private String RECIP_NAME;
    private String RECIP_INN;
    private String CPURPPLATVO;
    private String CREATE_USR;
    private String AGREE_USR;
    private Long CANCEL_AGREE_NUM;
    private Long CANCEL_TRANSACTION_ID;
    private Long CANCEL_REASON_CODE;
    private String CANCEL_REASON_MESSAGE;
    private String DOCREF;
    private Long STATUS_CODE;
    private String STATUS_MESSAGE;
    private LocalDateTime STATUS_DATE;

    public PIkBtlBase(){}

    @Id 
    @Column(name="ID",nullable = false,length = 0)
    public Long getID() {
        return ID;
    }
    public void setID(Long val) {
        ID = val; 
    }
    @Column(name="CREATED",nullable = false)
    public LocalDateTime getCREATED() {
        return CREATED;
    }
    public void setCREATED(LocalDateTime val) {
        CREATED = val; 
    }
    @Column(name="NUM",nullable = false,length = 0)
    public Long getNUM() {
        return NUM;
    }
    public void setNUM(Long val) {
        NUM = val; 
    }
    @Column(name="REQ_TYPE",nullable = false,length = 1)
    public Long getREQ_TYPE() {
        return REQ_TYPE;
    }
    public void setREQ_TYPE(Long val) {
        REQ_TYPE = val; 
    }

    @Transient
    @ProxyFor (columnName = "REQ_TYPE")
    public ReqTypeEnum getReqTypeName()  {
        return ReqTypeEnum.fromDatabase (REQ_TYPE);
    }

    public void setReqTypeName(ReqTypeEnum val ) {
        setREQ_TYPE(Long.parseLong(ReqTypeEnum.toDatabase (val)));
    }

    @Column(name="REQ_DATE",nullable = false)
    public LocalDateTime getREQ_DATE() {
        return REQ_DATE;
    }
    public void setREQ_DATE(LocalDateTime val) {
        REQ_DATE = val; 
    }
    @Column(name="MSUMM",nullable = false,length = 10)
    public BigDecimal getMSUMM() {
        return MSUMM;
    }
    public void setMSUMM(BigDecimal val) {
        MSUMM = val; 
    }
    @Column(name="MSUMMR",nullable = false,length = 10)
    public BigDecimal getMSUMMR() {
        return MSUMMR;
    }
    public void setMSUMMR(BigDecimal val) {
        MSUMMR = val; 
    }
    @Column(name="CCURCODE",nullable = false,length = 3)
    public String getCCURCODE() {
        return CCURCODE;
    }
    public void setCCURCODE(String val) {
        CCURCODE = val; 
    }
    @Column(name="LIMIT_CODE",nullable = false,length = 1)
    public String getLIMIT_CODE() {
        return LIMIT_CODE;
    }
    public void setLIMIT_CODE(String val) {
        LIMIT_CODE = val; 
    }
    @Column(name="PAYER_NAME",nullable = false,length = 160)
    public String getPAYER_NAME() {
        return PAYER_NAME;
    }
    public void setPAYER_NAME(String val) {
        PAYER_NAME = val; 
    }
    @Column(name="PAYER_INN",nullable = false,length = 12)
    public String getPAYER_INN() {
        return PAYER_INN;
    }
    public void setPAYER_INN(String val) {
        PAYER_INN = val; 
    }
    @Column(name="PAYER_BANK_BIC",nullable = false,length = 9)
    public String getPAYER_BANK_BIC() {
        return PAYER_BANK_BIC;
    }
    public void setPAYER_BANK_BIC(String val) {
        PAYER_BANK_BIC = val; 
    }
    @Column(name="RECIP_NAME",nullable = false,length = 160)
    public String getRECIP_NAME() {
        return RECIP_NAME;
    }
    public void setRECIP_NAME(String val) {
        RECIP_NAME = val; 
    }
    @Column(name="RECIP_INN",nullable = false,length = 12)
    public String getRECIP_INN() {
        return RECIP_INN;
    }
    public void setRECIP_INN(String val) {
        RECIP_INN = val; 
    }
    @Column(name="CPURPPLATVO",nullable = false,length = 210)
    public String getCPURPPLATVO() {
        return CPURPPLATVO;
    }
    public void setCPURPPLATVO(String val) {
        CPURPPLATVO = val; 
    }
    @Column(name="CREATE_USR",nullable = false,length = 32)
    public String getCREATE_USR() {
        return CREATE_USR;
    }
    public void setCREATE_USR(String val) {
        CREATE_USR = val; 
    }
    @Column(name="AGREE_USR",length = 32)
    public String getAGREE_USR() {
        return AGREE_USR;
    }
    public void setAGREE_USR(String val) {
        AGREE_USR = val; 
    }
    @Column(name="CANCEL_AGREE_NUM",length = 0)
    public Long getCANCEL_AGREE_NUM() {
        return CANCEL_AGREE_NUM;
    }
    public void setCANCEL_AGREE_NUM(Long val) {
        CANCEL_AGREE_NUM = val; 
    }
    @Column(name="CANCEL_TRANSACTION_ID",length = 0)
    public Long getCANCEL_TRANSACTION_ID() {
        return CANCEL_TRANSACTION_ID;
    }
    public void setCANCEL_TRANSACTION_ID(Long val) {
        CANCEL_TRANSACTION_ID = val; 
    }
    @Column(name="CANCEL_REASON_CODE",length = 0)
    public Long getCANCEL_REASON_CODE() {
        return CANCEL_REASON_CODE;
    }
    public void setCANCEL_REASON_CODE(Long val) {
        CANCEL_REASON_CODE = val; 
    }
    @Column(name="CANCEL_REASON_MESSAGE",length = 160)
    public String getCANCEL_REASON_MESSAGE() {
        return CANCEL_REASON_MESSAGE;
    }
    public void setCANCEL_REASON_MESSAGE(String val) {
        CANCEL_REASON_MESSAGE = val; 
    }
    @Column(name="DOCREF",nullable = false,length = 32)
    public String getDOCREF() {
        return DOCREF;
    }
    public void setDOCREF(String val) {
        DOCREF = val; 
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
    @Column(name="STATUS_DATE",nullable = false)
    public LocalDateTime getSTATUS_DATE() {
        return STATUS_DATE;
    }
    public void setSTATUS_DATE(LocalDateTime val) {
        STATUS_DATE = val; 
    }


    @Transient
    @Override
    public Long getMarkLongID() {
        return getID();
    }
    @Override
    public boolean isMark() {
        return super.isMark();
    }
}