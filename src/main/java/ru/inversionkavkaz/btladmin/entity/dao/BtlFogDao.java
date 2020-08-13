package ru.inversionkavkaz.btladmin.entity.dao;

import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.fx.form.AbstractBaseController;
import ru.inversionkavkaz.btladmin.entity.PIkBtlFog;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BtlFogDao extends BaseDao {

    private static final String RU_INVERSIONKAVKAZ_BTLADMIN_CONTROLLER_RES_PLSQL_DEF_XML = "/ru/inversionkavkaz/btladmin/controller/res/plsql/def.xml";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    BtlFogDao(Connection connection) {
        super(connection);
    }

    public void insert(PIkBtlFog btlFog, boolean needCommit, AbstractBaseController controller) throws Exception {
        setBtlFog(btlFog, MODE.INSERT, needCommit, controller);
    }

    public void update(PIkBtlFog btlFog, AbstractBaseController controller) throws Exception {
        setBtlFog(btlFog, MODE.UPDATE, true, controller);
    }

    public void delete(PIkBtlFog btlFog, AbstractBaseController controller) throws Exception {
        setBtlFog(btlFog, MODE.DELETE, true, controller);
    }

    private ParamMap buildParamMap(PIkBtlFog btlFog, MODE mode){
        ParamMap pm = new ParamMap();
        pm.add("id",btlFog.getCFOGBIC())
                .add("cfogbic", btlFog.getCFOGBIC())
                .add("enabled", btlFog.getENABLED()?1:0)
                .add("password", btlFog.getPASSWORD())
                .add("cmode", mode.val);
        return pm;
    }

    private void setBtlFog(PIkBtlFog btlFog, MODE mode, boolean needCommit, AbstractBaseController controller) throws Exception {
        if (btlFog == null) return;

        ParamMap pm = buildParamMap(btlFog, mode);

        pm.exec(controller, this.getClass().getResource(RU_INVERSIONKAVKAZ_BTLADMIN_CONTROLLER_RES_PLSQL_DEF_XML), "SetBtlFog");
        String errm = (String) pm.get("verr");
        if(errm!=null){
            throw new Exception(errm);
        }
        if(needCommit)
            controller.getTaskContext().commit();
    }

    private String getDateFormatted(LocalDate date) {
        if (date != null) {
            return date.format(formatter);
        } else {
            return null;
        }
    }

}