package github.informramiz.mapclusteringapplication.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterManager
import github.informramiz.mapclusteringapplication.R
import github.informramiz.mapclusteringapplication.clusterrenderer.PersonClusterRenderer
import github.informramiz.mapclusteringapplication.common.getDimension
import github.informramiz.mapclusteringapplication.detailview.DetailActivity
import github.informramiz.mapclusteringapplication.main.adapter.MapCardsAdapter
import github.informramiz.mapclusteringapplication.main.adapter.PageOffsetAndMarginTransformer
import github.informramiz.mapclusteringapplication.models.Person
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    companion object {
        const val DEFAULT_ZOOM = 8f
    }
    private lateinit var map: GoogleMap
    private val random = Random(1984)

    private val clusterManager by lazy {
        ClusterManager<Person>(this, map)
    }
    private val clusterRenderer by lazy {
        PersonClusterRenderer(this, map, clusterManager)
    }

    private val mapFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }
    private val persons = mutableListOf<Person>()
    private val mapCardsAdapter = MapCardsAdapter { item ->
        DetailActivity.getIntent(this, item).also { startActivity(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mapFragment.getMapAsync {
            setupMap(it)
            startDemo()
        }

        setupViewPager()
    }

    private fun setupViewPager() {
        map_cards_view_pager2.apply {
            adapter = mapCardsAdapter
            val margin = getDimension(R.dimen.map_cards_view_pager_margin)
            val offset = getDimension(R.dimen.map_cards_view_pager_page_offset)
            setPageTransformer(PageOffsetAndMarginTransformer(offset, margin))
            offscreenPageLimit = 3
        }

        map_cards_view_pager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val item = mapCardsAdapter.getItem(position)
                clusterRenderer.setMarkerSelected(item, true)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(item.location, getZoomLevel()))
            }
        })
    }

    private fun getZoomLevel(): Float {
        val currentZoom = map.cameraPosition.zoom
        return if (currentZoom < DEFAULT_ZOOM) DEFAULT_ZOOM else currentZoom
    }

    private fun setupMap(it: GoogleMap) {
        map = it
        map.uiSettings.setAllGesturesEnabled(true)
        map.uiSettings.isMapToolbarEnabled = true
        map.uiSettings.isZoomControlsEnabled = true
        clusterManager.renderer = clusterRenderer
        map.setOnMarkerClickListener { marker ->
            onMarkerClicked(marker)
            true
        }
    }

    private fun onMarkerClicked(marker: Marker) {
        clusterRenderer.setMarkerSelected(marker, true)
        val item = clusterRenderer.getClusterItem(marker)
        val itemPositionInViewPager = mapCardsAdapter.getItemPosition(item)
        itemPositionInViewPager ?: return
        map_cards_view_pager2.currentItem = itemPositionInViewPager
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
        initItems()
        clusterManager.addItems(persons)
        clusterManager.cluster()
        mapCardsAdapter.submitList(persons)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(persons[0].location, getZoomLevel()))
    }

    private fun initItems() {
        // http://www.flickr.com/photos/sdasmarchives/5036248203/
        persons.add(Person(
            position(),
            "Walter",
            "https://live.staticflickr.com/4144/5036248203_d019ccb885_n.jpg"))

        // http://www.flickr.com/photos/usnationalarchives/4726917149/
        persons.add(Person(position(), "Gran", "https:////live.staticflickr.com/1350/4726917149_2a7e7c579e_m.jpg"))

        // http://www.flickr.com/photos/nypl/3111525394/
        persons.add(Person(position(), "Ruth", "https://live.staticflickr.com/3101/3111525394_737eaf0dfd_z.jpg"))

        // http://www.flickr.com/photos/smithsonian/2887433330/
        persons.add(Person(position(), "Stefan", "https://live.staticflickr.com/3288/2887433330_7e7ed360b1_b.jpg"))

        // http://www.flickr.com/photos/library_of_congress/2179915182/
        persons.add(Person(position(), "Mechanic", "https://live.staticflickr.com/2405/2179915182_5a0ac98b49_z.jpg"))

        // http://www.flickr.com/photos/nationalmediamuseum/7893552556/
        persons.add(Person(position(), "Yeats", "https://live.staticflickr.com/8035/7893552556_3351c8a168_b.jpg"))

        // http://www.flickr.com/photos/sdasmarchives/5036231225/
        persons.add(Person(position(), "John", "https://live.staticflickr.com/4125/5036231225_549f804980_b.jpg"))

        // http://www.flickr.com/photos/anmm_thecommons/7694202096/
        persons.add(Person(position(), "Trevor the Turtle", "https://live.staticflickr.com/8026/7694202096_cc5e43ef2c_b.jpg"))

        // http://www.flickr.com/photos/usnationalarchives/4726892651/
        persons.add(Person(position(), "Teach", "https://live.staticflickr.com/1335/4726892651_3600607ec0_b.jpg"))
    }

    private fun position(): LatLng {
        return LatLng(random(51.6723432, 51.38494009999999), random(0.148271, -0.3514683))
    }

    private fun random(min: Double, max: Double): Double {
        return random.nextDouble() * (max - min) + min
    }
}
