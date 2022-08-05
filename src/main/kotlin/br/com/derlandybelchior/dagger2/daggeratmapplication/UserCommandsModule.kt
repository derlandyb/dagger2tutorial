package br.com.derlandybelchior.dagger2.daggeratmapplication

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class UserCommandsModule {
    @Binds
    @IntoMap
    @StringKey("deposit")
    abstract fun deposit(command: DepositCommand): Command

    @Binds
    @IntoMap
    @StringKey("withdraw")
    abstract fun withdraw(command: WithdrawCommand): Command

    @Binds
    @IntoMap
    @StringKey("logout")
    abstract fun logout(command: LogoutCommand): Command
}