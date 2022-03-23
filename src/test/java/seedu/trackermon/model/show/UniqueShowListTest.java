package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING;
import static seedu.trackermon.logic.commands.CommandTestUtil.VALID_TAG_MOVIE;
import static seedu.trackermon.logic.commands.CommandTestUtil.VALID_TAG_SERIES;
import static seedu.trackermon.testutil.Assert.assertThrows;
import static seedu.trackermon.testutil.TypicalShows.HANCOCK;
import static seedu.trackermon.testutil.TypicalShows.WEATHERING_WITH_YOU;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackermon.model.show.exceptions.DuplicatedShowException;
import seedu.trackermon.model.show.exceptions.ShowNotFoundException;
import seedu.trackermon.testutil.ShowBuilder;

public class UniqueShowListTest {

    private final UniqueShowList uniqueShowList = new UniqueShowList();

    @Test
    public void contains_nullShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShowList.contains(null));
    }

    @Test
    public void contains_showNotInList_returnsFalse() {
        assertFalse(uniqueShowList.contains(HANCOCK));
    }

    @Test
    public void contains_showInList_returnsTrue() {
        uniqueShowList.add(HANCOCK);
        assertTrue(uniqueShowList.contains(HANCOCK));
    }

    @Test
    public void contains_showWithSameIdentityFieldsInList_returnsTrue() {
        uniqueShowList.add(HANCOCK);
        Show editedMe = new ShowBuilder(HANCOCK).withTags(VALID_TAG_MOVIE)
                .build();
        assertTrue(uniqueShowList.contains(editedMe));
    }

    @Test
    public void add_nullShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShowList.add(null));
    }

    @Test
    public void add_duplicateShow_throwsDuplicateShowException() {
        uniqueShowList.add(HANCOCK);
        assertThrows(DuplicatedShowException.class, () -> uniqueShowList.add(HANCOCK));
    }

    @Test
    public void setShow_nullTargetShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShowList.setShow(null, HANCOCK));
    }

    @Test
    public void setShow_nullEditedShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShowList.setShow(HANCOCK, null));
    }

    @Test
    public void setShow_targetShowNotInList_throwsShowNotFoundException() {
        assertThrows(ShowNotFoundException.class, () -> uniqueShowList.setShow(HANCOCK, HANCOCK));
    }

    @Test
    public void setShow_editedShowIsSameShow_success() {
        uniqueShowList.add(HANCOCK);
        uniqueShowList.setShow(HANCOCK, HANCOCK);
        UniqueShowList expectedUniqueShowList = new UniqueShowList();
        expectedUniqueShowList.add(HANCOCK);
        assertEquals(expectedUniqueShowList, uniqueShowList);
    }

    @Test
    public void setShow_editedShowHasSameIdentity_success() {
        uniqueShowList.add(HANCOCK);
        Show editedMe = new ShowBuilder(HANCOCK).withStatus(VALID_STATUS_WATCHING).withTags(VALID_TAG_SERIES)
                .build();
        uniqueShowList.setShow(HANCOCK, editedMe);
        UniqueShowList expectedUniqueShowList = new UniqueShowList();
        expectedUniqueShowList.add(editedMe);
        assertEquals(expectedUniqueShowList, uniqueShowList);
    }

    @Test
    public void setShow_editedShowHasDifferentIdentity_success() {
        uniqueShowList.add(HANCOCK);
        uniqueShowList.setShow(HANCOCK, WEATHERING_WITH_YOU);
        UniqueShowList expectedUniqueShowList = new UniqueShowList();
        expectedUniqueShowList.add(WEATHERING_WITH_YOU);
        assertEquals(expectedUniqueShowList, uniqueShowList);
    }

    @Test
    public void setShow_editedShowHasNonUniqueIdentity_throwsDuplicateShowException() {
        uniqueShowList.add(HANCOCK);
        uniqueShowList.add(WEATHERING_WITH_YOU);
        assertThrows(DuplicatedShowException.class, () -> uniqueShowList.setShow(HANCOCK, WEATHERING_WITH_YOU));
    }

    @Test
    public void remove_nullShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShowList.remove(null));
    }

    @Test
    public void remove_showDoesNotExist_throwsShowNotFoundException() {
        assertThrows(ShowNotFoundException.class, () -> uniqueShowList.remove(HANCOCK));
    }

    @Test
    public void remove_existingShow_removesShow() {
        uniqueShowList.add(HANCOCK);
        uniqueShowList.remove(HANCOCK);
        UniqueShowList expectedUniqueShowList = new UniqueShowList();
        assertEquals(expectedUniqueShowList, uniqueShowList);
    }

    @Test
    public void setShows_nullUniqueShowList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShowList.setShows((UniqueShowList) null));
    }

    @Test
    public void setShows_uniqueShowList_replacesOwnListWithProvidedUniqueShowList() {
        uniqueShowList.add(HANCOCK);
        UniqueShowList expectedUniqueShowList = new UniqueShowList();
        expectedUniqueShowList.add(WEATHERING_WITH_YOU);
        uniqueShowList.setShows(expectedUniqueShowList);
        assertEquals(expectedUniqueShowList, uniqueShowList);
    }

    @Test
    public void setShows_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueShowList.setShows((List<Show>) null));
    }

    @Test
    public void setShows_list_replacesOwnListWithProvidedList() {
        uniqueShowList.add(HANCOCK);
        List<Show> showList = Collections.singletonList(WEATHERING_WITH_YOU);
        uniqueShowList.setShows(showList);
        UniqueShowList expectedUniqueShowList = new UniqueShowList();
        expectedUniqueShowList.add(WEATHERING_WITH_YOU);
        assertEquals(expectedUniqueShowList, uniqueShowList);
    }

    @Test
    public void setShows_listWithDuplicateShows_throwsDuplicateShowException() {
        List<Show> listWithDuplicateShows = Arrays.asList(HANCOCK, HANCOCK);
        assertThrows(DuplicatedShowException.class, () -> uniqueShowList.setShows(listWithDuplicateShows));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueShowList.asUnmodifiableObservableList().remove(0));
    }
}
