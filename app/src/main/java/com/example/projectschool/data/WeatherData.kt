package com.example.projectschool.data


data class Base(
    val response: Response?
)

data class Response(
    val header: Header?,
    val body: Body?
)

data class Header(
    val resultCode: String?,
    val resultMsg: String?
)

data class Body(
    val dataType: String?,
    val items: Item,
    val pageNo: Int?,
    val numOfRows: Int?,
    val totalCount: Int?
)

data class Item(
    val item : List<ItemList>
)

data class ItemList (
    val announceTime : Long?, //발표시간
    val numEf: Int?,          //발표번호
    val regId : String?,      //예보구역코드
    val rnSt: Int?,           //강수확률
    val rnYn: Int?,           //강수형태
    val ta: Int?,             //온도
    val wd1: String?,         //풍향연결(?)
    val wd2 : String?,        //풍속 강도
    val wdTnd: String?,       //풍향연결코드
    val wf: String?,          //날씨
    val wfCd: String?,        //날씨코드
    val wsIt: String?         //풍속강도코드
)