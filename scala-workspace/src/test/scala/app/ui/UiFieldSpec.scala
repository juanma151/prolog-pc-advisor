// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.ui

import org.scalatest.funsuite.AnyFunSuite

class UiFieldSpec extends AnyFunSuite {

	test("isCombo should be true when controlType is combo") {
		val field = UiField(
			id = "cpu",
			controlType = "combo",
			label = "CPU",
			order = 1
		)

		assert(field.isCombo)
	}

	test("isCombo should be false when controlType is not combo") {
		val field = UiField(
			id = "result",
			controlType = "textarea",
			label = "Result",
			order = 99
		)

		assert(!field.isCombo)
	}

	test("UiField should preserve its data") {
		val field = UiField(
			id = "ram",
			controlType = "combo",
			label = "RAM",
			order = 3
		)

		assert(field.id == "ram")
		assert(field.controlType == "combo")
		assert(field.label == "RAM")
		assert(field.order == 3)
	}
}
