代码及示例源自：

    https://mengkang.net/777.html

    https://github.com/zhoumengkang/netty-websocket.git

前端js代码示例：

    var socket;
    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }
    if (window.WebSocket) {
        socket = new WebSocket("ws://localhost:8083/websocket/?request=e2lkOjE7cmlkOjI2O3Rva2VuOiI0MzYwNjgxMWM3MzA1Y2NjNmFiYjJiZTExNjU3OWJmZCJ9");
        socket.onmessage = function(event) {
        console.log(event.data);
    };
    socket.onopen = function(event) {
        console.log("websocket 打开了");
    };
    socket.onclose = function(event) {
        console.log("websocket 关闭了");
    };
    }

    function send(message) {
        if (!window.WebSocket) { return; }
        if (socket.readyState == WebSocket.OPEN) {
            socket.send(message);
        } else {
            alert("The socket is not open.");
        }
    }