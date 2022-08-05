package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Command.Result

abstract class SingleArgCommand : Command {
    override fun handleInput(input: List<String>): Result {
        return if (input.size == 1 ) handleArg(input[0]) else Result.invalid()
    }

    protected abstract fun handleArg(arg: String): Result
}