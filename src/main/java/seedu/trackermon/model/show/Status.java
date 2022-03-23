package seedu.trackermon.model.show;

public enum Status {

    COMPLETED("completed"),
    WATCHING("watching");

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be either completed or watching!";

    private final String status;

    Status(String status) {
        this.status = status;
    }
    public static Status getStatus(String status) {
        System.out.println(status.toUpperCase());
        return Status.valueOf(status.toUpperCase());
    }

    @Override
    public String toString() {
        return "[" + status + "]";
    }

    /**
     * Checks the status of the show.
     * @return true if the show status is completed.
     */
    public Boolean isCompleted() {
        return status.equals(COMPLETED);
    }

    /**
     * Checks the status of the show.
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidStatus(String test) {
        String uppercaseTest = test.toUpperCase();
        return uppercaseTest.equals(COMPLETED.name()) || uppercaseTest.equals(WATCHING.name());
    }

    /**
     * Compare this status with other
     */
    public int compareStatus(Status other) {
        return this.status.compareTo(other.status);
    }
}
