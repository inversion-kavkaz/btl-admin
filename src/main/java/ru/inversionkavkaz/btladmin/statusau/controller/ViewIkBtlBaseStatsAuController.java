package ru.inversionkavkaz.btladmin.statusau.controller;

import javafx.fxml.FXML;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.btladmin.statusau.entity.PIkBtlBaseStatsAu;

/**
 * @author  bvv
 * @since   Thu Aug 13 10:49:53 MSK 2020
 */
public class ViewIkBtlBaseStatsAuController extends JInvFXBrowserController 
{
    public static String INIT_PARAM_BASEID = "baseID";

    @FXML private JInvTable<PIkBtlBaseStatsAu> IK_BTL_BASE_STATS_AU;
    @FXML private JInvToolBar toolBar;

    private final XXIDataSet<PIkBtlBaseStatsAu> dsIK_BTL_BASE_STATS_AU = new XXIDataSet<> ();    

    private void initDataSet () throws Exception 
    {
        dsIK_BTL_BASE_STATS_AU.setTaskContext (getTaskContext ());
        dsIK_BTL_BASE_STATS_AU.setRowClass (PIkBtlBaseStatsAu.class);
        dsIK_BTL_BASE_STATS_AU.setWherePredicat("baseID=" + getInitProperties().get(INIT_PARAM_BASEID));
        dsIK_BTL_BASE_STATS_AU.setOrderBy("ID");
    }

    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkBtlBaseStatsAu> dsfx = DSFXAdapter.bind (dsIK_BTL_BASE_STATS_AU, IK_BTL_BASE_STATS_AU, null, false); 

        dsfx.setEnableFilter (true);
 
        initToolBar ();

        IK_BTL_BASE_STATS_AU.setToolBar (toolBar);       
//        IK_BTL_BASE_STATS_AU.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
//        IK_BTL_BASE_STATS_AU.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
//        IK_BTL_BASE_STATS_AU.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
//        IK_BTL_BASE_STATS_AU.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_BTL_BASE_STATS_AU.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        

    private void doRefresh () 
    {
        IK_BTL_BASE_STATS_AU.executeQuery ();
    }

    private void initToolBar () 
    {
        toolBar.setStandartActions (
//                ActionFactory.ActionTypeEnum.CREATE,
//                                    ActionFactory.ActionTypeEnum.VIEW,
//                                    ActionFactory.ActionTypeEnum.UPDATE,
//                                    ActionFactory.ActionTypeEnum.DELETE,
                                    ActionFactory.ActionTypeEnum.REFRESH);
        
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
    }

    private void doOperation ( JInvFXFormController.FormModeEnum mode ) 
    {
//        PIkBtlBaseStatsAu p = null;
//
//        switch (mode) {
//            case VM_INS:
//                p = new PIkBtlBaseStatsAu ();
//                break;
//            case VM_EDIT:
//            case VM_SHOW:
//            case VM_DEL:
//                p = dsIK_BTL_BASE_STATS_AU.getCurrentRow ();
//                break;
//        }
//
//        if (p != null)
//            new FXFormLauncher<PIkBtlBaseStatsAu> (getTaskContext (), getViewContext (), EditIkBtlBaseStatsAuController.class)
//                .dataObject (p)
//                .dialogMode (mode)
//                .initProperties (getInitProperties ())
//                .callback (this::doFormResult)
//                .modal (true)
//                .show ();
    }

    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkBtlBaseStatsAu> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_BTL_BASE_STATS_AU.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_BTL_BASE_STATS_AU.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_BTL_BASE_STATS_AU.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_BTL_BASE_STATS_AU.requestFocus ();
    }        

}

