// Lắng nghe sự kiện gửi tin nhắn khi nhấn nút hoặc nhấn Enter
document.getElementById('user-input').addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        sendMessage(event);
    }
});

function sendMessage(event) {
    if (event.key === 'Enter') {
        let message = document.getElementById('user-input').value;
        let chatbox = document.getElementById('chat-box');
        
        // Hiển thị tin nhắn của người dùng
        let userMessage = document.createElement('div');
        userMessage.classList.add('chat-message', 'user-message');
        userMessage.textContent = 'You: ' + message;
        chatbox.appendChild(userMessage);
        
        document.getElementById('user-input').value = ''; // Xóa input sau khi gửi

        // Gửi tin nhắn đến server (ví dụ Rasa, ChatGPT API)
        fetch('https://5c7b-45-122-253-178.ngrok-free.app/webhooks/rest/webhook', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ sender: 'user', message: message })
        })
        .then(response => response.json())
        .then(data => {
            // Hiển thị phản hồi từ bot
            let botMessage = document.createElement('div');
            botMessage.classList.add('chat-message', 'bot-message');
            botMessage.textContent = 'Bot: ' + data[0].text;
            chatbox.appendChild(botMessage);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }
}
