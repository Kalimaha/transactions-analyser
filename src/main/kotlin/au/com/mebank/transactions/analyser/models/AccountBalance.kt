package au.com.mebank.transactions.analyser.models

import java.text.DecimalFormat

data class AccountBalance(val balance: Double = 0.0, val numberOfTransactions: Int = 0) {
    override fun toString(): String =
        "Relative balance for the period is: \$${DecimalFormat("#.00").format(balance)}\nNumber of transactions included is: $numberOfTransactions"
}