package com.shadowzilot.quiz_app.commons

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface ResultLogic<T> {

    fun doIfSuccess(data: T)

    fun doIfFailure(message: Int)

    fun doIfLoading() = run { }
}

abstract class ResultData<T>(
    private val mData: T? = null,
    private val mMessage: Int = -1,
    private val mIsLoading: Boolean = false
) {

    fun map(resultSet: ResultLogic<T>) {
        if (mIsLoading) {
            resultSet.doIfLoading()
        } else if (mMessage != -1) {
            resultSet.doIfFailure(mMessage)
        } else {
            resultSet.doIfSuccess(
                mData ?: throw Exception(
                    "Result data must accept at least one parameter"
                )
            )
        }
    }
}