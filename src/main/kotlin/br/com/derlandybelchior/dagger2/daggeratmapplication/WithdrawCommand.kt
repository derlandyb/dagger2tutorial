package br.com.derlandybelchior.dagger2.daggeratmapplication

import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val account: Database.Account,
    override val outputter: Outputter,
    @MinimumBalance private val minimumBalance: BigDecimal,
    private val withdrawalLimiter: WithdrawalLimiter,
    private val commandLineAtmMessages: CommandLineAtmMessages
) : BigDecimalCommand(outputter, commandLineAtmMessages) {

    override fun handleAmount(amount: BigDecimal) {

        val remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit
        if(amount > remainingWithdrawalLimit) {
            commandLineAtmMessages.remainingWithdrawalLimit(amount, remainingWithdrawalLimit)
            return
        }

        val newBalance = account.balance().subtract(amount)
        if(newBalance < minimumBalance) {
            commandLineAtmMessages.insufficientFunds(amount, account.balance(), minimumBalance)
        } else {
            account.withdraw(amount)
            withdrawalLimiter.recordWithdrawal(amount)
            commandLineAtmMessages.newBalance(account.username, account.balance())
        }
    }

    override fun key() = "withdraw"
}