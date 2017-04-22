package finatra

package object freemarker {
  val resolveFields = getBool("ftl.wrapper.resolveFields", true)
  val resolveMethods = getBool("ftl.wrapper.resolveMethods", true)
  val delegateToDefault = getBool("ftl.wrapper.delegateToDefault", false)

  def getBool(key: String, defaultVal: Boolean): Boolean = if (System.getProperty(key) != null) System.getProperty(key).toBoolean else defaultVal
}
