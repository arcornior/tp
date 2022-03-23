package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Show;

/**
 * Sort the list of shows in Trackermon.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "sort success!!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort shows based on the input prefix. If there are "
            + "no prefix, it will sort by name in ascending order.\n"
            + "if both prefixes for ascending and descending are used,\n"
            + "it will only sort by ascending.\n"
            + "By default it sort by name then status,\n"
            + "use so/ to sort by status then name.\n"
            + "Parameters: [sna/] [snd/] [ssa/] [ssd/] [so/]...\n"
            + "Example: " + COMMAND_WORD;

    private final Comparator<Show> comparator;

    public SortCommand(Comparator<Show> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedShowList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}

