package rmutsv.alisa.yusuf.mynavigater;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String idString, nameString, dateString, distanceString, latString, lngString;
    private TextView nameTextView, dateTextView, distanceTextView;
    private String[] latStrings, lngStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Get Value From Intent
        getValueFromIntent();

        //Show Text
        showText();

        //Create Array
        createArray();


        // Create Maps Fragment
        createMapsFragment();

    }   // Main Method

    private void createArray() {

        latStrings = latString.split(",");
        lngStrings = lngString.split(",");

        Log.d("23JulyV4", "lat.length ==> " + latStrings.length);
        Log.d("23JulyV4", "lng.length ==> " + lngStrings.length);


    }

    private void showText() {
        nameTextView = (TextView) findViewById(R.id.txtShowName);
        dateTextView = (TextView) findViewById(R.id.textDate);
        distanceTextView = (TextView) findViewById(R.id.txtDistance);

        nameTextView.setText(nameString);
        dateTextView.setText(dateString);
        distanceTextView.setText(String.format("%.2f", Double.parseDouble(distanceString)) + " m.");

    }

    private void getValueFromIntent() {
        idString = getIntent().getStringExtra("id");
        nameString = getIntent().getStringExtra("Name");
        dateString = getIntent().getStringExtra("Date");
        distanceString = getIntent().getStringExtra("Distance");
        latString = getIntent().getStringExtra("Lat");
        lngString = getIntent().getStringExtra("Lng");
    }

    private void createMapsFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }   // onMapReady

}   // Main Class
