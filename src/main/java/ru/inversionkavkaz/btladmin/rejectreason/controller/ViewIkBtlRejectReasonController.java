package ru.inversionkavkaz.btladmin.rejectreason.controller;


import javafx.fxml.FXML;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;


import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.btladmin.rejectreason.entity.PIkBtlRejectReason;

/**
 *
 * @author  porche
 * @since   Mon Jul 06 10:55:46 MSK 2020
 */
public class ViewIkBtlRejectReasonController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkBtlRejectReason> IK_BTL_REJECT_REASON;
    @FXML private JInvToolBar toolBar;

 
   
    private final XXIDataSet<PIkBtlRejectReason> dsIK_BTL_REJECT_REASON = new XXIDataSet<> ();    
//
//
//    
    private void initDataSet () throws Exception 
    {
        dsIK_BTL_REJECT_REASON.setTaskContext (getTaskContext ());
        dsIK_BTL_REJECT_REASON.setRowClass (PIkBtlRejectReason.class);
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkBtlRejectReason> dsfx = DSFXAdapter.bind (dsIK_BTL_REJECT_REASON, IK_BTL_REJECT_REASON, null, false); 

        dsfx.setEnableFilter (true);
 
                
        initToolBar ();

        IK_BTL_REJECT_REASON.setToolBar (toolBar);       
        IK_BTL_REJECT_REASON.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IK_BTL_REJECT_REASON.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IK_BTL_REJECT_REASON.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IK_BTL_REJECT_REASON.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_BTL_REJECT_REASON.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        
//
//
//    
    private void doRefresh () 
    {
        IK_BTL_REJECT_REASON.executeQuery ();
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
        PIkBtlRejectReason p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkBtlRejectReason ();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_BTL_REJECT_REASON.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<PIkBtlRejectReason> (getTaskContext (), getViewContext (), EditIkBtlRejectReasonController.class)
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
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkBtlRejectReason> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_BTL_REJECT_REASON.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_BTL_REJECT_REASON.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_BTL_REJECT_REASON.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_BTL_REJECT_REASON.requestFocus ();
    }        
//
//
//    
}

