package otus.lessons.hw01

package object business {

  class StringEx(val input: String) extends AnyVal {

    def isNullOrEmpty: Boolean =
      if (input == null || input.trim.isEmpty)
        true
      else
        false
  }

  implicit def isNullOrEmpty(input: String): StringEx = new StringEx(input)

}