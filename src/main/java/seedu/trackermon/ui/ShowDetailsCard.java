package seedu.trackermon.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.trackermon.model.show.Show;


/**
 * An UI component that displays information of a {@code Show}.
 */
public class ShowDetailsCard extends UiPart<Region> {

    private static final String FXML = "ShowDetailsCard.fxml";
    private static final double SPACING = 15;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4
     * where Trackermon is based on</a>
     */

    private Show show;

    @FXML
    private HBox cardPane;
    @FXML
    private TextArea name;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ShowCard} with the given {@code Show} and index to display.
     */
    public ShowDetailsCard(Show show) {
        super(FXML);
        name.setOpaqueInsets(Insets.EMPTY);
    }

    /**
     * Updates the show details information to show the new show details.
     *
     * @param show the show whose details should be showed.
     */
    public void updateShowDetails(Show show) {
        boolean isShowExists = (show != null);
        name.setVisible(isShowExists);
        status.setVisible(isShowExists);
        tags.setVisible(isShowExists);

        if (!isShowExists) {
            return;
        }

        this.show = show;
        name.setText(show.getName().fullName);
        status.setText(show.getStatus().toString());
        tags.getChildren().clear();
        show.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        updateTextArea(name);
    }

    /**
     * Updates TextArea to resize with text height length.
     * @param textArea the text area to be resized.
     */
    public void updateTextArea(TextArea textArea) {

        Text helper = new Text(textArea.getText());

        helper.setFont(textArea.getFont());

        double wrappingWidth = textArea.getWidth();

        double fontHeight = helper.getFont().getSize() + SPACING;
        double singleHeight = helper.getBoundsInLocal().getHeight();

        helper.setWrappingWidth(wrappingWidth);
        double rowHeight = helper.getBoundsInParent().getHeight();

        int count = (int) Math.ceil(rowHeight / singleHeight);

        textArea.setPrefHeight(count * fontHeight);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowDetailsCard)) {
            return false;
        }

        // state check
        ShowDetailsCard card = (ShowDetailsCard) other;
        return show.equals(card.show);
    }
}
