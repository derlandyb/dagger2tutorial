package br.com.derlandybelchior.dagger2.daggeratmapplication

import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryDataBase @Inject constructor() : Database {

    private val accounts = HashMap<String, Database.Account>()

    override fun getAccount(username: String): Database.Account {
        return accounts.computeIfAbsent(username) {
            InMemoryAccount(it)
        }
    }

    inner class InMemoryAccount(override val username: String) : Database.Account {
        private var balance: BigDecimal = BigDecimal.ZERO

        override fun deposit(amount: BigDecimal) {
            checkNonNegative(amount, DEPOSIT_ACTION)
            balance = balance.add(amount)
        }

        override fun withdraw(amount: BigDecimal) {
            checkNonNegative(amount, WITHDRAW_ACTION)
            balance = balance.subtract(amount)
        }

        override fun balance() = balance

        private fun checkNonNegative(amount: BigDecimal, action: String) {
            if (amount.signum() == -1) {
                throw java.lang.IllegalArgumentException(
                    String.format(NON_NEGATIVE_AMOUNT_MESSAGE, action, amount)
                )
            }
        }
    }

    private companion object {
        const val NON_NEGATIVE_AMOUNT_MESSAGE = "Cannot %s negative amounts: %s"
        const val DEPOSIT_ACTION = "deposit"
        const val WITHDRAW_ACTION = "withdraw"
    }
}