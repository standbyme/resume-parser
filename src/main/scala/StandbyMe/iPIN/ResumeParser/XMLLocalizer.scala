package StandbyMe.iPIN.ResumeParser

import scala.xml.Node

class XMLLocalizer(val keywords: Set[String]) {

  def extract_simple_text_from_node(node: Node): String = node.text.trim.replace("\n", "").replace(" ", "")

}
