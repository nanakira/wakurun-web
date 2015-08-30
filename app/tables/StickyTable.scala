package tables

import models.Sticky
import slick.driver.JdbcProfile

trait StickyTable {
  protected val driver: JdbcProfile
  import driver.api._
  class Stickies(tag: Tag) extends Table[Sticky](tag, "STICKY") {

    def name = column[String]("NAME", O.PrimaryKey)
    def content = column[String]("CONTENT")

    def * = (name, content) <> (Sticky.tupled, Sticky.unapply _)
  }
}