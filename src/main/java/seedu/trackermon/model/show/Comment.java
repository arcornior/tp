package seedu.trackermon.model.show;

public class Comment {

    public final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return comment;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Comment
                && comment.equals(((Comment) other).comment));
    }

    @Override
    public int hashCode() {
        return comment.hashCode();
    }
}
