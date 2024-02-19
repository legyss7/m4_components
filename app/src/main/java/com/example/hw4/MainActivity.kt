package com.example.hw4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.hw4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var statusBar : Int = 0;
    private var statusBarStr : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameText.doOnTextChanged { _, _, _, _ -> checkAndEnableSaveButton(binding) }

        binding.phoneTextView.doOnTextChanged { _, _, _, _ -> checkAndEnableSaveButton(binding) }
        binding.maleRadioButton.setOnCheckedChangeListener { _, _ ->
            checkAndEnableSaveButton(
                binding
            )
        }
        binding.femaleRadioButton.setOnCheckedChangeListener { _, _ ->
            checkAndEnableSaveButton(
                binding
            )
        }
        binding.receiveNotificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            checkAndEnableSaveButton(binding)
            if (isChecked) {
                binding.aboutAuthorizationOnDeviceCheckBox.isEnabled = true
                binding.aboutNewProductsAndOffersCheckBox.isEnabled = true
            } else {
                binding.aboutAuthorizationOnDeviceCheckBox.isEnabled = false
                binding.aboutNewProductsAndOffersCheckBox.isEnabled = false
            }
        }
        binding.aboutAuthorizationOnDeviceCheckBox.setOnCheckedChangeListener { _, _ ->
            checkAndEnableSaveButton(
                binding
            )
        }
        binding.aboutNewProductsAndOffersCheckBox.setOnCheckedChangeListener { _, _ ->
            checkAndEnableSaveButton(
                binding
            )
        }
        statusBar = Random.nextInt(101)
        binding.progressBar.progress = statusBar
        statusBarStr = "$statusBar/100"
        binding.charCount.text = statusBarStr

        binding.saveButton.setOnClickListener {
            Toast.makeText(this, "Информация сохранена", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAndEnableSaveButton(binding: ActivityMainBinding) {
        val isNameValid =
            !binding.nameText.text.isNullOrEmpty() && binding.nameText.text!!.length <= 40
        val isPhoneValid = !binding.phoneTextView.text.isNullOrEmpty()
        val isGenderSelected =
            binding.maleRadioButton.isChecked || binding.femaleRadioButton.isChecked
        val isNotificationValid = !binding.receiveNotificationSwitch.isChecked ||
                (binding.aboutAuthorizationOnDeviceCheckBox.isChecked ||
                        binding.aboutNewProductsAndOffersCheckBox.isChecked)

        binding.saveButton.isEnabled =
            isNameValid && isPhoneValid && isGenderSelected && isNotificationValid
    }
}