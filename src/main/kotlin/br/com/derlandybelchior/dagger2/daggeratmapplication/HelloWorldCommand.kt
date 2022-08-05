package br.com.derlandybelchior.dagger2.daggeratmapplication

import br.com.derlandybelchior.dagger2.daggeratmapplication.Command.Result
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(private val outputter: Outputter) : Command {
    override fun key() = "hello"

    override fun handleInput(input: List<String>): Result {
        return if (input.isNotEmpty()) {
           Result.invalid()
        } else {
            outputter.output("world!")
            Result.handled()
        }
    }
}