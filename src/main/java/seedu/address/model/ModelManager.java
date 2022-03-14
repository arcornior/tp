package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.show.Show;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ShowList showList;
    private final UserPrefs userPrefs;
    private final FilteredList<Show> filteredShows;

    /**
     * Initializes a ModelManager with the given showList and userPrefs.
     */
    public ModelManager(ReadOnlyShowList showList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(showList, userPrefs);

        logger.fine("Initializing with address book: " + showList + " and user prefs " + userPrefs);

        this.showList = new ShowList(showList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredShows = new FilteredList<>(this.showList.getShowList());
    }

    public ModelManager() {
        this(new ShowList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getShowListFilePath() {
        return userPrefs.getShowListFilePath();
    }

    @Override
    public void setShowListFilePath(Path showListFilePath) {
        requireNonNull(showListFilePath);
        userPrefs.setShowListFilePath(showListFilePath);
    }

    //=========== ShowList ================================================================================

    @Override
    public void setShowList(ReadOnlyShowList showList) {
        this.showList.resetData(showList);
    }

    @Override
    public ReadOnlyShowList getShowList() {
        return showList;
    }

    @Override
    public boolean hasShow(Show person) {
        requireNonNull(person);
        return showList.hasShow(person);
    }

    @Override
    public void deleteShow(Show target) {
        showList.removeShow(target);
    }

    @Override
    public void addShow(Show person) {
        showList.addShow(person);
        updateFilteredShowList(PREDICATE_SHOW_ALL_SHOWS);
    }

    @Override
    public void setShow(Show target, Show editedShow) {
        requireAllNonNull(target, editedShow);

        showList.setShow(target, editedShow);
    }

    //=========== Filtered Show List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Show} backed by the internal list of
     * {@code versionedShowList}
     */
    @Override
    public ObservableList<Show> getFilteredShowList() {
        return filteredShows;
    }

    @Override
    public void updateFilteredShowList(Predicate<Show> predicate) {
        requireNonNull(predicate);
        filteredShows.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return showList.equals(other.showList)
                && userPrefs.equals(other.userPrefs)
                && filteredShows.equals(other.filteredShows);
    }
}
