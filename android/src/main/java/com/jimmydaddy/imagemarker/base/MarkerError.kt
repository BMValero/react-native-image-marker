package com.jimmydaddy.imagemarker.base

class MarkerError internal constructor(private var errorCode: ErrorCode, private var errMsg: String) : Error() {

  fun getErrorCode(): String {
    return errorCode.value
  }

  @JvmName("functionOfKotlin")
  fun getErrMsg(): String {
    return errMsg
  }
}
