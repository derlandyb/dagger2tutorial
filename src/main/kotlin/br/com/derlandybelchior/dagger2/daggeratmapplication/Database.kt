package br.com.derlandybelchior.dagger2.daggeratmapplication
import java.math.BigDecimal
import javax.inject.Singleton

@Singleton
interface Database {

    fun getAccount(@Username username: String) : Account

    interface Account {
        @Username
        val username: String

        fun deposit(amount: BigDecimal)
        fun withdraw(amount: BigDecimal)
        fun balance(): BigDecimal
    }
}