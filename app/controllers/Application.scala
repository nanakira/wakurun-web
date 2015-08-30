package controllers

import models.Sticky
import play.api.Play
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfig
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import play.api.mvc.Controller
import slick.driver.JdbcProfile
import tables.StickyTable

class Application extends Controller with StickyTable with HasDatabaseConfig[JdbcProfile]{
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import driver.api._

  //create an instance of the table
  val Stickies = TableQuery[Stickies] //see a way to architect your app in the computers-database sample

  def index = Action.async {
    db.run(Stickies.result).map(res => Ok(views.html.index(res.toList)))
  }

  val stickyForm = Form(
    mapping(
      "name" -> text(),
      "content" -> text()
    )(Sticky.apply)(Sticky.unapply)
  )

  def insert = Action.async { implicit rs =>
    val sticky = stickyForm.bindFromRequest.get
    db.run(Stickies += sticky).map(_ => Redirect(routes.Application.index))
  }
}