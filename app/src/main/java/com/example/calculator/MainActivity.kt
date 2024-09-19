package com.example.calculator

import android.annotation.SuppressLint
import android.icu.number.FormattedNumber
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private var strNumber: StringBuilder = StringBuilder()
    private lateinit var solutionPanel:TextView
    private lateinit var numberButtons: Array<Button>
    private lateinit var operatorButtons:List<Button>
    private var operator: Operator=Operator.None


    private var isOperatorClicked: Boolean=false
    private var operand1: Float = 0f

    private lateinit var button9:Button
    private lateinit var button8:Button
    private lateinit var button7:Button
    private lateinit var button6:Button
    private lateinit var button5:Button
    private lateinit var button4:Button
    private lateinit var button3:Button
    private lateinit var button2:Button
    private lateinit var button1:Button
    private lateinit var button0:Button
    private lateinit var decimalButton:Button
    private lateinit var addButton:Button
    private lateinit var subtractButton:Button
    private lateinit var multiplyButton:Button
    private lateinit var divideButton:Button
    private lateinit var equalButton:Button
    private lateinit var allClearButton:Button
    private lateinit var clearButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeComponents()
    } //on create method ends here

    private fun initializeComponents(){
        solutionPanel=findViewById(R.id.solutionPanel)
        button9=findViewById(R.id.button9)
        button8=findViewById(R.id.button8)
        button7=findViewById(R.id.button7)
        button6=findViewById(R.id.button6)
        button5=findViewById(R.id.button5)
        button4=findViewById(R.id.button4)
        button3=findViewById(R.id.button3)
        button2=findViewById(R.id.button2)
        button1=findViewById(R.id.button1)
        button0=findViewById(R.id.button0)
        decimalButton=findViewById(R.id.decimalButton)
        addButton=findViewById(R.id.additionButton)
        subtractButton=findViewById(R.id.subtractButton)
        multiplyButton=findViewById(R.id.multiplyButton)
        divideButton=findViewById(R.id.divideButton)
        equalButton=findViewById(R.id.EqualButton)
        allClearButton=findViewById(R.id.allClearButton)
        clearButton=findViewById(R.id.clearButton)


        numberButtons= arrayOf(addButton,subtractButton,decimalButton,multiplyButton,divideButton,button0,button1,button2,button3,button4,button5,button6,button7,button8,button9)
        operatorButtons= listOf(addButton,subtractButton,multiplyButton,divideButton)

        for (i in numberButtons){ i.setOnClickListener{numberButtonClicked(i)} }
        for (i in operatorButtons){i.setOnClickListener{operatorButtonsClicked(i)}}
        equalButton.setOnClickListener{buttonEqualClick()}
        allClearButton.setOnClickListener{buttonAllClearClick()}
        clearButton.setOnClickListener{buttonClearClick()}
    }
    private fun buttonClearClick(){
        val length= solutionPanel.length()
        if (length>0){
            solutionPanel.text=solutionPanel.text.subSequence(0,length -1)
        }
    }
    private fun buttonAllClearClick(){
        solutionPanel.text=""
        resultPanel.text= "0"

    }
    private fun buttonEqualClick() {
        val operand2 = strNumber.toString().toFloat()
        val result = when (operator) {
            Operator.Add -> operand1 + operand2
            Operator.Subtract ->operand1 - operand2
            Operator.Multiply ->operand1 * operand2
            Operator.Divide ->operand1 / operand2
            else -> 0
        }
        strNumber.clear()
        strNumber.append(result.toString().toFloat())
        resultPanel.text = strNumber
        isOperatorClicked=true
    }
    private fun operatorButtonsClicked(btn: Button) {
        if (btn.text=="+") operator = Operator.Add
        else if (btn.text=="-") operator = Operator.Subtract
        else if (btn.text=="x") operator = Operator.Multiply
        else if (btn.text=="/") operator = Operator.Divide
        else operator = Operator.None
        isOperatorClicked=true

    }

    private fun numberButtonClicked(btn:Button) {
        if(isOperatorClicked) {
            operand1=strNumber.toString().toFloat()
            strNumber.clear()
            isOperatorClicked=false
        }
        strNumber.append(btn.text)
        display()
    }

    @SuppressLint("SetTextI18n")
    private fun display() {
        try {
            val textDisplay = strNumber.toString().toInt()
            solutionPanel.text = textDisplay.toString()
        }
        catch (e:java.lang.IllegalArgumentException){
            strNumber.clear()
            resultPanel.text="Error"
        }
    }

} //main class ends here

enum class Operator {Add,Subtract,Multiply,Divide,None}