package seedu.trackermon.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.trackermon.logic.commands.EditCommand.EditShowDescriptor;
import seedu.trackermon.model.show.Name;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.show.Status;
import seedu.trackermon.model.tag.Tag;

/**
 * A utility class to help with building EditShowDescriptor objects.
 */

public class EditShowDescriptorBuilder {

    private EditShowDescriptor descriptor;

    public EditShowDescriptorBuilder() {
        descriptor = new EditShowDescriptor();
    }

    public EditShowDescriptorBuilder(EditShowDescriptor descriptor) {
        this.descriptor = new EditShowDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditShowDescriptor} with fields containing {@code show}'s details
     */
    public EditShowDescriptorBuilder(Show show) {
        descriptor = new EditShowDescriptor();
        descriptor.setName(show.getName());
        descriptor.setStatus(show.getStatus());
        descriptor.setTags(show.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditShowDescriptor} that we are building.
     */
    public EditShowDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditShowDescriptor} that we are building.
     */
    public EditShowDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(Status.getStatus(status));
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditShowDescriptor}
     * that we are building.
     */
    public EditShowDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditShowDescriptor build() {
        return descriptor;
    }
}
