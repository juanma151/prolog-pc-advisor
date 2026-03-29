// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app

import app.prolog.JplPrologGateway
import app.ui.FormRenderer
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.VBox

object Main extends JFXApp3 {
	override def start(): Unit = {
		val gateway = new JplPrologGateway()
		val fields = gateway.loadUiFields()

		stage = new JFXApp3.PrimaryStage {
			title = "Prolog PC Advisor"

			scene = new Scene(480, 320) {
				root = new VBox {
					padding = Insets(20)
					children = Seq(
						FormRenderer.render(fields)
					)
				}
			}
		}
	}
}
