package StandbyMe.iPIN.ResumeParser

import scala.xml.XML

object Hello extends App {
  val root = XML.loadFile("output.xml")
  val node__list = ((root \ "body").head \\ "_").toList
  val a = root \\ "sz"
  val b = root \\ "szCs"
  val c = (a ++ b).toArray
  val d = c.map(_.attributes.value.text.toInt).view.distinct.sorted.apply(2)
  println(d)
  //  b.foreach(println)
  //  val node_text_zip__list = node__list.map(n=>(n,n.text))
}
