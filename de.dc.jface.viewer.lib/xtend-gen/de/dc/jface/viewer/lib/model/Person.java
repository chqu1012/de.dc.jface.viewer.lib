package de.dc.jface.viewer.lib.model;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class Person {
  @Accessors
  private String name;
  
  @Accessors
  private String vorname;
  
  @Accessors
  private String handy;
  
  @Pure
  public String getName() {
    return this.name;
  }
  
  public void setName(final String name) {
    this.name = name;
  }
  
  @Pure
  public String getVorname() {
    return this.vorname;
  }
  
  public void setVorname(final String vorname) {
    this.vorname = vorname;
  }
  
  @Pure
  public String getHandy() {
    return this.handy;
  }
  
  public void setHandy(final String handy) {
    this.handy = handy;
  }
}
