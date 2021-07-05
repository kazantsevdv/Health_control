package com.kazantsev.healthcontrol.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kazantsev.healthcontrol.App
import com.kazantsev.healthcontrol.R
import com.kazantsev.healthcontrol.databinding.FragmertHealthBinding
import com.kazantsev.healthcontrol.model.HealthItem
import com.kazantsev.healthcontrol.ui.adapter.OnListItemClickListener
import com.kazantsev.healthcontrol.ui.adapter.RvAdapter
import com.kazantsev.healthcontrol.ui.adapter.SwipeToDelete
import com.kazantsev.healthcontrol.ui.adapter.vhitem.RecordVHList
import com.kazantsev.healthcontrol.ui.adapter.vhitem.TitleVHList
import com.kazantsev.healthcontrol.ui.model.DataItem
import javax.inject.Inject
import javax.inject.Provider


class HealthFragment : Fragment() {
    @Inject
    lateinit var viewModeProvider: Provider<HealthViewModel.Factory>

    private val viewModel: HealthViewModel by viewModels { viewModeProvider.get() }

    private var _viewBinding: FragmertHealthBinding? = null
    private val viewBinding get() = checkNotNull(_viewBinding)


    private val adapterList by lazy(LazyThreadSafetyMode.NONE) {
        RvAdapter(getVhList(), onListItemClickListener)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.component.inject(this)
        _viewBinding = FragmertHealthBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
        initSwipeToDelete()
    }

    private fun setupUI() {
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)

        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)?.let {
            dividerItemDecoration.setDrawable(
                it
            )
        }
        viewBinding.rvData.apply {
            adapter = adapterList
            addItemDecoration(dividerItemDecoration)
        }
        viewBinding.fab.setOnClickListener {
            val newFragment = AddDialog.newInstance()
            newFragment
                .show(parentFragmentManager, "dialog")
        }
        parentFragmentManager.setFragmentResultListener(
            DIALOG_RESULT,
            this
        ) { key: String, bundle: Bundle ->
            if (key == DIALOG_RESULT) {
                val rez: HealthItem = bundle.getParcelable(DIALOG_RESULT) ?: HealthItem()
                viewModel.saveItem(rez)

            }
        }
    }

    private fun initSwipeToDelete() {

        val swipeToDeleteCallback = SwipeToDelete { positionForRemove: Int ->
            val removedItem = viewModel.item.value
            removedItem?.let {
                viewModel.deleteItem(it[positionForRemove])

            }
        }
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(viewBinding.rvData)
    }

    private fun setupObservers() {
        viewModel.item.observe(viewLifecycleOwner, {
            adapterList.submitList(it)

        })
    }

    private val onListItemClickListener: OnListItemClickListener =
        object : OnListItemClickListener {
            override fun onItemClick(data: DataItem.Item) {
                val newFragment = AddDialog.newInstance(
                    HealthItem(
                        data.date,
                        data.pressUp.toInt(),
                        data.pressDown.toInt(),
                        data.pulse.toInt()
                    )
                )
                newFragment
                    .show(parentFragmentManager, "dialog")
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    private fun getVhList() = listOf(
        TitleVHList(),
        RecordVHList()
    )

    companion object {
        const val DIALOG_RESULT = "DIALOG_RESULT"
    }
}