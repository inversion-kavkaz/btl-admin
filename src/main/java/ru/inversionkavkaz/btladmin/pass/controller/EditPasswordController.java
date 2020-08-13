package ru.inversionkavkaz.btladmin.pass.controller;

import javafx.fxml.FXML;
import ru.inversion.fx.form.Alerts;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.JInvCheckBox;
import ru.inversion.fx.form.controls.JInvPasswordField;
import ru.inversionkavkaz.btladmin.pass.entity.PPassword;

/**
 * @author  porche
 * @since   Sun Aug 09 17:29:30 MSK 2020
 */
public class EditPasswordController extends JInvFXFormController <PPassword>
{  
   @FXML JInvPasswordField PASS1;
   @FXML JInvPasswordField PASS2;
   @FXML JInvCheckBox cbENCODEPASSWORD;

//
// Initializes the controller class.
//
    @Override
    protected void init () throws Exception 
    {
        super.init ();
        setTitle("Установка пароля");
    }

    @Override
    protected boolean onOK() {
        if(PASS1.getText()==null || !PASS1.getText().equals(PASS2.getText())){
            Alerts.error("Ошибка",  "Установка пароля", "Ошибка установки пароля. Пароли несовпадают или пусты.");
            return false;
        }
        dataObject.setPASS1(PASS1.getText());
        dataObject.setPASS2(PASS2.getText());
        dataObject.setEENCODEPASSWORD(cbENCODEPASSWORD.isSelected());

        return super.onOK();
    }
}

