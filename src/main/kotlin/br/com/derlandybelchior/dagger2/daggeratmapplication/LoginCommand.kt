package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Command.Result
import br.com.derlandybelchior.dagger2.daggeratmapplication.Database.Account
import java.util.Optional
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val outputter: Outputter,
    private val userCommandsFactory: UserCommandsRouter.Factory,
    private val account: Optional<Account>,
    private val commandLineAtmMessages: CommandLineAtmMessages
) : SingleArgCommand() {
    override fun key() = "login"

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun handleArg(username: String): Result {
        return if (account.isPresent) {
            val loggedUser = account.get().username
            commandLineAtmMessages.user(loggedUser).alreadyLogged()

            if(loggedUser != username) {
                commandLineAtmMessages.user(loggedUser).tryLoginAnotherUser()
            }
            Result.handled()
        } else {
            val userCommands = userCommandsFactory.create(username)
            Result.enterNestedCommandSet(userCommands.router())
        }
    }
}