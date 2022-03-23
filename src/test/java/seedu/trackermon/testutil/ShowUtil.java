package seedu.trackermon.testutil;

import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.trackermon.logic.commands.AddCommand;
import seedu.trackermon.logic.commands.EditCommand.EditShowDescriptor;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.tag.Tag;

/**
 * A utility class for show.
 */
public class ShowUtil {

    /**
     * Returns an add command string for adding the {@code show}.
     */
    public static String getAddCommand(Show show) {
        return AddCommand.COMMAND_WORD + " " + getShowDetails(show);
    }

    /**
     * Returns the part of command string for the given {@code show}'s details.
     */
    public static String getShowDetails(Show show) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + show.getName().fullName + " ");
        sb.append(PREFIX_STATUS + show.getStatus().name() + " ");
        show.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditShowDescriptor}'s details.
     */
    public static String getEditShowDescriptorDetails(EditShowDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.name()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }



}
