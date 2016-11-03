package de.dc.jface.viewer.lib

import java.util.List
import org.eclipse.jface.viewers.IStructuredContentProvider
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.jface.viewers.TableViewerColumn
import org.eclipse.jface.viewers.ViewerComparator
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Composite
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Text
import org.eclipse.jface.viewers.ArrayContentProvider

abstract class BaseTableViewerComposite<T> extends Composite {
	
	@Accessors TableViewer viewer
	Text searchText
	
	new(Composite parent){
		super(parent, 0)
		setLayout(new GridLayout(1, false));
		if(isVisibleTop){
			new Composite(this, 0)=>[
				layoutData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1)
				createTopComposite
			]
		}
		if(hasFilter){
			searchText = new Text(this, SWT.BORDER)=>[
				layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1)
				message = 'Search ...'
			]
		}
		
		parent.initTableViewer		
		if(isVisibleBottom){
			new Composite(this, 0)=>[
				layoutData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1)
				createBottomComposite
			]
		}
	}
	
	def createTopComposite(Composite topComposite) {
	}
	
	def createBottomComposite(Composite topComposite) {
	}
	
	def initTableViewer(Composite parent){
		viewer = new TableViewer(this, SWT.BORDER.bitwiseOr(SWT.V_SCROLL))
		viewer.contentProvider = getContentProvider
		viewer.labelProvider = getLabelProvider
		viewer.comparator = comparator
		viewer.table.linesVisible = isLinesVisible
		viewer.table.headerVisible = isHeaderVisible
		viewer.table.layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1)
		
		for(i : 0..(columnTitles.size-1)){
			viewer.createColumn(columnTitles.get(i), columnBounds.get(i))
		}
	}
	
	def createColumn(TableViewer viewer, String text, int bounds){
		new TableViewerColumn(viewer, SWT.NONE)=>[
			column.width = bounds
			column.text = text
		]
	}
	
	def abstract LabelProvider getLabelProvider()
	def abstract ViewerComparator getComparator()
	def abstract String[] getColumnTitles()
	def abstract int[] getColumnBounds()
	def isVisibleTop(){false}
	def isVisibleBottom(){false}
	def isLinesVisible(){true}
	def isHeaderVisible(){true}
	def hasFilter(){true}
	def IStructuredContentProvider getContentProvider(){ArrayContentProvider.instance}
	
	def protected List<T> getSelections(){
		if(viewer.selection instanceof IStructuredSelection){
			val ss = viewer.selection as IStructuredSelection
			return ss.toList
		}		
	}
	
	def protected T getFirstSelection(){
		if(viewer.selection instanceof IStructuredSelection){
			val ss = viewer.selection as IStructuredSelection
				return ss.firstElement as T
		}		
	}
}