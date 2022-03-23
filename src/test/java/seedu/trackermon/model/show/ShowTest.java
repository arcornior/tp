package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.logic.commands.CommandTestUtil.VALID_NAME_HANCOCK;
import static seedu.trackermon.logic.commands.CommandTestUtil.VALID_NAME_WEATHERING_WITH_YOU;
import static seedu.trackermon.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING;
import static seedu.trackermon.logic.commands.CommandTestUtil.VALID_TAG_SERIES;
import static seedu.trackermon.testutil.Assert.assertThrows;
import static seedu.trackermon.testutil.TypicalShows.ALICE_IN_WONDERLAND;
import static seedu.trackermon.testutil.TypicalShows.WEATHERING_WITH_YOU;

import org.junit.jupiter.api.Test;

import seedu.trackermon.testutil.ShowBuilder;

public class ShowTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Show show = new ShowBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> show.getTags().remove(0));
    }

    @Test
    public void isSameShow() {
        // same object -> returns true
        assertTrue(ALICE_IN_WONDERLAND.isSameShow(ALICE_IN_WONDERLAND));

        // null -> returns false
        assertFalse(ALICE_IN_WONDERLAND.isSameShow(null));

        // same name, all other attributes different -> returns true
        Show editedAlice = new ShowBuilder(ALICE_IN_WONDERLAND).withStatus(VALID_STATUS_WATCHING)
                .withTags(VALID_TAG_SERIES).build();
        assertTrue(ALICE_IN_WONDERLAND.isSameShow(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ShowBuilder(ALICE_IN_WONDERLAND).withName(VALID_NAME_WEATHERING_WITH_YOU).build();
        assertFalse(ALICE_IN_WONDERLAND.isSameShow(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Show editedYou = new ShowBuilder(WEATHERING_WITH_YOU).withName(VALID_NAME_HANCOCK.toLowerCase()).build();
        assertFalse(WEATHERING_WITH_YOU.isSameShow(editedYou));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_WEATHERING_WITH_YOU + " ";
        editedYou = new ShowBuilder(WEATHERING_WITH_YOU).withName(nameWithTrailingSpaces).build();
        assertFalse(WEATHERING_WITH_YOU.isSameShow(editedYou));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Show aliceCopy = new ShowBuilder(ALICE_IN_WONDERLAND).build();
        assertTrue(ALICE_IN_WONDERLAND.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_IN_WONDERLAND.equals(ALICE_IN_WONDERLAND));

        // null -> returns false
        assertFalse(ALICE_IN_WONDERLAND.equals(null));

        // different type -> returns false
        assertFalse(ALICE_IN_WONDERLAND.equals(5));

        // different Show -> returns false
        assertFalse(ALICE_IN_WONDERLAND.equals(WEATHERING_WITH_YOU));

        // different name -> returns false
        Show editedAlice = new ShowBuilder(ALICE_IN_WONDERLAND).withName(VALID_NAME_WEATHERING_WITH_YOU).build();
        assertFalse(ALICE_IN_WONDERLAND.equals(editedAlice));

        // different status -> returns false
        editedAlice = new ShowBuilder(ALICE_IN_WONDERLAND).withStatus(VALID_STATUS_WATCHING).build();
        assertFalse(ALICE_IN_WONDERLAND.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ShowBuilder(ALICE_IN_WONDERLAND).withTags(VALID_TAG_SERIES).build();
        assertFalse(ALICE_IN_WONDERLAND.equals(editedAlice));
    }

}
