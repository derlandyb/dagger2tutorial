package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Command.Result
import javax.inject.Inject

class CommandRouter @Inject constructor(
    private val commands: Map<String, @JvmSuppressWildcards Command>,
    private val commandLineAtmMessages: CommandLineAtmMessages
) {

    fun route(input: String): Result{
        val splitInput = split(input)

        if (splitInput.isEmpty()) {
            return invalidCommand(input)
        }

        val commandKey = splitInput[0]
        val command = commands[commandKey] ?: return invalidCommand(input)

        val result = command.handleInput(splitInput.subList(1, splitInput.size))

        if (result.status == Command.Status.INVALID) {
            commandLineAtmMessages.commandMessages().invalidArguments(commandKey)
        }

        return result

    }

    private fun invalidCommand(input: String) : Result {
        commandLineAtmMessages.commandMessages().invalidCommand(input)

        return Result.invalid()
    }

    private fun split(input: String) = input.split(" ")
        .toTypedArray()
        .filter { it.isNotEmpty() }
}