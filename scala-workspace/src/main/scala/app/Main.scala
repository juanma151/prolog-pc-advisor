// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app

import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.VBox

object Main extends JFXApp3 {
	override def start(): Unit = {
		stage = new JFXApp3.PrimaryStage {
			title = "Prolog PC Advisor"

			scene = new Scene(480, 240) {
				root = new VBox {
					spacing = 12
					padding = Insets(20)
					children = Seq(
						new Label("Prolog PC Advisor"),
						new Label("US-03: ScalaFX con Scala 3")
					)
				}
			}
		}
	}
}
