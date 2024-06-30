const chatInput = 
    document.querySelector('.chat-input textarea');
const sendChatBtn = 
    document.querySelector('.chat-input button');
const chatbox = document.querySelector(".chatbox");
 
let userMessage;
const API_KEY = 
    "sk-proj-XzQVk8AUsuC9aILYIyvRT3BlbkFJcDVV71rtEDiJgGOPrH2w";

 
const createChatLi = (message, className) => {
    const chatLi = document.createElement("li");
    chatLi.classList.add("chat", className);
    let chatContent = 
        className === "chat-outgoing" ? `<p>${message}</p>` : `<p>${message}</p>`;
    chatLi.innerHTML = chatContent;
    return chatLi;
}
 
const generateResponse = (incomingChatLi) => {
    const API_URL = "https://api.openai.com/v1/chat/completions";
    const messageElement = incomingChatLi
    .querySelector("p");
    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${API_KEY}`
        },
        body: JSON.stringify({
            "model": "gpt-4-turbo",
            "messages": [
                {
                    role: "user",
                    content: userMessage
                },
                {
                    role: "assistant",
                    content: "You MUST speak old English in EVERY response! You are a wise Sage, speak like one! You also Know magic.",
                    assistant_id: "asst_kIhE78744OJpRxXspvCVvMu8"
                }
            ]
        })
    };
 
    fetch(API_URL, requestOptions)
        .then(res => {
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        })
        .then(data => {
            messageElement
            .textContent = data.choices[0].message.content;
        })
        .catch((error) => {
            messageElement
            .classList.add("error");
            messageElement
            .textContent = "Oops! Something went wrong. Please try again!";
        })
        .finally(() => chatbox.scrollTo(0, chatbox.scrollHeight));
};
 
 
const handleChat = () => {
    userMessage = chatInput.value.trim();
    if (!userMessage) {
        return;
    }
    chatbox
    .appendChild(createChatLi(userMessage, "chat-outgoing"));
    chatbox
    .scrollTo(0, chatbox.scrollHeight);
 
    setTimeout(() => {
        const incomingChatLi = createChatLi("Hhhmmmm...", "chat-incoming")
        chatbox.appendChild(incomingChatLi);
        chatbox.scrollTo(0, chatbox.scrollHeight);
        generateResponse(incomingChatLi);
    }, 1);
}
 
sendChatBtn.addEventListener("click", handleChat);