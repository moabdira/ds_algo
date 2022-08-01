data class Node(val value: Int, var left: Node? = null, var right: Node? = null, var next: Node? = null)

fun preOrderTraversal(node: Node?){
    if(node == null) return
        
    print("${node.value} ")
    preOrderTraversal(node.left)
    preOrderTraversal(node.right)
}

fun inOrderTraversal(node: Node?){
    if(node == null) return
        
    preOrderTraversal(node.left)    
    print("${node.value} ")
    preOrderTraversal(node.right)
}

fun postOrderTraversal(node: Node?){
    if(node == null) return
        
    preOrderTraversal(node.left)    
    preOrderTraversal(node.right)
    print("${node.value} ")
}

fun allDFSPaths(node: Node?, path: MutableList<Int>, result: MutableList<List<Int>>) {
  if(node == null) return
  
  path.add(node.value) // add the item first
  
  // check if it is a leaf node
  if(node.left == null && node.right == null){
    result.addAll(listOf(path.toList()))
    path.removeLast() // backtrack
    return
  }
  
  allDFSPaths(node.left, path, result)
  allDFSPaths(node.right, path, result)
  
  // backtrack here as well since we traversed its children
  path.removeLast()
}
fun getAllPaths(node: Node?): List<List<Int>>{
  if(node == null) return emptyList()
  
  val result = mutableListOf<List<Int>>()
  allDFSPaths(node, mutableListOf<Int>(), result)
  
  return result
}

/* Get sum of all paths
*/
private fun getSumAllPaths(node: Node?, path: MutableList<Int>, result: MutableList<Int>){
  if(node == null) return
  
  path.add(node.value)
  
  if(node.left == null && node.right == null){
    // get the sume of the path
    result.add(path.sum())
    path.removeLast()
    return
  }
  
  getSumAllPaths(node.left, path, result)
  getSumAllPaths(node.right, path, result)
  
  // remove the node after traversing its left and right subtrees
  path.removeLast()
}
fun getSumAllPaths(node: Node?): List<Int>{
  if(node == null) return emptyList()

  val result = mutableListOf<Int>()
  getSumAllPaths(node, mutableListOf<Int>(), result)
  
  return result
}

// get maximum sum path
var maxSum = 0
fun getMaxSumAllPaths(node: Node?, path: MutableList<Int>){
  if(node == null) return
  
  path.add(node.value)
  
  if(node.left == null && node.right == null){
    val sum = path.sum()
    if(sum > maxSum)
      maxSum = sum
    path.removeLast()
    return
  }
  
  getMaxSumAllPaths(node.left, path)
  getMaxSumAllPaths(node.right, path)
  
  path.removeLast()
}
fun getMaxSumAllPaths(node: Node?): Int {
  if(node == null) return -1
  
  getMaxSumAllPaths(node, mutableListOf<Int>())
  
  return maxSum
}

//get all level paths 
fun getBFSLevelPath(root: Node?): List<List<Int>>{
  if (root == null) return emptyList()
  
  var result = mutableListOf<List<Int>>()
  val queue = ArrayDeque<Node>()
  queue.add(root)
  
  while(queue.isNotEmpty()){
    val levelSize = queue.size 
    val items = mutableListOf<Int>()
    for(i in 0 until levelSize){
      val node = queue.removeFirst()
      items.add(node.value)
      
      // expand it's neighbours
      if(node.left != null) queue.add(node.left as Node)
      if(node.right != null) queue.add(node.right as Node)
    }  
    
    result.add(items)
  }
  
  return result
}

// level based next items set
fun setNextNode(root: Node?): Node? {
  if(root == null) return null
  
  val queue = ArrayDeque<Node>()
  queue.add(root)
  var nextNode = queue.lastOrNull()
  while(queue.isNotEmpty()){
    val levelSize = queue.size
    
    for(i in 0 until levelSize){
      val node = queue.removeFirst()
      
      // expand the neighbours
      if(node.left != null) queue.add(node.left as Node)
      if(node.right != null) queue.add(node.right as Node)
      
      if(nextNode != null) nextNode.next = node
      nextNode = node
    }
  }
  
  return root
}

fun main() {
    var root = Node(1)
    root.left = Node(7)
    root.left?.left = Node(2)
    root.left?.right = Node(6)
    root.left?.right?.left = Node(5)
    root.left?.right?.right = Node(11)
    root.right = Node(9)
    root.right?.right = Node(9)
    root.right?.right?.left = Node(5)
    
    print("PreOrderTraversal: "); preOrderTraversal(root)
    print("\ninOrderTraversal: "); inOrderTraversal(root)
    print("\nPostOrderTraversal: "); postOrderTraversal(root)
    
    println("\n\ngetAllPaths: ${getAllPaths(root)}")
    println("\ngetSumAllPaths: ${getSumAllPaths(root)}")
    println("\ngetMaxSumAllPaths: ${getMaxSumAllPaths(root)}")
    
    val result = getBFSLevelPath(root)
    println("\nBFS levels=${result.size} LevelItems=$result")
    
    var node: Node? = setNextNode(root)
    print("\nSet Next Nodes: ")
    while(node != null){
      print("${node.value} -> ")
      node = node.next
    }
    println("null")
}
