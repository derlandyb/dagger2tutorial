package br.com.derlandybelchior.dagger2.daggeratmapplication

import java.math.BigDecimal
import javax.inject.Inject

@PerSession
class WithdrawalLimiter @Inject constructor(
    @MaximumWithdrawal val maximumWithdrawal: BigDecimal
) {
    var remainingWithdrawalLimit: BigDecimal = BigDecimal.ZERO
        private set
    init {
        remainingWithdrawalLimit = maximumWithdrawal
    }
    fun recordDeposit(amount: BigDecimal) {
        remainingWithdrawalLimit = remainingWithdrawalLimit.add(amount)
    }
    fun recordWithdrawal(amount: BigDecimal) {
        remainingWithdrawalLimit = remainingWithdrawalLimit.subtract(amount)
    }
}