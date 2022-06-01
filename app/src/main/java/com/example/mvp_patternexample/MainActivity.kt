package com.example.mvp_patternexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvp_patternexample.databinding.ActivityMainBinding

/**
 * View klassi Presenter tomonidan kiritilgan o’zgarishlarga muvofiq UI ni yangilash uchun javobgardir.
 * Model tomonidan taqdim etilgan ma’lumotlar View tomonidan ishlatiladi va activity da tegishli o’zgartirishlar kiritiladi.
 */
class MainActivity : AppCompatActivity(), Contract.View {

    // creating object of Presenter interface in Contract
    var presenter : Presenter? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // instantiating object of Presenter Interface
        presenter = Presenter(this, Model())
        initViews()
    }

    private fun initViews() {
        binding.button.setOnClickListener {
            presenter?.onButtonClick()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.textView.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
        binding.textView.visibility = View.VISIBLE
    }

    override fun setString(string: String?) {
        binding.textView.text = string
    }
}