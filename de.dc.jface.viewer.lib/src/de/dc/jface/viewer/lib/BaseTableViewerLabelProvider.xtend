package de.dc.jface.viewer.lib

import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.jface.viewers.ITableLabelProvider

abstract class BaseTableViewerLabelProvider<T> extends LabelProvider implements ITableLabelProvider {
	
	override getColumnImage(Object element, int columnIndex) {
	}
	
	override getColumnText(Object element, int columnIndex) {
		val t = element as T
		return getText(t, columnIndex)
	}
	
	def abstract String getText(T t, int i)
}