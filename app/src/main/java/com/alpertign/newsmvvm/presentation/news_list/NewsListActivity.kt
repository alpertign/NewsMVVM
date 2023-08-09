package com.alpertign.newsmvvm.presentation.news_list

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.alpertign.newsmvvm.R
import com.alpertign.newsmvvm.databinding.ActivityNewsListBinding
import com.alpertign.newsmvvm.domain.model.Article
import com.alpertign.newsmvvm.domain.model.NetworkResult
import com.alpertign.newsmvvm.presentation.news_detail.NewsDetailActivity
import com.alpertign.newsmvvm.util.extensions.observeOnce
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsListBinding
    private val vm: NewsListViewModel by viewModels()

    @Inject
    lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeObservers()
        initializeAdapter()

        binding.apply {
            ivHearth.setOnClickListener {
                Toast.makeText(
                    this@NewsListActivity,
                    getString(R.string.love_you_3),
                    Toast.LENGTH_SHORT
                ).show() }

            ivFilter.setOnClickListener {
                showDateRangePicker()
            }
        }
    }

    private fun initializeAdapter() {
        binding.apply {
            rvNews.layoutManager = GridLayoutManager(
                this@NewsListActivity,
                2,
                RecyclerView.VERTICAL,
                false
            )
            rvNews.adapter = newsListAdapter

            newsListAdapter.callBack = object : NewsListAdapter.NewsListAdapterCallBack {
                override fun onclickArticle(article: Article) {
                    this@NewsListActivity.run {
                        startActivity(NewsDetailActivity.newIntent(this@NewsListActivity,article.url?: ""))
                    }
                }

            }

        }
    }

    private fun initializeObservers() {
        vm.articleResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingAnimation()
                    response.data?.let {
                        newsListAdapter.setData(it)
                    }
                }

                is NetworkResult.Error -> {
                    hideLoadingAnimation()
                    readDatabase()
                    Toast.makeText(
                        this,
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    showLoadingAnimation()
                }
            }
        }
    }

    private fun showLoadingAnimation() {
        binding.animationView.visibility = View.VISIBLE
    }

    private fun hideLoadingAnimation() {
        binding.animationView.visibility = View.GONE
    }

    private fun showDateRangePicker(){
        val constraintsBuilder = CalendarConstraints.Builder()
        val now = MaterialDatePicker.todayInUtcMilliseconds()
        constraintsBuilder.setStart(now - TimeUnit.DAYS.toMillis(1))
        constraintsBuilder.setEnd(now)

        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText(getString(R.string.select_date_range))
            .setTheme(R.style.CustomMaterial3DatePicker)
            .setCalendarConstraints(constraintsBuilder.build())
            .build()

        dateRangePicker.show(supportFragmentManager,"alpertign")

        dateRangePicker.addOnPositiveButtonClickListener { selection ->
            val startDate = selection.first ?: return@addOnPositiveButtonClickListener
            val endDate = selection.second ?: return@addOnPositiveButtonClickListener

            val startDateText = formatDateToYYYYMMDD(startDate)
            val endDateText = formatDateToYYYYMMDD(endDate)

            vm.getArticles(startDateText,endDateText)

        }
        dateRangePicker.addOnNegativeButtonClickListener {
            dateRangePicker.dismiss()
        }


    }
    private fun formatDateToYYYYMMDD(date: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date(date))
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            vm.readArticles.observeOnce(this@NewsListActivity) { databaseData ->
                if (databaseData.isNotEmpty()) {
                    newsListAdapter.setData(databaseData)
                }
            }
        }
    }


}