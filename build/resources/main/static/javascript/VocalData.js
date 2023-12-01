//startRecording*********************************************************************************

let recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
recognition.lang = 'ko-KR';
recognition.maxAlternatives = 10000;
recognition.interimResults = true; // true�� ������ ���������� �ν��ϳ� false�� �� ������ �����


let vocalText = "";

console.log('vd go');
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

    console.log(vocalText);
    let type = vocalText.match(/([가-힣]+\s[가-힣]*)/g)[0];
    let position = vocalText.match((/\d+(\s\d+)*/g))[0];
    console.log(type);
    console.log(position);
    document.getElementById('information').innerHTML = type + position;


    let vocaldata = {'type': type, 'position': position};

    return vocaldata;
}