package seedu.trackermon.logic.commands;

import static seedu.trackermon.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.trackermon.model.Model.PREDICATE_SHOW_ALL_SHOWS;

import java.util.List;

import seedu.trackermon.commons.core.Messages;
import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.logic.commands.exceptions.CommandException;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Comment;
import seedu.trackermon.model.show.Show;

/**
 * Allows users to comment on the show.
 */
public class CommentCommand extends Command {

    public static final String COMMAND_WORD = "comment";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String COMMENT_ADD_SUCCESS = "Added comment to show: %1$s";
    public static final String COMMENT_DELETE_SUCCESS = "Delete comment from show: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing comment will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_COMMENT + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMMENT + "Good Show.";

    private final Index index;
    private final Comment comment;


    /**
     * Creates an CommentCommand to edit comments on the specified show at {@code index}
     * @param index the index of the show whose comment are to be edited.
     * @param comment replace the show's comment with said comment
     */
    public CommentCommand(Index index, Comment comment) {
        requireAllNonNull(index, comment);
        this.index = index;
        this.comment = comment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Show> lastShownList = model.getFilteredShowList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SHOW_DISPLAYED_INDEX);
        }

        Show showToEdit = lastShownList.get(index.getZeroBased());
        Show editedShow = new Show(showToEdit.getName(), showToEdit.getStatus(),
                showToEdit.getTags(), comment);

        model.setShow(showToEdit, editedShow);
        model.updateFilteredShowList(PREDICATE_SHOW_ALL_SHOWS);

        return new CommandResult(generateSuccessMessage(editedShow));
    }

    /**
     * Generates a command execution success message based on whether the comment is added to or removed from
     * {@code showToEdit}.
     */
    private String generateSuccessMessage(Show showToEdit) {
        String message = !comment.comment.isEmpty() ? COMMENT_ADD_SUCCESS : COMMENT_DELETE_SUCCESS;
        return String.format(message, showToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CommentCommand)) {
            return false;
        }

        CommentCommand e = (CommentCommand) other;
        return index.equals(e.index)
                && comment.equals(e.comment);
    }
}
