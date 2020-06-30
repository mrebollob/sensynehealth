package com.dhis.store.data.network.mapper

import android.util.Log
import com.dhis.store.data.network.model.ApiHospitalModel
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

private const val ID_IDX = 0
private const val CODE_IDX = 1
private const val TYPE_IDX = 2
private const val SUB_TYPE_IDX = 3
private const val SECTOR_IDX = 4
private const val STATUS_IDX = 5
private const val PIM_MANAGED_IDX = 6
private const val NAME_IDX = 7
private const val ADDRESS_1_IDX = 8
private const val ADDRESS_2_IDX = 9
private const val ADDRESS_3_IDX = 10
private const val CITY_IDX = 11
private const val COUNTY_IDX = 12
private const val POSTCODE_IDX = 13
private const val LATITUDE_IDX = 14
private const val LONGITUDE_IDX = 15
private const val PARENT_ODS_CODE_IDX = 16
private const val PARENT_NAME_IDX = 17
private const val PHONE_IDX = 18
private const val EMAIL_IDX = 19
private const val WEB_IDX = 20
private const val FAX_IDX = 21

class HospitalsFileMapper {

    fun map(body: ResponseBody): List<ApiHospitalModel> {
        var fileReader: BufferedReader? = null

        try {
            val apiHospitals = ArrayList<ApiHospitalModel>()
            var line: String?

            fileReader = BufferedReader(InputStreamReader(body.byteStream()))

            // Read CSV header
            fileReader.readLine()

            // Read the file line by line starting from the second line
            line = fileReader.readLine()
            while (line != null) {
                val tokens = line
                    .replace("�", "\t")
                    .split("\t")
                if (tokens.isNotEmpty()) {
                    val apiHospital = ApiHospitalModel(
                        organisationId = Integer.parseInt(tokens[ID_IDX]),
                        organisationCode = tokens[CODE_IDX],
                        organisationType = tokens[TYPE_IDX],
                        subType = tokens[SUB_TYPE_IDX],
                        sector = tokens[SECTOR_IDX],
                        organisationStatus = tokens[STATUS_IDX],
                        isPimsManaged = tokens[PIM_MANAGED_IDX].toLowerCase().toBoolean(),
                        organisationName = tokens[NAME_IDX],
                        address1 = tokens[ADDRESS_1_IDX],
                        address2 = tokens[ADDRESS_2_IDX],
                        address3 = tokens[ADDRESS_3_IDX],
                        city = tokens[CITY_IDX],
                        county = tokens[COUNTY_IDX],
                        postcode = tokens[POSTCODE_IDX],
                        latitude = tokens[LATITUDE_IDX],
                        longitude = tokens[LONGITUDE_IDX],
                        parentODSCode = tokens[PARENT_ODS_CODE_IDX],
                        parentName = tokens[PARENT_NAME_IDX],
                        phone = tokens[PHONE_IDX],
                        email = tokens[EMAIL_IDX],
                        website = tokens[WEB_IDX],
                        fax = tokens[FAX_IDX]
                    )
                    apiHospitals.add(apiHospital)
                }
                line = fileReader.readLine()
            }

            return apiHospitals
        } catch (e: Exception) {
            Log.e("HospitalsFileMapper", "Reading CSV Error!", e)
            return emptyList()
        } finally {
            try {
                fileReader!!.close()
            } catch (e: IOException) {
                Log.e("HospitalsFileMapper", "Closing fileReader Error!", e)
                return emptyList()
            }
        }
    }

    companion object {
        @Volatile
        private var instance: HospitalsFileMapper? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: buildInstance().also {
                    instance = it
                }
            }

        private fun buildInstance(): HospitalsFileMapper {
            return HospitalsFileMapper()
        }
    }
}