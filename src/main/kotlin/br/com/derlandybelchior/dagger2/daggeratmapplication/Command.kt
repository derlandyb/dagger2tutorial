package br.com.derlandybelchior.dagger2.daggeratmapplication

import java.util.*

interface Command {

    fun key(): String

    fun handleInput(input: List<String>) : Result

    class Result(val status: Status, val nestedCommandRouter: Optional<CommandRouter>) {

        companion object {
            @JvmStatic
            fun enterNestedCommandSet(nestedCommandRouter: CommandRouter): Result {
                return Result(Status.HANDLED, Optional.of(nestedCommandRouter))
            }

            @JvmStatic
            fun invalid(): Result {
                return Result(Status.INVALID, Optional.empty())
            }

            @JvmStatic
            fun handled(): Result {
                return Result(Status.HANDLED, Optional.empty())
            }

            @JvmStatic
            fun inputCompleted(): Result {
                return Result(Status.INPUT_COMPLETED, Optional.empty())
            }
        }


    }

    enum class Status {
        INVALID, HANDLED, INPUT_COMPLETED
    }
}