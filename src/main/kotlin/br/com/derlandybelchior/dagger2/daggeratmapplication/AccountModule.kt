package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Database.Account
import dagger.Module
import dagger.Provides

@Module
interface AccountModule {
    companion object {
        @Provides
        @JvmStatic
        fun account(database: Database, @Username username: String): Account {
            return database.getAccount(username)
        }
    }

}