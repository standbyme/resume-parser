package StandbyMe.iPIN

import scala.xml.Node

package object ResumeParser {
  def extract_simple_text_from_node(node: Node): String = node.text.trim.replace("\n", "").replace(" ", "")

  def get_font_sizes_of_node(node: Node): Array[Int] = {
    val sz = node \\ "sz"
    val szCs = node \\ "szCs"
    val font_size__array = (sz ++ szCs).toArray.map(_.attributes.value.text.toInt)
    font_size__array
  }

  def strategy_factory(root_node: Node): (Node => Boolean) = {
    val key_font_size = get_font_sizes_of_node(root_node).toStream.distinct.sorted.reverse.apply(1)
    println(key_font_size)

    def strategy(node: Node): Boolean = {
      val length_result = extract_simple_text_from_node(node).lengthCompare(1) > 0
      val font_sizes_of_node = get_font_sizes_of_node(node)
      val font_size_result = font_sizes_of_node.forall(_ == key_font_size) && (font_sizes_of_node.length > 0)
      length_result && font_size_result
    }

    strategy
  }
}
