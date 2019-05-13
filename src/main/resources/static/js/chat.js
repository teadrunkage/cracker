'use strict';


var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('#connecting');

var stompClient = null;
var username = null;


function connect() {
    username = document.querySelector('#username').innerText.trim();
    var socket= new SockJS('http://localhost:5030/cracker');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

// Connect to WebSocket Server.
connect();

function onConnected() {
    stompClient.subscribe('/topic/publicChatRoom', onMessageReceived);
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )
    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        setTimeout(function() {stompClient.send("/app/chat.answerByBot", {}, JSON.stringify(chatMessage)) }, 500);
        messageInput.value = '';
    }
    
    event.preventDefault();
    
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        var usernameElement = document.createElement('strong');
        usernameElement.classList.add('nickname');
        var usernameText = document.createTextNode(message.sender);
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }
    
    
    if (message.content.startsWith("http") ) {
    	 var textElement = document.createElement('a');
    	 var messageText = document.createTextNode("Вот Ваш запрос");
    	 textElement.appendChild(messageText);
    	 textElement.title = "Вот Ваш запрос";
    	 textElement.href = message.content;
    	 textElement.target = "_blank";
    	 
    } else {
	    var textElement = document.createElement('span');
	    var messageText = document.createTextNode(message.content);
	    textElement.appendChild(messageText);
    }

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


messageForm.addEventListener('submit', sendMessage, true);