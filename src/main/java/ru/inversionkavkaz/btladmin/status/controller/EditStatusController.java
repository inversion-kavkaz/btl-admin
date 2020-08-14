package ru.inversionkavkaz.btladmin.status.controller;

import javafx.fxml.FXML;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvComboBox;
import ru.inversionkavkaz.btladmin.status.entity.PStatusBase;
//import static ru.inversionkavkaz.taxex.utils.TaxexConstant.IK_TAXEX_DEF;
//import static ru.inversionkavkaz.taxex.utils.TaxexConstant.IK_TAXEX_SET_NEW_STATUS;

public class EditStatusController extends JInvFXFormController<PStatusBase> {
    @FXML
    private JInvComboBox<Long, PStatusBase> STYPE;

    protected void init() throws Exception {
        super.init();
        this.setTitle(this.getBundleString("EDIT.TITLE"));

//        SQLDataSet<PStatusBase> dsStatusFile;
//        dsStatusFile = populateDataSet(PStatusBase.class, null, null, "ID", 2);
//        DataSetStringConverter<PStatusBase, Long> scRstType = new DataSetStringConverter<>(dsStatusFile, PStatusBase::getID, PStatusBase::getID);
//
//        STYPE.setConverter(scRstType);
//        STYPE.getItems().addAll(scRstType.keySet());
    }

    protected boolean onOK() {
//        if (STYPE.getValue() == null) {
//            Alerts.error(this, "Укажите новый статус.");
//            return false;
//        }
//        ParamMap m1 = new ParamMap();
//        m1.put("mrkid", this.getInitProperties().get("mrkid"));
//        m1.put("stat", this.STYPE.getValue());
//        try {
//            m1.exec(getTaskContext(), this.getClass().getResource(IK_TAXEX_DEF), IK_TAXEX_SET_NEW_STATUS);
//            if (m1.get("err") != null) {
//                Alerts.error(this, (String) m1.get("err"));
//                return false;
//            }
//        } catch (SQLExpressionException e) {
//            e.printStackTrace();
//            this.handleException(e);
//            return false;
//        }
        return super.onOK();
    }
}
