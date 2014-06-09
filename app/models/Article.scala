package models


class Article(val oid:String,
              val series:String,
              val title:Option[String],
              val summary:Option[String],
              val author:String,
              val epoch:Long,
              val content:String)