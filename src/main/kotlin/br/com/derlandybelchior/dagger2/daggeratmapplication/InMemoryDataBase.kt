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
            checkNonNegative(amount, "deposit")
            balance = balance.add(amount)
        }

        override fun withdraw(amount: BigDecimal) {
            checkNonNegative(amount, "withdraw")
            balance = balance.subtract(amount)
        }

        override fun balance() = balance

        private fun checkNonNegative(amount: BigDecimal, action: String) {
            if (amount.signum() == -1) {
                throw java.lang.IllegalArgumentException(
                    String.format("Cannot %s negative amounts: %s", action, amount)
                )
            }
        }
    }
}