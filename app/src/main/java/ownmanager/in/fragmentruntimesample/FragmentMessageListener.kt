package ownmanager.`in`.fragmentruntimesample

/** interface to communicate messages to activity */
interface FragmentMessageListener {
    //NOTE : We can use String instead of CharSequence, but we don't need to convert CharSequence into string
    fun onMessageRead(message: CharSequence?)
}