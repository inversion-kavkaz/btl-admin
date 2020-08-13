package ru.inversionkavkaz.btladmin.limits.controller;

import javafx.fxml.FXML;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.btladmin.limits.entity.PIkBtlLim;

/**
 *
 * @author  porche
 * @since   Sun Jul 05 15:53:21 MSK 2020
 */
public class ViewIkBtlLimController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkBtlLim> IK_BTL_LIM;
    @FXML private JInvToolBar toolBar;
    //@FXML private JInvComboBoxSimple VALIDITY;

    private final XXIDataSet<PIkBtlLim> dsIK_BTL_LIM = new XXIDataSet<> ();    
//
//
//    
    private void initDataSet () throws Exception 
    {
        dsIK_BTL_LIM.setTaskContext (getTaskContext ());
        dsIK_BTL_LIM.setRowClass (PIkBtlLim.class);

//        ListStringConverter<String, String> sc3 = new ListStringConverter<> (initData.getList ("ABB_MAIL", PRgAbb.class), PRgAbb::getCABBABB, PRgAbb::getCABBCOM);
//        VALIDITY.setConverter(sc3);
//        VALIDITY.getItems().addAll(sc3.keySet());

    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkBtlLim> dsfx = DSFXAdapter.bind (dsIK_BTL_LIM, IK_BTL_LIM, null, false); 

        dsfx.setEnableFilter (true);
 
        initToolBar ();

        IK_BTL_LIM.setToolBar (toolBar);       
        IK_BTL_LIM.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IK_BTL_LIM.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IK_BTL_LIM.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IK_BTL_LIM.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_BTL_LIM.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();

       // ListStringConverter<PRgAbb, String> sc3 = new ListStringConverter<> (initData.getList ("ABB_STATUS", PRgAbb.class), PRgAbb::getCABBABB, PRgAbb::getCABBCOM);

    }        
//
//
//    
    private void doRefresh () 
    {
        IK_BTL_LIM.executeQuery ();
    }
//
//
//    
    private void initToolBar () 
    {
        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.CREATE, 
                                    ActionFactory.ActionTypeEnum.VIEW,
                                    ActionFactory.ActionTypeEnum.UPDATE,
                                    ActionFactory.ActionTypeEnum.DELETE,
                                    ActionFactory.ActionTypeEnum.REFRESH);
        
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
    }
//
//
//    
    private void doOperation ( JInvFXFormController.FormModeEnum mode ) 
    {
        PIkBtlLim p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkBtlLim ();
                p.setVALIDITY("M");
                p.setNREZCALC("N");
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_BTL_LIM.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<PIkBtlLim> (getTaskContext (), getViewContext (), EditIkBtlLimController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResult)    
                .modal (true)
                .show ();
    }
//
// 
//
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkBtlLim> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_BTL_LIM.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_BTL_LIM.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_BTL_LIM.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_BTL_LIM.requestFocus ();
    }        
//
//
//    
}

