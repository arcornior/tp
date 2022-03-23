package seedu.trackermon.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_SORT_NAME_ACS = new Prefix("sna/");
    public static final Prefix PREFIX_SORT_STATUS_ACS = new Prefix("ssa/");
    public static final Prefix PREFIX_SORT_NAME_DES = new Prefix("snd/");
    public static final Prefix PREFIX_SORT_STATUS_DES = new Prefix("ssd/");
    public static final Prefix PREFIX_SORT_STATUS_ORD = new Prefix("so/");

}
