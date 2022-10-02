package at.maximilian.memosyne.db

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
@Deprecated("Not used. Use rooms built-in ability to execute queries on a background thread")
fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}