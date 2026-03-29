// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.ui

import scalafx.collections.ObservableBuffer
import scalafx.scene.Node
import scalafx.scene.control.{ComboBox, Label, ListCell, ListView}
import scalafx.scene.layout.VBox

final case class RenderedForm(
										container: VBox,
										comboBoxes: Map[String, ComboBox[UiOption]]
									)

object FormRenderer {

	def render(fields: Seq[UiField]): RenderedForm = {
		val orderedFields = fields.sortBy(_.order)

		val combos = scala.collection.mutable.Map.empty[String, ComboBox[UiOption]]
		val nodes = orderedFields.flatMap(field => renderField(field, combos))

		val container = new VBox {
			spacing = 10
			children = ObservableBuffer.from(nodes)
		}

		RenderedForm(
			container = container,
			comboBoxes = combos.toMap
		)
	}

	private def renderField(
									field: UiField,
									combos: scala.collection.mutable.Map[String, ComboBox[UiOption]]
								): Seq[Node] = {
		val labelNode = new Label(field.label)

		val controlNode: Node =
			field.controlType match {
				case "combo" =>
					val listCellFactory: ListView[UiOption] => ListCell[UiOption] =
						(_: ListView[UiOption]) => createUiOptionListCell()

					val combo = new ComboBox[UiOption] {
						items = ObservableBuffer.from(field.options)
						promptText = s"Select ${field.label}"
						cellFactory = listCellFactory
						buttonCell = createUiOptionListCell()
					}

					combos += (field.id -> combo)
					combo

				case other =>
					new Label(s"Unsupported control type: $other")
			}

		Seq(labelNode, controlNode)
	}

	private def createUiOptionListCell(): ListCell[UiOption] =
		new ListCell[UiOption] {
			item.onChange { (_, _, newValue) =>
				text = Option(newValue).map(_.label).getOrElse("")
			}
		}
}
