package ru.inversionkavkaz.btladmin.btlbase.controller;


import javafx.event.Event;
import javafx.fxml.FXML;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.dataset.fx.F7FilterGroup;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.renderer.JInvTableCell;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;
import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversion.icons.enums.FontAwesome;
import ru.inversionkavkaz.btladmin.btlbase.entity.PIkBtlBase;
import ru.inversionkavkaz.btladmin.statusau.controller.ViewIkBtlBaseStatsAuController;
import ru.inversionkavkaz.btladmin.utils.AltPrintReportType;
import ru.inversionkavkaz.btladmin.utils.ButtonUtils;

import java.util.function.BiConsumer;

/**
 * @author  BVV
 * @since   Mon Aug 10 12:56:04 MSK 2020
 */
public class ViewIkBtlBaseController extends JInvFXBrowserController 
{
    public static final String STYLE_ERROR = "-fx-background-color: #ff182c;\n" +
            "    -fx-background-insets: 0 1 1 0;\n" +
            "    -fx-text-fill: #ffffff;\n";
    public static final String STYLE_REJECTED = "-fx-background-color: #ffd6e4;\n" +
            "    -fx-background-insets: 0 1 1 0;\n" +
            "    -fx-text-fill: black;\n";
    public static final String STYLE_NEW = "-fx-background-color: #f6ebbc;\n" +
            "    -fx-background-insets: 0 1 1 0;\n" +
            "    -fx-text-fill: black;\n";
    public static final int STATUS_CODE_NEW = -1;
    public static final int STATUS_CODE_ACCEPTED = 0;
    public static final int STATUS_CODE_REJECTED = 1;

    public static final String IK_BTL_BASE_DEF = "ru/inversionkavkaz/btladmin/controller/res/plsql/def.xml";
    public static final String IK_BTL_BASE_SET_NEW_STATUS = "IK_TAXEX.setNewStatus";
    public static final String IK_BTL_BASE_CHECK_SAME_STATUS = "IK_TAXEX.CheckSameStatus";

    @FXML private JInvTable<PIkBtlBase> IK_BTL_BASE;
    @FXML private JInvToolBar toolBar;
    @FXML private JInvTableColumn PAYER_BANK_BIC;
    @FXML private DSInfoBar IK_BTL_BASE$MARK;

    @FXML private JInvTableColumn STATUS_CODE;

    JInvButtonPrint altPrint = null;
 
   
    private final XXIDataSet<PIkBtlBase> dsIK_BTL_BASE = new XXIDataSet<> ();    

    private void initDataSet () throws Exception
    {
        dsIK_BTL_BASE.setTaskContext (getTaskContext ());
        dsIK_BTL_BASE.setRowClass (PIkBtlBase.class);
    }

    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        
        initDataSet ();
        DSFXAdapter<PIkBtlBase> dsfx = DSFXAdapter.bind (dsIK_BTL_BASE, IK_BTL_BASE, null, true);
        dsfx.setFilterGroups(
                new F7FilterGroup("F7PAYER_GROUP","Плательщик",2, true),
                new F7FilterGroup("F7RECIP_GROUP","Получатель",3, true),
                new F7FilterGroup("F7CANCEL_GROUP","Аннулирование",4,true),
                new F7FilterGroup("F7USER_GROUP", "Пользователь",5,true),
                new F7FilterGroup("F7STATUS_GROUP","Статус",6,true)
        );
        dsfx.setEnableFilter (true, getName (), "IK_BTL_BASE");
//        dsfx.setFilterModifier (multiFilterModifier);

        dsfx.setEnableFilter (true);
        IK_BTL_BASE$MARK.init (IK_BTL_BASE.getDataSetAdapter ());
        IK_BTL_BASE$MARK.addAggregator ("1", AggrFuncEnum.COUNT, AggregatorType.MARK, null, null);

        initToolBar ();
        initCellRenderer();

        IK_BTL_BASE.setToolBar (toolBar);       
//        IK_BTL_BASE.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        IK_BTL_BASE.setAction (ActionFactory.ActionTypeEnum.VIEW, (a) -> doOperation (FormModeEnum.VM_SHOW));
//        IK_BTL_BASE.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
//        IK_BTL_BASE.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        IK_BTL_BASE.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh ());

        doRefresh ();
    }        

    private void doRefresh ()
    {
        IK_BTL_BASE.executeQuery ();
    }

    private void initToolBar ()
    {
        toolBar.setStandartActions (
//                ActionFactory.ActionTypeEnum.CREATE,
                                    ActionFactory.ActionTypeEnum.VIEW,
//                                    ActionFactory.ActionTypeEnum.UPDATE,
//                                    ActionFactory.ActionTypeEnum.DELETE,
                                    ActionFactory.ActionTypeEnum.REFRESH);
        
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
        toolBar.getItems().add(ActionFactory.createButton(FontAwesome.fa_flag_checkered, "", (a) -> changeStatus(), getBundleString("Принудительная смена статуса")));
        ButtonUtils.addBtn(toolBar, "Статусы", "История изменения статуссов", FontAwesome.fa_exclamation_circle, this::showStatusHistory);

        altPrint = new JInvButtonPrint(this::prePrintAp);
        altPrint.setReportTypeId(AltPrintReportType.PRINT_BTL_BASE);
        toolBar.getItems().add(altPrint);

    }

    private void doOperation ( JInvFXFormController.FormModeEnum mode )
    {
        PIkBtlBase p = null;

        switch (mode) {
            case VM_INS:
                p = new PIkBtlBase ();
                break;
            case VM_EDIT:
            case VM_SHOW:
            case VM_DEL:
                p = dsIK_BTL_BASE.getCurrentRow ();
                break;
        }

        if (p != null) 
            new FXFormLauncher<PIkBtlBase> (getTaskContext (), getViewContext (), EditIkBtlBaseController.class)
                .dataObject (p)
                .dialogMode (mode)
                .initProperties (getInitProperties ())
                .callback (this::doFormResult)    
                .modal (true)
                .show ();
    }

    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PIkBtlBase> dctl )
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok)
        {
            switch (dctl.getFormMode ()) 
            {
                case VM_INS:
                    dsIK_BTL_BASE.insertRow (dctl.getDataObject (), IDataSet.InsertRowModeEnum.AFTER_CURRENT, true);
                    break;
                case VM_EDIT:                
                    dsIK_BTL_BASE.updateCurrentRow (dctl.getDataObject ());
                    break;
                case VM_DEL:
                    dsIK_BTL_BASE.removeCurrentRow ();
                    break;
                default:
                    break;
            }                
        }    

        IK_BTL_BASE.requestFocus ();
    }

    private void changeStatus() {
//        if (!dsIK_BTL_BASE.isEmpty()) {
//            if (dsIK_BTL_BASE.hasMarkedRows()) {
//                ParamMap m1 = new ParamMap();
//                m1.put("mrkid", dsIK_BTL_BASE.getMarkerID());
//                try {
//                    m1.exec(getTaskContext(), this.getClass().getResource(IK_BTL_BASE_DEF), IK_BTL_BASE_CHECK_SAME_STATUS);
//                    if (m1.getLong("cnt") > 1L) {
//                        Alerts.error(this, "Изменений не требуется");
//                    } else {
//                        m1.clear();
//                        m1.put("mrkid", dsIK_BTL_BASE.getMarkerID());
//                        new FXFormLauncher<>(getTaskContext(), getViewContext(), EditStatusController.class)
//                                .initProperties(m1)
//                                .callback(this::AfterChangeStatus)
//                                .modal(true)
//                                .show();
//                    }
//                } catch (Throwable ex) {
//                    handleException(ex);
//                }
//            } else
//                Alerts.error(this, "Необходимо пометить строки.");
//        }
    }

    private void showStatusHistory(Event event) {
        if (!dsIK_BTL_BASE.isEmpty()) {
            ParamMap pe = new ParamMap();
            pe.put(ViewIkBtlBaseStatsAuController.INIT_PARAM_BASEID, dsIK_BTL_BASE.getCurrentRow().getID());
            new FXFormLauncher<>(getTaskContext(), getViewContext(), ViewIkBtlBaseStatsAuController.class)
                    .initProperties(pe)
                    .modal(true)
                    .show();
        }
    }

    private void prePrintAp(ApReport apReport) {
        String p1 = dsIK_BTL_BASE.getCurrentRow() == null ? "" : dsIK_BTL_BASE.getCurrentRow().getID().toString();
        String p2 = dsIK_BTL_BASE.getMarkerID() == null ? "" : dsIK_BTL_BASE.getMarkerID().toString();
        apReport.setParam(p1, p2);
    }

    private void initCellRenderer() {
        STATUS_CODE.setCellRenderer((BiConsumer<JInvTableCell<PIkBtlBase, Long>, Long>) (cell, aString) -> {
            PIkBtlBase ikBtlBase = (PIkBtlBase) cell.getTableRow().getItem();
            if (ikBtlBase != null) {
                switch (ikBtlBase.getSTATUS_CODE().intValue()){
                    case STATUS_CODE_NEW:      cell.setStyle(STYLE_NEW); break;
                    case STATUS_CODE_ACCEPTED: break;
                    case STATUS_CODE_REJECTED: cell.setStyle(STYLE_REJECTED); break;
                    default: cell.setStyle(STYLE_ERROR);
                }
            }
        });
    }

}

