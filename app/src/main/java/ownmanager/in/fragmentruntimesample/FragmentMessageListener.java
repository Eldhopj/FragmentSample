package ownmanager.in.fragmentruntimesample;

/** interface to communicate messages to activity*/
public interface FragmentMessageListener {
    //NOTE : We can use String instead of CharSequence, but we don't need to convert CharSequence into string
    void onMessageRead(CharSequence message);
}
