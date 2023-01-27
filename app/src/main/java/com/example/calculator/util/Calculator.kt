package com.example.calculator.util

import com.example.calculator.enums.Operator
import com.example.calculator.enums.Operator.PLUS
import com.example.calculator.enums.Operator.MINUS
import com.example.calculator.enums.Operator.DIVIDE
import com.example.calculator.enums.Operator.MULTIPLY
import java.math.BigInteger

class Calculator {

    companion object {
        const val DIVIDE_BY_ZERO_ERROR = "Can't divide by zero"
    }

    private var num1: BigInteger? = null
    private var num2: BigInteger? = null
    private var result: BigInteger? = null
    private var operator: Operator? = null
    private var num1OperatorStr = ""
    private var resultString = ""

    private var hasDivideByZeroException = false


    fun addNum(value: String): String {
        hasDivideByZeroException = false
        val hasOperator = operator != null
        if (hasOperator) {
            num2 = setNum(num2 ?: BigInteger("0"), value)
        } else {
            if (result != null) {
                result = null
                num1 = null
            }
            num1 = setNum(num1 ?: BigInteger("0"), value)
        }
        resultString = if (hasOperator) "$num1OperatorStr $num2" else "$num1"
        return resultString
    }

    fun addOperator(operator: Operator): String {
        if (num1 == null) return resultString
        if (num2 != null) count()
        if (hasDivideByZeroException) return DIVIDE_BY_ZERO_ERROR
        this.operator = operator
        val operatorStr = when (operator) {
            PLUS -> "+"
            MINUS -> "-"
            DIVIDE -> "/"
            MULTIPLY -> "*"
        }
        num1OperatorStr = "$num1 $operatorStr"
        resultString = num1OperatorStr
        return resultString
    }

    fun count(): String {
        if (num1 == null) {
            return ""
        }
        if (num2 == null) {
            result = null
            operator = null
            resultString = "$num1"
            return resultString
        }
        try {
            result = when (operator) {
                PLUS -> num1!! + num2!!
                MINUS -> num1!! - num2!!
                DIVIDE -> num1!! / num2!!
                MULTIPLY -> num1!! * num2!!
                null -> num1
            }
            operator = null
            num1 = result
            num2 = null
            resultString = "$resultString = $result"
            return resultString
        } catch (e: ArithmeticException) {
            operator = null
            num1 = result
            num2 = null
            hasDivideByZeroException = true
            return DIVIDE_BY_ZERO_ERROR
        }
    }

    fun clear(): String {
        hasDivideByZeroException = false
        operator = null
        num1 = null
        num2 = null
        result = null
        num1OperatorStr = ""
        resultString = ""
        return resultString
    }

    private fun setNum(field: BigInteger, value: String): BigInteger {
        return if (field < BigInteger("0")) {
            field * BigInteger("10") - BigInteger(value)
        } else {
            field * BigInteger("10") + BigInteger(value)
        }
    }
}