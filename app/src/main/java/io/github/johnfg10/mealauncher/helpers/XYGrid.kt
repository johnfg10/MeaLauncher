package io.github.johnfg10.mealauncher.helpers

class XYGrid {
    // if idref is -1 it is treated as effectivly not present
    protected val grid = mutableMapOf</*contains the x pos*/Int, /* contains the y pos and a id ref */MutableMap<Int, Int>>()

    fun containsXPos(xPos: Int) : Boolean{
        return grid.containsKey(xPos)
    }

    fun contaisnYPos(xPos : Int, yPos: Int): Boolean {
        if (containsXPos(xPos)){
            return grid[xPos]!!.contains(yPos)
        }
        return false
    }

    fun isXYPosFilled(xPos: Int, yPos: Int): Boolean {
        if (contaisnYPos(xPos, yPos)){
            return grid[xPos]!![yPos]!! >= 0
        }
        return false
    }

    protected fun addXPos(xPos: Int){
        if (!grid.containsKey(xPos)) grid[xPos] = mutableMapOf()
    }

    protected fun addXYPos(xPos: Int, yPos: Int){
        addXPos(xPos)
        if(!grid[xPos]!!.contains(yPos)) grid[xPos]!!.plus(mutableMapOf(Pair(yPos, -1)))
    }

    fun add(xPos: Int, yPos: Int, id: Int){
        addXYPos(xPos, yPos)
        grid[xPos]!![yPos] = id
    }

    fun clearXPos(xPos: Int){
        if (containsXPos(xPos)) grid.remove(xPos)
    }

    fun clearYPos(xPos: Int, yPos: Int){
        if (contaisnYPos(xPos, yPos)){
            grid[xPos]!!.remove(yPos)
        }
    }

    fun clearId(xPos: Int, yPos: Int){
        if (contaisnYPos(xPos, yPos)){
            grid[xPos]!![yPos] = -1
        }
    }
}