//startRecording*********************************************************************************
function startRecording() {
    return new Promise((resolve, reject) => {
        if ('SpeechRecognition' in window || 'webkitSpeechRecognition' in window) {
            const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
            recognition.lang = 'ko-KR';
            recognition.start();
            recognition.onresult = function (event) {
                const transcript = event.results[0][0].transcript;

                const voiceData = { speechText: transcript };

                fetch('XX', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(voiceData),
                })
                    .then((response) => response.json())
                    .then((result) => {
                        let voiceHazard = [];
                        let voiceColor = [];

                        if (result.hazardList != null) {
                            voiceHazard = result.hazardList.match(/\d+/g).map(Number);
                        }
                        if (result.colorBlobList != null) {
                            voiceColor = result.colorBlobList.match(/\d+/g).map(Number);
                        }
                        resolve([voiceHazard, voiceColor]);
                    })
                    .catch((error) => {
                        reject(error);
                    });
            };
        } else {
            reject(new Error('SpeechRecognition not supported'));
        }
    });
}
//stopRecording*********************************************************************************
function stopRecording() {
    if (recognition) {
        recognition.stop();
        recognition = null;
    }
}
