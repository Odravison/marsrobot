package com.contaazul.utils;

import com.contaazul.exceptions.BadMovementException;

/**
 * Created by odravison on 01/02/17.
 */
public class CommandVerify {

    public static String checkCommand(String command) throws BadMovementException {

        if (!command.isEmpty()) {
            /**
             * Verify if is a invalid command
             */
            if (command.matches("^[LRM]*$")) {
                return command;
            }
        }

        throw new BadMovementException("INVALID COMMAND");
    }
}
