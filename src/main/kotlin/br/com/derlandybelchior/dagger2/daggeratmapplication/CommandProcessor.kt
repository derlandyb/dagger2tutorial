package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Command.Status
import java.util.LinkedList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommandProcessor @Inject constructor(firstCommandRouter: CommandRouter) {

    private val commandRouterStack = LinkedList<CommandRouter>()

    init {
        commandRouterStack.push(firstCommandRouter)
    }

    fun process(input: String): Status {
        val result = commandRouterStack.peek().route(input)

        if(result.status == Status.INPUT_COMPLETED) {
            commandRouterStack.pop()
            return if (commandRouterStack.isEmpty()) {
                Status.INPUT_COMPLETED
            } else {
                Status.HANDLED
            }
        }
        result.nestedCommandRouter.ifPresent(commandRouterStack::push)

        return result.status
    }
}