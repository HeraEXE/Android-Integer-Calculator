package com.example.calculator

import com.example.calculator.enums.Operator
import com.example.calculator.util.Calculator
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class TestCalculator {

    private lateinit var calc: Calculator

    @Before
    fun start() {
        calc = Calculator()
    }

    @Test
    fun test1() {
        assertEquals("2", calc.addNum("2"))
        assertEquals("23", calc.addNum("3"))
        assertEquals("23 +", calc.addOperator(Operator.PLUS))
        assertEquals("23 + 1", calc.addNum("1"))
        assertEquals("23 + 12", calc.addNum("2"))
        assertEquals("23 + 12 = 35", calc.count())
        assertEquals("", calc.clear())
    }

    @Test
    fun test2() {
        assertEquals("", calc.addOperator(Operator.MINUS))
        assertEquals("", calc.count())
    }

    @Test
    fun test3() {
        assertEquals("3", calc.addNum("3"))
        assertEquals("3", calc.count())
        assertEquals("3 -", calc.addOperator(Operator.MINUS))
        assertEquals("3", calc.count())
    }

    @Test
    fun test4() {
        assertEquals("1", calc.addNum("1"))
        assertEquals("1 +", calc.addOperator(Operator.PLUS))
        assertEquals("1 -", calc.addOperator(Operator.MINUS))
        assertEquals("1 *", calc.addOperator(Operator.MULTIPLY))
        assertEquals("1 /", calc.addOperator(Operator.DIVIDE))
    }

    @Test
    fun test5() {
        assertEquals("0", calc.addNum("0"))
        assertEquals("0", calc.addNum("0"))
        assertEquals("1", calc.addNum("1"))
    }

    @Test
    fun test6() {
        assertEquals("1", calc.addNum("1"))
        assertEquals("1 +", calc.addOperator(Operator.PLUS))
        assertEquals("1 + 1", calc.addNum("1"))
        assertEquals("2 -", calc.addOperator(Operator.MINUS))
    }

    @Test
    fun test7() {
        assertEquals("7", calc.addNum("7"))
        assertEquals("7 *", calc.addOperator(Operator.MULTIPLY))
        assertEquals("7 * 2", calc.addNum("2"))
        assertEquals("7 * 2 = 14", calc.count())
        assertEquals("14 +", calc.addOperator(Operator.PLUS))
    }

    @Test
    fun test8() {
        assertEquals("7", calc.addNum("7"))
        assertEquals("7 *", calc.addOperator(Operator.MULTIPLY))
        assertEquals("7 * 2", calc.addNum("2"))
        assertEquals("7 * 2 = 14", calc.count())
        assertEquals("14", calc.count())
        assertEquals("145", calc.addNum("5"))
    }

    @Test
    fun test9() {
        assertEquals("1", calc.addNum("1"))
        assertEquals("1 -", calc.addOperator(Operator.MINUS))
        assertEquals("1 - 2", calc.addNum("2"))
        assertEquals("1 - 2 = -1", calc.count())
        assertEquals("-1", calc.count())
        assertEquals("-15", calc.addNum("5"))
    }

    @Test
    fun test10() {
        assertEquals("1", calc.addNum("1"))
        assertEquals("1 /", calc.addOperator(Operator.DIVIDE))
        assertEquals("1 / 0", calc.addNum("0"))
        assertEquals(Calculator.DIVIDE_BY_ZERO_ERROR, calc.count())
    }
}