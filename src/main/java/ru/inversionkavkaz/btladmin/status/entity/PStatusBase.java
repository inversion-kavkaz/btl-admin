package ru.inversionkavkaz.btladmin.status.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 * @author bvv
 * @since 2019/07/29 14:26:29
 */
@Entity(name = "ru.inversionkavkaz.btladmin.status.entity.PStatusBase")
@NamedNativeQuery(name = "ru.inversionkavkaz.btladmin.status.entity.PStatusBase",
        query = "select id, cname from xxi.\"ik_btl_base_stats\" order by id"
)
public class PStatusBase implements Serializable {

    private static final long serialVersionUID = 29_07_2019_14_26_29l;

    private Long ID;
    private String CNAME;

    public PStatusBase() {
    }

    @Id
    @Column(name = "ID", nullable = false)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Column(name = "CNAME", nullable = false, length = 255)
    public String getCNAME() {
        return CNAME;
    }

    public void setCNAME(String val) {
        CNAME = val;
    }
}
