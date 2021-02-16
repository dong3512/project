package com.dong.pms.domain;

import java.sql.Time;

public class Schedule {
  private int no;
  private String destination ;
  private String airno ;
  private String name;
  private Time dtime ;
  private Time atime ;
  private String pilot;




  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((airno == null) ? 0 : airno.hashCode());
    result = prime * result + ((destination == null) ? 0 : destination.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + no;
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
    Schedule other = (Schedule) obj;
    if (airno == null) {
      if (other.airno != null)
        return false;
    } else if (!airno.equals(other.airno))
      return false;
    if (destination == null) {
      if (other.destination != null)
        return false;
    } else if (!destination.equals(other.destination))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (no != other.no)
      return false;
    return true;
  }


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getDestination() {
    return destination;
  }
  public void setDestination(String destination) {
    this.destination = destination;
  }
  public String getAirno() {
    return airno;
  }
  public void setAirno(String airno) {
    this.airno = airno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Time getDtime() {
    return dtime;
  }
  public void setDtime(Time dtime) {
    this.dtime = dtime;
  }
  public Time getAtime() {
    return atime;
  }
  public void setAtime(Time atime) {
    this.atime = atime;
  }
  public String getPilot() {
    return pilot;
  }
  public void setPilot(String pilot) {
    this.pilot = pilot;
  }


}
