package com.example.calculator.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.util.Calculator
import com.example.calculator.enums.Operator
import com.example.calculator.R

class CalculatorActivity : AppCompatActivity() {

    private val calculator = Calculator()

    private var animationListener = CalculatorAnimationListener()

    private var zoomInAnim: Animation? = null
    private var zoomOutAnim:Animation? = null

    private var resultTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        zoomOutAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        zoomInAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_in)

        resultTv = findViewById(R.id.result_tv)
        val oneBtn: Button = findViewById(R.id.one_btn)
        val twoBtn: Button = findViewById(R.id.two_btn)
        val threeBtn: Button = findViewById(R.id.three_btn)
        val fourBtn: Button = findViewById(R.id.four_btn)
        val fiveBtn: Button = findViewById(R.id.five_btn)
        val sixBtn: Button = findViewById(R.id.six_btn)
        val sevenBtn: Button = findViewById(R.id.seven_btn)
        val eightBtn: Button = findViewById(R.id.eight_btn)
        val nineBtn: Button = findViewById(R.id.nine_btn)
        val zeroBtn: Button = findViewById(R.id.zero_btn)
        val clearBtn: Button = findViewById(R.id.clear_btn)
        val plusBtn: Button = findViewById(R.id.plus_btn)
        val minusBtn: Button = findViewById(R.id.minus_btn)
        val multiplyBtn: Button = findViewById(R.id.multiply_btn)
        val divideBtn: Button = findViewById(R.id.divide_btn)
        val equalsBtn: Button = findViewById(R.id.equals_btn)

        oneBtn.setOnClickListener { onNumClick(it, "1") }
        twoBtn.setOnClickListener { onNumClick(it, "2") }
        threeBtn.setOnClickListener { onNumClick(it, "3") }
        fourBtn.setOnClickListener { onNumClick(it, "4") }
        fiveBtn.setOnClickListener { onNumClick(it, "5") }
        sixBtn.setOnClickListener { onNumClick(it, "6") }
        sevenBtn.setOnClickListener { onNumClick(it, "7") }
        eightBtn.setOnClickListener { onNumClick(it, "8") }
        nineBtn.setOnClickListener { onNumClick(it, "9") }
        zeroBtn.setOnClickListener { onNumClick(it, "0") }
        plusBtn.setOnClickListener { onOperatorClick(it, Operator.PLUS) }
        minusBtn.setOnClickListener { onOperatorClick(it, Operator.MINUS) }
        multiplyBtn.setOnClickListener { onOperatorClick(it, Operator.MULTIPLY) }
        divideBtn.setOnClickListener { onOperatorClick(it, Operator.DIVIDE) }
        equalsBtn.setOnClickListener { onEqualsClick(it) }
        clearBtn.setOnClickListener { onClearClick(it) }
    }

    private fun onNumClick(btn: View, num: String) {
        resultTv?.text = calculator.addNum(num)
        onBtnClick(btn)
    }

    private fun onOperatorClick(btn: View, operator: Operator) {
        resultTv?.text = calculator.addOperator(operator)
        onBtnClick(btn)
    }

    private fun onEqualsClick(btn: View) {
        resultTv?.text = calculator.count()
        onBtnClick(btn)
    }

    private fun onClearClick(btn: View) {
        resultTv?.text = calculator.clear()
        onBtnClick(btn)
    }

    private fun onBtnClick(btn: View) {
        btn.startAnimation(zoomOutAnim)
        animationListener.view = btn
        zoomOutAnim?.setAnimationListener(animationListener)
    }

    private inner class CalculatorAnimationListener : Animation.AnimationListener {
        var view: View? = null
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            view?.startAnimation(zoomInAnim)
        }
        override fun onAnimationRepeat(animation: Animation?) {}
    }
}