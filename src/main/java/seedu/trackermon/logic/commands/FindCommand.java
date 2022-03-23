package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.trackermon.commons.core.Messages;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Show;

/**
 * Finds and lists all shows in Trackermon whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all shows whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " hero kyojin academia";

    private final Predicate<Show> predicate;

    public FindCommand(Predicate<Show> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredShowList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_SHOWS_LISTED_OVERVIEW, model.getFilteredShowList().size()),
                true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

