package ru.inversionkavkaz.btladmin.limits.controller;

import ru.inversion.bicomp.stringconverter.BundleStringConverter;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.*;
import javafx.fxml.FXML;
import ru.inversionkavkaz.btladmin.limits.entity.PIkBtlLim;

import java.util.ResourceBundle;

/**
 * @author  bvv
 * @since   Sun Jul 05 15:53:32 MSK 2020
 */
public class EditIkBtlLimController extends JInvFXFormController <PIkBtlLim>
{  
//    @FXML JInvTextField ID;
//    @FXML JInvTextField MSUMM;
    @FXML JInvComboBox<String, String> VALIDITY;
    @FXML JInvCheckBoxString NREZCALC;
//    @FXML JInvTextField CDESCR;

//
// Initializes the controller class.
//
    @Override
    protected void init () throws Exception 
    {
        super.init ();
        setupUI();
    }


    void setupUI(){
        ResourceBundle validities = ResourceBundle.getBundle("ru.inversionkavkaz.btladmin.limits.controller.res.Validities");
        BundleStringConverter bsc = new BundleStringConverter(validities);
        VALIDITY.setConverter(bsc);
        VALIDITY.getItems().addAll(bsc.keySet());
    }

    @Override
    protected boolean onOK() {
//        getDataObject().setNREZCALC(NREZCALC.isSelected() ? "Y": "N");
        return super.onOK();
    }
}

