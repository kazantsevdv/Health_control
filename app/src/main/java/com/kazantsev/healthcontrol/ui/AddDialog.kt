package com.kazantsev.healthcontrol.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kazantsev.healthcontrol.R
import com.kazantsev.healthcontrol.databinding.DialogAddBinding
import com.kazantsev.healthcontrol.model.HealthItem
import com.kazantsev.healthcontrol.ui.HealthFragment.Companion.DIALOG_RESULT
import java.text.SimpleDateFormat
import java.util.*


class AddDialog : DialogFragment() {
    private var _viewBinding: DialogAddBinding? = null
    private val binding get() = checkNotNull(_viewBinding)
    var dateAndTime: Calendar = Calendar.getInstance()
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
        setInitialDateTime()
    }

    private fun setInitialDateTime() {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yy HH:mm", Locale.getDefault())
        binding.tvDate.text = simpleDateFormat.format(dateAndTime.time)

    }


    private fun setBtnListeners() {

        binding.btOk.setOnClickListener {
            saveData()

        }

        binding.btCancel.setOnClickListener { dismiss() }
    }

    private fun saveData() {
        if (checkData()) {
            val bundle = Bundle().apply {
                putParcelable(
                    DIALOG_RESULT, HealthItem(
                        dateAndTime.time,
                        binding.etPressUp.text.toString().toInt(),
                        binding.etPressDown.text.toString().toInt(),
                        binding.etPulse.text.toString().toInt()
                    )
                )

            }
            parentFragmentManager.setFragmentResult(DIALOG_RESULT, bundle)
            dismiss()
        }

    }

    private fun checkData(): Boolean {


        if (binding.etPressUp.text.isNullOrEmpty() || binding.etPressUp.text.toString()
                .toInt() < 50 || binding.etPressUp.text.toString().toInt() > 300
        ) {
            binding.etPressUp.error = getString(R.string.err)
            return false
        }
        if (binding.etPressDown.text.isNullOrEmpty() || binding.etPressDown.text.toString()
                .toInt() < 50 || binding.etPressDown.text.toString()
                .toInt() > 300 || binding.etPressDown.text.toString()
                .toInt() > binding.etPressUp.text.toString().toInt()
        ) {
            binding.etPressDown.error = getString(R.string.err)
            return false
        }
        if (binding.etPulse.text.isNullOrEmpty() || binding.etPulse.text.toString()
                .toInt() < 1 || binding.etPulse.text.toString().toInt() > 300
        ) {
            binding.etPulse.error = getString(R.string.err)
            return false
        }
        return true
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