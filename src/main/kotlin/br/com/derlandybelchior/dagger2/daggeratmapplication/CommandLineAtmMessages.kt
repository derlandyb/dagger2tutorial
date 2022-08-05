package br.com.derlandybelchior.dagger2.daggeratmapplication

import java.math.BigDecimal
import javax.inject.Inject

class CommandLineAtmMessages @Inject constructor(
    private val outputter: Outputter
    ) {

    fun insufficientFunds(
        amount: BigDecimal,
        balance: BigDecimal,
        minimumBalance: BigDecimal
    ) {
        outputter.output(
            String.format(
                INSUFFICIENT_FUNDS,
                amount, balance, minimumBalance))
    }

    fun newBalance(@Username username: String, balance: BigDecimal) = outputter
        .output(String.format(NEW_BALANCE, username, balance))

    fun remainingWithdrawalLimit(amount: BigDecimal, limit: BigDecimal) {
        outputter.output(
            String.format(
                REMAINING_WITHDRAWAL_LIMIT,
                amount, limit
            )
        )
    }

    fun onlyPositiveAmount() = outputter.output(AMOUNT_MUST_BE_POSITIVE)

    fun user(username: String) = UserMessages(username)

    fun commandMessages() = CommandMessages()

    inner class CommandMessages {
        fun invalidArguments(commandKey: String) {
            outputter.output(
                String.format(INVALID_ARGUMENTS, commandKey)
            )
        }

        fun invalidCommand(input: String) {
            outputter.output(
                String.format(COULD_NOT_UNDERSTAND_COMMAND, input)
            )
        }
    }

    inner class UserMessages(@Username private val username: String) {
        fun alreadyLogged() {
            outputter.output(
                String.format(ALREADY_LOGGED, username)
            )
        }

        fun tryLoginAnotherUser() {
            outputter.output(TRY_LOGIN_ANOTHER_USER)
        }
    }

    private companion object {
        const val INSUFFICIENT_FUNDS = "you don't have sufficient funds to withdraw %s. Result balance is %s and the minimum balance is %s"

        const val NEW_BALANCE = "%s now has: %s"
        const val REMAINING_WITHDRAWAL_LIMIT = "you may not withdraw %s; you may withdraw %s more in this session"
        const val ALREADY_LOGGED = "%s is already logged in"
        const val TRY_LOGIN_ANOTHER_USER = "run `logout` first before trying to log in another user"
        const val AMOUNT_MUST_BE_POSITIVE = "amount must be positive"
        const val INVALID_ARGUMENTS = "%s: invalid arguments"
        const val COULD_NOT_UNDERSTAND_COMMAND = "couldn't understand %s. please try again"
    }
}