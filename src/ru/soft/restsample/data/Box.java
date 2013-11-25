package ru.soft.restsample.data;

public class Box {
	String ct;
	String title;
	String body;
	String login;
	String name;
	String uid;
	Double lng;
	Double lat;
	String type;
	String imageUrl;
	String _id;
	String boxName;

	public Box(String ct, String title, String body, String login, String name,
			String uid, Double lng, Double lat, String type, String imageUrl,
			String _id, String boxName) {
		super();
		this.ct = ct;
		this.title = title;
		this.body = body;
		this.login = login;
		this.name = name;
		this.uid = uid;
		this.lng = lng;
		this.lat = lat;
		this.type = type;
		this.imageUrl = imageUrl;
		this._id = _id;
		this.boxName = boxName;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getBoxName() {
		return boxName;
	}

	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
}
