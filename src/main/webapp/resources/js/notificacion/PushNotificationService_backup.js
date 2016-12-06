modulo_notificacion.service("PushNotificationService",
        ['$q',
            '$timeout',
            function ($q, $timeout) {

                var service = {}, listener = $q.defer(), socket = {
                    client: null,
                    stomp: null
                }, messageIds = [];

                service.RECONNECT_TIMEOUT = 30000;
                service.SOCKET_URL = "/cmpyWebSocket";
                service.CHAT_TOPIC = "/topic/greetings";
                service.PRIVATE_CHAT = "/user/queue/greetings";
                service.CHAT_BROKER = "/app/greetings";

                service.receive = function () {
                    return listener.promise;
                };

                service.send = function (message) {
                    var id = Math.floor(Math.random() * 1000000);
                    socket.stomp.send(service.CHAT_BROKER, {
                        priority: 9
                    }, JSON.stringify({
                        message: message,
                        id: id
                    }));
                    messageIds.push(id);
                };

                service.disconnect = function () {
                    socket.stomp.disconnect();
                };

                var reconnect = function () {
                    $timeout(function () {
                        initialize();
                    }, this.RECONNECT_TIMEOUT);
                };

                var getMessage = function (data) {
                    var message = JSON.parse(data), out = {};
                    out.message = message.message;
                    out.time = new Date(message.time);
                    return out;
                };

                var startListener = function () {
                    socket.stomp.subscribe(service.CHAT_TOPIC, function (data) {
                        listener.notify(getMessage(data.body));
                    });
                    socket.stomp.subscribe(service.PRIVATE_CHAT, function (data) {
                        listener.notify(getMessage(data.body));
                    });

                };

                var initialize = function () {
                    socket.client = new SockJS(service.SOCKET_URL);
                    socket.stomp = Stomp.over(socket.client);
                    socket.stomp.connect({}, startListener);
                    socket.stomp.onclose = reconnect;
                };

                initialize();
                return service;
            }]);

