const express = require('express');
const http = require('http');
const WebSocket = require('ws');

const app = express();
const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

// 클라이언트 접속 시 처리
wss.on('connection', function (ws) {
    console.log('클라이언트가 접속했습니다.');

    // 클라이언트로부터 메시지 수신 시 처리
    ws.on('message', function (message) {
        console.log('받은 메시지: %s', message);

        // 모든 클라이언트에게 메시지 전송
        wss.clients.forEach(function (client) {
            if (client.readyState === WebSocket.OPEN) {
                client.send(message);
            }
        });
    });
});

server.listen(8080, function () {
    console.log('웹 소켓 서버가 포트 8081에서 실행 중입니다.');
});