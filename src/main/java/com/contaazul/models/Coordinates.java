package com.contaazul.models;


public class Coordinates {

  private Integer x;
  private Integer y;
  private String orientation;

	/**
	* Default empty Coordinates constructor
	*/
	public Coordinates() {
	}

	/**
	* Default Coordinates constructor
	*/
	public Coordinates(Integer x, Integer y, String orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	/**
	* Returns value of x
	* @return
	*/
	public Integer getX() {
		return x;
	}

	/**
	* Sets new value of x
	* @param
	*/
	public void setX(Integer x) {
		this.x = x;
	}

	/**
	* Returns value of y
	* @return
	*/
	public Integer getY() {
		return y;
	}

	/**
	* Sets new value of y
	* @param
	*/
	public void setY(Integer y) {
		this.y = y;
	}

	/**
	* Returns value of orientation
	* @return
	*/
	public String getOrientation() {
		return orientation;
	}

	/**
	* Sets new value of orientation
	* @param
	*/
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	/**
	* Returns value of s
	* @return
	*/
	public Coordinate getS() {
		return s;
	}

	/**
	* Sets new value of s
	* @param
	*/
	public void setS(Coordinate s) {
		this.s = s;
	}

	/**
	* Returns value of s
	* @return
	*/
	public Coordinate getS() {
		return s;
	}

	/**
	* Sets new value of s
	* @param
	*/
	public void setS(Coordinate s) {
		this.s = s;
	}
}
