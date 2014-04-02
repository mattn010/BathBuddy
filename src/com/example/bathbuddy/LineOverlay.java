package com.example.bathbuddy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

class LineOverlay extends Overlay{
	
	private GeoPoint start;
	private GeoPoint destination;
	private Projection projection;
	
	/**
	 * Constructor for Debug use only
	 * 
	 * Generates a line from 8 West to the SU
	 * 
	 * @param projection	The projection of the Map's View
	 */
	public LineOverlay(Projection projection){
		this.projection = projection;
		start = new GeoPoint(51379971,-2331226);
		destination = new GeoPoint(51379837,-2326741);
	}
	
	public LineOverlay(Projection projection, GeoPoint start, GeoPoint destination){
		this.projection = projection;
		this.start = start;
		this.destination = destination;
	}

	public void draw(Canvas canvas, MapView mapv, boolean shadow){
		super.draw(canvas, mapv, shadow);
		//Configuring the paint brush
		Paint mPaint = new Paint();
		mPaint.setDither(true);
		mPaint.setColor(Color.BLUE);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setShadowLayer(5.0f, 0.0f, 0.0f,Color.BLUE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(4);
		mPaint.setAntiAlias(true);
		
		Path path = new Path();
		
		Point p1 = new Point();
		Point p2 = new Point();
		projection.toPixels(start, p1);
		projection.toPixels(destination, p2);
		
		path.moveTo(p1.x, p1.y);
		path.lineTo(p2.x,p2.y);
		
		canvas.drawPath(path, mPaint);
	}

}
