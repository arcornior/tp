package seedu.trackermon.logic.parser;

import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_SORT_NAME_ACS;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_SORT_NAME_DES;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_SORT_STATUS_ACS;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_SORT_STATUS_DES;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_SORT_STATUS_ORD;

import java.util.Comparator;
import java.util.stream.Stream;

import seedu.trackermon.logic.commands.SortCommand;
import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.show.NameComparator;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.show.StatusComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new SortCommand(new NameComparator());
        }

        boolean isNamePresent = false;
        boolean isStatusPresent = false;
        Comparator<Show> nameComparator = new NameComparator();
        Comparator<Show> statusComparator = new StatusComparator();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_NAME_ACS,
                        PREFIX_SORT_STATUS_ACS, PREFIX_SORT_NAME_DES,
                        PREFIX_SORT_STATUS_DES, PREFIX_SORT_STATUS_ORD);

        if (arePrefixesPresent(argMultimap, PREFIX_SORT_NAME_ACS)) {
            isNamePresent = true;
        } else if (arePrefixesPresent(argMultimap, PREFIX_SORT_NAME_DES)) {
            isNamePresent = true;
            nameComparator = nameComparator.reversed();
        }

        if (arePrefixesPresent(argMultimap, PREFIX_SORT_STATUS_ACS)) {
            isStatusPresent = true;
        } else if (arePrefixesPresent(argMultimap, PREFIX_SORT_STATUS_DES)) {
            isStatusPresent = true;
            statusComparator = statusComparator.reversed();
        }

        //only name present
        if (isNamePresent && !isStatusPresent) {
            return new SortCommand(nameComparator);
        }

        //only status present
        if (isStatusPresent && !isNamePresent) {
            return new SortCommand(statusComparator);
        }

        //both name and status present - status first then name
        if (arePrefixesPresent(argMultimap, PREFIX_SORT_STATUS_ORD)) {
            return new SortCommand(statusComparator.thenComparing(nameComparator));
        }

        //both name and status present - name first then status
        return new SortCommand(nameComparator.thenComparing(statusComparator));

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
