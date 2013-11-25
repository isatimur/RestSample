package ru.soft.restsample.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ru.soft.restsample.MainActivity;
import ru.soft.restsample.R;
import ru.soft.restsample.data.Box;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ServiceWebAPITask extends AsyncTask<String, Integer, String> {
	private ProgressDialog progDialog;
	private Context context;
	private MainActivity activity;
	private static final String debugTag = "ServiceWebAPITask";

	public ServiceWebAPITask(MainActivity activity) {
		super();
		this.activity = activity;
		this.context = this.activity.getApplicationContext();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progDialog = ProgressDialog.show(this.activity, "Search", this.context
				.getResources().getString(R.string.app_name), true, false);
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			Log.d(debugTag, "Background:" + Thread.currentThread().getName());
			String result = ServiceHelper.downloadFromServer(params);
			return result;
		} catch (Exception exception) {
			return new String();
		}
	}

	@Override
	protected void onPostExecute(String result) {
		ArrayList<Box> boxdata = new ArrayList<Box>();

		progDialog.closeOptionsMenu();
		progDialog.cancel();
		progDialog.dismiss();

		if (result.length() == 0) {
			this.activity.alert("Unable to find box data. Try again later.");
			return;
		}

		try {
			JSONArray boxes = new JSONArray(result);
			String ct = null;
			String title = null;
			String body = null;
			String login = null;
			String name = null;
			String uid = null;
			Double lng = null;
			Double lat = null;
			String type = null;
			String imageUrl = null;
			String _id = null;
			String boxName = null;
			for (int i = 0; i < boxes.length(); i++) {
				JSONObject box = boxes.getJSONObject(i);
				_id = box.getString("_id");
				boxName = box.getString("filename");
				try {
					JSONArray metadata = box.getJSONArray("metadata");
					for (int j = 0; j < metadata.length(); j++) {
						JSONObject boxObj = metadata.getJSONObject(j);
						ct = boxObj.getString("ct");
						title = boxObj.getString("t");
						body = boxObj.getString("b");
						login = boxObj.getString("l");
						name = boxObj.getString("n");
						uid = boxObj.getString("ud");
						JSONObject avatar = boxObj.getJSONObject("av");
						imageUrl = avatar.getString("u");
						JSONObject locObj = boxObj.getJSONObject("loc");
						JSONArray coordinates = locObj
								.getJSONArray("coordinates");
						lng = coordinates.getDouble(0);
						lat = coordinates.getDouble(1);
						type = locObj.getString("type");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				boxdata.add(new Box(ct, title, body, login, name, uid, lng,
						lat, type, imageUrl, _id, boxName));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		this.activity.setBoxes(boxdata);

	}
}
