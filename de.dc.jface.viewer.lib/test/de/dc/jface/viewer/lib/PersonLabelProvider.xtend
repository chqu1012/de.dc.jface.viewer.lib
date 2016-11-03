package de.dc.jface.viewer.lib

import de.dc.jface.viewer.lib.model.Person

class PersonLabelProvider extends BaseTableViewerLabelProvider<Person> {
	
	override getText(Person t, int i) {
		switch i{
			case 0: t.name
			case 1: t.vorname
			case 2: t.handy
			default: ''
		}
	}
	
}