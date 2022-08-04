import java.util.PriorityQueue
import java.util.UUID

data class User(val id: String, val name: String, val age: Int) : Comparable<User> {
  override fun compareTo(other: User) = when {
    age != other.age -> age - other.age
    else -> name.compareTo(other.name)
  }
}

class UserComparator {
  companion object : Comparator<User> {
    override fun compare(a: User, b: User): Int = when {
      a.age != b.age -> a.age - b.age
      else -> a.name.length - b.name.length
    }
  }
}

// Sorting using Comparator and custom Comparator
fun sortingExamples(){
    val users = mutableListOf<User>()
    users.add(User(UUID.randomUUID().toString(), "John Smith", 42))
    users.add(User(UUID.randomUUID().toString(), "Eric Sood", 35))
    users.add(User(UUID.randomUUID().toString(), "Seth Summerseth", 52))
    users.add(User(UUID.randomUUID().toString(), "David Nicholas", 49))
    users.add(User(UUID.randomUUID().toString(), "Ali Elmi", 25))
    
    users.sortWith(UserComparator)
    users.forEach(){ println(it) }
}

fun priorityQueueExample(){
  // val compareByLength: Comparator<User> = compareBy { it.age }
  // val maxHeapUser = PriorityQueue<User>(compareByLength)
  val maxHeapUser = PriorityQueue{ a: User, b: User -> b.age - a.age }
  val minHeapUser = PriorityQueue<User>()
  
  maxHeapUser.add(User(UUID.randomUUID().toString(), "John Smith", 42))
  maxHeapUser.add(User(UUID.randomUUID().toString(), "Eric Sood", 35))
  maxHeapUser.add(User(UUID.randomUUID().toString(), "Seth Summerseth", 52))
  maxHeapUser.add(User(UUID.randomUUID().toString(), "David Nicholas", 49))
  maxHeapUser.add(User(UUID.randomUUID().toString(), "Ali Elmi", 25))
  
  minHeapUser.add(User(UUID.randomUUID().toString(), "John Smith", 42))
  minHeapUser.add(User(UUID.randomUUID().toString(), "Eric Sood", 35))
  minHeapUser.add(User(UUID.randomUUID().toString(), "Seth Summerseth", 52))
  minHeapUser.add(User(UUID.randomUUID().toString(), "David Nicholas", 52))
  minHeapUser.add(User(UUID.randomUUID().toString(), "Ali Elmi", 25))
  
  println("\nMax Heap: ")
  while (!maxHeapUser.isEmpty()) {
    println(maxHeapUser.remove())
  }
  
  println("\nMin Heap: ")
  while (!minHeapUser.isEmpty()) {
    println(minHeapUser.remove())
  }
}

fun main(args: Array<String>) {
  sortingExamples()  
  priorityQueueExample()
  
}
