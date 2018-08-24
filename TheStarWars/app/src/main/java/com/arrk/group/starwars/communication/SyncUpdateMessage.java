package com.arrk.group.starwars.communication;

/**
 * @author SANDY
 */
public final class SyncUpdateMessage {

    // message codes
    public static final int NO_CONNECTION = 1;
    public static final int SYNC_SUCCESSFUL = 2;
    public static final int SYNC_STARTED = 3;
    public static final int SYNC_CUSTOM_ERROR = 4;
    public static final int SYNC_FILE_UPLOAD_ERROR = 5;

    private int messageCode;
    private Object what;

    public SyncUpdateMessage(int messageCode, Object what)
    {
        this.messageCode = messageCode;
        this.what = what;
    }

    public int getMessageCode()
    {
        return this.messageCode;
    }

    public Object getWhat()
    {
        return this.what;
    }
}
