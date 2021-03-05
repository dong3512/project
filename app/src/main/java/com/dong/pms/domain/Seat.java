package com.dong.pms.domain;

import java.io.Serializable;

public class Seat implements Serializable{
  private static final long serialVersionUTD = 1L;

  private int no ;
  private String mgrade;
  private int sgrade ;
  private String sno ;
  private String etc ;




  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + no;
    result = prime * result + sgrade;
    result = prime * result + ((sno == null) ? 0 : sno.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Seat other = (Seat) obj;
    if (no != other.no)
      return false;
    if (sgrade != other.sgrade)
      return false;
    if (sno == null) {
      if (other.sno != null)
        return false;
    } else if (!sno.equals(other.sno))
      return false;
    return true;
  }


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getMgrade() {
    return mgrade;
  }
  public void setMgrade(String mgrade) {
    this.mgrade = mgrade;
  }
  public int getSgrade() {
    return sgrade;
  }
  public void setSgrade(int sgrade) {
    this.sgrade = sgrade;
  }
  public String getSno() {
    return sno;
  }
  public void setSno(String sno) {
    this.sno = sno;
  }
  public String getEtc() {
    return etc;
  }
  public void setEtc(String etc) {
    this.etc = etc;
  }


}
