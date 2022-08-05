package br.com.derlandybelchior.dagger2.daggeratmapplication

import java.math.BigDecimal
import javax.inject.Inject

@PerSession
class WithdrawalLimiter @Inject constructor(
    @MaximumWithdrawal val remainingWithdrawalLimit: BigDecimal
) {
    fun recordDeposit(amount: BigDecimal) {remainingWithdrawalLimit.add(amount)}
    fun recordWithdrawal(amount: BigDecimal) {remainingWithdrawalLimit.subtract(amount)}
}