package com.example.projectschool.data


data class FoodBase(
    val mealServiceDietInfo: List<MealServiceDietInfo>?
)

data class MealServiceDietInfo(
    val row: List<Row>?,
    val head : List<Head>?
)

data class Head(
    val list_total_count: Number?,
    val result: RESULT?
)

data class RESULT(
    val CODE: String?,
    val MESSAGE: String?
)

data class Row(
    val ATPT_OFCDC_SC_CODE: String?,
    val ATPT_OFCDC_SC_NM: String?,
    val SD_SCHUL_CODE: String?,
    val SCHUL_NM: String?,
    val MMEAL_SC_CODE: String?,
    val MMEAL_SC_NM: String?,
    val MLSV_YMD: String?,
    val MLSV_FGR: String?,
    val DDISH_NM: String?,
    val ORPLC_INFO: String?,
    val CAL_INFO: String?,
    val NTR_INFO: String?,
    val MLSV_FROM_YMD: String?,
    val MLSV_TO_YMD: String?
)

