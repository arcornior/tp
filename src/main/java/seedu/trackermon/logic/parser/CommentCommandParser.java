package seedu.trackermon.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_COMMENT;

import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.commons.exceptions.IllegalValueException;
import seedu.trackermon.logic.commands.CommentCommand;
import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.show.Comment;

/**
 * Parses input arguments and create a new CommentCommand object.
 */
public class CommentCommandParser {

    /**
     * Parse the given {@code String} of arguments in the context of the CommentCommand
     * and returns a CommentCommand object for execution.
     * @throw ParseException if the user input does not confirm the expected format.
     */
    public CommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CommentCommand.MESSAGE_USAGE), ive);
        }

        Comment comment = new Comment(argumentMultimap.getValue(PREFIX_COMMENT).orElse(""));
        System.out.println(comment.comment);
        return new CommentCommand(index, comment);
    }
}
