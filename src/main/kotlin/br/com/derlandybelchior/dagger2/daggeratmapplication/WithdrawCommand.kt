package br.com.derlandybelchior.dagger2.daggeratmapplication

import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val account: Database.Account,
    override val outputter: Outputter,
    @MinimumBalance private val minimumBalance: BigDecimal,
    private val withdrawalLimiter: WithdrawalLimiter
) : BigDecimalCommand(outputter) {

    override fun handleAmount(amount: BigDecimal) {

        val remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit
        if(amount > remainingWithdrawalLimit) {
            outputter.output(
                String.format(
                    "you may not withdraw %s; you may withdraw %s more in this session",
                    amount, remainingWithdrawalLimit
                )
            )
            return
        }

        val newBalance = account.balance().subtract(amount)
        if(newBalance < minimumBalance) {
            outputter.output(
                String.format(
                    "you don't have sufficient funds to withdraw %s. "
                        + "Result balance is %s and the minimum balance is %s",
                    amount, account.balance(), minimumBalance))
        } else {
            account.withdraw(amount)
            withdrawalLimiter.recordWithdrawal(amount)
            outputter.output("Result new balance is: ${account.balance()}")
        }
    }

    override fun key() = "withdraw"
}