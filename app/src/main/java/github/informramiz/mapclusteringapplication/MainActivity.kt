package github.informramiz.mapclusteringapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import github.informramiz.mapclusteringapplication.clusterrenderer.PersonClusterRenderer
import github.informramiz.mapclusteringapplication.models.Person
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var map: GoogleMap
    private val random = Random(1984)
    private val clusterManager by lazy {
        ClusterManager<Person>(this, map)
    }
    private val mapFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }
    private val persons = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mapFragment.getMapAsync {
            map = it
            map.uiSettings.setAllGesturesEnabled(true)
            map.uiSettings.isMapToolbarEnabled = true
            startDemo()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startDemo() {
        clusterManager.renderer = PersonClusterRenderer(this, map, clusterManager)
        initItems()
        clusterManager.addItems(persons)
        clusterManager.cluster()

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(persons[0].location, 8f))
    }

    private fun initItems() {
        // http://www.flickr.com/photos/sdasmarchives/5036248203/
        persons.add(Person(position(), "Walter", R.drawable.placeholder_image))

        // http://www.flickr.com/photos/usnationalarchives/4726917149/
        persons.add(Person(position(), "Gran", R.drawable.placeholder_image))

        // http://www.flickr.com/photos/nypl/3111525394/
        persons.add(Person(position(), "Ruth", R.drawable.placeholder_image))

        // http://www.flickr.com/photos/smithsonian/2887433330/
        persons.add(Person(position(), "Stefan", R.drawable.placeholder_image))

        // http://www.flickr.com/photos/library_of_congress/2179915182/
        persons.add(Person(position(), "Mechanic", R.drawable.placeholder_image))

        // http://www.flickr.com/photos/nationalmediamuseum/7893552556/
        persons.add(Person(position(), "Yeats", R.drawable.placeholder_image))

        // http://www.flickr.com/photos/sdasmarchives/5036231225/
        persons.add(Person(position(), "John", R.drawable.placeholder_image))

        // http://www.flickr.com/photos/anmm_thecommons/7694202096/
        persons.add(Person(position(), "Trevor the Turtle", R.drawable.placeholder_image))

        // http://www.flickr.com/photos/usnationalarchives/4726892651/
        persons.add(Person(position(), "Teach", R.drawable.placeholder_image))
    }

    private fun position(): LatLng {
        return LatLng(random(51.6723432, 51.38494009999999), random(0.148271, -0.3514683))
    }

    private fun random(min: Double, max: Double): Double {
        return random.nextDouble() * (max - min) + min
    }
}
