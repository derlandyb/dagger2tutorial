package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Command.Result
import br.com.derlandybelchior.dagger2.daggeratmapplication.Database.Account
import java.util.Optional
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val outputter: Outputter,
    private val userCommandsFactory: UserCommandsRouter.Factory,
    private val account: Optional<Account>
) : SingleArgCommand() {
    override fun key() = "login"

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun handleArg(username: String): Result {
        if (account.isPresent) {
            val loggedUser = account.get().username
            outputter.output("$loggedUser is already logged in")

            if(loggedUser != username) {
                outputter.output("run `logout` first before trying to log in another user")
            }
            return Result.handled()
        } else {
            val userCommands = userCommandsFactory.create(username)
            return Result.enterNestedCommandSet(userCommands.router())
        }

    }
}