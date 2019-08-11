package au.com.mebank.transactions.analyser.engines

import au.com.mebank.transactions.analyser.constants.TransactionType
import au.com.mebank.transactions.analyser.inputs.DATE_FORMAT
import au.com.mebank.transactions.analyser.inputs.read
import au.com.mebank.transactions.analyser.models.AccountBalance
import au.com.mebank.transactions.analyser.models.Transaction
import java.text.SimpleDateFormat
import java.util.*
import kotlin.streams.toList

fun balance(filepath: String, accountId: String, from: String, to: String): AccountBalance {
    val fromDate    = toDate(from)
    val toDate      = toDate(to)
    val payments    = payments(filepath, accountId, fromDate, toDate)
    val reversals   = reversals(filepath, accountId)

    return reconcile(payments, reversals)
}

private fun toDate(date: String): Date =
    SimpleDateFormat(DATE_FORMAT).parse(date)

private fun payments(filepath: String, accountId: String, fromDate: Date, toDate: Date): AccountBalance =
    read(filepath)
        .filter { it.fromAccountId == accountId && it.transactionType == TransactionType.PAYMENT && it.createdAt in fromDate..toDate }
        .toList()
        .fold(AccountBalance(), updatePaymentsBalance)

private fun reversals(filepath: String, accountId: String): AccountBalance =
    read(filepath)
        .filter { it.fromAccountId == accountId && it.transactionType == TransactionType.REVERSAL }
        .toList()
        .fold(AccountBalance(), updateReversalsBalance)

private val updatePaymentsBalance = {
    accountBalance: AccountBalance, transaction: Transaction ->
        accountBalance.copy(
            balance              = accountBalance.balance - transaction.amount,
            numberOfTransactions = accountBalance.numberOfTransactions + 1
        )
}

private val updateReversalsBalance = {
    accountBalance: AccountBalance, transaction: Transaction ->
        accountBalance.copy(
            balance              = accountBalance.balance + transaction.amount,
            numberOfTransactions = accountBalance.numberOfTransactions + 1
        )
}

private fun reconcile(payments: AccountBalance, reversals: AccountBalance): AccountBalance =
    payments.copy(
        balance              = payments.balance + reversals.balance,
        numberOfTransactions = payments.numberOfTransactions - reversals.numberOfTransactions
    )
