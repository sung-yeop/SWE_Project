//startRecording*********************************************************************************

let recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
recognition.lang = 'ko-KR';
recognition.maxAlternatives = 10000;
recognition.interimResults = true; // true�� ������ ���������� �ν��ϳ� false�� �� ������ �����


let vocalText = "";

recognition.onresult = function (event) {
    vocalText = Array.from(event.results)
        .map(results => results[0].transcript).join("");
}

function startRecording() {
    recognition.start();
}

//stopRecording*********************************************************************************
function stopRecording() {
    recognition.stop();
    const textArr = vocalText.split(" ");

    console.log(vocalText);
    let type = textArr[0];
    let position = textArr[2];

    document.getElementById('information').innerHTML = type + position;


    let vocaldata = {'type': type, 'position': position};

    return vocaldata;
}