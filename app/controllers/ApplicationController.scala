package controllers

import play.api.mvc._


class ApplicationController extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def contacts = Action {
    Ok(views.html.contacts())
  }

}