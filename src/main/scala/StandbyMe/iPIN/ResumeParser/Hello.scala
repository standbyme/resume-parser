package StandbyMe.iPIN.ResumeParser

import StandbyMe.XMLUtil.{XMLSplit, XMLSplitResult}

import scala.xml.{Node, NodeSeq, XML}

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

  def condition_tbl(n: Node): Boolean = {
    (n.label == "tc") && n.descendant.forall(_.label != "tc")
  }

  def condition_any(n: Node): Boolean = {
    (n.label == "r") && n.descendant.forall(_.label != "r")
  }

  def k(n: Node): NodeSeq = {
    val descendant = n \\ "_"
    // if added to here
    if (descendant.exists(_.label == "tbl")) descendant.filter(condition_tbl)
    else descendant.filter(condition_any)
  }

  def f(n: (Node, List[Node])): (Node, List[Node]) = {
    val (keynode, content_of_keynode) = n
    val new_content_of_keynode = content_of_keynode.flatMap(k)
    (keynode, new_content_of_keynode)
  }

  b.init.foreach(n => println(pn(n)))
  println("//////")
  b.pair__list.map(f).foreach(p)
}
