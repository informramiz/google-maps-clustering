package github.informramiz.mapclusteringapplication.detailview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import github.informramiz.mapclusteringapplication.R
import github.informramiz.mapclusteringapplication.common.load
import github.informramiz.mapclusteringapplication.models.Person

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_PERSON = "extra_person"
        fun getIntent(context: Context, person: Person): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_PERSON, person)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val person = intent.extras!![EXTRA_PERSON] as Person
        circle_image_view.load(person.imageUrl)
        text_view_name.text = person.name
    }
}
