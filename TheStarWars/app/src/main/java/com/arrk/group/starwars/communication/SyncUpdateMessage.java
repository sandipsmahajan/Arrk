package com.arrk.group.starwars.communication;

/**
 * @author SANDY
 */
public final class SyncUpdateMessage {

    // message codes
    public static final int SYNC_SUCCESSFUL = 1;
    public static final int SYNC_ERROR = 2;

    private final int messageCode;
    private final Object what;

    public SyncUpdateMessage(int messageCode, Object what) {
        this.messageCode = messageCode;
        this.what = what;
    }

    public int getMessageCode() {
        return this.messageCode;
    }

    public Object getWhat() {
        return this.what;
    }
}
