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
    console.log(vocalText);
    let type = vocalText.match(/([가-힣]+\s[가-힣]*)/g)[0];
    const positionArr = vocalText.match((/\d+(\s\d+)*/g));
    let position

    console.log(positionArr);

    if (type == null || positionArr == null) {
        vocalText = "다시 입력";
    } else if (positionArr.length == 1) {
        position = positionArr[0];
    } else {
        position = positionArr[0] + " " + positionArr[1];
    }

    console.log(type);
    console.log(position);

    document.getElementById('information').innerHTML = vocalText;


    let vocaldata = {'type': type, 'position': position};

    vocalText = "";

    return vocaldata;
}