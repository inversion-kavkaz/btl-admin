package ru.inversionkavkaz.btladmin.btlbase.entity;
import javafx.util.StringConverter;
import ru.inversion.bicomp.stringconverter.EnumStringConverter;


public enum ReqTypeEnum {
    REQ_CANCEL, REQ_PAY;

    private final StringConverter<ReqTypeEnum> sc = new EnumStringConverter<> (ReqTypeEnum.class);

    public static ReqTypeEnum fromDatabase (Long val )
    {
        if (val == null )
            return null;
        if(val==-1)  val=0L;
        return ReqTypeEnum.values()[Math.toIntExact(val)];
    }

    public static String toDatabase (ReqTypeEnum val )
    {
        if (val == null)
            return null;
        if(val.ordinal ()==0) return "-1";
        return String.valueOf (val.ordinal ());
    }

    @Override
    public String toString ()
    {
        return sc.toString (this);
    }
}
