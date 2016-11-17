modulo_notificacion.service("PushNotificationService", function ($q, $timeout, $log, $window) {

    var service = {}, listener = $q.defer(), socket = {
        client: null,
        stomp: null
    }, messageIds = [];

    service.RECONNECT_TIMEOUT = 2000;
    service.SOCKET_URL = "/cmpyWebSocket";
    service.CHAT_TOPIC = "/topic/greetings";
    service.PRIVATE_CHAT = "/user/queue/greetings";
    service.CHAT_BROKER = "/app/greetings";
    service.notificaciones = [];

    var windowElement = angular.element($window);
    windowElement.on('beforeunload', function (event) {
        // do whatever you want in here before the page unloads.        
        listener.reject("scope destroyed");
        socket.stomp.unsubscribe(service.CHAT_TOPIC);
        socket.stomp.unsubscribe(service.PRIVATE_CHAT);
        socket.stomp.disconnect();

        var service = {}, listener = $q.defer(), socket = {
            client: null,
            stomp: null
        }, messageIds = [];
        // the following line of code will prevent reload or navigating away.
//        event.preventDefault();
    });

    windowElement.on('onBeforeunload', function (event) {
        // do whatever you want in here before the page unloads.        
        listener.reject("scope destroyed");
        socket.stomp.unsubscribe(service.CHAT_TOPIC);
        socket.stomp.unsubscribe(service.PRIVATE_CHAT);
        socket.stomp.disconnect();

        socket = {
            client: null,
            stomp: null
        };
        // the following line of code will prevent reload or navigating away.
//        event.preventDefault();
    });

    service.receive = function () {
        return listener.promise;
    };

    //use local promise
    listener.promise.then(null, null, function onProgress(data) {
        $log.info('Received data from service');
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
//      var message = JSON.parse(data), out = {};
//      out.message = message.message;
//      out.time = new Date(message.time);
////      if (_.contains(messageIds, message.id)) {
////        out.self = true;
////        messageIds = _.remove(messageIds, message.id);
////      }
        return JSON.parse(data);
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

        socket.client = new SockJS(service.SOCKET_URL);
        socket.stomp = Stomp.over(socket.client);
        socket.stomp.connect({}, startListener);
        socket.stomp.onclose = reconnect;
    };

    initialize();
    return service;
});

