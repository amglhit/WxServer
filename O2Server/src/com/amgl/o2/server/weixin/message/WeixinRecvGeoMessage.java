package com.amgl.o2.server.weixin.message;

public class WeixinRecvGeoMessage extends WeixinRecvMessage {
	// Location_x
	private double latitude;
	// Location_y
	private double longitude;
	private int scale;
	private String label;

	public WeixinRecvGeoMessage(WeixinRecvMessage msg, double latitude,
			double longitude, int scale, String label) {
		super(msg);
		this.latitude = latitude;
		this.longitude = longitude;
		this.scale = scale;
		this.label = label;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
