package StandbyMe.iPIN.ResumeParser

import StandbyMe.XMLUtil.XMLSplit

import scala.xml.XML

object Hello extends App {
  val root = XML.loadFile("output.xml")
  val strategy = strategy_factory(root)

  val a = XMLSplit(strategy)((root \\ "_").toList)
  println(a.pair__list.length)
  a.pair__list.foreach(n => println(extract_simple_text_from_node(n._1)))
}
