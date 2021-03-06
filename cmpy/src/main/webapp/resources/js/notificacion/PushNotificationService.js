modulo_notificacion.service("PushNotificationService",
        ['$q',
            '$timeout',
            '$log',
            '$window',
            function ($q, $timeout, $log, $window) {

                var service = {}, listener = $q.defer(), publicListener = $q.defer(), socket = {
                    client: null,
                    stomp: null
                }, messageIds = [];

                service.RECONNECT_TIMEOUT = 2000;
                service.SOCKET_URL = "/cmpyWebSocket";
                service.CHAT_TOPIC = "/topic/greetings";
                service.PRIVATE_CHAT = "/user/queue/greetings";
                service.CHAT_BROKER = "/app/greetings";

                var windowElement = angular.element($window);
                windowElement.on('beforeunload', function (event) {
                    // do whatever you want in here before the page unloads.        
                    listener.reject("scope destroyed");
                    publicListener.reject("scope destroyed");
                    socket.stomp.unsubscribe(service.CHAT_TOPIC);
                    socket.stomp.unsubscribe(service.PRIVATE_CHAT);
                    socket.stomp.disconnect();

                    var service = {}, listener = $q.defer(), publicListener = $q.defer(),  socket = {
                        client: null,
                        stomp: null
                    }, messageIds = [];
                });

                windowElement.on('onBeforeunload', function (event) {
                    // do whatever you want in here before the page unloads.        
                    listener.reject("scope destroyed");
                    publicListener.reject("scope destroyed");
                    socket.stomp.unsubscribe(service.CHAT_TOPIC);
                    socket.stomp.unsubscribe(service.PRIVATE_CHAT);
                    socket.stomp.disconnect();

                    var service = {}, listener = $q.defer(), publicListener = $q.defer(), socket = {
                        client: null,
                        stomp: null
                    }, messageIds = [];
                });

                service.receive = function () {
                    return listener.promise;
                };
                
                service.receivePublicMessage = function () {
                    return publicListener.promise;
                };

                //use local promise
                listener.promise.then(null, null, function onProgress(data) {
                    $log.info('Received data from service');
                });
                
                publicListener.promise.then(null, null, function onProgress(data) {
                    $log.info('Received public data from service');
                });

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
                    listener.reject("scope destroyed");
                    publicListener.reject("scope destroyed");
                    socket.stomp.unsubscribe(service.CHAT_TOPIC);
                    socket.stomp.unsubscribe(service.PRIVATE_CHAT);
                    socket.stomp.disconnect();
                };

                service.reconnect = function () {
                    initialize();
                };

                var reconnect = function () {
                    $timeout(function () {
                        initialize();
                    }, this.RECONNECT_TIMEOUT);
                };

                var getMessage = function (data) {
                    return JSON.parse(data);
                };

                var startListener = function () {
                    socket.stomp.subscribe(service.CHAT_TOPIC, function (data) {
                        publicListener.notify(getMessage(data.body));
                    });
                    socket.stomp.subscribe(service.PRIVATE_CHAT, function (data) {
                        listener.notify(getMessage(data.body));
                    });

                };

                var initialize = function () {

                    if (socket.stomp !== null) {
                        socket.stomp.unsubscribe(service.CHAT_TOPIC);
                        socket.stomp.unsubscribe(service.PRIVATE_CHAT);
                        socket.stomp.disconnect();
                    }

                    socket = {
                        client: null,
                        stomp: null
                    };

                    listener = $q.defer();
                    publicListener = $q.defer();

                    socket.client = new SockJS(service.SOCKET_URL);
                    socket.stomp = Stomp.over(socket.client);
                    socket.stomp.connect({}, startListener);
                    socket.stomp.onclose = reconnect;
                };

                initialize();
                return service;
            }]);

