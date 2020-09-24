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
    val announceTime : Long?,
    val numEf: Int?,
    val regId : String?,
    val rnSt: Int?,
    val rnYn: Int?,
    val ta: Int?,
    val wd1: String?,
    val wd2 : String?,
    val wdTnd: String?,
    val wf: String?,
    val wfCd: String?,
    val wsIt: String?
)