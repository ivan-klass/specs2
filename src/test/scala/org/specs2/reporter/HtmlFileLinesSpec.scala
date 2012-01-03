package org.specs2
package reporter

import specification._
import ExecutedSpecificationData._

class HtmlFileLinesSpec extends Specification { def is =

  "A linked specification must create a new file"                                                                     ! e1^
    "that file must be linked to the parent file"                                                                     ! e2^
  "A see specification must not create a new file"                                                                    ! e3^
                                                                                                                      end

  lazy val spec1: Fragments = "ex1" ! failure ^ "a " ~  ("successfull spec", successfulSubSpec) ^ end
  lazy val spec2: Fragments = "ex1" ! failure ^ "a " ~/ ("successfull spec", successfulSubSpec) ^ end
  lazy val successfulSubSpec = new Specification { def is = "ex1" ! success }

  def e1 = htmlFileLines(spec1).flatten must have size(1)
  def e2 = htmlFileLines(spec2).subForest must have size(1)
  def e3 = htmlFileLines(spec2).flatten must have size(2)

  def printer = new HtmlPrinter { }
  def htmlFileLines(spec: Fragments) = printer.createHtmlLinesFiles(execute(spec))

}