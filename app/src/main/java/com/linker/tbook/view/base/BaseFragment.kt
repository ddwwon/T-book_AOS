package com.linker.tbook.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes private val layoutId: Int) : Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!

    //abstract fun observeViewModel()
    //protected abstract fun initViewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initViewBinding()
        //observeViewModel()
        //initView()
        //initViewModel()
    }

    //protected open fun initView() {}
    //protected open fun initViewModel() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}