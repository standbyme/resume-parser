package StandbyMe.iPIN.ResumeParser

import StandbyMe.XMLUtil.XMLSplit

import scala.xml.{Node, XML}

object Hello extends App {
  val root = XML.loadFile("output.xml")
  val strategy = strategy_factory(root)

  val a = XMLSplit(strategy)((root \\ "_").toList)
  //  println(a.pair__list.length)

  def pn(n: Node) = extract_simple_text_from_node(n)

  def p(n: (Node, List[Node])) = {
    val title = pn(n._1)
    println(title)
    val node__list = n._2
    node__list.foreach(m => println(pn(m)))
    println("------")
  }

  a.init.foreach(n => println(pn(n)))
  println("//////")
  a.pair__list.foreach(p)
}
