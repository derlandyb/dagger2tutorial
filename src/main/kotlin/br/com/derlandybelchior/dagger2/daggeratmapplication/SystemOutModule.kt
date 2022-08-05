package br.com.derlandybelchior.dagger2.daggeratmapplication

import dagger.Module
import dagger.Provides

@Module
abstract class SystemOutModule {
    companion object {
        @Provides
        @JvmStatic
        fun textOutputter()= object : Outputter {
            override fun output(output: String) {
                println(output)
            }
        }
    }

}