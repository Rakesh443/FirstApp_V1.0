package com.example.firstapp
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_music.*


class MusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        // Important : handle the runtime permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        }
        var song:String
        Toast.makeText(applicationContext,"stsdfghjkkjhgfghjklart",Toast.LENGTH_LONG).show()
        // Button click listener
        button.setOnClickListener{
            // Get the external storage/sd card music files list
            Toast.makeText(applicationContext,"start",Toast.LENGTH_LONG).show()
            val list:MutableList<Music> = musicFiles()
            Toast.makeText(applicationContext,"start11111",Toast.LENGTH_LONG).show()
            // Get the sd card music titles list
            val titles = mutableListOf<String>()
            Toast.makeText(applicationContext,"start2222",Toast.LENGTH_LONG).show()
            for (music in list){titles.add(music.title)}
            Toast.makeText(applicationContext,"start3333",Toast.LENGTH_LONG).show()
            // Display external storage music files list on list view
            val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,titles)
            Toast.makeText(applicationContext,"start4444",Toast.LENGTH_LONG).show()
            list_view.adapter = adapter
            Toast.makeText(applicationContext,"start5555",Toast.LENGTH_LONG).show()
        }



    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]!=PackageManager.PERMISSION_GRANTED){
           // longToast("Access Denied:Please Provide Storage Access")
        }
    }
}


// Extension method to get all music files list from external storage/sd card
fun Context.musicFiles():MutableList<Music>{
    // Initialize an empty mutable list of music
    val list:MutableList<Music> = mutableListOf()

    // Get the external storage media store audio uri
    val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    //val uri: Uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI

    // IS_MUSIC : Non-zero if the audio file is music
    val selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0"

    // Sort the musics
    val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"
    //val sortOrder = MediaStore.Audio.Media.TITLE + " DESC"

    // Query the external storage for music files
    val cursor: Cursor? = this.contentResolver.query(
        uri, // Uri
        null, // Projection
        selection, // Selection
        null, // Selection arguments
        sortOrder // Sort order
    )

    // If query result is not empty
    if (cursor!= null && cursor.moveToFirst()){
        val id:Int = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
        val title:Int = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
        val data: Int =cursor.getColumnIndex(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString())
        // Now loop through the music files
        do {
            val audioId:Long = cursor.getLong(id)
            val audioTitle:String = cursor.getString(title)
            val audioUri: String? =cursor.getString(data)
            // Add the current music to the list
            list.add(Music(audioId,audioTitle,audioUri))
        }while (cursor.moveToNext())
    }

    // Finally, return the music files list

    return  list
}




// Initialize a new data class to hold music data
data class Music(val id: Long, val title: String, val audioUri: String?)

