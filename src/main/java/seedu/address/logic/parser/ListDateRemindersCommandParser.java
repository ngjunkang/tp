package seedu.address.logic.parser;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;

import java.time.LocalDate;

import seedu.address.logic.commands.ListDateRemindersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.ReminderDate;

/**
 * Parses input arguments and creates a new ListDateRemindersCommand object.
 */
public class ListDateRemindersCommandParser implements Parser<ListDateRemindersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ListDateRemindersCommand}
     * and returns a ListDateRemindersCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListDateRemindersCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_REMINDER_DATE);
        ReminderDate reminderDate;

        if (!argMultimap.getValue(PREFIX_REMINDER_DATE).isPresent()) {
            reminderDate = new ReminderDate(LocalDate.now());
        } else {
            try {
                reminderDate = ParserUtil.parseReminderDate(args);
            } catch (ParseException pe) {
                throw new ParseException(format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListDateRemindersCommand.MESSAGE_USAGE), pe);
            }
        }

        return new ListDateRemindersCommand(reminderDate);
    }
}
