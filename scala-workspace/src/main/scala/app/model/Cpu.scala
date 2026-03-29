// vim: filetype=scala: tabstop=3: shiftwidth=3: noexpandtab
package app.model

final case class Cpu(
							id: String,
							label: String, 
							socket: String
						) {
	def displayName: String =
		s"$label ($socket)"
}
