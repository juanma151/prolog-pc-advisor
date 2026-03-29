// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.model

import org.scalatest.funsuite.AnyFunSuite

class CpuSpec extends AnyFunSuite {

	test("displayName should combine label and socket") {
		val cpu = Cpu(
			id = "ryzen7_7700",
			label = "AMD Ryzen 7 7700",
			socket = "am5"
		)

		assert(cpu.displayName == "AMD Ryzen 7 7700 (am5)")
	}

	test("Cpu should preserve its fields") {
		val cpu = Cpu(
			id = "i5_14600k",
			label = "Intel Core i5-14600K",
			socket = "lga1700"
		)

		assert(cpu.id == "i5_14600k")
		assert(cpu.label == "Intel Core i5-14600K")
		assert(cpu.socket == "lga1700")
	}
}
