import org.json4s._
import org.json4s.jackson.JsonMethods._ 
implicit val formats = org.json4s.DefaultFormats

case class Person(name: String, age: Int)

class user{

    def add(){
        val json="""{"1":{"name":"user1", "age":16}}"""

            print(parse(json).extract[Map[String,Person]])
    }
}
