package seedu.trackermon.logic.parser;

import static seedu.trackermon.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.trackermon.logic.commands.FindCommand;
import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.show.NameContainsKeywordsPredicate;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.show.ShowContainsKeywordsPredicate;
import seedu.trackermon.model.show.StatusContainsKeywordsPredicate;
import seedu.trackermon.model.show.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STATUS, PREFIX_TAG);
        boolean hasPrefix = false;
        boolean hasNamePrefix = argumentMultimap.arePrefixesPresent(PREFIX_NAME);
        boolean hasStatusPrefix = argumentMultimap.arePrefixesPresent(PREFIX_STATUS);
        boolean hasTagPrefix = argumentMultimap.arePrefixesPresent(PREFIX_TAG);
        String[] keywordsArr;

        List<Predicate<Show>> predicateArrayList = new ArrayList<>();

        // Based on prefix, add user input as keywords into predicateArrayList
        // PredicateArrayList.add -> AND Operator
        // Whole list of keywords -> OR Operator
        if (hasNamePrefix) {
            hasPrefix = true;
            String input = argumentMultimap.getValue(PREFIX_NAME).get();
            keywordsArr = getKeywords(input);
            for (int i = 0; i < keywordsArr.length; i++) {
                predicateArrayList.add(new NameContainsKeywordsPredicate(Arrays.asList(keywordsArr[i])));
            }
        }

        if (hasStatusPrefix) {
            hasPrefix = true;
            String input = argumentMultimap.getValue(PREFIX_STATUS).get();
            keywordsArr = getKeywords(input);
            predicateArrayList.add(new StatusContainsKeywordsPredicate(Arrays.asList(keywordsArr)));
        }

        if (hasTagPrefix) {
            hasPrefix = true;
            List<String> input = argumentMultimap.getAllValues(PREFIX_TAG);
            for (int i = 0; i < input.size(); i++) {
                predicateArrayList.add(new TagsContainsKeywordsPredicate(Arrays.asList(input.get(i))));
            }
        }

        // if no prefix, find acts as a general search based on 1 keyword,
        // otherwise it acts as a precise search based on prefix and keywords and implements an AND search
        if (!hasPrefix) {
            keywordsArr = getKeywords(args);
            return new FindCommand(new ShowContainsKeywordsPredicate(Arrays.asList(keywordsArr)));
        } else {
            return new FindCommand(predicateArrayList.stream().reduce(Predicate::and).orElse(x ->true));
        }
    }

    public String[] getKeywords(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return trimmedArgs.split("\\s+");
    }
}
