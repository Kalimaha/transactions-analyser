package au.com.mebank.transactions.analyser.inputs

import au.com.mebank.transactions.analyser.models.Transaction
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

fun read(filename: String): Stream<Transaction> = read(Files.lines(Paths.get(filename)).skip(1))

private fun read(lines: Stream<String>): Stream<Transaction> = lines.map { csv2Transaction(it) }
