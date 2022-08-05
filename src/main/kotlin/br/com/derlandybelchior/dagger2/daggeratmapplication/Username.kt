package br.com.derlandybelchior.dagger2.daggeratmapplication

import javax.inject.Qualifier

/** Qualifier for the currently logged-in user. */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class Username
