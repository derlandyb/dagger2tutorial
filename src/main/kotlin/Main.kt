import br.com.derlandybelchior.dagger2.daggeratmapplication.Command
import br.com.derlandybelchior.dagger2.daggeratmapplication.CommandProcessorFactory
import java.util.Scanner

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val commandProcessor = CommandProcessorFactory.create().processor()
    val scanner = Scanner(System.`in`)

    while (scanner.hasNextLine()) {
        val commandStatus = commandProcessor.process(scanner.nextLine())

        if(commandStatus == Command.Status.INPUT_COMPLETED) {
            break
        }
    }
}