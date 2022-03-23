package seedu.trackermon.model.show;

import static seedu.trackermon.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.trackermon.model.tag.Tag;

public class Show {

    //Identity field
    private final Name name;
    private final Status status;

    //Data field
    private final Set<Tag> tags = new HashSet<>();
    private final Comment comment;

    /**
     * Every field must be present and not null.
     */
    public Show(Name name, Status status, Set<Tag> tags, Comment comment) {
        requireAllNonNull(name, status, tags, comment);
        this.name = name;
        this.status = status;
        this.tags.addAll(tags);
        this.comment = comment;
    }

    public Name getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Comment getComment() {
        return comment;
    }

    /**
     * Returns true if both show have the same name.
     * This defines a weaker notion of equality between two show.
     */
    public boolean isSameShow(Show otherShow) {
        if (otherShow == this) {
            return true;
        }

        return otherShow != null
                && otherShow.getName().equals(getName());
    }

    /**
     * Returns true if both shows have the same identity and data fields.
     * This defines a stronger notion of equality between two shows.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Show)) {
            return false;
        }

        Show otherShow = (Show) other;
        return otherShow.getName().equals(getName())
                && otherShow.getTags().equals(getTags())
                    && otherShow.getStatus().equals(getStatus())
                        && otherShow.getComment().equals(getComment());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName().toString());
        builder.append(getStatus().toString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append(getComment().toString());
        return builder.toString();
    }

    /**
     * Compare this name with other lexicographically
     */
    public int compareNames(Show other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Compare this status with other lexicographically
     */
    public int compareStatus(Show other) {
        return this.status.compareTo(other.status);
    }



}
