package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Database.Account
import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface CommandsModule {

    @Binds
    @IntoMap
    @StringKey("hello")
    fun helloWorld(command: HelloWorldCommand): Command

    @Binds
    @IntoMap
    @StringKey("login")
    fun login(command: LoginCommand): Command

    @BindsOptionalOf
    fun loggedInAccount(): Account
}