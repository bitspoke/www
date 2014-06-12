package models


class Article(val oid:Option[String],
              val author:String,
              val title:String,
              val epoch:Long,
              val summary:String,
              val content:String)