// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.ui

import scalafx.collections.ObservableBuffer
import scalafx.scene.Node
import scalafx.scene.control.{ComboBox, Label}
import scalafx.scene.layout.VBox

object FormRenderer {

	def render(fields: Seq[UiField]): VBox = {
		val orderedFields = fields.sortBy(_.order)

		new VBox {
			spacing = 10

			children = ObservableBuffer.from(
				orderedFields.flatMap(renderField)
			)
		}
	}

	private def renderField(field: UiField): Seq[Node] = {
		val labelNode = new Label(field.label)

		val controlNode: Node =
			field.controlType match {
				case "combo" =>
					new ComboBox[String] {
						items = ObservableBuffer.from(field.options.map(_.label))
						promptText = s"Select ${field.label}"
					}

				case other =>
					new Label(s"Unsupported control type: $other")
			}

		Seq(labelNode, controlNode)
	}
}
