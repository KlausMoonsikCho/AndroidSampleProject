package com.klaus.calcurator

/**
 * Created by chomoonsik on 2017. 10. 23..
 *
 * 1부터 50까지 짝수인 수만 더하는 클래스
 */
class EvenNumSum {
    private val MIN_NUM:Int = 1
    private val MAX_NUM:Int = 50

    fun getEvenNumSum1():Int {
        var sum:Int = 0
        var addNum:Int = 0;

        for(addnum in MIN_NUM..MAX_NUM) {
            if(addnum%2==0) {
                sum += addnum
            }
        }

        return sum
    }

    fun getEvenNumSum2():Int {
        var sum:Int = 0
        var addNum:Int = 0

        for(addNum in (MIN_NUM+1)..MAX_NUM step 2) sum += addNum

        return sum
    }
}