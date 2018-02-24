package cn.mars.websocket.entity;

/**
 * Description：
 * Created by Mars on 2018/2/23.
 */
public class Client {

    private long id;
    private int roomId;

    public Client() {
        id = 0l;
        roomId = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
