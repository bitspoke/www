package controllers

import play.api.mvc._
import models._


class ApplicationController extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def contacts = Action {
    Ok(views.html.contacts(List(
    	new Developer(
    		"Paolo Angioletti",
    		"images/paolo.jpg",
    		"Highly passionate and enthusiastic Senior Java / Scala / Web Developer and Tech Leader with 12 years commercial experience in Core Multithreading, Databases, Domain-Driven and Architecture Design but also OO JavaScript / FrontEnd Developer building complex applications for major blue chip clients using the most productive tools and methodologies. End-to-end full software development lifecycle expertise applying Agile Principles, SCRUM and Kanban.",
    		"https://www.linkedin.com/pub/paolo-angioletti/10/609/9b4",
    		"paolo.angioletti@bitspoke.co.uk",
    		"curricula/paolo.doc"
		),
		new Developer(
    		"Dario Busco",
    		"images/dario.jpg",
    		"Software developer, tech leader and software architect in several contexts of business applications, mainly skilled in java related technologies and surrounding platforms. Successful experiences in mentoring and team leading.",
    		"https://www.linkedin.com/pub/dario-busco/4/343/575",
    		"dario.busco@bitspoke.co.uk",
    		"curricula/dario.doc"
		),
		new Developer(
    		"Giuseppe Romano",
    		"images/giuseppe.jpg",
    		"Highly proficient SENIOR JAVA/J2EE and FLEX DEVELOPER with more than 10 years experience building complex applications for major clients in Italy using Java EE 6 and Adobe Flex 3 and 4. Full software life-cycle knowledge using Object Oriented, Continuous Integration and Agile SCRUM methodologies.",
    		"https://www.linkedin.com/pub/giuseppe-romano/22/991/85",
    		"giuseppe.romano@bitspoke.co.uk",
    		"curricula/giuseppe.doc"
		)
	)))
  }

  def projects = Action {
    Ok(views.html.projects())
  }

}