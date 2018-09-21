package com.slumberjer.tourism;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.ViewGroup.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInfoWindowClickListener {
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isInternetOn()) {
			setContentView(R.layout.activity_main);
			sv = (ScrollView) findViewById(R.id.ScrollView1);
			myMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			myMap.setMyLocationEnabled(true);
			sp1 = (Spinner) findViewById(R.id.spinner1);
			myMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
			myMap.setOnInfoWindowClickListener(this);
			CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(4.5259963, 108.1950059))
					.zoom(5).build();
			myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		} else {
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.item1) {
			myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			return true;
		}
		if (id == R.id.item2) {
			myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			return true;
		}
		if (id == R.id.item3) {
			myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			return true;
		}
		if (id == R.id.item4) {
			myMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void retLoc(View v) {
		if (!isInternetOn()) {
			Toast.makeText(getApplicationContext(), "Check your network", Toast.LENGTH_SHORT).show();
			return;
		}
		Object str = sp1.getSelectedItem();
		String st = str.toString();
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

	class GetLocAll extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Searching location. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... arg) {

			try {
				int success;
				Object str = sp1.getSelectedItem();
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
						runOnUiThread(new Runnable() {
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
			pDialog = new ProgressDialog(MainActivity.this);
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
						runOnUiThread(new Runnable() {
							@Override 
							public void run() {
								tv2.setText(desc);
								tv3.setText(phone);
								tv4.setText(url);
								tv5.setText(ad);
								Toast.makeText(getBaseContext(), loc, Toast.LENGTH_SHORT).show();
								
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

	class MyInfoWindowAdapter implements InfoWindowAdapter {

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
		ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

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

			Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
			return false;
		}
		return false;
	}

	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first
		if (!isInternetOn()) {
			Toast.makeText(getApplicationContext(), "Check your Internet", Toast.LENGTH_SHORT).show();
			finish();
		}

	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		View popupView = layoutInflater.inflate(R.layout.popupwindow, null);
		final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
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

		popupWindow.showAtLocation(sp1, Gravity.CENTER, 100, 100);

	}

}
