package org.apache.spark.sql

import scala.reflect._

package object insightedge {

  val GigaSpacesFormat = "org.apache.spark.sql.insightedge"

  def gridOptions(): Map[String, String] = Map()

  implicit class DataFrameReaderWrapper(val reader: DataFrameReader) extends AnyVal {
    def grid = {
      reader.format(GigaSpacesFormat)
    }

    def loadClass[T: ClassTag]: DataFrame = {
      reader.format(GigaSpacesFormat).option("class", classTag[T].runtimeClass.getName).load()
    }
  }

  implicit class DataFrameWriterWrapper(val writer: DataFrameWriter) extends AnyVal {
    def grid(collection:String) = {
      writer.format(GigaSpacesFormat).option("collection", collection)
    }

    def grid = {
      writer.format(GigaSpacesFormat)
    }
  }

}