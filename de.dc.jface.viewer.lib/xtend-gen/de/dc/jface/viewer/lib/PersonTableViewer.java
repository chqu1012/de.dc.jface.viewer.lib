package de.dc.jface.viewer.lib;

import de.dc.jface.viewer.lib.BaseTableViewerComposite;
import de.dc.jface.viewer.lib.PersonLabelProvider;
import de.dc.jface.viewer.lib.model.Person;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("all")
public class PersonTableViewer extends BaseTableViewerComposite<Person> {
  public PersonTableViewer(final Composite parent) {
    super(parent);
  }
  
  @Override
  public LabelProvider getLabelProvider() {
    return new PersonLabelProvider();
  }
  
  @Override
  public String[] getColumnTitles() {
    return new String[] { "Name", "Vorname", "Handy" };
  }
  
  @Override
  public int[] getColumnBounds() {
    return new int[] { 150, 150, 150 };
  }
  
  @Override
  public ViewerComparator getComparator() {
    return null;
  }
}
