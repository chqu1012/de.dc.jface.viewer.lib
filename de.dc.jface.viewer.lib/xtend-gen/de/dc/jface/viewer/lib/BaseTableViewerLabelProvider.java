package de.dc.jface.viewer.lib;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

@SuppressWarnings("all")
public abstract class BaseTableViewerLabelProvider<T extends Object> extends LabelProvider implements ITableLabelProvider {
  @Override
  public Image getColumnImage(final Object element, final int columnIndex) {
    return null;
  }
  
  @Override
  public String getColumnText(final Object element, final int columnIndex) {
    final T t = ((T) element);
    return this.getText(t, columnIndex);
  }
  
  public abstract String getText(final T t, final int i);
}
