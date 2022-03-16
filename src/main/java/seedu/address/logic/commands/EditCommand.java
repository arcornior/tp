
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.show.Name;
import seedu.address.model.show.Show;
import seedu.address.model.show.Status;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing show in Trackermon.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the show identified "
            + "by the index number used in the displayed show list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_STATUS + "NAME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Attack on Titan "
            + PREFIX_STATUS + "watching";

    public static final String MESSAGE_EDIT_SHOW_SUCCESS = "Edited Show: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SHOW = "This show already exists in Trackermon.";

    private final Index index;
    private final EditShowDescriptor editShowDescriptor;

    /**
     * @param index of the show in the filtered show list to edit
     * @param editShowDescriptor details to edit the show with
     */
    public EditCommand(Index index, EditShowDescriptor editShowDescriptor) {
        requireNonNull(index);
        requireNonNull(editShowDescriptor);

        this.index = index;
        this.editShowDescriptor = new EditShowDescriptor(editShowDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Show> lastShownList = model.getFilteredShowList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SHOW_DISPLAYED_INDEX);
        }

        Show showToEdit = lastShownList.get(index.getZeroBased());
        Show editedShow = createEditedShow(showToEdit, editShowDescriptor);


        if (!showToEdit.isSameShow(editedShow) && model.hasShow(editedShow)) {
            throw new CommandException(MESSAGE_DUPLICATE_SHOW);
        }

        model.setShow(showToEdit, editedShow);
        model.updateFilteredShowList(Model.PREDICATE_SHOW_ALL_SHOWS);
        return new CommandResult(String.format(MESSAGE_EDIT_SHOW_SUCCESS, editedShow));
    }

    /**
     * Creates and returns a {@code Show} with the details of {@code showToEdit}
     * edited with {@code editShowDescriptor}.
     */
    private static Show createEditedShow(Show showToEdit, EditShowDescriptor editShowDescriptor) {
        assert showToEdit != null;

        Name updatedName = editShowDescriptor.getName().orElse(showToEdit.getName());
        Status updatedStatus = editShowDescriptor.getStatus().orElse(showToEdit.getStatus());
        Set<Tag> updatedTags = editShowDescriptor.getTags().orElse(showToEdit.getTags());

        return new Show(updatedName, updatedStatus, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editShowDescriptor.equals(e.editShowDescriptor);
    }

    /**
     * Stores the details to edit the show with. Each non-empty field value will replace the
     * corresponding field value of the show.
     */
    public static class EditShowDescriptor {
        private Name name;
        private Status status;
        private Set<Tag> tags;

        public EditShowDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditShowDescriptor(EditShowDescriptor toCopy) {
            setName(toCopy.name);
            setStatus(toCopy.status);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, status, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditShowDescriptor)) {
                return false;
            }

            // state check
            EditShowDescriptor e = (EditShowDescriptor) other;

            return getName().equals(e.getName())
                    && getStatus().equals(e.getStatus())
                    && getTags().equals(e.getTags());
        }
    }
}
