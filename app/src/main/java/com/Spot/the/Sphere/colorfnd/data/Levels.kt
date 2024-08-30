package com.Color.Sphere.Challenge.gamecolor.data


enum class Levels(val N: Int,val M: Int, val T: Int, val time: Int, val outTime: Int, val plusTime: Int) {
    first(2,2, 1, 10, 0, 2),
    second(2,3 ,2, 10, 1, 2),
    third(3,3, 3, 10, 2, 2),
    fourth(3,4, 4, 10, 3, 2),
    fifth(4,4, 5, 10, 4, 2),
    sixth(4, 5,6, 10, 5, 2),
    seventh(5,5, 7, 10, 6, 2),
    eighth(5,6, 8, 20, 7, 5),
    ninth(5,6, 9, 20, 8, 5),
    tenth(5,7, 10, 30, 9, 5),
    eleventh(5,7, 11, 30, 10, 5),
    twelfth(5,8, 12, 40, 11, 5),
    thirteenth(5,8, 13, 40, 12, 5),
    fourteenth(5,8, 14, 40, 13, 5),
    fifteenth(5,8, 15, 40, 14, 5),

}
