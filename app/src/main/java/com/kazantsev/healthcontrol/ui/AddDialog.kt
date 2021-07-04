package com.kazantsev.healthcontrol.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kazantsev.healthcontrol.databinding.DialogAddBinding

class AddDialog : DialogFragment() {
    private var _viewBinding: DialogAddBinding? = null
    private val binding get() = checkNotNull(_viewBinding)

//    private val data: Marker? by lazy { arguments?.getParcelable(ARG_PARAM1) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = DialogAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setBtnListeners()

    }

    private fun setupUI() {

//        data?.let {
//            binding.tvLatitude.text = it.latitudeStr
//            binding.tvLongitude.text = it.longitudeStr
//            binding.tvTitle.setText(it.title)
//            binding.tvAnnotation.setText(it.info)
//        }
    }

    private fun setBtnListeners() {

        binding.btOk.setOnClickListener {
            saveData()
            dismiss()
        }

        binding.btCancel.setOnClickListener { dismiss() }
    }

    private fun saveData() {
//        data?.let { data ->
//            (activity as? DialogListener)?.onOkClick(
//                Marker(
//                    data.latitude,
//                    data.longitude,
//                    binding.tvTitle.text.toString(),
//                    binding.tvAnnotation.text.toString()
//                )
//            )
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    companion object {
        private const val ARG_PARAM1 = "ARG_PARAM1"

        @JvmStatic
        fun newInstance(data: String) =
            AddDialog().apply {
                arguments = Bundle().apply {
                    //putParcelable(ARG_PARAM1, data)
                }
            }
    }


}