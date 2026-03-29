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

	test("UiField should preserve its options") {
		val field = UiField(
			id = "cpu",
			controlType = "combo",
			label = "CPU",
			order = 1,
			options = Seq(
				UiOption("ryzen7_7700", "AMD Ryzen 7 7700"),
				UiOption("i5_14600k", "Intel Core i5-14600K")
			)
		)

		assert(field.options.length == 2)
		assert(field.options.head.id == "ryzen7_7700")
		assert(field.options.head.label == "AMD Ryzen 7 7700")
	}
}
