package com.shrikanthravi.customnavigationdrawer;

import android.support.v4.app.FragmentManager;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener,OnMapReadyCallback,View.OnClickListener {
    GoogleMap myMap;
    Spinner sp1;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    JSONArray destination = null;
    ArrayList<HashMap<String, String>> destlist;
    List<String> alldest = new ArrayList<String>();
    private static String url_search_state = "http://slumberjer.com/visitmalaysia/search_destination.php";
    private static String url_search_pid = "http://slumberjer.com/visitmalaysia/search_pid.php";
    TextView tv2,tv3,tv4,tv5;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    ScrollView sv;
    String st;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, null, false);

        Button b = (Button) view.findViewById(R.id.button1);
        sv = (ScrollView) getActivity().findViewById(R.id.ScrollView1);
        b.setOnClickListener(this);
        if (isInternetOn()) {
            SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } else {
            getActivity().finish();
        }
        return view;
    }

    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        final View v = rootView;

        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner1);

        List<String> categories = new ArrayList<String>();
        categories.add("Perlis");
        categories.add("Kedah");
        categories.add("Perak");
        categories.add("Pulau Pinang");
        categories.add("Terengganu");
        categories.add("Selangor");
        categories.add("Melaka");
        categories.add("Negeri Sembilan");
        categories.add("Pahang");
        categories.add("Johor");
        categories.add("Sarawak");
        categories.add("Sabah");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                 st = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        myMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
        myMap.setOnInfoWindowClickListener(this);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(4.5259963, 108.1950059))
                .zoom(5).build();
        myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                if (!isInternetOn()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Check your network", Toast.LENGTH_SHORT).show();
                    return;
                }

//                Object str = sp1.getSelectedItem();
//                String st = str.toString();

                if (st.equals("Perlis")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(6.5412846, 100.2304697))
                            .zoom(10).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Kedah")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(6.1305489, 100.4505743))
                            .zoom(9).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Perak")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(4.685329, 100.973284))
                            .zoom(8).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Pulau Pinang")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(5.354624, 100.350461))
                            .zoom(11).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Kelantan")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(5.347596, 102.029206))
                            .zoom(9).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Terengganu")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(5.4439688, 102.6985634))
                            .zoom(8).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Selangor")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(3.4326749, 101.1756293))
                            .zoom(9).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Negeri Sembilan")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(2.681943, 102.165219))
                            .zoom(10).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Melaka")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(2.2519937, 102.2255829))
                            .zoom(10).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Pahang")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(3.832428, 102.422700))
                            .zoom(9).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Johor")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(2.030206, 103.413278))
                            .zoom(9).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Sarawak")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(2.432596, 113.394921))
                            .zoom(7).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                if (st.equals("Sabah")) {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(5.390814, 116.910262))
                            .zoom(8).build();
                    myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                myMap.clear();
                new GetLocAll().execute();
        }
    }

    class GetLocAll extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Searching location. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... arg) {

            try {
                int success;
                Object str = st;
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("state", str.toString()));

                JSONObject json = jsonParser.makeHttpRequest(url_search_state, "GET", params);
                success = json.getInt("success");
                if (success == 1) {
                    JSONArray destObj = json.getJSONArray("destination");
                    for (int i = 0; i < destObj.length(); i++) {
                        JSONObject c = destObj.getJSONObject(i);
                        final String pid = c.getString("pid");
                        final String lac = c.getString("loc_name");
                        final String lat = c.getString("latitude");
                        final String lon = c.getString("longitude");
                        final String desc = c.getString("description");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Marker newMarker = myMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                        .snippet(pid));
                                newMarker.setTitle(":" + lac);
                                newMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                // newMarker.getId();
                                // newMarker.setSnippet(pid);
                            }
                        });
                    }

                }
            } catch (Exception e) {
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }

    }

    class GetLocSingle extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Searching location. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... arg) {

            try {
                int success;
                String pid = arg[0];
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("pid", pid));

                JSONObject json = jsonParser.makeHttpRequest(url_search_pid, "GET", params);
                success = json.getInt("success");
                if (success == 1) {
                    JSONArray destObj = json.getJSONArray("destination");
                    JSONObject c = destObj.getJSONObject(0);
                    final String loc = c.getString("location");
                    final String desc = c.getString("description");
                    final String phone = c.getString("phone");
                    final String url = c.getString("url");
                    final String ad = c.getString("address");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv2.setText(desc);
                            tv3.setText(phone);
                            tv4.setText(url);
                            tv5.setText(ad);
                            Toast.makeText(getActivity().getBaseContext(), loc, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            } catch (Exception e) {
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter() {
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {
            TextView tvPid = ((TextView) myContentsView.findViewById(R.id.pid));
            tvPid.setText(marker.getSnippet());
            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) getActivity().getSystemService(getActivity().getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED
                || connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING
                || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING
                || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            // if connected with internet

            // Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED
                || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            Toast.makeText(getActivity(), " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume(); // Always call the superclass method first
        if (!isInternetOn()) {
            Toast.makeText(getActivity().getApplicationContext(), "Check your Internet", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }

    }

    public void onInfoWindowClick(Marker arg0) {
        // TODO Auto-generated method stub
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popupwindow, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Button btnDismiss = (Button) popupView.findViewById(R.id.button1);
        TextView tv1 = (TextView)popupView.findViewById(R.id.textView1);
        tv2 = (TextView)popupView.findViewById(R.id.textView2);
        tv3 = (TextView)popupView.findViewById(R.id.textView3);
        tv4 = (TextView)popupView.findViewById(R.id.textView4);
        tv5 = (TextView)popupView.findViewById(R.id.textView5);
        tv1.setText(arg0.getSnippet() + arg0.getTitle());
        new GetLocSingle().execute(tv1.getText().toString());
        btnDismiss.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 100, 100);

    }
}
