package br.com.derlandybelchior.dagger2.daggeratmapplication

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    UserCommandsRouter.InstallationModule::class,
    CommandsModule::class,
    SystemOutModule::class,
    InMemoryDatabaseModule::class
])
interface CommandProcessorFactory {
    fun processor(): CommandProcessor

    companion object {
        @JvmStatic
        fun create() = DaggerCommandProcessorFactory.create()
    }
}