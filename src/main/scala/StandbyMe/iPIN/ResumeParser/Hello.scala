package StandbyMe.iPIN.ResumeParser

import StandbyMe.XMLUtil.{XMLSplit, XMLSplitResult}

import scala.xml.{Node, XML}

object Hello extends App {
  val root = XML.loadFile("output.xml")
  val strategy = strategy_factory(root)

  val a = XMLSplit(strategy)((root \\ "_").toList)
  val b = XMLSplitResult(a.init, a.pair__list.map { case (n, nl) => (n, nl.filter(extract_simple_text_from_node(_).lengthCompare(0) > 0)) })

  def pn(n: Node) = extract_simple_text_from_node(n)

  def p(n: (Node, List[Node])) = {
    val title = pn(n._1)
    println(title)
    println("....")
    val node__list = n._2
    node__list.foreach(m => println(pn(m)))
    println("------")
  }

  b.init.foreach(n => println(pn(n)))
  println("//////")
  b.pair__list.foreach(p)
}
