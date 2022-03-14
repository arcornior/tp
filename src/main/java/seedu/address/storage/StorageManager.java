package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyShowList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ShowList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ShowListStorage showListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ShowListStorage showListStorage, UserPrefsStorage userPrefsStorage) {
        this.showListStorage = showListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ShowList methods ==============================

    @Override
    public Path getShowListFilePath() {
        return showListStorage.getShowListFilePath();
    }

    @Override
    public Optional<ReadOnlyShowList> readShowList() throws DataConversionException, IOException {
        return readShowList(showListStorage.getShowListFilePath());
    }

    @Override
    public Optional<ReadOnlyShowList> readShowList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return showListStorage.readShowList(filePath);
    }

    @Override
    public void saveShowList(ReadOnlyShowList showList) throws IOException {
        saveShowList(showList, showListStorage.getShowListFilePath());
    }

    @Override
    public void saveShowList(ReadOnlyShowList showList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        showListStorage.saveShowList(showList, filePath);
    }

}
