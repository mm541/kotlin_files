fun main(args: Array<String>) {
  //<<##############-Transformations:-map-########################>  
    var set = mutableSetOf(1,2,3,4)
    val list = set.map{if(it==3) it*100 else it*1000} 
       println(list)
    
  //  <--------map--------------->
  val numberMap = mapOf("key 1" to 1,"key 2" to 2,"key 3" to 3)
  println(numberMap.mapKeys{it.key.uppercase()})
  println(numberMap.map{
      it.key.uppercase()
      10*it.value
  })

   //<<##############-Transformations:-zipping-##############################>  
   val colors = listOf("red","brown","grey")
    val animals = listOf("fox","bear","wolf")
    
    println(colors.zip(animals))
    println(colors.zip(animals){color,animal -> "The ${animal.replaceFirstChar{it.uppercase()}} is $color"})
    //unzip
    val numbers = listOf(1 to "one",2 to "two",3 to "three")
    println(numbers.unzip())

    //<<##############-Transformations:-Association-#########################> 
    val numbers = listOf("one","two","three","four")
    println(numbers.associateWith{it.length})
    println(numbers.associateBy{it.first().uppercase()})
    println(numbers.associateBy(keySelector={it.first().uppercase()},valueTransform={it.length}))
     
}