package com.example.projectschool.data

// result generated from /json

data class ScheduleBase(
    val SchoolSchedule: List<SchoolSchedule>?
)

data class SchoolSchedule(
    val head2: List<Head2>?,
    val row: List<Row2>?
)

data class Head2(
    val list_total_count: Number?,
    val resulT2: RESULT2?
)

data class RESULT2(
    val CODE: String?,
    val MESSAGE: String?
)

data class Row2(
    val ATPT_OFCDC_SC_CODE: String?,
    val ATPT_OFCDC_SC_NM: String?,
    val SD_SCHUL_CODE: String?,
    val SCHUL_NM: String?,
    val AY: String?,
    val DGHT_CRSE_SC_NM: String?,
    val SCHUL_CRSE_SC_NM: String?,
    val SBTR_DD_SC_NM: String?,
    val AA_YMD: String?,
    val EVENT_NM: String?,
    val EVENT_CNTNT: Any?,
    val ONE_GRADE_EVENT_YN: String?,
    val TW_GRADE_EVENT_YN: String?,
    val THREE_GRADE_EVENT_YN: String?,
    val FR_GRADE_EVENT_YN: String?,
    val FIV_GRADE_EVENT_YN: String?,
    val SIX_GRADE_EVENT_YN: String?,
    val LOAD_DTM: String?
)

