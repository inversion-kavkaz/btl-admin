package ru.inversionkavkaz.btladmin.controller;

import javafx.event.ActionEvent;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.ViewContext;
import ru.inversion.fx.form.controls.*;
import javafx.fxml.FXML;
import ru.inversion.tc.TaskContext;
import ru.inversionkavkaz.btladmin.entity.PIkBtlFog;
import ru.inversion.fxpdoc.fog.*;
import ru.inversionkavkaz.btladmin.entity.dao.DaoFactory;
import ru.inversionkavkaz.btladmin.pass.controller.EditPasswordController;
import ru.inversionkavkaz.btladmin.pass.entity.PPassword;
import ru.inversionkavkaz.btladmin.utils.Alert;
import ru.inversionkavkaz.btladmin.utils.Secure;
import java.util.Map;

/**
 * @author  bvv
 * @since   Fri Aug 07 18:17:51 MSK 2020
 */
public class EditIkBtlFogController extends JInvFXFormController <PIkBtlFog>
{  
 @FXML JInvTextField CFOGBIC;
 @FXML JInvTextField CFOGNAME;
 @FXML JInvCheckBox ENABLED;
 @FXML JInvPasswordField PASSWORD;

//
// Initializes the controller class.
//
    @Override
    protected void init () throws Exception 
    {
        super.init ();
        initLov();
    }

    private void initLov(){
        PFogLov fogLov = new PFogLov(getTaskContext());
        CFOGBIC.setLOV(fogLov);
        fogLov.bindControl(CFOGNAME, fog->fog.getCFOGNAME());
        fogLov.checkValue(getDataObject().getCFOGBIC(), true);
    }

    public void setPassword(ActionEvent actionEvent) {
        ParamMap mapProp = new ParamMap();
        new FXFormLauncher<>(getTaskContext(), getViewContext(), EditPasswordController.class)
                .initProperties(mapProp)
                .dataObject(new PPassword())
                .callback(this::doPasswordResult)
                .modal(true)
                .show();
    }

    private void doPasswordResult(FormReturnEnum ok, JInvFXFormController<PPassword> passwordController) {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok) {
            String password = passwordController.getDataObject().getPASS1();
            if(passwordController.getDataObject().getENCODEPASSWORD()){
                password = Secure.encrypt(password);
            }
            PASSWORD.setText(password);
        }
    }

    @Override
    protected boolean onOK() {
        getFXEntity().commit();
        if(getDataObject().getPASSWORD()==null){
            setPassword(null);
            return false;
        }

        try {
            getFXEntity().commit();
            switch (formMode) {
                case VM_INS:
                    DaoFactory.getInstance(getTaskContext().getConnection()).getImpDocDbfDao()
                            .insert(getDataObject(), true, this);
                    break;
                case VM_EDIT:
                    DaoFactory.getInstance(getTaskContext().getConnection()).getImpDocDbfDao()
                            .update(getDataObject(), this);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert.showError("Ошибка", "Ошибка сохранения документа", e);
            return false;
        }

        return true;
    }

}

