package br.com.derlandybelchior.dagger2.daggeratmapplication

import dagger.Module
import dagger.Provides
import java.math.BigDecimal

@Module
interface AmountsModule {
    companion object {
        @Provides
        @MinimumBalance
        @JvmStatic
        fun minimumBalance(): BigDecimal = BigDecimal.ZERO


        @Provides
        @MaximumWithdrawal
        @JvmStatic
        fun maximumWithdrawal(): BigDecimal = BigDecimal(1000)

    }

}