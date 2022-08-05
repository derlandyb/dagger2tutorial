package br.com.derlandybelchior.dagger2.daggeratmapplication

import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent

@PerSession
@Subcomponent(modules = [
    AccountModule::class,
    AmountsModule::class,
    UserCommandsModule::class
])
interface UserCommandsRouter {
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance @Username username: String): UserCommandsRouter
    }

    @Module(subcomponents = [UserCommandsRouter::class])
    interface InstallationModule{}
}