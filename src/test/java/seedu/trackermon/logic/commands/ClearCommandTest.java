//package seedu.trackermon.logic.commands;
//
//import static seedu.trackermon.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.trackermon.testutil.TypicalPersons.getTypicalAddressBook;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.trackermon.model.AddressBook;
//import seedu.trackermon.model.Model;
//import seedu.trackermon.model.ModelManager;
//import seedu.trackermon.model.UserPrefs;
//
//public class ClearCommandTest {
//
//    @Test
//    public void execute_emptyAddressBook_success() {
//        Model model = new ModelManager();
//        Model expectedModel = new ModelManager();
//
//        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//
//    @Test
//    public void execute_nonEmptyAddressBook_success() {
//        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//        expectedModel.setAddressBook(new AddressBook());
//
//        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
//    }
//
//}
