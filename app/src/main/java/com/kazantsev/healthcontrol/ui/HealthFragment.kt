package com.kazantsev.healthcontrol.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kazantsev.healthcontrol.databinding.FragmertHealthBinding


class HealthFragment : Fragment() {
//    @Inject
//    lateinit var viewModeProvider: Provider<RadditViewModel.Factory>

//    private val viewModel: RadditViewModel by viewModels { viewModeProvider.get() }

    private var _viewBinding: FragmertHealthBinding? = null
    private val viewBinding get() = checkNotNull(_viewBinding)

//    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
//        AdapterList()
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        App.component.inject(this)
        _viewBinding = FragmertHealthBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        loadData()
    }


    private fun loadData() {

//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            viewModel.getPosts().collectLatest {
//                adapter.submitData(it)
//            }
//        }
    }

    private fun setupUI() {


//        viewBinding.rvData.adapter = adapter
//            .withLoadStateHeaderAndFooter(
//                header = LoaderStateAdapter({ adapter.retry() }, {
//                    Snackbar.make(
//                        viewBinding.root,
//                        it,
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }),
//                footer = LoaderStateAdapter({ adapter.retry() }, {
//                    Snackbar.make(
//                        viewBinding.root,
//                        it,
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                })
//
//            )
//        viewBinding.refresh.setOnRefreshListener { adapter.refresh() }

    }


    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}