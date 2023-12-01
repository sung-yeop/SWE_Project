//startRecording*********************************************************************************

let recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
recognition.lang = 'ko-KR';
recognition.maxAlternatives = 20000;
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
    vocalText = vocalText.replace(",", "");
    console.log(vocalText);
    let type = textArr[0];
    let position = "";
    if (textArr.length < 4)
        position = textArr[2];
    else
        position = textArr[2] + textArr[textArr.length - 1];

    console.log(type);
    console.log(position);

    document.getElementById('information').innerHTML = vocalText;


    let vocaldata = {'type': type, 'position': position};

    vocalText = "";

    return vocaldata;
}