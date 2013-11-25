package ru.soft.restsample;

import java.util.ArrayList;

import ru.soft.restsample.adapter.DataAdaper;
import ru.soft.restsample.data.Box;
import ru.soft.restsample.service.ServiceImageTask;
import ru.soft.restsample.service.ServiceWebAPITask;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button boxButton;
	private ArrayList<Box> boxes;
	private ServiceImageTask imgFetcher;
	private ListView boxesList;
	private LayoutInflater layoutInflator;
	private Spinner metroSpinner;
	private InputMethodManager inMgr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.inMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		boxButton = (Button) findViewById(R.id.row_button);
		this.boxesList = (ListView) findViewById(R.id.listView1);
		this.imgFetcher = new ServiceImageTask(this);
		this.layoutInflator = LayoutInflater.from(this);
		boxButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ServiceWebAPITask apiTask = new ServiceWebAPITask(
						MainActivity.this);
				try {
					apiTask.execute("");
				} catch (Exception e) {
					apiTask.cancel(true);
					alert(getResources().getString(R.string.no_boxes));
				}
			}
		});

		// Restore any already fetched data on orientation change.
		final Object[] data = (Object[]) getLastNonConfigurationInstance();
		if (data != null) {
			this.boxes = (ArrayList<Box>) data[0];
			this.imgFetcher = (ServiceImageTask) data[1];
			boxesList.setAdapter(new DataAdaper(this, this.imgFetcher,
					this.layoutInflator, this.boxes));
		}
	}

	public void alert(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * Save any fetched track data for orientation changes.
	 */
	@Override
	public Object onRetainNonConfigurationInstance() {
		Object[] myStuff = new Object[2];
		myStuff[0] = this.boxes;
		myStuff[1] = this.imgFetcher;
		return myStuff;
	}

	public static class ViewHolder {
		public TextView login;
		public TextView title;
		public ImageView avatar;
		public Button row_button;
		public Box box;

	}

	public void setBoxes(ArrayList<Box> boxes) {
		this.boxes = boxes;
		this.boxesList.setAdapter(new DataAdaper(this, this.imgFetcher,
				this.layoutInflator, this.boxes));
	}

}
