// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.model

import org.scalatest.funsuite.AnyFunSuite

class BuildSelectionSpec extends AnyFunSuite {

	test("BuildSelection should preserve its fields") {
		val selection = BuildSelection(
			cpuId = "ryzen7_7700",
			motherboardId = "b650_tomahawk",
			ramId = "ddr5_32_6000"
		)

		assert(selection.cpuId == "ryzen7_7700")
		assert(selection.motherboardId == "b650_tomahawk")
		assert(selection.ramId == "ddr5_32_6000")
	}
}
