package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyShowList;
import seedu.address.model.ShowList;
import seedu.address.model.show.Show;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "showlist")
class JsonSerializableShowList {

    public static final String MESSAGE_DUPLICATE_SHOW = "Trackermon contains duplicate show(s).";

    private final List<JsonAdaptedShow> shows = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableShowList} with the given shows.
     */
    @JsonCreator
    public JsonSerializableShowList(@JsonProperty("shows") List<JsonAdaptedShow> shows) {
        this.shows.addAll(shows);
    }

    /**
     * Converts a given {@code ReadOnlyShowList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableShowList}.
     */
    public JsonSerializableShowList(ReadOnlyShowList source) {
        shows.addAll(source.getShowList().stream().map(JsonAdaptedShow::new).collect(Collectors.toList()));
    }

    /**
     * Converts this show list into the model's {@code ShowList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ShowList toModelType() throws IllegalValueException {
        ShowList addressBook = new ShowList();
        for (JsonAdaptedShow jsonAdaptedShow : shows) {
            Show show = jsonAdaptedShow.toModelType();
            if (addressBook.hasShow(show)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SHOW);
            }
            addressBook.addShow(show);
        }
        return addressBook;
    }

}
