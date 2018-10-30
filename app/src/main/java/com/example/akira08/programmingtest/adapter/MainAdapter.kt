package com.example.akira08.programmingtest.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.akira08.programmingtest.R
import com.example.akira08.programmingtest.api.model.DataContactModel



class MainAdapter(private val context: Context, private val dataSource: List<DataContactModel>?): BaseAdapter(){

    lateinit var firstNameText:TextView
    lateinit var lastNameText:TextView
    lateinit var ageText:TextView
    lateinit var profPicImg:ImageView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var convertView = convertView
        convertView = inflater.inflate(R.layout.main_list_item, null)

        firstNameText = convertView.findViewById(R.id.firstNameText)
        firstNameText.text = dataSource?.get(position)?.firstName

        lastNameText = convertView.findViewById(R.id.lastNameText)
        lastNameText.text = dataSource?.get(position)?.lastName

        ageText = convertView.findViewById(R.id.ageText)
        ageText.text = dataSource?.get(position)?.age.toString()

        profPicImg = convertView.findViewById(R.id.profPicImg)
        DownloadImageTask(profPicImg).execute(dataSource?.get(position)?.photo);

        return convertView
    }

    override fun getItem(position: Int): Any {
        return dataSource!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource!!.size
    }

    private inner class DownloadImageTask(internal var bmImage: ImageView) : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val urldisplay = urls[0]
            var bmp: Bitmap? = null
            try {
                val `in` = java.net.URL(urldisplay).openStream()
                bmp = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message)
                e.printStackTrace()
            }
            if(bmp == null){
                bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_person)
            }

            return bmp
        }

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }
    }

}