package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyShowList;
import seedu.address.model.ShowList;
import seedu.address.model.show.Name;
import seedu.address.model.show.Show;
import seedu.address.model.show.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ShowList} with sample data.
 */
public class SampleDataUtil {
    public static Show[] getSampleShows() {
        return new Show[] {
            new Show(new Name("Attack on Titan"), Status.WATCHING, getTagSet("Anime")),
            new Show(new Name("Another"), Status.COMPLETED, getTagSet("Anime", "Horror"))
        };
    }

    public static ReadOnlyShowList getSampleShowList() {
        ShowList sampleShowList = new ShowList();
        for (Show sampleShow : getSampleShows()) {
            sampleShowList.addShow(sampleShow);
        }
        return sampleShowList;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
