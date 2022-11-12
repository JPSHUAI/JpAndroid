package com.jp.androidpro.ui.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.drake.engine.base.EngineFragment
import com.drake.net.Get
import com.drake.net.sample.constants.Api
import com.drake.net.utils.scope
import com.drake.net.utils.scopeNetLife
import com.jp.androidpro.R
import com.jp.androidpro.interfaces.LeastAnimationStateChangedHandler
import com.jp.androidpro.databinding.FragmentHomeBinding
import com.jp.androidpro.model.Article
import com.jp.androidpro.model.PageResponse


class HomeFragment : EngineFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var viewModel: HomeFragmentViewModel

    override fun initData() {
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        //binding.state.stateChangedHandler = LeastAnimationStateChangedHandler()
    }

    override fun initView() {
        binding.rv.linear().setup {
            addType<Article>(R.layout.item_article)
        }

        binding.page.onRefresh {
            scope {
                val response = Get<PageResponse<Article>>(String.format(Api.ARTICLE, index)).await()
                Log.d("jp", "initView: $response")
                addData(response.datas) {
                    index < response.pageCount
                }
            }
        }.autoRefresh()
    }

}