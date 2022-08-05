package br.com.derlandybelchior.dagger2.daggeratmapplication

import dagger.Binds
import dagger.Module

@Module
interface InMemoryDatabaseModule {
    @Binds
    fun inMemory(dataBase: InMemoryDataBase): Database
}