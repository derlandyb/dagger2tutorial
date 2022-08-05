package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Database.Account
import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(
    private val account: Account,
    override val outputter: Outputter,
    private val withdrawalLimiter: WithdrawalLimiter,
    private val commandLineAtmMessages: CommandLineAtmMessages
): BigDecimalCommand(outputter, commandLineAtmMessages) {
    override fun key() = "deposit"

    override fun handleAmount(amount: BigDecimal) {
        account.deposit(amount)
        withdrawalLimiter.recordDeposit(amount)
        commandLineAtmMessages.newBalance(account.username, account.balance())
    }
}