package de.dc.jface.viewer.lib

import de.dc.jface.viewer.lib.model.Person
import org.eclipse.swt.widgets.Composite

class PersonTableViewer extends BaseTableViewerComposite<Person> {
	
	new(Composite parent) {
		super(parent)
	}
	
	override getLabelProvider() {new PersonLabelProvider}
	override getColumnTitles() {#['Name', 'Vorname', 'Handy']}
	override getColumnBounds(){#[150,150,150]}
	
	override getComparator() {
//		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}	
	
	
}