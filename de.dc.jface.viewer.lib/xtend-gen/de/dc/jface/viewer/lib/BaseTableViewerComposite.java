package de.dc.jface.viewer.lib;

import java.util.List;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public abstract class BaseTableViewerComposite<T extends Object> extends Composite {
  @Accessors
  private TableViewer viewer;
  
  private Text searchText;
  
  public BaseTableViewerComposite(final Composite parent) {
    super(parent, 0);
    GridLayout _gridLayout = new GridLayout(1, false);
    this.setLayout(_gridLayout);
    boolean _isVisibleTop = this.isVisibleTop();
    if (_isVisibleTop) {
      Composite _composite = new Composite(this, 0);
      final Procedure1<Composite> _function = (Composite it) -> {
        GridData _gridData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
        it.setLayoutData(_gridData);
        this.createTopComposite(it);
      };
      ObjectExtensions.<Composite>operator_doubleArrow(_composite, _function);
    }
    boolean _hasFilter = this.hasFilter();
    if (_hasFilter) {
      Text _text = new Text(this, SWT.BORDER);
      final Procedure1<Text> _function_1 = (Text it) -> {
        GridData _gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        it.setLayoutData(_gridData);
        it.setMessage("Search ...");
      };
      Text _doubleArrow = ObjectExtensions.<Text>operator_doubleArrow(_text, _function_1);
      this.searchText = _doubleArrow;
    }
    this.initTableViewer(parent);
    boolean _isVisibleBottom = this.isVisibleBottom();
    if (_isVisibleBottom) {
      Composite _composite_1 = new Composite(this, 0);
      final Procedure1<Composite> _function_2 = (Composite it) -> {
        GridData _gridData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
        it.setLayoutData(_gridData);
        this.createBottomComposite(it);
      };
      ObjectExtensions.<Composite>operator_doubleArrow(_composite_1, _function_2);
    }
  }
  
  public Object createTopComposite(final Composite topComposite) {
    return null;
  }
  
  public Object createBottomComposite(final Composite topComposite) {
    return null;
  }
  
  public void initTableViewer(final Composite parent) {
    TableViewer _tableViewer = new TableViewer(this, (SWT.BORDER | SWT.V_SCROLL));
    this.viewer = _tableViewer;
    IStructuredContentProvider _contentProvider = this.getContentProvider();
    this.viewer.setContentProvider(_contentProvider);
    LabelProvider _labelProvider = this.getLabelProvider();
    this.viewer.setLabelProvider(_labelProvider);
    ViewerComparator _comparator = this.getComparator();
    this.viewer.setComparator(_comparator);
    Table _table = this.viewer.getTable();
    boolean _isLinesVisible = this.isLinesVisible();
    _table.setLinesVisible(_isLinesVisible);
    Table _table_1 = this.viewer.getTable();
    boolean _isHeaderVisible = this.isHeaderVisible();
    _table_1.setHeaderVisible(_isHeaderVisible);
    Table _table_2 = this.viewer.getTable();
    GridData _gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
    _table_2.setLayoutData(_gridData);
    String[] _columnTitles = this.getColumnTitles();
    int _size = ((List<String>)Conversions.doWrapArray(_columnTitles)).size();
    int _minus = (_size - 1);
    IntegerRange _upTo = new IntegerRange(0, _minus);
    for (final Integer i : _upTo) {
      String[] _columnTitles_1 = this.getColumnTitles();
      String _get = _columnTitles_1[(i).intValue()];
      int[] _columnBounds = this.getColumnBounds();
      int _get_1 = _columnBounds[(i).intValue()];
      this.createColumn(this.viewer, _get, _get_1);
    }
  }
  
  public TableViewerColumn createColumn(final TableViewer viewer, final String text, final int bounds) {
    TableViewerColumn _tableViewerColumn = new TableViewerColumn(viewer, SWT.NONE);
    final Procedure1<TableViewerColumn> _function = (TableViewerColumn it) -> {
      TableColumn _column = it.getColumn();
      _column.setWidth(bounds);
      TableColumn _column_1 = it.getColumn();
      _column_1.setText(text);
    };
    return ObjectExtensions.<TableViewerColumn>operator_doubleArrow(_tableViewerColumn, _function);
  }
  
  public abstract LabelProvider getLabelProvider();
  
  public abstract ViewerComparator getComparator();
  
  public abstract String[] getColumnTitles();
  
  public abstract int[] getColumnBounds();
  
  public boolean isVisibleTop() {
    return false;
  }
  
  public boolean isVisibleBottom() {
    return false;
  }
  
  public boolean isLinesVisible() {
    return true;
  }
  
  public boolean isHeaderVisible() {
    return true;
  }
  
  public boolean hasFilter() {
    return true;
  }
  
  public IStructuredContentProvider getContentProvider() {
    return ArrayContentProvider.getInstance();
  }
  
  protected List<T> getSelections() {
    ISelection _selection = this.viewer.getSelection();
    if ((_selection instanceof IStructuredSelection)) {
      ISelection _selection_1 = this.viewer.getSelection();
      final IStructuredSelection ss = ((IStructuredSelection) _selection_1);
      return ss.toList();
    }
    return null;
  }
  
  protected T getFirstSelection() {
    ISelection _selection = this.viewer.getSelection();
    if ((_selection instanceof IStructuredSelection)) {
      ISelection _selection_1 = this.viewer.getSelection();
      final IStructuredSelection ss = ((IStructuredSelection) _selection_1);
      Object _firstElement = ss.getFirstElement();
      return ((T) _firstElement);
    }
    return null;
  }
  
  @Pure
  public TableViewer getViewer() {
    return this.viewer;
  }
  
  public void setViewer(final TableViewer viewer) {
    this.viewer = viewer;
  }
}
