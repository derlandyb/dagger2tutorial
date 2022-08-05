package br.com.derlandybelchior.dagger2.daggeratmapplication

import java.math.BigDecimal

abstract class BigDecimalCommand(
    protected open val outputter: Outputter,
    private val commandLineAtmMessages: CommandLineAtmMessages
) : SingleArgCommand(){

    override fun handleArg(arg: String): Command.Result {
        val amount = tryParse(arg)

        amount?.let {
            if(amount.signum() <= 0) {
                commandLineAtmMessages.onlyPositiveAmount()
            } else {
                handleAmount(it)
            }
        }

        return Command.Result.handled()
    }

    private fun tryParse(arg: String): BigDecimal? {
        return try {
            BigDecimal(arg)
        } catch (e: NumberFormatException) {
            null
        }
    }

    /** Handles the given (positive) `amount` of money.  */
    protected abstract fun handleAmount(amount: BigDecimal)
}