package ru.inversionkavkaz.btladmin.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversionkavkaz.btladmin.btlbase.controller.EditIkBtlBaseController;
import ru.inversionkavkaz.btladmin.btlbase.controller.ViewIkBtlBaseController;
import ru.inversionkavkaz.btladmin.btlbase.entity.PIkBtlBase;
import ru.inversionkavkaz.btladmin.cancelreason.controller.ViewIkBtlCancelReasonController;
import ru.inversionkavkaz.btladmin.cancelreason.entity.PIkBtlCancelReason;
import ru.inversionkavkaz.btladmin.entity.PIkBtlFog;
import ru.inversionkavkaz.btladmin.limits.controller.ViewIkBtlLimController;
import ru.inversionkavkaz.btladmin.limits.entity.PIkBtlLim;
import ru.inversionkavkaz.btladmin.rejectreason.controller.ViewIkBtlRejectReasonController;
import ru.inversionkavkaz.btladmin.rejectreason.entity.PIkBtlRejectReason;
import ru.inversionkavkaz.btladmin.utils.AltPrintReportType;
import ru.inversionkavkaz.btladmin.utils.ButtonUtils;

/**
 * @author  bvv
 * @since   Fri Aug 07 18:17:47 MSK 2020
 */
public class ViewIkBtlFogController extends JInvFXBrowserController 
{
    @FXML private JInvTable<PIkBtlFog> IK_BTL_FOG;
    @FXML private JInvToolBar toolBar;
    @FXML private DSInfoBar IK_BTL_FOG$MARK;
    JInvButtonPrint altPrint = null;

    private final XXIDataSet<PIkBtlFog> dsIK_BTL_FOG = new XXIDataSet<> ();

    private void initDataSet () throws Exception 
    {
        dsIK_BTL_FOG.setTaskContext (getTaskContext ());
        dsIK_BTL_FOG.setRowClass (PIkBtlFog.class);
    }

    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkBtlFog> dsfx = DSFXAdapter.bind (dsIK_BTL_FOG, IK_BTL_FOG, null, true); 

        dsfx.setEnableFilter (true);
        IK_BTL_FOG$MARK.init (IK_BTL_FOG.getDataSetAdapter ());
        IK_BTL_FOG$MARK.addAggregator ("1", AggrFuncEnum.COUNT, AggregatorType.MARK, null, null);
 
        initToolBar ();

        IK_BTL_FOG.setToolBar (toolBar);       
        IK_BTL_FOG.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IK_BTL_FOG.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
        IK_BTL_FOG.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        IK_BTL_FOG.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_BTL_FOG.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        

    private void doRefresh () 
    {
        IK_BTL_FOG.executeQuery ();
    }

    private void initToolBar() {
        toolBar.setStandartActions(ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.VIEW,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE,
                ActionFactory.ActionTypeEnum.REFRESH);
//    ButtonUtils.addBtn(toolBar,"Доп. параметры сервиса", this::onShowServiceParams);
    ButtonUtils.addBtn(toolBar,"База заявок", this::onShowBtlBase);
        altPrint = new JInvButtonPrint(this::prePrintAp);
        altPrint.setReportTypeId(AltPrintReportType.PRINT_BTL_FOG);
        toolBar.getItems().add(altPrint);
    }

    private void doOperation ( JInvFXFormController.FormModeEnum mode )
    {
        PIkBtlFog p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkBtlFog ();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_BTL_FOG.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<PIkBtlFog> (getTaskContext (), getViewContext (), EditIkBtlFogController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResult)    
                .modal (true)
                .show ();
    }

    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkBtlFog> dctl )    
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_BTL_FOG.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_BTL_FOG.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_BTL_FOG.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_BTL_FOG.requestFocus ();
    }

    public void onPrint(ActionEvent actionEvent) {
        if(altPrint!=null) altPrint.fire();
    }

    public void onShowLimits(ActionEvent actionEvent) {
        new FXFormLauncher<PIkBtlLim>(getTaskContext(), getViewContext(), ViewIkBtlLimController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .modal(true)
                .show();
    }

    public void onShowRejectReasons(ActionEvent actionEvent) {
        new FXFormLauncher<PIkBtlRejectReason>(getTaskContext(), getViewContext(), ViewIkBtlRejectReasonController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .modal(true)
                .show();
    }

    public void onShowCancelReasons(ActionEvent actionEvent) {
        new FXFormLauncher<PIkBtlCancelReason>(getTaskContext(), getViewContext(), ViewIkBtlCancelReasonController.class)
                .dialogMode(FormModeEnum.VM_SHOW)
                .modal(true)
                .show();
    }

    private void prePrintAp(ApReport apReport) {
        String p1 = dsIK_BTL_FOG.getCurrentRow() == null ? "" : dsIK_BTL_FOG.getCurrentRow().getCFOGBIC().toString();
        String p2 = dsIK_BTL_FOG.getMarkerID() == null ? "" : dsIK_BTL_FOG.getMarkerID().toString();
        apReport.setParam(p1, p2);
    }

    private void onShowBtlBase(Event event) {
        new FXFormLauncher<> (getTaskContext (), getViewContext (), ViewIkBtlBaseController.class)
                .dialogMode (FormModeEnum.VM_SHOW)
                .initProperties (getInitProperties ())
                .modal (true)
                .show ();
    }


    public void onExit(ActionEvent actionEvent) {
        this.close();
    }

}

