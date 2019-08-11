package au.com.mebank.transactions.analyser.engines

import au.com.mebank.transactions.analyser.inputs.read
import au.com.mebank.transactions.analyser.models.AccountBalance
import au.com.mebank.transactions.analyser.models.Transaction
import java.text.SimpleDateFormat
import java.util.*
import kotlin.streams.toList

private const val DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"

fun balance(filepath: String, accountId: String, from: String, to: String): AccountBalance {
    val fromDate: Date  = toDate(from)
    val toDate: Date    = toDate(to)

    val balance = read(filepath)
            .filter { it.fromAccountId == accountId && it.createdAt in fromDate..toDate }
            .toList()
            .fold(AccountBalance(), updateBalance)

    return balance
}

private fun toDate(date: String) = SimpleDateFormat(DATE_FORMAT).parse(date)

private val updateBalance = {
    accountBalance: AccountBalance, transaction: Transaction ->
        accountBalance.copy(
            balance = accountBalance.balance - transaction.amount,
            numberOfTransactions = accountBalance.numberOfTransactions + 1
        )
}
