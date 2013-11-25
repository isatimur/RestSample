package ru.soft.restsample.adapter;

import java.util.ArrayList;

import ru.soft.restsample.MainActivity;
import ru.soft.restsample.MainActivity.ViewHolder;
import ru.soft.restsample.R;
import ru.soft.restsample.data.Box;
import ru.soft.restsample.service.ServiceImageTask;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DataAdaper extends BaseAdapter implements OnClickListener {
	MainActivity activity;
	ServiceImageTask imgFetcher;
	LayoutInflater layoutInflater;
	ArrayList<Box> boxes;

	public DataAdaper(MainActivity mainActivity, ServiceImageTask imgFetcher,
			LayoutInflater layoutInflator, ArrayList<Box> boxes) {
		this.activity = mainActivity;
		this.imgFetcher = imgFetcher;
		this.layoutInflater = layoutInflator;
		this.boxes = boxes;
	}

	@Override
	public int getCount() {
		return this.boxes.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.row, parent, false);
			holder = new ViewHolder();
			holder.login = (TextView) convertView.findViewById(R.id.login);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
			holder.row_button = (Button) convertView
					.findViewById(R.id.row_button);
			holder.row_button.setTag(holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}
		convertView.setOnClickListener(this);

		Box box = boxes.get(pos);
		holder.box = box;
		holder.login.setText(box.getLogin());
		holder.title.setText(box.getTitle());
		holder.row_button.setOnClickListener(this);
		if (box.getImageUrl() != null) {
			holder.avatar.setTag(box.getImageUrl());
			Drawable dr = imgFetcher.loadImage(this, holder.avatar);
			if (dr != null) {
				holder.avatar.setImageDrawable(dr);
			}
		} else {
			holder.avatar.setImageResource(R.drawable.filler_icon);
		}

		return convertView;
	}

	@Override
	public void onClick(View v) {
		ViewHolder holder = (ViewHolder) v.getTag();
		if (v instanceof Button) {

			Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse(holder.box.getBoxName()));
			this.activity.startActivity(intent);

		} else if (v instanceof View) {
			Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse(holder.box.getBoxName()));
			this.activity.startActivity(intent);
		}
		Log.d("DEBUG", "OnClick pressed.");

	}

}
