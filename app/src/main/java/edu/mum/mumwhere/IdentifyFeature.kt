package edu.mum.mumwhere

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.esri.arcgisruntime.mapping.view.Graphic
import kotlinx.android.synthetic.main.identify_feature.*
import kotlin.coroutines.coroutineContext

class IdentifyFeature: DialogFragment()
    {
        override fun onCreateDialog(savedInstanceState: Bundle?):Dialog {
            if (arguments != null)
            {

                if (arguments?.getBoolean("notAlertDialog")!!)
                {
                    return super.onCreateDialog(savedInstanceState)
                }
            }
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Alert Dialog")
            builder.setMessage("Hello! I am Alert Dialog")
            builder.setPositiveButton("Cool", object: DialogInterface.OnClickListener {
                override fun onClick(dialog:DialogInterface, which:Int) {
                    dismiss()
                }
            })
            builder.setNegativeButton("Cancel", object: DialogInterface.OnClickListener {
                override fun onClick(dialog:DialogInterface, which:Int) {
                    dismiss()
                }
            })
            return builder.create()
        }
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            return inflater.inflate(R.layout.identify_feature, container, false)
        }
        override fun onViewCreated(view:View, savedInstanceState: Bundle?) {


            var id = arguments?.getString("id")
            name.text =  "Building Name : " +  arguments?.getString("name")
            desc.text = "Building Name : "  +  arguments?.getString("desc")

            val btnClassroom = view.findViewById<Button>(R.id.btnClassroom)
            btnClassroom.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view:View) {

                    var i = Intent(context, ListOfActivity::class.java)
                    i.putExtra("calledBy", "classrooms")
                    i.putExtra("build_id", id)
                    startActivityForResult(i, 1)

                    dismiss()
                }
            })

            val btnOffices = view.findViewById<Button>(R.id.btnOffices)
            btnOffices.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view:View) {

                    var i = Intent(context, ListOfActivity::class.java)
                    i.putExtra("calledBy", "offices")
                    i.putExtra("build_id", id)
                    startActivityForResult(i, 1)

                    dismiss()
                }
            })

            val btnServices = view.findViewById<Button>(R.id.btnServices)
            btnServices.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view:View) {

                    var i = Intent(context, ListOfActivity::class.java)
                    i.putExtra("calledBy", "services")
                    i.putExtra("build_id", id)
                    startActivityForResult(i, 1)

                    dismiss()
                }
            })

            val btnDone = view.findViewById<Button>(R.id.btnDone)
            btnDone.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view:View) {
                    dismiss()
                }
            })
        }
        override fun onResume() {
            super.onResume()
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d("Hey", "onCreate")
            var  setFullScreen = false




            if (arguments != null) {
                setFullScreen = requireNotNull(arguments?.getBoolean("fullScreen"))
            }
            if (setFullScreen)
                setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        }
        override fun onDestroyView() {
            super.onDestroyView()
        }
        interface DialogListener {
            fun onFinishEditDialog(inputText:String)
        }
}