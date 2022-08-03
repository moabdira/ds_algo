/* Imagine we have a machine with a single core CPU. on this machine we can schedule at most one task to run at a time.
 * We want to write a function that accepts a list of tasks and determines whether or not these tasks are able to be 
 * scheduled.
 * 
 * Schedules are given as a list of tasks where each task is a tuple containing (start_time, duration).
 * 
 * Examples:
 * can be scheduled - [(3, 10), (15, 5)]
 * cannot be scheduled - [(2, 7), (5, 9)]
 * cannot be scheduled - [(3, 14), (15, 5)]
 *
 * duration = end_time - start_time
 * end_time = start_time + duration
 * 
 * constraints:
 * 1. Is this already sorted based on start_time + duration?
 * 
 * Part 2: Now imagine that instead of a single core, we have N cores and can run up to N tasks simultaneously. 
 * Can we update our function to handle this?
 * 
 * Examples:
 * cannot be scheduled - [(1, 9), (1, 9), (1, 9), (1, 1), (3, 7), (5, 5)], N = 4
 * cannot be scheduled - [(1, 9), (1, 9), (1, 9), (1, 6), (5, 5)], N=4 5 tasks running from times 5-7
 */

fun isSchedulableSimultaneously(tasks: Array<Pair<Int, Int>>, n: Int): Boolean {
    if(tasks.size < n+1) return true
    
    // Sort it based on end_time
    val sortedTasks = tasks.sortedBy{ it.first + it.second }
    println("sortedTasks=$sortedTasks")
    
    var ptr1 = 0; var ptr2 = n
    while(ptr2 < sortedTasks.size){
        val (start_time, dur) = sortedTasks[ptr1]
        val (start_time_end, dur_end) = sortedTasks[ptr2]
        
        if(start_time_end <= (start_time + dur))
        	return false // there overlap
        
        ++ptr1
        ++ptr2
    }
    
    return true
    
}

fun isSchedulable(tasks: Array<Pair<Int, Int>>): Boolean {
    if(tasks.isEmpty()) return true
    
    // Sort it based on start_time
    val sortedTasks = tasks.sortedBy({ it.first + it.second })
    println("sortedTasks=$sortedTasks")
    
    for((i, task) in sortedTasks.withIndex()){
        val (start_time, duration) = task
        val end_time = start_time + duration
        
        if(i+1 < sortedTasks.size){
            // compare it
            val(start_time_next, duration_next) = sortedTasks[i+1]
            if(start_time_next <= end_time)
            	return false // there is an overlap
        }
    }
    
    return true
}

fun main() {
   val result1 = isSchedulable(arrayOf(Pair(3, 10), Pair(15, 5)))
   val result2 = isSchedulable(arrayOf(Pair(2, 7), Pair(5, 9)))
   val result3 = isSchedulable(arrayOf(Pair(3, 14), Pair(15, 5)))
   
   println("\nisSchedulable Tests:")
   println("result1=$result1")
   println("result2=$result2")
   println("result3=$result3\n\n")
   
   val result4 = isSchedulableSimultaneously(arrayOf(Pair(1, 9), Pair(1, 9), Pair(1, 9), Pair(1, 1), Pair(3, 7), Pair(5, 5)), 4)
   val result5 = isSchedulableSimultaneously(arrayOf(Pair(1, 9), Pair(1, 9), Pair(1, 9), Pair(1, 6), Pair(5, 5)), 4)
   val result6 = isSchedulableSimultaneously(arrayOf(Pair(1, 9), Pair(1, 9), Pair(1, 6), Pair(8, 5)), 3)
   println("\nisSchedulableSimultaneously Tests:")
   println("result4=$result4")
   println("result5=$result5")
   println("result6=$result6")
}
