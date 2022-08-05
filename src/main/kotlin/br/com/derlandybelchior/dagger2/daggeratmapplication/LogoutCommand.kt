package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Database.Account
import javax.inject.Inject

class LogoutCommand @Inject constructor(
    private val account: Account,
    private val outputter: Outputter
) : Command {
    override fun key() = "logout"

    override fun handleInput(input: List<String>): Command.Result {

        if(input.isNotEmpty()) {
            return Command.Result.invalid()
        }

        outputter.output("logged out ${account.username}")
        return Command.Result.inputCompleted()
    }
}