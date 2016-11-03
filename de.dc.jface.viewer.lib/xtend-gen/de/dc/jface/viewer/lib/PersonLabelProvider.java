package de.dc.jface.viewer.lib;

import de.dc.jface.viewer.lib.BaseTableViewerLabelProvider;
import de.dc.jface.viewer.lib.model.Person;

@SuppressWarnings("all")
public class PersonLabelProvider extends BaseTableViewerLabelProvider<Person> {
  @Override
  public String getText(final Person t, final int i) {
    String _switchResult = null;
    switch (i) {
      case 0:
        _switchResult = t.getName();
        break;
      case 1:
        _switchResult = t.getVorname();
        break;
      case 2:
        _switchResult = t.getHandy();
        break;
      default:
        _switchResult = "";
        break;
    }
    return _switchResult;
  }
}
