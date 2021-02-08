package com.linshu.billionaire.entity;


import java.util.List;

public class NextBallEntity {

  private int id;
  private int numId;
  private int red1;
  private int red2;
  private int red3;
  private int red4;
  private int red5;
  private int red6;
  private int redSum;
  private List<Integer> onesPlace;

  private List<Integer> normalRecommend;


  public NextBallEntity() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNumId() {
    return numId;
  }

  public void setNumId(int numId) {
    this.numId = numId;
  }

  /**
   * 红色球总和
   */
  public int getRedSum() {
    return this.red1 + this.red2 + this.red3 + this.red4 + this.red5 + this.red6;
  }

  /**
   * 个位数集合
   */
  public List<Integer> getOnesPlace() {
    return onesPlace;
  }

  public void setOnesPlace(List<Integer> onesPlace) {
    this.onesPlace = onesPlace;
  }

  /**
   * 普通推荐号
   */
  public List<Integer> getNormalRecommend() {
    return normalRecommend;
  }

  public void setNormalRecommend(List<Integer> normalRecommend) {
    this.normalRecommend = normalRecommend;
  }

  public int getRed1() {
    return red1;
  }

  public void setRed1(int red1) {
    this.red1 = red1;
  }

  public int getRed2() {
    return red2;
  }

  public void setRed2(int red2) {
    this.red2 = red2;
  }

  public int getRed3() {
    return red3;
  }

  public void setRed3(int red3) {
    this.red3 = red3;
  }

  public int getRed4() {
    return red4;
  }

  public void setRed4(int red4) {
    this.red4 = red4;
  }

  public int getRed5() {
    return red5;
  }

  public void setRed5(int red5) {
    this.red5 = red5;
  }

  public int getRed6() {
    return red6;
  }

  public void setRed6(int red6) {
    this.red6 = red6;
  }

  public String toString() {
    return "NextBallEntity(id=" + this.getId() + ", numId=" + this.getNumId() + ", red1=" + this.getRed1() + ", red2=" + this.getRed2() + ", red3=" + this.getRed3() + ", red4=" + this.getRed4() + ", red5=" + this.getRed5() + ", red6=" + this.getRed6() + ", redSum=" + this.getRedSum() + ", onesPlace=" + this.getOnesPlace() + ", normalRecommend=" + this.getNormalRecommend() + ")";
  }
}
