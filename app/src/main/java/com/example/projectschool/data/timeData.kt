package com.example.projectschool.data


data class TimeBase(
    val hisTimetable: List<HisTimetable>?
)

data class HisTimetable(
    val head: List<TimeHead>?,
    val row: List<TimeRow>?
)

data class TimeHead(
    val list_total_count: Int?,
    val timeRESULT: TimeRESULT?
)

data class TimeRESULT(
    val CODE: String?,
    val MESSAGE: String?
)

data class TimeRow(
    val ATPT_OFCDC_SC_CODE: String?,
    val ATPT_OFCDC_SC_NM: String?,
    val SD_SCHUL_CODE: String?,
    val SCHUL_NM: String?,
    val AY: String?,
    val SEM: String?,
    val ALL_TI_YMD: String?,
    val DGHT_CRSE_SC_NM: String?,
    val ORD_SC_NM: String?,
    val DDDEP_NM: String?,
    val GRADE: String?,
    val CLRM_NM: String?,
    val CLASS_NM: String?,
    val PERIO: String?,
    val ITRT_CNTNT: String?,
    val LOAD_DTM: String?
)
