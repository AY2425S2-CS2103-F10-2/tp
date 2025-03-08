package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static seedu.address.logic.Messages.MESSAGE_NOT_IMPLEMENTED_YET;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import seedu.address.model.Model;

public class RemarkCommandTest {
    private Model model;

    @Test
    public void execute() {
        assertCommandFailure(new RemarkCommand(), model, MESSAGE_NOT_IMPLEMENTED_YET);
    }

}
